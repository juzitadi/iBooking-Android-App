package com.example.ibooking2.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ibooking2.R;
import com.example.ibooking2.object.Room;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RoomItemActivity extends AppCompatActivity {

    private Button btn_back;
    Fragment details=new DetailsFragment();
    Fragment timetable=new TimetableFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_item);

        btn_back=findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /// used for image slider///
        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.test1,"1 Image"));
        slideModels.add(new SlideModel(R.drawable.test2,"2 Image"));
        slideModels.add(new SlideModel(R.drawable.test3,"3 Image"));

        imageSlider.setImageList(slideModels,true);

        BottomNavigationView bottomNav = findViewById(R.id.middle_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        openFragment(details);
        //接受所传递的信息
    }

    public void openFragment(Fragment fragment) {
        Bundle bundle=getIntent().getExtras();
        Room room = (Room)bundle.getSerializable("room");
        System.out.println(room.getLocation()+"location here is now");
        bundle.putSerializable("room",room);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_details:
                            openFragment(details);
                            return true;
                        case R.id.nav_timetable:
                            openFragment(timetable);
                            return true;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return false;
                }
            };

    /*重写父类的onBackPressed*/
    @Override
    public void onBackPressed() {
//            Toast.makeText(RoomItemActivity.this, "点击了返回键", Toast.LENGTH_SHORT).show();
            finish();


    }
}
