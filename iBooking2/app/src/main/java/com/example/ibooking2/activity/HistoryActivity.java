package com.example.ibooking2.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ibooking2.R;
import com.example.ibooking2.database.DBOpenHelper;
import com.example.ibooking2.object.Order;
import com.example.ibooking2.object.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity{

    private Button btn_history_back;
    private Intent intent=null;
    private ListView history_lv;
    private ArrayList<Map<String,Object>> orders_map = new ArrayList<>();
    private ArrayList<Order> orders_list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btn_history_back = findViewById(R.id.btn_history_back);
        btn_history_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryActivity.this.finish();
            }
        });

        history_lv=findViewById(R.id.history_lv);
        getOrdersFromUserName();
        setupList(history_lv);
    }

    public SimpleAdapter setupList(View view){ //最初始展示的
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                HistoryActivity.this,/*传入一个上下文作为参数*/
                orders_map, /*传入相对应的数据源，这个数据源不仅仅是数据而且还是和界面相耦合的混合体。*/
                R.layout.history_single_block, /*设置具体某个items的布局，需要是新的布局，而不是ListView控件的布局*/
                new String[]{"image", "name","location","bookedT","createdT"}, /*传入上面定义的Map中key的名称,会自动根据传入的key找到对应的值*/
                new int[]{R.id.room, R.id.room_name,R.id.location,R.id.booked_time,R.id.created_time}){
            /*传入items布局文件中所对应的控件，这里直接上id即可*/

            //get corresponding room info and record its info
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
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
                        intent=new Intent(HistoryActivity.this, RoomItemActivity.class);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
                return view;
            }
        };

        //3、为listView加入适配器
        history_lv.setAdapter(simpleAdapter);

        return simpleAdapter;
    }

    public void getOrdersFromUserName(){
        //get current user
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
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

            Map<String, Object> anOrder = new HashMap<>();
            anOrder.put("image", image);
            anOrder.put("name", roomName);
            anOrder.put("location",location);
            anOrder.put("bookedT",ordered_date);
            anOrder.put("createdT",create_date);
            orders_map.add(anOrder);
        }
    }
}