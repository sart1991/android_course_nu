package com.exercises.sart1991.evaluacionfinal5.usable;

import android.app.Activity;
import android.content.Intent;

import com.exercises.sart1991.evaluacionfinal5.R;

/**
 * Created by sart1 on 4/19/2017.
 */

public class CustomTheme {
    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_FACEBOOK = 1;
    public final static int THEME_INSTAGRAM = 2;
    public final static int THEME_GPLUS = 3;
    public final static int THEME_TWITTER = 4;
    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_FACEBOOK:
                activity.setTheme(R.style.AppTheme_Facebook);
                break;
            case THEME_INSTAGRAM:
                activity.setTheme(R.style.AppTheme_Instagram);
                break;
            case THEME_GPLUS:
                activity.setTheme(R.style.AppTheme_Gplus);
                break;
            case THEME_TWITTER:
                activity.setTheme(R.style.AppTheme_Twitter);
                break;
        }
    }

    public static int getTheme() {
        return sTheme;
    }
}
