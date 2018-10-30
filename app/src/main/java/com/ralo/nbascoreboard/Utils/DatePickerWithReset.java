package com.ralo.nbascoreboard.Utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;

public class DatePickerWithReset extends DatePickerDialog {

    public DatePickerWithReset(Context context, OnDateSetListener callBack,
                               int year, int monthOfYear, int dayOfMonth) {
        super(context, 0, callBack, year, monthOfYear, dayOfMonth);

        setButton(BUTTON_POSITIVE, ("Ok"), this);
        setButton(BUTTON_NEUTRAL, ("Today"), this); // ADD THIS
        setButton(BUTTON_NEGATIVE, ("Cancel"), this);
    }
}