package com.example.lightsout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.RaceViewHolder> {

    ArrayList<Race> races;

    public RaceAdapter(ArrayList<Race> races) {
        this.races = races;
    }

    @NonNull
    @Override
    public RaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_race, parent, false);
        return new RaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaceViewHolder holder, int position) {
        Race race = races.get(position);

        holder.roundText.setText("ROUND " + race.round);
        holder.raceNameText.setText(race.raceName);
        holder.circuitText.setText(race.circuit);
        holder.countryDateText.setText(race.country + " • " + race.date);
    }

    @Override
    public int getItemCount() {
        return races.size();
    }

    public static class RaceViewHolder extends RecyclerView.ViewHolder {

        TextView roundText, raceNameText, circuitText, countryDateText;

        public RaceViewHolder(@NonNull View itemView) {
            super(itemView);

            roundText = itemView.findViewById(R.id.roundText);
            raceNameText = itemView.findViewById(R.id.raceNameText);
            circuitText = itemView.findViewById(R.id.circuitText);
            countryDateText = itemView.findViewById(R.id.countryDateText);
        }
    }
}