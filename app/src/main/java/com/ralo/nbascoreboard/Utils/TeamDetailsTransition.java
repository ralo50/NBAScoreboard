package com.ralo.nbascoreboard.Utils;

import android.annotation.SuppressLint;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

public class TeamDetailsTransition extends TransitionSet {

    @SuppressLint("NewApi")
    public TeamDetailsTransition(){
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds()).addTransition(new ChangeTransform()).addTransition(new ChangeImageTransform());
        }
}
