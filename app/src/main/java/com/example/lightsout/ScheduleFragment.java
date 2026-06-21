package com.example.lightsout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    RecyclerView raceRecyclerView;
    RaceAdapter raceAdapter;
    ArrayList<Race> raceList;

    String apiUrl = "https://api.jolpi.ca/ergast/f1/current/races.json";

    public ScheduleFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        raceRecyclerView = view.findViewById(R.id.raceRecyclerView);

        raceList = new ArrayList<>();
        raceAdapter = new RaceAdapter(raceList);

        raceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        raceRecyclerView.setAdapter(raceAdapter);

        loadSchedule();

        return view;
    }

    private void loadSchedule() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                null,
                response -> {
                    try {
                        JSONObject mrData = response.getJSONObject("MRData");
                        JSONObject raceTable = mrData.getJSONObject("RaceTable");
                        JSONArray races = raceTable.getJSONArray("Races");

                        raceList.clear();

                        for (int i = 0; i < races.length(); i++) {
                            JSONObject race = races.getJSONObject(i);

                            String round = race.getString("round");
                            String raceName = race.getString("raceName");
                            String date = race.getString("date");

                            JSONObject circuit = race.getJSONObject("Circuit");
                            String circuitName = circuit.getString("circuitName");

                            JSONObject location = circuit.getJSONObject("Location");
                            String country = location.getString("country");

                            raceList.add(new Race(round, raceName, circuitName, country, date));
                        }

                        raceAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "API parsing error", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }
}