package com.example.lightsout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    ImageButton backButton;
    Button driverTab, teamTab, gpTab;
    LinearLayout optionsContainer;
    SharedPreferences sharedPreferences;

    String[][] drivers = {
            {"Max Verstappen", "Red Bull Racing"},
            {"Charles Leclerc", "Ferrari"},
            {"Lewis Hamilton", "Ferrari"},
            {"George Russell", "Mercedes"},
            {"Fernando Alonso", "Aston Martin"}
    };

    String[] teams = {
            "Red Bull Racing", "Mercedes", "Ferrari", "Aston Martin", "Williams"
    };

    String[] gps = {
            "Monaco GP", "Bahrain GP", "Saudi Arabian GP",
            "Australian GP", "Japanese GP", "Spanish GP", "Miami GP"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        backButton = findViewById(R.id.backButton);
        driverTab = findViewById(R.id.driverTab);
        teamTab = findViewById(R.id.teamTab);
        gpTab = findViewById(R.id.gpTab);
        optionsContainer = findViewById(R.id.optionsContainer);

        sharedPreferences = getSharedPreferences("LightsOutPrefs", MODE_PRIVATE);

        backButton.setOnClickListener(v -> finish());

        showDrivers();

        driverTab.setOnClickListener(v -> showDrivers());
        teamTab.setOnClickListener(v -> showTeams());
        gpTab.setOnClickListener(v -> showGps());
    }

    private void showDrivers() {
        setActiveTab(driverTab);
        optionsContainer.removeAllViews();

        for (String[] driver : drivers) {
            addDriverCard(driver[0], driver[1]);
        }
    }

    private void showTeams() {
        setActiveTab(teamTab);
        optionsContainer.removeAllViews();

        for (String team : teams) {
            addTeamCard(team);
        }
    }

    private void showGps() {
        setActiveTab(gpTab);
        optionsContainer.removeAllViews();

        for (String gp : gps) {
            addGpCard(gp);
        }
    }

    private void addDriverCard(String driverName, String teamName) {
        LinearLayout card = createCard();

        ImageView image = new ImageView(this);
        image.setImageResource(getHelmetImage(driverName, teamName));
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(125, 125);
        imageParams.setMargins(0, 0, 20, 0);
        card.addView(image, imageParams);

        card.addView(createTextBox(driverName, teamName));
        card.addView(createAddButton());

        card.setOnClickListener(v -> {
            sharedPreferences.edit()
                    .putString("favorite_driver", driverName)
                    .putString("favorite_team", teamName)
                    .apply();

            Toast.makeText(this, driverName + " saved", Toast.LENGTH_SHORT).show();
        });

        optionsContainer.addView(card);
    }

    private void addTeamCard(String teamName) {
        LinearLayout card = createCard();

        ImageView image = new ImageView(this);
        image.setImageResource(getTeamCar(teamName));
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(155, 115);
        imageParams.setMargins(0, 0, 20, 0);
        card.addView(image, imageParams);

        card.addView(createTextBox(teamName, "Constructor"));
        card.addView(createAddButton());

        card.setOnClickListener(v -> {
            sharedPreferences.edit()
                    .putString("favorite_team", teamName)
                    .apply();

            Toast.makeText(this, teamName + " saved", Toast.LENGTH_SHORT).show();
        });

        optionsContainer.addView(card);
    }

    private void addGpCard(String gpName) {
        LinearLayout card = createCard();

        ImageView image = new ImageView(this);
        image.setImageResource(getTrackImage(gpName));
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(130, 100);
        imageParams.setMargins(0, 0, 20, 0);
        card.addView(image, imageParams);

        card.addView(createTextBox(gpName, "Grand Prix"));
        card.addView(createAddButton());

        card.setOnClickListener(v -> {
            sharedPreferences.edit()
                    .putString("favorite_gp", gpName)
                    .apply();

            Toast.makeText(this, gpName + " saved", Toast.LENGTH_SHORT).show();
        });

        optionsContainer.addView(card);
    }

    private LinearLayout createCard() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(18, 16, 18, 16);
        card.setBackgroundResource(R.drawable.hero_card);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                165
        );

        params.setMargins(0, 0, 0, 16);
        card.setLayoutParams(params);

        return card;
    }

    private LinearLayout createTextBox(String title, String subtitle) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams boxParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );

        box.setLayoutParams(boxParams);

        TextView titleText = new TextView(this);
        titleText.setText(title);
        titleText.setTextColor(Color.WHITE);
        titleText.setTextSize(19);
        titleText.setTypeface(null, 1);

        TextView subtitleText = new TextView(this);
        subtitleText.setText(subtitle);
        subtitleText.setTextColor(Color.parseColor("#B5B5C5"));
        subtitleText.setTextSize(14);
        subtitleText.setPadding(0, 5, 0, 0);

        box.addView(titleText);
        box.addView(subtitleText);

        return box;
    }

    private TextView createAddButton() {
        TextView add = new TextView(this);
        add.setText("ADD");
        add.setTextColor(Color.WHITE);
        add.setTextSize(13);
        add.setTypeface(null, 1);
        add.setGravity(Gravity.CENTER);
        add.setBackgroundResource(R.drawable.small_red_button);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(72, 42);
        add.setLayoutParams(params);

        return add;
    }

    private void setActiveTab(Button selected) {
        driverTab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#15161D")));
        teamTab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#15161D")));
        gpTab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#15161D")));
        selected.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#E10600")));
    }

    private int getHelmetImage(String driverName, String teamName) {
        String driver = driverName.toLowerCase();
        String team = teamName.toLowerCase();

        if (driver.contains("verstappen") || team.contains("red bull")) return R.drawable.redbull_healmet;
        if (driver.contains("leclerc") || driver.contains("hamilton") || team.contains("ferrari")) return R.drawable.ferrari_healmet;
        if (driver.contains("russell") || team.contains("mercedes")) return R.drawable.mercedes_healmet;
        if (driver.contains("alonso") || team.contains("aston")) return R.drawable.astonmartin_healmet;

        return R.drawable.mclaren_healmet;
    }

    private int getTeamCar(String team) {
        String lower = team.toLowerCase();

        if (lower.contains("red bull")) return R.drawable.redbull_car;
        if (lower.contains("ferrari")) return R.drawable.ferrari_car;
        if (lower.contains("mercedes")) return R.drawable.mercedes_car;
        if (lower.contains("aston")) return R.drawable.astonmartin_car;
        if (lower.contains("williams")) return R.drawable.williams_car;

        return R.drawable.f1_car;
    }

    private int getTrackImage(String gp) {
        if (gp.contains("Monaco")) return R.drawable.monaco_gp;
        if (gp.contains("Bahrain")) return R.drawable.bahrain_gp;
        if (gp.contains("Saudi")) return R.drawable.saudiarabian_gp;
        if (gp.contains("Australian")) return R.drawable.australian_gp;
        if (gp.contains("Japanese")) return R.drawable.japanise_gp;
        if (gp.contains("Spanish")) return R.drawable.barcelona_gp;
        if (gp.contains("Miami")) return R.drawable.miami_gp;

        return R.drawable.monaco_gp;
    }
}