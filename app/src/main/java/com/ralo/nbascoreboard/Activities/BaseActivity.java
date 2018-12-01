package com.ralo.nbascoreboard.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout mainContainer;
    private View child;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_base,null,false);
        setContentView(view);
        mainContainer = view.findViewById(R.id.main_container);
        setup();
    }

    private void setup() {
        NbaApp.setCurrentActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        child = getLayoutInflater().inflate(layoutResID,null,false);
        mainContainer.addView(child);
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return child.findViewById(id);
    }
}