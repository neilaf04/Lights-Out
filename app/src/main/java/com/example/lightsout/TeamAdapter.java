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

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    ArrayList<TeamStanding> teamList;

    public TeamAdapter(ArrayList<TeamStanding> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_team, parent, false);

        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        TeamStanding team = teamList.get(position);

        holder.positionText.setText(formatPosition(team.position));
        holder.teamNameText.setText(team.teamName);
        holder.pointsText.setText(team.points + " PTS");

        holder.teamStripe.setBackgroundColor(getTeamColor(team.teamName));
        holder.teamCarImage.setImageResource(getTeamCar(team.teamName));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {

        TextView positionText, teamNameText, pointsText;
        ImageView teamCarImage;
        View teamStripe;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            positionText = itemView.findViewById(R.id.teamPositionText);
            teamNameText = itemView.findViewById(R.id.teamNameText);
            pointsText = itemView.findViewById(R.id.teamPointsText);
            teamCarImage = itemView.findViewById(R.id.teamCarImage);
            teamStripe = itemView.findViewById(R.id.teamStripe);
        }
    }

    private String formatPosition(String position) {
        if (position.length() == 1) {
            return "0" + position;
        }
        return position;
    }

    private int getTeamColor(String team) {
        String lower = team.toLowerCase();

        if (lower.contains("red bull")) return Color.parseColor("#3671C6");
        if (lower.contains("mercedes")) return Color.parseColor("#00D2BE");
        if (lower.contains("ferrari")) return Color.parseColor("#E10600");
        if (lower.contains("aston")) return Color.parseColor("#006F62");
        if (lower.contains("williams")) return Color.parseColor("#64C4FF");

        return Color.parseColor("#E10600");
    }

    private int getTeamCar(String team) {
        String lower = team.toLowerCase();

        if (lower.contains("red bull")) return R.drawable.redbull_car;
        if (lower.contains("mercedes")) return R.drawable.mercedes_car;
        if (lower.contains("ferrari")) return R.drawable.ferrari_car;
        if (lower.contains("aston")) return R.drawable.astonmartin_car;
        if (lower.contains("williams")) return R.drawable.williams_car;

        return R.drawable.f1_car;
    }
}