package com.example.lightsout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    TextView favDriverText, favTeamText, favGpText;
    TextView nextRaceText, raceDateText, daysLeftText;

    ImageView trackImage, favTrackImage, driverHelmet, teamCar;

    Button addFavoritesButton, editFavoritesButton, logoutButton;
    SharedPreferences sharedPreferences;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        favDriverText = view.findViewById(R.id.favDriverText);
        favTeamText = view.findViewById(R.id.favTeamText);
        favGpText = view.findViewById(R.id.favGpText);

        nextRaceText = view.findViewById(R.id.nextRaceText);
        raceDateText = view.findViewById(R.id.raceDateText);
        daysLeftText = view.findViewById(R.id.daysLeftText);

        trackImage = view.findViewById(R.id.trackImage);
        favTrackImage = view.findViewById(R.id.favTrackImage);
        driverHelmet = view.findViewById(R.id.driverHelmet);
        teamCar = view.findViewById(R.id.teamCar);

        addFavoritesButton = view.findViewById(R.id.addFavoritesButton);
        editFavoritesButton = view.findViewById(R.id.editFavoritesButton);
        logoutButton = view.findViewById(R.id.logoutButton);

        sharedPreferences = requireActivity().getSharedPreferences("LightsOutPrefs", Context.MODE_PRIVATE);

        loadFavorites();

        nextRaceText.setText("Monaco Grand Prix");
        raceDateText.setText("Circuit de Monaco • 2026-06-07");
        daysLeftText.setText("8 days left");
        trackImage.setImageResource(R.drawable.monaco_gp);

        addFavoritesButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddActivity.class));
        });

        editFavoritesButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), EditActivity.class));
        });

        logoutButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            requireActivity().finish();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (sharedPreferences != null) {
            loadFavorites();
        }
    }

    private void loadFavorites() {
        String driver = sharedPreferences.getString("favorite_driver", "Max Verstappen");
        String team = sharedPreferences.getString("favorite_team", "Red Bull Racing");
        String gp = sharedPreferences.getString("favorite_gp", "Monaco GP");

        favDriverText.setText(driver);
        favTeamText.setText(team);
        favGpText.setText(gp);

        driverHelmet.setImageResource(getHelmetImage(driver));
        teamCar.setImageResource(getTeamCar(team));
        favTrackImage.setImageResource(getTrackImage(gp));
    }

    private int getHelmetImage(String driver) {
        if (driver.contains("Verstappen")) return R.drawable.redbull_healmet;
        if (driver.contains("Hamilton")) return R.drawable.ferrari_healmet;
        if (driver.contains("Leclerc")) return R.drawable.ferrari_healmet;
        if (driver.contains("Russell")) return R.drawable.mercedes_healmet;
        if (driver.contains("Alonso")) return R.drawable.astonmartin_healmet;
        return R.drawable.mclaren_healmet;
    }

    private int getTeamCar(String team) {
        if (team.contains("Ferrari")) return R.drawable.ferrari_car;
        if (team.contains("Mercedes")) return R.drawable.mercedes_car;
        if (team.contains("Red Bull")) return R.drawable.redbull_car;
        if (team.contains("Aston")) return R.drawable.astonmartin_car;
        if (team.contains("Williams")) return R.drawable.williams_car;
        return R.drawable.f1_car;
    }

    private int getTrackImage(String gp) {
        if (gp.contains("Monaco")) return R.drawable.monaco_gp;
        if (gp.contains("Miami")) return R.drawable.miami_gp;
        if (gp.contains("Saudi")) return R.drawable.saudiarabian_gp;
        if (gp.contains("Bahrain")) return R.drawable.bahrain_gp;
        if (gp.contains("Japanese")) return R.drawable.japanise_gp;
        if (gp.contains("Spanish")) return R.drawable.barcelona_gp;
        if (gp.contains("Australian")) return R.drawable.australian_gp;
        return R.drawable.monaco_gp;
    }
}