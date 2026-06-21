package com.example.lightsout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    ImageButton homeBtn, driversBtn, teamsBtn, scheduleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBtn = findViewById(R.id.homeBtn);
        driversBtn = findViewById(R.id.driversBtn);
        teamsBtn = findViewById(R.id.teamsBtn);
        scheduleBtn = findViewById(R.id.scheduleBtn);

        selectTab(homeBtn);
        loadFragment(new HomeFragment());

        homeBtn.setOnClickListener(v -> {
            selectTab(homeBtn);
            loadFragment(new HomeFragment());
        });

        driversBtn.setOnClickListener(v -> {
            selectTab(driversBtn);
            loadFragment(new DriversFragment());
        });

        teamsBtn.setOnClickListener(v -> {
            selectTab(teamsBtn);
            loadFragment(new TeamsFragment());
        });

        scheduleBtn.setOnClickListener(v -> {
            selectTab(scheduleBtn);
            loadFragment(new ScheduleFragment());
        });
    }

    private void selectTab(ImageButton selected) {
        homeBtn.setBackgroundColor(Color.TRANSPARENT);
        driversBtn.setBackgroundColor(Color.TRANSPARENT);
        teamsBtn.setBackgroundColor(Color.TRANSPARENT);
        scheduleBtn.setBackgroundColor(Color.TRANSPARENT);

        selected.setBackgroundResource(R.drawable.nav_selected);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}