package com.exercise.nextu.evaluacionfinal12.utils;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public class Util {

    private Util() {

    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
