package com.example.ibooking2.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.ibooking2.R;
import com.example.ibooking2.database.DBOpenHelper;
import com.example.ibooking2.object.Order;
import com.example.ibooking2.object.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class TimetableFragment extends Fragment implements MyCustomDialog.OnInputSelected{

    private Room room;
    private String userName;
    private String inputWeekday;
    private String givenDay;



    //yy-mm-rr
    public static int getDistanceTime(Date startTime, Date endTime) {
        long time1 = startTime.getTime();
        long time2 = endTime.getTime();

        long diff;
        diff = time2 - time1;

        int days = (int) (diff/(60*60*24*1000));
        return days;
    }

    //exchange Date to Weekday
    public static String exchangeToWeekdays(int year, int month, int day){
        int y = year - 1;
        int c = 20;
        int m = month;
        int d = day + 12;
        int w = (y + (y / 4) + (c / 4) - 2 * c + (26 * (m + 1) / 10) + d - 1) % 7;
//        String inputWeekday = "";
        String InputWeekday="";
        switch (w) {
            case 0:
                InputWeekday = "Wednesday";
                break;
            case 1:
                InputWeekday = "Thursday";
                break;
            case 2:
                InputWeekday = "Friday";
                break;
            case 3:
                InputWeekday = "Saturday";
                break;
            case 4:
                InputWeekday = "Sunday";
                break;
            case 5:
                InputWeekday = "Monday";
                break;
            case 6:
                InputWeekday = "Tuesday";
                break;
            default:
                break;
        }
        return InputWeekday;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        //接受所传递的信息
        Bundle bundle=this.getArguments();

        if(bundle!=null){
            room=(Room)bundle.getSerializable("room");
            System.out.println("room name!!!!!!"+room.getName());
        }
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button forsearch = getActivity().findViewById(R.id.buttonSearch);
        final DatePicker datePicker_bir=getActivity().findViewById(R.id.datePickerBir);
        Button bookBtn = getActivity().findViewById(R.id.bookBtn); //预定按钮
        TextView ttbl_tv_hint=getActivity().findViewById(R.id.ttbl_tv_hint);

        ListView listView = getActivity().findViewById(R.id.timetable_Lv);
        ArrayList<String> validTimeBlocks=new ArrayList<>();
        ArrayAdapter<String> schedule=new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, validTimeBlocks);
        listView.setAdapter(schedule);
        bookBtn.setAlpha(0);
        bookBtn.setAlpha(0);
        ttbl_tv_hint.setAlpha(0);
        datePicker_bir.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                validTimeBlocks.clear();
                schedule.notifyDataSetChanged();
                bookBtn.setAlpha(0);
                ttbl_tv_hint.setAlpha(0);
            }
        });

        //search clicked day
        forsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker_bir.getYear();
                int month = datePicker_bir.getMonth()+1;  //index+1
                int day = datePicker_bir.getDayOfMonth();

                try {
                    if (checkValidSearchDate(year,month,day)) {
                        inputWeekday=exchangeToWeekdays(year,month,day);
                        for (String timeBlcok:DBOpenHelper.getTimeByRoomAndWeekday(room.getName(),inputWeekday)){
                            validTimeBlocks.add(timeBlcok);
                        }
                        schedule.notifyDataSetChanged();

                        ttbl_tv_hint.setAlpha(1);
                        bookBtn.setAlpha(1);
                        bookBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (DBOpenHelper.getCapacityByRoomAndWeekday(room.getName(), inputWeekday) < room.getFull_capacity()){
                                    // 获取当前登录人用户名
                                    SharedPreferences sp = getContext().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
                                    userName = sp.getString("loginName","");

                                    givenDay = year + "-" + month  + "-" + day;
                                    openDialog();

                                }else {
                                    Toast.makeText(getActivity(), "Full, can't book!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }

                            }
                        });
                    }
                    else {
                        Toast.makeText(getActivity(), "No Valid Schedule for that Date", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Boolean checkValidSearchDate(int year, int month, int day) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date searchedDate=java.sql.Date.valueOf(sdf.format(sdf.parse(year+"-"+month+"-"+day)));
        Date currentdate= java.sql.Date.valueOf(sdf.format(new Date()));
        int distance=getDistanceTime(currentdate,searchedDate);
        if (distance<0 || distance>=7)    return false;
        else return true;
    }

    // 预定 method
    public void bookRoom(String roomName, String userName, String weekday, String givenDay) throws ParseException {
        ArrayList<Order> orderList = DBOpenHelper.getOrderByUsernameAndDate(userName, givenDay);
        if (checkBookedRoom(orderList, roomName)){
            Toast.makeText(getActivity(), "Already have booked order!", Toast.LENGTH_SHORT).show();
        }else {
            DBOpenHelper.createAnOrder(userName, roomName, givenDay);
            int currentCapacity = DBOpenHelper.getCapacityByRoomAndWeekday(roomName, weekday);
            DBOpenHelper.updateCapacity(roomName, weekday, currentCapacity+1);
            Toast.makeText(getActivity(), "Book Successfully!", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("id", 1);
        startActivity(intent);
    }

    private Boolean checkBookedRoom(ArrayList<Order> orders, String roomName){
        Boolean flag = false;
        for(Order order: orders){
            if (order.getRoomName().equals(roomName)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public void sendInput(Boolean selectedOK) throws ParseException {
//        Log.d(TAG, "sendInput: found incoming input: " + input);

        bookRoom(room.getName(), userName, inputWeekday, givenDay);


    }

    public void openDialog(){
        Log.d(TAG,"onClick: opening dialog");

        MyCustomDialog dialog=new MyCustomDialog();

        dialog.setTargetFragment(TimetableFragment.this,1);
        Bundle bundle=new Bundle();

        bundle.putSerializable("room",room);

        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(),"MyCustomDialog");
    }


}