package com.rukiasoft.githubfetcher.utils;

import android.util.Log;
import android.view.View;
import static com.google.common.base.Preconditions.*;

/**
 * Created by Roll on 21/7/17.
 */

public class MyViewUtils {
    private static final String TAG = LogHelper.makeLogTag(MyViewUtils.class);

    public static void setViewVisible(View view){
        setViewVisibility(view, View.VISIBLE);
    }

    public static void setViewInisible(View view){
        setViewVisibility(view, View.INVISIBLE);
    }

    public static void setViewGone(View view){
        setViewVisibility(view, View.GONE);
    }

    private static void setViewVisibility(View view, int state){
        try {
            view = checkNotNull(view);
            view.setVisibility(state);
        }catch (NullPointerException e){
            Log.e(TAG, "he pasado una vista nula a setViewVisible");
        }
    }
}
