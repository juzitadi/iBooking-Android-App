package com.example.ibooking2.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ibooking2.R;
import com.example.ibooking2.database.DBOpenHelper;
import com.example.ibooking2.object.Room;

import java.util.Calendar;

public class DetailsFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);
        //接受所传递的信息
        Bundle bundle=this.getArguments();
        Room room;
        if(bundle!=null){
            room=(Room)bundle.getSerializable("room");
            System.out.println("room name!!!!!!"+room.getName());

            SetText(view,room);
        }

        return view;

    }

    public void SetText(View view, Room room){
        TextView location= (TextView)view.findViewById(R.id.location1);
        location.setText(room.getLocation());
        TextView score = (TextView)view.findViewById(R.id.usibilityScore1);
        score.setText(String.valueOf(room.getScore()));
        TextView capacity = (TextView)view.findViewById(R.id.capacity1);

        // 获取当天日期
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH)+1;
        int day = calendar.get(calendar.DAY_OF_MONTH);
        String currentDay = TimetableFragment.exchangeToWeekdays(year, month, day);

        capacity.setText(String.valueOf(DBOpenHelper.getCapacityByRoomAndWeekday(room.getName(), currentDay)));
        TextView description = (TextView)view.findViewById(R.id.description1);
        description.setText(room.getDescription());
    }




}
