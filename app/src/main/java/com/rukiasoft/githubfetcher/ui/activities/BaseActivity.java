package com.rukiasoft.githubfetcher.ui.activities;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rukiasoft.githubfetcher.utils.MyViewUtils;

import java.lang.reflect.Field;

public class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {


    private LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    protected void setToolbar(Toolbar toolbar, boolean backArrow){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(backArrow);
            try {
                if (toolbar.getClass() != null) {
                    Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
                    f.setAccessible(true);
                    TextView titleTextView = (TextView) f.get(toolbar);
                    titleTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    titleTextView.setFocusable(true);
                    titleTextView.setFocusableInTouchMode(true);
                    titleTextView.requestFocus();
                    titleTextView.setSingleLine(true);
                    titleTextView.setSelected(true);
                    titleTextView.setMarqueeRepeatLimit(-1);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycle;
    }

}
