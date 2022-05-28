package com.example.ibooking2.activity.navigationbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ibooking2.R;
import com.example.ibooking2.activity.MySpinner;
import com.example.ibooking2.activity.RoomItemActivity;
import com.example.ibooking2.activity.TimetableFragment;
import com.example.ibooking2.database.DBOpenHelper;
import com.example.ibooking2.object.Room;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private Context context;
    private PullToRefreshListView lv;
    public static ArrayList<Room> roomList = new ArrayList<Room>();  //所有room
    public static ArrayList<Room> show_list=new ArrayList<>();
    public int roomList_size;
    private ArrayList<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>(); //展示的room
    private SearchView searchView;
    private boolean isInitial=true;
    private SimpleAdapter simpleAdapter;  //展示的容器

    private int current_index=5;

    // 获取当天日期
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(calendar.YEAR);
    int month = calendar.get(calendar.MONTH)+1;
    int day = calendar.get(calendar.DAY_OF_MONTH);
    String currentDay = TimetableFragment.exchangeToWeekdays(year, month, day);

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context =getContext();
        PullToRefreshListView lv = (PullToRefreshListView) view.findViewById(R.id.lv); //获取我们关心的控件

        SearchListener(view);
        setupData();
        simpleAdapter=setupList(view);
        setDropbox(view);

        //1、initial refresh layout
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout startLabels = lv.getLoadingLayoutProxy(true, false);
        startLabels.setReleaseLabel("Update and Refresh");
        startLabels.setRefreshingLabel("Updating...");
        startLabels.setPullLabel("Update Successfully!");
        ILoadingLayout endLabels = lv.getLoadingLayoutProxy(false, true);
        endLabels.setReleaseLabel("Load More");
        endLabels.setRefreshingLabel("Loading...");
        endLabels.setPullLabel("Load Successfully!");


        //列表的【下拉刷新】事件监听
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                //AsyncTask 实际上是一个帮助类，可以让我们很简单的从子线程切换到主线程，去更新UI界面
                new AsyncTask<Void, Void, Void>() {
                    //doInBackground这个方法所有代码都会在一个子线程中执行，所有的耗时任务都在这处理
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(3000);//刷新时间3秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    //主线程中修改UI数据,更新UI
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        ArrayList<Map<String,Object>> new_list_map = new ArrayList<Map<String,Object>>();
                        LoadData();
                        FormMap(show_list, new_list_map);
                        reFresh(simpleAdapter,new_list_map);

                        //提示“刷新成功”的pop up
                        Toast.makeText(getActivity(),"Load Successfully!", Toast.LENGTH_SHORT).show();

                        //通知lv数据已经加载完
                        lv.onRefreshComplete();
                    }
                }.execute();
            }
        });

        return view;
    }

    public void setupData(){
        roomList = DBOpenHelper.getAvailableRoom(currentDay);
        roomList_size=roomList.size();

        for(int i=0;i<roomList_size - 1;i++){
            Room temp=roomList.get(i);
            show_list.add(temp);
        }
        FormMap(show_list,list_map);
    }

    public void LoadData(){
        if(current_index+5<roomList_size) {
            for (int i = current_index; i < current_index + 5; i++) {
                Room temp = roomList.get(i);
                show_list.add(temp);
            }
        }
    }


    public void FormMap(ArrayList<Room> room_list, ArrayList<Map<String,Object>> map_list){
        for(Room room:room_list){
            Map<String, Object> item = new HashMap<String, Object>();
            String name = room.getName();
            String location=room.getLocation();
            int image=room.getImage_url();
            int score=room.getScore();
            Integer capacity =  DBOpenHelper.getCapacityByRoomAndWeekday(name, currentDay); //待修改
            if (capacity != null){
                item.put("name", name);
                item.put("image", image);
                item.put("score",score);
                item.put("location",location);
                item.put("capacity", capacity);  //待修改

                map_list.add(item);
            }
        }
    }

    public SimpleAdapter setupList(View view){ //最初始展示的
        lv=view.findViewById(R.id.lv); //获取我们关心的控件

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getActivity(),/*传入一个上下文作为参数*/
                list_map, /*传入相对应的数据源，这个数据源不仅仅是数据而且还是和界面相耦合的混合体。*/
                R.layout.single_block_layout, /*设置具体某个items的布局，需要是新的布局，而不是ListView控件的布局*/
                new String[]{"image", "name","score","location","capacity"}, /*传入上面定义的Map中key的名称,会自动根据传入的key找到对应的值*/
                new int[]{R.id.room, R.id.room_name,R.id.usibilityScore,R.id.location,R.id.capacity}){
                /*传入items布局文件中所对应的控件，这里直接上id即可*/
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    final View view = super.getView(position, convertView, parent);
                    Button useBtn = (Button) view.findViewById(R.id.btn_singleRoom);
                    useBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String name =list_map.get(position).get("name").toString();
                            System.out.println("name is "+name);
                            Room detail_room= DBOpenHelper.getRoomByName(name);

                            Bundle bundle=new Bundle();
                            bundle.putSerializable("room",detail_room);
                            Intent intent=new Intent(getActivity(), RoomItemActivity.class);
                            intent.putExtras(bundle);

                            startActivity(intent);
                        }
                    });
                    return view;
                }
        };

        //3、为listView加入适配器
        lv.setAdapter(simpleAdapter);

        return simpleAdapter;
    }

    public void SearchListener(View view){
        searchView = (SearchView)view.findViewById(R.id.roomListSearchView);
        searchView.setIconifiedByDefault(false);//在最上面已创建searchView

        //set layout for searchView
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);//14sp

        int svIconId = searchView.getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null);
        ImageView icon = searchView.findViewById(svIconId);
        icon.setImageResource(R.drawable.search);
        icon.setPadding(5, 5, 5, 5);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Room> search_List = DBOpenHelper.getRoomByKeyword(s);

                if (!search_List.isEmpty()){
                    ArrayList<Map<String,Object>> filted_list_map = new ArrayList<Map<String,Object>>();  //放筛选后的那些room map
                    FormMap(search_List,filted_list_map);

                    reFresh(simpleAdapter,filted_list_map);
                }
                return false;
            }
        });
    }

    public void reFresh(SimpleAdapter simpleAdapter, ArrayList<Map<String, Object>> result_list) {
        list_map.clear();
        //复制
        for (Map room:result_list){
            list_map.add(room);
        }
        simpleAdapter.notifyDataSetChanged();
    }


    public void setDropbox(View view) {
        String[] ctype={"Default", "Location","Score", "Capacity"};
        MySpinner spinner =  view.findViewById(R.id.spinner1);
        spinner.setDropDownHorizontalOffset(-15);
        spinner.setDropDownVerticalOffset(80);
        spinner.setDropDownWidth(242);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.context, R.layout.spinner_item, ctype);
        adapter.setDropDownViewResource(R.layout.dropdown_style);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                System.out.println("spinner position= " + position);
                if (isInitial) {
                    isInitial = false;
                    return;
                }
                System.out.println("success");

                String dropbox_text = parent.getItemAtPosition(position).toString();

                switch (dropbox_text) {

                    case "Default":
                        ArrayList<Map<String, Object>> default_list_map = new ArrayList<Map<String, Object>>();
                        roomList = DBOpenHelper.getAvailableRoom(currentDay);

                        FormMap(roomList, default_list_map);

                        reFresh(simpleAdapter, default_list_map);

                        break;

                    case "Location":
                        ArrayList<Map<String, Object>> distance_sorted_list_map = new ArrayList<Map<String, Object>>();
                        roomList = DBOpenHelper.sortRoomByLocation(currentDay);

                        FormMap(roomList, distance_sorted_list_map);

                        reFresh(simpleAdapter,distance_sorted_list_map);

                        break;

                    case "Score":
                        ArrayList<Map<String, Object>> score_sorted_list_map = new ArrayList<Map<String, Object>>();
                        roomList = DBOpenHelper.sortRoomByScore(currentDay);

                        FormMap(roomList, score_sorted_list_map);

                        reFresh(simpleAdapter,score_sorted_list_map);

                        break;

                    case "Capacity":
                        ArrayList<Map<String, Object>> capacity_sorted_list_map = new ArrayList<Map<String, Object>>();
                        roomList = DBOpenHelper.sortByCapacity(currentDay);

                        FormMap(roomList, capacity_sorted_list_map);

                        reFresh(simpleAdapter, capacity_sorted_list_map);

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }}