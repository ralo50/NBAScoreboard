package com.ralo.nbascoreboard.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;

public abstract class BaseActivity extends AppCompatActivity {

    private FrameLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mainContainer = findViewById(R.id.main_container);
        setup();
    }

    private void setup() {
        NbaApp.setCurrentActivity(this);
        onCreateActivity();
    }

    public void setMainContainer(View child){
        this.mainContainer.addView(child);
    }

    public abstract void onCreateActivity();

}