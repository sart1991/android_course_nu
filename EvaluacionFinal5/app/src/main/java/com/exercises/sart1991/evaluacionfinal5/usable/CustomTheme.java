package com.exercises.sart1991.evaluacionfinal5.usable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.exercises.sart1991.evaluacionfinal5.R;

/**
 * Created by sart1 on 4/19/2017.
 */

public class CustomTheme {
    private static int sTheme;
    private static String sSubtitle;
    private static int sPrimaryColor;
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
                sSubtitle = activity.getResources().getString(R.string._home);
                sPrimaryColor = R.color.colorPrimary;
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_FACEBOOK:
                sSubtitle = activity.getResources().getString(R.string._facebook);
                sPrimaryColor = R.color.colorPrimaryF;
                activity.setTheme(R.style.AppTheme_Facebook);
                break;
            case THEME_INSTAGRAM:
                sSubtitle = activity.getResources().getString(R.string._instagram);
                sPrimaryColor = R.color.colorPrimaryI;
                activity.setTheme(R.style.AppTheme_Instagram);
                break;
            case THEME_GPLUS:
                sSubtitle = activity.getResources().getString(R.string._gplus);
                sPrimaryColor = R.color.colorPrimaryG;
                activity.setTheme(R.style.AppTheme_Gplus);
                break;
            case THEME_TWITTER:
                sSubtitle = activity.getResources().getString(R.string._twitter);
                sPrimaryColor = R.color.colorPrimaryT;
                activity.setTheme(R.style.AppTheme_Twitter);
                break;
        }
    }

    public static int getTheme() {
        return sTheme;
    }

    public static String getSubtitle() {
        return sSubtitle;
    }
    public static int getPrimaryColor() {
        return sPrimaryColor;
    }

    public  static int[][] getArrayOfIconTabs() {
        switch (sTheme) {
            default:
            case THEME_DEFAULT:
                return new int[][] {
                        {R.string.content_home},
                        {R.drawable.home}
                };
            case THEME_FACEBOOK:
                return new int[][] {
                        {R.string.content_news, R.string.content_people, R.string.content_earth},
                        {R.drawable.news_selector, R.drawable.people_selector, R.drawable.earth_selector}
                };
            case THEME_INSTAGRAM:
                return new int[][] {
                        {R.string.content_search, R.string.content_camera, R.string.content_heart},
                        {R.drawable.search_selector, R.drawable.camera_selector, R.drawable.heart_selector}
                };
            case THEME_GPLUS:
                return new int[][] {
                        {R.string.content_apps, R.string.content_communities, R.string.content_bell},
                        {R.drawable.apps_selector, R.drawable.communities_selector, R.drawable.bell_selector}
                };
            case THEME_TWITTER:
                return new int[][] {
                        {R.string.content_bell, R.string.content_message, R.string.content_search},
                        {R.drawable.bell_selector, R.drawable.message_selector, R.drawable.search_selector}
                };
        }
    }
}
