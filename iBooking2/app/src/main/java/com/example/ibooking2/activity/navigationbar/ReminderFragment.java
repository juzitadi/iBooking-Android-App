package com.example.ibooking2.activity.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ibooking2.R;
import com.example.ibooking2.activity.RoomItemActivity;
import com.example.ibooking2.activity.TimetableFragment;
import com.example.ibooking2.database.DBOpenHelper;
import com.example.ibooking2.object.Order;
import com.example.ibooking2.object.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReminderFragment extends Fragment {

    private Intent intent=null;
    private ListView reminder_lv;
    private TextView reminder_tv_hint;
    private ArrayList<Map<String,Object>> orders_map = new ArrayList<>();
    private ArrayList<Order> orders_list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reminder,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        reminder_tv_hint=getActivity().findViewById(R.id.reminder_tv_hint);
        reminder_tv_hint.setAlpha(0);

        reminder_lv=getActivity().findViewById(R.id.reminder_lv);
        try {
            getOrdersFromUserName();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public SimpleAdapter setupList(View view){ //最初始展示的
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getActivity(),/*传入一个上下文作为参数*/
                orders_map, /*传入相对应的数据源，这个数据源不仅仅是数据而且还是和界面相耦合的混合体。*/
                R.layout.reminder_single_block, /*设置具体某个items的布局，需要是新的布局，而不是ListView控件的布局*/
                new String[]{"image", "name","location","bookedT","createdT"}, /*传入上面定义的Map中key的名称,会自动根据传入的key找到对应的值*/
                new int[]{R.id.room, R.id.room_name,R.id.location,R.id.booked_time,R.id.created_time}){
            /*传入items布局文件中所对应的控件，这里直接上id即可*/

            //get corresponding room info and record its info
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                Button mBtnDeleteRecord=(Button) view.findViewById(R.id.btn_cancel);
                Button mBtnDetails=(Button) view.findViewById(R.id.btn_details);

                //jump to the clicked room
                mBtnDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name =orders_map.get(position).get("name").toString();
                        System.out.println("name is "+name);
                        Room detail_room= DBOpenHelper.getRoomByName(name);

                        Bundle bundle=new Bundle();
                        bundle.putSerializable("room",detail_room);
                        intent=new Intent(getActivity(), RoomItemActivity.class);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });

                //delete record of clicked room
                mBtnDeleteRecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> clicked_order =orders_map.get(position);
                        //delete step1
                        int order_index=orders_map.indexOf(clicked_order);
                        DBOpenHelper.deleteOrderById(orders_list.get(order_index).getOrder_id());
                        String roomName = orders_list.get(order_index).getRoomName();
                        String rawDay = orders_list.get(order_index).getOrdered_date();
                        String inputDay = TimetableFragment.exchangeToWeekdays(Integer.parseInt(rawDay.split("-")[0]),
                                Integer.parseInt(rawDay.split("-")[1]),
                                Integer.parseInt(rawDay.split("-")[2]));
                        DBOpenHelper.updateCapacity(roomName, inputDay, DBOpenHelper.getCapacityByRoomAndWeekday(roomName, inputDay)-1);

                        //delete step2
                        orders_map.remove(clicked_order);
                        notifyDataSetChanged();
                    }
                });
                return view;
            }
        };

        //3、为listView加入适配器
        reminder_lv.setAdapter(simpleAdapter);

        return simpleAdapter;
    }

    public void getOrdersFromUserName() throws ParseException {
        //empty containers
        orders_map.clear();
        orders_list.clear();

        //get current user
        SharedPreferences sp = getActivity().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginName","");

        orders_list = DBOpenHelper.getOrderListByUserName(userName);

        //form map from list
        for(Order order:orders_list){
            String roomName=order.getRoomName();
            Room room=DBOpenHelper.getRoomByName(roomName);
            String location=room.getLocation();
            String ordered_date=order.getOrdered_date();
            String create_date=order.getCreate_date();
            int image=room.getImage_url();

            //get valid orders
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date searchedDate=java.sql.Date.valueOf(sdf.format(sdf.parse(ordered_date)));
            Date currentdate= java.sql.Date.valueOf(sdf.format(new Date()));
            if (TimetableFragment.getDistanceTime(currentdate,searchedDate)>=0){
                Map<String, Object> anOrder = new HashMap<>();
                anOrder.put("image", image);
                anOrder.put("name", roomName);
                anOrder.put("location",location);
                anOrder.put("bookedT",ordered_date);
                anOrder.put("createdT",create_date);
                orders_map.add(anOrder);
            }
        }

        if (orders_map.isEmpty()){
            reminder_tv_hint.setAlpha(1);
        }
        else {
            //put into SimpleAdapter
            setupList(reminder_lv);
        }

    }

}