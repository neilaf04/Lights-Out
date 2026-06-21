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

public class TeamsFragment extends Fragment {

    RecyclerView teamsRecyclerView;
    TeamAdapter teamAdapter;
    ArrayList<TeamStanding> teamList;

    String apiUrl = "https://api.jolpi.ca/ergast/f1/current/constructorstandings.json";

    public TeamsFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        teamsRecyclerView = view.findViewById(R.id.teamsRecyclerView);

        teamList = new ArrayList<>();
        teamAdapter = new TeamAdapter(teamList);

        teamsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        teamsRecyclerView.setAdapter(teamAdapter);

        loadTeamStandings();

        return view;
    }

    private void loadTeamStandings() {
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
                            Toast.makeText(getContext(), "No team standings available yet", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        JSONObject firstList = standingsLists.getJSONObject(0);
                        JSONArray standings = firstList.getJSONArray("ConstructorStandings");

                        teamList.clear();

                        for (int i = 0; i < standings.length(); i++) {
                            JSONObject item = standings.getJSONObject(i);

                            String position = item.getString("position");
                            String points = item.getString("points");

                            JSONObject constructor = item.getJSONObject("Constructor");
                            String teamName = constructor.getString("name");

                            if (shouldShowTeam(teamName)) {
                                teamList.add(new TeamStanding(position, teamName, points));
                            }
                        }

                        teamAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "API parsing error", Toast.LENGTH_SHORT).show();
                    }
                },

                error -> Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

    private boolean shouldShowTeam(String teamName) {
        String lower = teamName.toLowerCase();

        return lower.contains("red bull")
                || lower.contains("mercedes")
                || lower.contains("ferrari")
                || lower.contains("aston")
                || lower.contains("williams");
    }
}