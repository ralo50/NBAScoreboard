package com.ralo.nbascoreboard.Prototype;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;

public class NbaTabView extends FrameLayout {
    private int SELECTOR_ANIMATION_DURATION = 150;

    private View parentView;
    private Context context;

    private View viewSelector;
    private TextView tab0, tab1, tab2;
    private Fragment tabFragment0, tabFragment1, tabFragment2;
    private TextView selectedTab;
    private int currentIndex = -1;

    public NbaTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        parentView = LayoutInflater.from(context).inflate(R.layout.control_game_tab, null, false);
        addView(parentView);
        invalidate();
        setup();
    }

    private void setup() {
        viewSelector = parentView.findViewById(R.id.view_selector);
        tab0 = parentView.findViewById(R.id.tab0);
        tab1 = parentView.findViewById(R.id.tab1);
        tab2 = parentView.findViewById(R.id.tab2);
        tab0.setOnClickListener(onTabClickListener);
        tab1.setOnClickListener(onTabClickListener);
        tab2.setOnClickListener(onTabClickListener);
        selectedTab = tab1;
    }

    public void setTab0(Fragment fragment, String tabName) {
        this.tabFragment0 = fragment;
        this.tab0.setText(tabName);
    }

    public void setTab1(Fragment fragment, String tabName) {
        this.tabFragment1 = fragment;
        this.tab1.setText(tabName);
    }

    public void setTab2(Fragment fragment, String tabName) {
        this.tabFragment2 = fragment;
        this.tab2.setText(tabName);
    }

    private OnClickListener onTabClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            setupChangedTab((TextView) v);
        }
    };

    private void setupChangedTab(TextView selectedTab) {
        this.selectedTab = selectedTab;
        loadFragmentBySelectedIndex();
    }

    public int getSelectedTabIndex() {
        if (selectedTab == tab0) {
            return 0;
        }
        if (selectedTab == tab1) {
            return 1;
        }
        if (selectedTab == tab2) {
            return 2;
        }
        return -1;
    }

    public void loadFragmentById(int index) {
        switch (index) {
            case 0:
                loadFragment(tabFragment0, true, currentIndex < index);
                break;
            case 1:
                loadFragment(tabFragment1, true, currentIndex < index);
                break;
            case 2:
                loadFragment(tabFragment2, true, currentIndex < index);
                break;
        }
        setSelectorToIndex(index, index - currentIndex);
        currentIndex = index;
    }

    public void loadFragmentBySelectedIndex() {
        int selectedIndex = getSelectedTabIndex();
        loadFragmentById(selectedIndex);
    }

    private void loadFragment(Fragment fragment, boolean animate, boolean forwardAnimation) {
        FragmentTransaction fragmentTransaction;
        if(animate) {
            fragmentTransaction = getAnimatedFragmentTransaction(forwardAnimation);
        } else {
            fragmentTransaction = NbaApp.getFragmentSupportManager().beginTransaction();
        }
        fragmentTransaction.replace(R.id.tab_fragment_container, fragment,"");
        fragmentTransaction.commit();
    }

    private void animateViewSelector() {
        //TODO move tabSelector animation using margins to match layout params of selectedTab
    }

    private FragmentTransaction getAnimatedFragmentTransaction(boolean forwardAnimation) {
        if(forwardAnimation) {
            return NbaApp.getFragmentSupportManager().beginTransaction().setCustomAnimations(R.anim.push_left_forward, R.anim.push_right_forward);
        } else {
            return NbaApp.getFragmentSupportManager().beginTransaction().setCustomAnimations(R.anim.push_right, R.anim.push_left);
        }
    }

    private void setSelectorToIndex(int index, int difference) {
        if(difference>0) {
            animateWidthForward(index, difference + 1);
        } else if(difference<0){
            animateMarginBackward(index,Math.abs(difference) + 1);
        }
    }

    private void animateWidthForward(final int index, final int widthMultiplier) {
        final int width = selectedTab.getWidth();
        ValueAnimator widthAnimator = ValueAnimator.ofInt(width, widthMultiplier*width);
        widthAnimator.setDuration(SELECTOR_ANIMATION_DURATION);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewSelector.getLayoutParams();
                params.width = animatedValue;
                viewSelector.setLayoutParams(params);
            }
        });
        widthAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animateMarginForward(index,widthMultiplier);
            }
        });
        widthAnimator.start();
    }

    private void animateMarginForward(int index, final int widthMultiplier) {
        int beginMargin = ((LinearLayout.LayoutParams)viewSelector.getLayoutParams()).leftMargin;
        int marginLeft = index * selectedTab.getWidth();
        final int width = selectedTab.getWidth();

        PropertyValuesHolder pvMarginLeft = PropertyValuesHolder.ofInt("marginLeft",beginMargin, marginLeft);
        PropertyValuesHolder pvWidth = PropertyValuesHolder.ofInt("width",widthMultiplier*width, width);

        ValueAnimator widthAnimator = ValueAnimator.ofPropertyValuesHolder(pvMarginLeft,pvWidth);
        widthAnimator.setDuration(SELECTOR_ANIMATION_DURATION);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedMargin = (int) animation.getAnimatedValue("marginLeft");
                int animatedWidth = (int) animation.getAnimatedValue("width");
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewSelector.getLayoutParams();
                params.width = animatedWidth;
                params.leftMargin = animatedMargin;
                viewSelector.setLayoutParams(params);
            }
        });
        widthAnimator.start();
    }

    private void animateMarginBackward(final int index, final int widthMultiplier) {
        int beginMargin = ((LinearLayout.LayoutParams) viewSelector.getLayoutParams()).leftMargin;
        int marginLeft = index * selectedTab.getWidth();
        final int width = selectedTab.getWidth();

        PropertyValuesHolder pvMarginLeft = PropertyValuesHolder.ofInt("marginLeft",beginMargin, marginLeft);
        PropertyValuesHolder pvWidth = PropertyValuesHolder.ofInt("width",width, widthMultiplier*width);

        ValueAnimator widthAnimator = ValueAnimator.ofPropertyValuesHolder(pvMarginLeft,pvWidth);
        widthAnimator.setDuration(SELECTOR_ANIMATION_DURATION);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedMargin = (int) animation.getAnimatedValue("marginLeft");
                int animatedWidth = (int) animation.getAnimatedValue("width");
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewSelector.getLayoutParams();
                params.width = animatedWidth;
                params.leftMargin = animatedMargin;
                viewSelector.setLayoutParams(params);
            }
        });
        widthAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animateWidthBackward(widthMultiplier);
            }
        });
        widthAnimator.start();
    }

    private void animateWidthBackward(final int widthMultiplier) {
        final int width = selectedTab.getWidth();

        PropertyValuesHolder pvWidth = PropertyValuesHolder.ofInt("width",widthMultiplier*width, width);

        ValueAnimator widthAnimator = ValueAnimator.ofPropertyValuesHolder(pvWidth);
        widthAnimator.setDuration(SELECTOR_ANIMATION_DURATION);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedWidth = (int) animation.getAnimatedValue("width");
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewSelector.getLayoutParams();
                params.width = animatedWidth;
                viewSelector.setLayoutParams(params);
            }
        });
        widthAnimator.start();
    }
}