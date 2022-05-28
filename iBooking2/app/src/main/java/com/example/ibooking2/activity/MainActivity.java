package com.example.ibooking2.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ibooking2.R;
import com.example.ibooking2.activity.navigationbar.HomeFragment;
import com.example.ibooking2.activity.navigationbar.ProfileFragment;
import com.example.ibooking2.activity.navigationbar.ReminderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Fragment homePage=new HomeFragment();
    Fragment reminderPage=new ReminderFragment();
    Fragment profilePage=new ProfileFragment();


    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            openFragment(homePage);
                        }
                    }).start();
                    return true;
                case R.id.nav_profile:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            openFragment(profilePage);

                        }
                    }).start();
                    return true;
                case R.id.nav_reminder:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            openFragment(reminderPage);

                        }
                    }).start();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_buttons);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(homePage);




    }

    @Override
        protected void onResume() {

        int id = getIntent().getIntExtra("id", 0);
        if (id == 1) {
            openFragment(reminderPage);
        }
        super.onResume();
    }


    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}