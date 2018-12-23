package com.ralo.nbascoreboard.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.DataClasses.GameStat;

import java.util.ArrayList;

public class GameStatAdapter extends RecyclerView.Adapter<GameStatAdapter.MyViewHolder> {

    private ArrayList<GameStat> gameStatArrayList;
    
    public GameStatAdapter(ArrayList<GameStat> gameStats) {
        this.gameStatArrayList = gameStats;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView awayTeamStat;
        private TextView statDescription;
        private TextView homeTeamStat;

        MyViewHolder(View itemView) {
            super(itemView);
            awayTeamStat = itemView.findViewById(R.id.away_team_stat);
            statDescription = itemView.findViewById(R.id.stat_description);
            homeTeamStat = itemView.findViewById(R.id.home_team_stat);
        }
    }

    @NonNull
    @Override
    public GameStatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_game_stat, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        GameStat gameStat = gameStatArrayList.get(position);
        myViewHolder.awayTeamStat.setText(gameStat.getAwayTeamStat());                              //TODO
        myViewHolder.statDescription.setText(gameStat.getStatDescription());                        //TODO
        myViewHolder.homeTeamStat.setText(gameStat.getHomeTeamStat());                              //TODO
    }

    @Override
    public int getItemCount() {
        return gameStatArrayList.size();
    }
}
