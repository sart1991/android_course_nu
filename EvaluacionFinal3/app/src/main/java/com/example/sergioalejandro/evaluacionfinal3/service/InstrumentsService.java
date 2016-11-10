package com.example.sergioalejandro.evaluacionfinal3.service;

import android.content.Context;

import com.example.sergioalejandro.evaluacionfinal3.R;
import com.example.sergioalejandro.evaluacionfinal3.model.Instrument;
import com.example.sergioalejandro.evaluacionfinal3.resources.SourceOfInstruments;

import java.util.List;

/**
 * Created by Admin on 11/10/2016.
 */

public class InstrumentsService {

    private static Context gContext;

    private InstrumentsService(){SourceOfInstruments.setContext(gContext);}

    public static void setContext(Context context){gContext = context;}

    public static String getClasificationTitle(int position) {
        switch (position) {
            case 0:
                return gContext.getResources().getString(R.string.activity_name_string);
            case 1:
                return gContext.getResources().getString(R.string.activity_name_percussion);
            case 2:
                return gContext.getResources().getString(R.string.activity_name_wind);
            default:
                return gContext.getResources().getString(R.string.activity_name_electric);
        }
    }

    public static String getClasificationContent(int position) {
        switch (position) {
            case 0:
                return gContext.getResources().getString(R.string.string_content);
            case 1:
                return gContext.getResources().getString(R.string.percusion_content);
            case 2:
                return gContext.getResources().getString(R.string.wind_content);
            default:
                return gContext.getResources().getString(R.string.percusion_content);
        }
    }

    public static List<Instrument> getClasificationInstruments(int position) {
        switch (position) {
            case 0:
                return SourceOfInstruments.getInstrumentsByClassification(Instrument.InstrumentsClasification.STRING);
            case 1:
                return SourceOfInstruments.getInstrumentsByClassification(Instrument.InstrumentsClasification.PERCUSSION);
            case 2:
                return SourceOfInstruments.getInstrumentsByClassification(Instrument.InstrumentsClasification.WIND);
            default:
                return SourceOfInstruments.getInstrumentsByClassification(Instrument.InstrumentsClasification.ELECTRIC);
        }
    }
}
