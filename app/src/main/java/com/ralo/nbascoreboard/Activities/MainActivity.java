package com.ralo.nbascoreboard.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ralo.nbascoreboard.Adapters.GameAdapter;
import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.CardCreaters.GameCardsCreater;
import com.ralo.nbascoreboard.Utils.DatePickerWithReset;
import com.ralo.nbascoreboard.Utils.DataClasses.Game;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainActivity extends BaseActivity {

    public TextView textView;
    public String url;
    public TextView noteTextView;
    private AdView adView;
    private View dateChooser;
    private View loadingPanel;
    private RecyclerView myRecyclerView;
    private ArrayList<Game> gameArrayList;
    private GameCardsCreater gameCardsCreater;
    private static Calendar myCalendar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFont();
        setViews();
        setUrl(getYesterdayDate());
        setDatePicker();
        initAd();
    }

    private void setFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DINMedium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void initAd() {
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("8623F0A041C7A59B4C3BA8CAD7B28F64").build();
        adView.loadAd(adRequest);
    }

    private void setViews() {
        dateChooser = findViewById(R.id.dateChooser);
        myCalendar = getYesterdaysCalendar();
        adView = findViewById(R.id.adView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        textView = findViewById(R.id.dateTextView);
        textView.setText(getCurrentTextViewDate(getYesterdaysCalendar()));
        myRecyclerView = findViewById(R.id.recyclerview);
        noteTextView = findViewById(R.id.note);
        loadingPanel = findViewById(R.id.loadingPanel);
        setListeners();
    }

    private void setUrl(final String dateUrl) {
        myRecyclerView.setAlpha(0.5f);
        loadingPanel.setVisibility(View.VISIBLE);
        noteTextView.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                url = "http://data.nba.net/10s/prod/v1/" + dateUrl + "/scoreboard.json";
                final RequestQueue requestQueue = Volley.newRequestQueue(NbaApp.getCurrentActivity());
                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonObject = response;
                        gameCardsCreater = new GameCardsCreater(jsonObject);
                        if (gameCardsCreater.isGameNight()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    gameCardsCreater.populateCards();
                                    setCardsCreater();
                                    myRecyclerView.setEnabled(true);
                                    myRecyclerView.setVisibility(View.VISIBLE);
                                    loadingPanel.setVisibility(View.GONE);
                                    myRecyclerView.setAlpha(1);
                                    runLayoutAnimation(myRecyclerView);
                                }
                            });
                        } else {
                            myRecyclerView.setEnabled(false);
                            myRecyclerView.setVisibility(View.GONE);
                            noteTextView.setVisibility(View.VISIBLE);
                            noteTextView.setText(R.string.no_games_tonight);
                            loadingPanel.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        myRecyclerView.setEnabled(false);
                        myRecyclerView.setVisibility(View.GONE);
                        noteTextView.setVisibility(View.VISIBLE);
                        noteTextView.setText(R.string.error_getting_info);
                        loadingPanel.setVisibility(View.GONE);
                    }
                });
                requestQueue.add(objectRequest);
            }
        }).start();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setCardsCreater() {
        gameArrayList = new ArrayList<>();
        gameArrayList = gameCardsCreater.getGameArrayList();
        GameAdapter adapter = new GameAdapter(gameArrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (isNetworkConnected()) {
                    Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("gameDate", gameArrayList.get(position).getGameDate());
                    extras.putString("gameId", gameArrayList.get(position).getGameId());
                    extras.putString("homeTeamWins", gameArrayList.get(position).getHomeTeamWins());
                    extras.putString("awayTeamWins", gameArrayList.get(position).getAwayTeamWins());
                    extras.putBoolean("isGameActivated", gameArrayList.get(position).isGameActive());
                    extras.putString("homeTeamName", gameArrayList.get(position).getHomeTeamName());
                    extras.putString("awayTeamName", gameArrayList.get(position).getAwayTeamName());
                    extras.putBoolean("isGameOver", gameArrayList.get(position).isGameOver());
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    myIntent.putExtras(extras);
                    MainActivity.this.startActivity(myIntent);
                } else {
                    Toast.makeText(NbaApp.getCurrentActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(View v, int position) {
            }
        });
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);
    }

    private void setDatePicker() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                MainActivity.myCalendar.set(Calendar.YEAR, year);
                MainActivity.myCalendar.set(Calendar.MONTH, monthOfYear);
                MainActivity.myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyyMMdd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                setUrl(sdf.format(MainActivity.myCalendar.getTime()));
                myFormat = "dd/MMM/yyyy";
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                String textViewText = "Date: " + (sdf.format(MainActivity.myCalendar.getTime()));
                textView.setText(textViewText);
            }

        };

        dateChooser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final DatePickerWithReset datePickerWithReset = new DatePickerWithReset(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), MainActivity.myCalendar.get(Calendar.MONTH), MainActivity.myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerWithReset.show();
                datePickerWithReset.getDatePicker().setMinDate(1477346400000L);
                //datePickerWithReset.getDatePicker().setMaxDate();
                datePickerWithReset.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                myCalendar = Calendar.getInstance();
                                String myFormat = "yyyyMMdd";
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                                setUrl(sdf.format(MainActivity.myCalendar.getTime()));

                                myFormat = "dd/MMM/yyyy";
                                sdf = new SimpleDateFormat(myFormat, Locale.US);
                                String textViewText = "Date: " + (sdf.format(MainActivity.myCalendar.getTime()));
                                textView.setText(textViewText);
                                datePickerWithReset.dismiss();
                            }
                        });
                datePickerWithReset.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePickerWithReset.dismiss();
                            }
                        });
            }
        });
    }

//    private String getCurrentDate() {
//        Calendar myCalendar = Calendar.getInstance();
//        String myFormat = "yyyyMMdd";
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        return sdf.format(myCalendar.getTime());
//    }

    private String getYesterdayDate() {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.add(Calendar.DAY_OF_YEAR, -1);
        String myFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return sdf.format(myCalendar.getTime());
    }

    private String getCurrentTextViewDate(Calendar calendar) {
        String myFormat = "dd/MMM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return "Date: " + sdf.format(calendar.getTime());
    }

    private Calendar getYesterdaysCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar;
    }

    public void changeDate(View view) {
        switch (view.getId()) {
            case (R.id.leftArrow):
                changeDateYesterday();
                break;
            case (R.id.rightArrow):
                changeDateTomorrow();
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
     /*   myRecyclerView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                changeDateTomorrow();
            }
            @Override
            public void onSwipeRight(){
                changeDateYesterday();
            }
        });
    */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                String myFormat = "yyyyMMdd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                setUrl(sdf.format(MainActivity.myCalendar.getTime()));
                myRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    public void changeDateYesterday() {
        noteTextView.setVisibility(View.GONE);
        myCalendar.add(Calendar.DAY_OF_YEAR, -1);
        String myFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        textView.setText(getCurrentTextViewDate(myCalendar));
        setUrl(sdf.format(myCalendar.getTime()));
    }

    public void changeDateTomorrow() {
        noteTextView.setVisibility(View.GONE);
        myCalendar.add(Calendar.DAY_OF_YEAR, 1);
        String myFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        textView.setText(getCurrentTextViewDate(myCalendar));
        setUrl(sdf.format(myCalendar.getTime()));
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) NbaApp.getCurrentActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}