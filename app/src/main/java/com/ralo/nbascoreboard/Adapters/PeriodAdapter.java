package com.ralo.nbascoreboard.Adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.DataClasses.Period;

import java.util.ArrayList;

public class PeriodAdapter extends RecyclerView.Adapter<PeriodAdapter.MyViewHolder> {
    private ArrayList<Period> periodArrayList;


    public PeriodAdapter(ArrayList<Period> myPeriods) {
        this.periodArrayList = myPeriods;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView periodCount;
        private TextView awayTeamPeriodPoints;
        private TextView homeTeamPeriodPoints;


        MyViewHolder(View itemView) {
            super(itemView);
            itemView.setBackgroundColor(Color.parseColor("#282828"));
            periodCount = itemView.findViewById(R.id.periodCount);
            awayTeamPeriodPoints = itemView.findViewById(R.id.awayTeamPeriodPoints);
            homeTeamPeriodPoints = itemView.findViewById(R.id.homeTeamPeriodPoints);
        }
    }

    @NonNull
    @Override
    public PeriodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_game_period_scores, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PeriodAdapter.MyViewHolder myViewHolder, int position) {
        Period period = periodArrayList.get(position);
        myViewHolder.periodCount.setText(period.getPeriodNumber());
        myViewHolder.awayTeamPeriodPoints.setText(String.valueOf(period.getAwayTeamPeriodScore()));
        myViewHolder.homeTeamPeriodPoints.setText(String.valueOf(period.getHomeTeamPeriodScore()));
        if (period.getAwayTeamPeriodScore() > period.getHomeTeamPeriodScore())
            myViewHolder.awayTeamPeriodPoints.setTextColor(Color.parseColor("#ffffff"));
        else if (period.getAwayTeamPeriodScore() < period.getHomeTeamPeriodScore())
            myViewHolder.homeTeamPeriodPoints.setTextColor(Color.parseColor("#ffffff"));
    }

    @Override
    public int getItemCount() {
        return periodArrayList.size();
    }
}
