package com.ralo.nbascoreboard.Adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.DataClasses.Play;
import com.ralo.nbascoreboard.Utils.DataClasses.Player;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonGameParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.MyViewHolder> {

    private ArrayList<Play> playList;
    private final static int PLAYER_ID_THRESHOLD = 1500000000;

    public PlayAdapter(ArrayList<Play> myPlays) {
        this.playList = myPlays;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView playQuarter;
        private TextView playTime;
        private TextView playCurrentScore;
        private TextView playDescription;
        private ImageView playerImage;

        MyViewHolder(View itemView) {
            super(itemView);
            playQuarter = itemView.findViewById(R.id.play_quarter);
            playTime = itemView.findViewById(R.id.play_time);
            playCurrentScore = itemView.findViewById(R.id.play_current_score);
            playDescription = itemView.findViewById(R.id.play_description);
            playerImage = itemView.findViewById(R.id.player_photo);
        }
    }

    @NonNull
    @Override
    public PlayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_play, viewGroup, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Play play = playList.get(position);
        String period = "Q" + play.getPeriod();
        myViewHolder.playQuarter.setText(period);
        myViewHolder.playTime.setText(play.getClockTime());
        String score = play.getVisitorScore() + "-" + play.getHomeScore();
        myViewHolder.playCurrentScore.setText(score);
        myViewHolder.playDescription.setText(play.getPlayDescription());
        if (play.isShotMade())
            myViewHolder.itemView.setBackgroundColor(Color.parseColor("#3d3d3d"));
        else
            myViewHolder.itemView.setBackgroundColor(Color.parseColor("#282828"));
        if (play.getPersonId() == 0 || play.getPersonId() > PLAYER_ID_THRESHOLD)
            myViewHolder.playerImage.setImageResource(JsonGameParser.getImageId(play.getTeamCode()));
        else
            Picasso.get().load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/" + play.getPersonId() + ".png").into(myViewHolder.playerImage);
    }

    @Override
    public int getItemCount() {
        return playList.size();
    }
}
