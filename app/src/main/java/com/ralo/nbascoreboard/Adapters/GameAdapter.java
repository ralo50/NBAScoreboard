package com.ralo.nbascoreboard.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.Game;

import java.util.ArrayList;


public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {

    private final CustomItemClickListener listener;
    private ArrayList<Game> gameList;


    public GameAdapter(ArrayList<Game> myValues, CustomItemClickListener listener){
        this.gameList = myValues;
        this.listener = listener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView homeTeamNameTextView;
        private TextView awayTeamNameTextView;
        private ImageView awayTeamLogoImageView;
        private ImageView homeTeamLogoImageView;
        private TextView awayTeamScoreTextView;
        private TextView homeTeamScoreTextView;
        private TextView awayTeamWinsTextView;
        private TextView homeTeamWinsTextView;
        private TextView nuggetTextView;
        private TextView gameTimeTextView;

        MyViewHolder(View itemView) {
            super(itemView);
            awayTeamNameTextView = itemView.findViewById(R.id.awayteamname);
            homeTeamNameTextView = itemView.findViewById(R.id.hometeamname);
            awayTeamLogoImageView = itemView.findViewById(R.id.awayteamlogo);
            homeTeamLogoImageView = itemView.findViewById(R.id.hometeamlogo);
            awayTeamScoreTextView = itemView.findViewById(R.id.awayteamscore);
            homeTeamScoreTextView = itemView.findViewById(R.id.hometeamscore);
            awayTeamWinsTextView = itemView.findViewById(R.id.awayteamwins);
            homeTeamWinsTextView = itemView.findViewById(R.id.hometeamwins);
            nuggetTextView = itemView.findViewById(R.id.nuggettext);
            gameTimeTextView = itemView.findViewById(R.id.gameTime);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(listItem);
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, myViewHolder.getAdapterPosition());

            }
        });

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.homeTeamNameTextView.setText(game.getHomeTeamName());
        holder.awayTeamNameTextView.setText(game.getAwayTeamName());
        holder.awayTeamLogoImageView.setImageResource(game.getAwayTeamImage());
        holder.homeTeamLogoImageView.setImageResource(game.getHomeTeamImage());
        holder.awayTeamScoreTextView.setText(game.getAwayTeamScore());
        holder.homeTeamScoreTextView.setText(game.getHomeTeamScore());
        holder.awayTeamWinsTextView.setText(game.getAwayTeamWins());
        holder.homeTeamWinsTextView.setText(game.getHomeTeamWins());
        holder.nuggetTextView.setText(game.getNugget());
        holder.gameTimeTextView.setText(game.getGameTime());
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }
}