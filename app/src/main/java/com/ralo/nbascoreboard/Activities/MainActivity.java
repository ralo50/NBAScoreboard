package com.ralo.nbascoreboard.Activities;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
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
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.CardsCreater;
import com.ralo.nbascoreboard.Utils.DatePickerWithReset;
import com.ralo.nbascoreboard.Utils.Game;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public String url;
    public TextView noteTextView;
    RecyclerView myView;
    ArrayList<String> myValues;
    ArrayList<Game> gameArrayList;
    CardsCreater cardsCreater;
    static Calendar myCalendar;
    AdView adView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setUrl(getYesterdayDate());
        setDatePicker();
        myCalendar = getYesterdaysCalendar();

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("8623F0A041C7A59B4C3BA8CAD7B28F64").build();
        adView.loadAd(adRequest);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setViews(){
        textView = findViewById(R.id.textView);
        textView.setText(getCurrentTextViewDate(getYesterdaysCalendar()));
        myView =  findViewById(R.id.recyclerview);
        noteTextView = findViewById(R.id.note);
        myValues = new ArrayList<>();

    }



    private void setUrl(String dateUrl){
        url = "http://data.nba.net/10s/prod/v1/" + dateUrl + "/scoreboard.json";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                noteTextView.setText("");
                cardsCreater = new CardsCreater(response);
                if(cardsCreater.isGameNight()) {
                    noteTextView.setVisibility(View.GONE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cardsCreater.populateCards();
                                    setCardsCreater();
                                    myView.setEnabled(true);
                                    myView.setAlpha(1);
                                }
                            });
                        }
                    }).start();
                }
                else{
                    myView.setEnabled(false);
                    myView.setAlpha(0f);
                    noteTextView.setVisibility(View.VISIBLE);
                    noteTextView.setText("No games on this day");
                    findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myView.setEnabled(false);
                myView.setAlpha(0f);
                noteTextView.setVisibility(View.VISIBLE);
                noteTextView.setText("Unable to show information");
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }
        });
        requestQueue.add(objectRequest);
    }


    public void setCardsCreater(){
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        gameArrayList = new ArrayList<>();
        gameArrayList = cardsCreater.getGameArrayList();

        GameAdapter adapter = new GameAdapter(gameArrayList);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);


    }



    @RequiresApi(api = Build.VERSION_CODES.N)
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


        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                final DatePickerWithReset datePickerWithReset = new DatePickerWithReset(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), MainActivity.myCalendar.get(Calendar.MONTH), MainActivity.myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerWithReset.show();
              //datePickerWithReset.getDatePicker().setMinDate();
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
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getCurrentDate() {
        Calendar myCalendar = Calendar.getInstance();

        String myFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return sdf.format(myCalendar.getTime());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getYesterdayDate() {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.add(Calendar.DAY_OF_YEAR, -1);
        String myFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return sdf.format(myCalendar.getTime());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getCurrentTextViewDate(Calendar calendar){
        String myFormat = "dd/MMM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        return "Date: " + sdf.format(calendar.getTime());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Calendar getYesterdaysCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void changeDate(View view) {
        switch (view.getId()){
            case (R.id.leftArrow):
                findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                myView.setAlpha(0);
                myCalendar.add(Calendar.DAY_OF_YEAR, -1);
                String myFormat = "yyyyMMdd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                textView.setText(getCurrentTextViewDate(myCalendar));
                setUrl(sdf.format(myCalendar.getTime()));
                break;

            case (R.id.rightArrow):
                findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                myView.setAlpha(0);
                myCalendar.add(Calendar.DAY_OF_YEAR, 1);
                myFormat = "yyyyMMdd";
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                textView.setText(getCurrentTextViewDate(myCalendar));
                setUrl(sdf.format(myCalendar.getTime()));
                break;
        }
    }
}