package com.example.lightsout;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DriversFragment extends Fragment {

    RecyclerView driversRecyclerView;
    DriverAdapter driverAdapter;
    ArrayList<DriverStanding> driverList;

    String apiUrl = "https://api.jolpi.ca/ergast/f1/current/driverstandings.json";

    public DriversFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drivers, container, false);

        driversRecyclerView = view.findViewById(R.id.driversRecyclerView);

        driverList = new ArrayList<>();
        driverAdapter = new DriverAdapter(driverList);

        driversRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        driversRecyclerView.setAdapter(driverAdapter);

        loadDriverStandings();

        return view;
    }

    private void loadDriverStandings() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                null,

                response -> {
                    try {
                        JSONObject mrData = response.getJSONObject("MRData");
                        JSONObject standingsTable = mrData.getJSONObject("StandingsTable");
                        JSONArray standingsLists = standingsTable.getJSONArray("StandingsLists");

                        if (standingsLists.length() == 0) {
                            Toast.makeText(getContext(), "No standings available yet", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        JSONObject firstList = standingsLists.getJSONObject(0);
                        JSONArray standings = firstList.getJSONArray("DriverStandings");

                        driverList.clear();

                        for (int i = 0; i < standings.length(); i++) {
                            JSONObject item = standings.getJSONObject(i);

                            String position = item.getString("position");
                            String points = item.getString("points");

                            JSONObject driver = item.getJSONObject("Driver");
                            String driverName = driver.getString("givenName") + " " + driver.getString("familyName");

                            JSONArray constructors = item.getJSONArray("Constructors");
                            JSONObject constructor = constructors.getJSONObject(0);
                            String teamName = constructor.getString("name");

                            driverList.add(new DriverStanding(position, driverName, teamName, points));
                        }

                        driverAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "API parsing error", Toast.LENGTH_SHORT).show();
                    }
                },

                error -> Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }
}