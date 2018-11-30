package com.ralo.nbascoreboard.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ralo.nbascoreboard.Adapters.PlayerAdapter;
import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.JsonTeamParser;
import com.ralo.nbascoreboard.Utils.Player;
import com.ralo.nbascoreboard.Utils.PlayerCardsCreater;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoxscoreFragment extends Fragment {

    RadioGroup teamRadioGroup;
    JSONObject jsonObject;
    RecyclerView myRecyclerView;
    ArrayList<Player> playerArrayList;
    PlayerCardsCreater playerCardsCreater;
    RadioButton homeRadioButton;
    RadioButton awayRadioButton;


    public BoxscoreFragment(){}
    @SuppressLint("ValidFragment")
    public BoxscoreFragment(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boxscore, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        setupPlayerDetails();

    }

    private void setupViews(){
        teamRadioGroup = getView().findViewById(R.id.toggle);
        myRecyclerView = getView().findViewById(R.id.myRecyclerView);
        homeRadioButton = getView().findViewById(R.id.homeTeamRadioButton);
        awayRadioButton = getView().findViewById(R.id.awayTeamRadioButton);
        getTeamNames();
        setupAwayPlayersDetails();
    }

    private void setupPlayerDetails() {
        teamRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.awayTeamRadioButton:
                        setupAwayPlayersDetails();
                        //something
                        break;
                    case R.id.homeTeamRadioButton:
                        setupHomePlayerDetails();
                        //something
                        break;
                }
            }
        });
    }

    private void setupAwayPlayersDetails() {
        playerCardsCreater = new PlayerCardsCreater(jsonObject, "visitor");
        playerCardsCreater.populateCards();
        setCardsCreater();
    }

    private void setupHomePlayerDetails() {
        playerCardsCreater = new PlayerCardsCreater(jsonObject, "home");
        playerCardsCreater.populateCards();
        setCardsCreater();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setCardsCreater(){
        playerArrayList = new ArrayList<>();
        playerArrayList = playerCardsCreater.getPlayerArrayList();

        PlayerAdapter adapter = new PlayerAdapter(playerArrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);

    }
    private void getTeamNames(){
        JsonTeamParser jsonTeamParser = new JsonTeamParser(jsonObject);
        homeRadioButton.setText(jsonTeamParser.getHomeTeamName());
        awayRadioButton.setText(jsonTeamParser.getAwayTeamName());

    }


}
