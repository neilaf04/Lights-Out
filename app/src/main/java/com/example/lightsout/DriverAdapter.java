package com.example.lightsout;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    ArrayList<DriverStanding> driverList;

    public DriverAdapter(ArrayList<DriverStanding> driverList) {
        this.driverList = driverList;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_driver, parent, false);

        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        DriverStanding driver = driverList.get(position);

        holder.positionText.setText("#" + driver.position);
        holder.driverNameText.setText(driver.driverName);
        holder.teamText.setText(driver.teamName);
        holder.pointsText.setText(driver.points + " pts");

        holder.teamStripe.setBackgroundColor(getTeamColor(driver.teamName));
        holder.helmetImage.setImageResource(getHelmetImage(driver.driverName, driver.teamName));
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public static class DriverViewHolder extends RecyclerView.ViewHolder {

        TextView positionText, driverNameText, teamText, pointsText;
        ImageView helmetImage;
        View teamStripe;

        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);

            positionText = itemView.findViewById(R.id.positionText);
            driverNameText = itemView.findViewById(R.id.driverNameText);
            teamText = itemView.findViewById(R.id.teamText);
            pointsText = itemView.findViewById(R.id.pointsText);
            helmetImage = itemView.findViewById(R.id.helmetImage);
            teamStripe = itemView.findViewById(R.id.teamStripe);
        }
    }

    private int getTeamColor(String team) {
        String lower = team.toLowerCase();

        if (lower.contains("ferrari")) {
            return Color.parseColor("#E10600");
        } else if (lower.contains("mercedes")) {
            return Color.parseColor("#00D2BE");
        } else if (lower.contains("red bull")) {
            return Color.parseColor("#3671C6");
        } else if (lower.contains("mclaren")) {
            return Color.parseColor("#FF8700");
        } else if (lower.contains("aston")) {
            return Color.parseColor("#006F62");
        } else if (lower.contains("williams")) {
            return Color.parseColor("#64C4FF");
        } else if (lower.contains("alpine")) {
            return Color.parseColor("#2293D1");
        } else if (lower.contains("haas")) {
            return Color.parseColor("#B6BABD");
        } else {
            return Color.parseColor("#E10600");
        }
    }

    private int getHelmetImage(String driverName, String teamName) {
        String driver = driverName.toLowerCase();
        String team = teamName.toLowerCase();

        if (driver.contains("verstappen") || team.contains("red bull")) {
            return R.drawable.redbull_healmet;
        } else if (driver.contains("leclerc") || driver.contains("hamilton") || team.contains("ferrari")) {
            return R.drawable.ferrari_healmet;
        } else if (driver.contains("russell") || team.contains("mercedes")) {
            return R.drawable.mercedes_healmet;
        } else if (driver.contains("alonso") || team.contains("aston")) {
            return R.drawable.astonmartin_healmet;
        } else {
            return R.drawable.mclaren_healmet;
        }
    }
}