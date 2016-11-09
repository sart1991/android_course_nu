package com.example.sergioalejandro.evaluacionfinal3.resources;

import android.content.Context;

import com.example.sergioalejandro.evaluacionfinal3.R;
import com.example.sergioalejandro.evaluacionfinal3.model.Instrument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/9/2016.
 */

public class SourceOfInstruments {

    private static Context gContext;
    private static Instrument stringGuitar;
    private static Instrument stringViolin;
    private static Instrument percussionPanderet;
    private static Instrument percussionCajon;
    private static Instrument windSaxo;
    private static Instrument windTrumpet;
    private static Instrument electricGuitar;
    private static Instrument electricBass;

    public static void setContext(Context context) {

        gContext = context;
        stringGuitar = new Instrument(getStringFromResource(R.string.string_guitar_name), Instrument.InstrumentsClasification.STRING, R.drawable.string_guitar);
        stringViolin = new Instrument(getStringFromResource(R.string.string_violin_name), Instrument.InstrumentsClasification.STRING, R.drawable.string_violin);
        percussionPanderet = new Instrument(getStringFromResource(R.string.percussion_panderet_name), Instrument.InstrumentsClasification.PERCUSSION, R.drawable.percussion_panderet);
        percussionCajon = new Instrument(getStringFromResource(R.string.percussion_cajon_name), Instrument.InstrumentsClasification.PERCUSSION, R.drawable.percussion_cajon);
        windSaxo = new Instrument(getStringFromResource(R.string.wind_saxo_name), Instrument.InstrumentsClasification.WIND, R.drawable.wind_saxo);
        windTrumpet = new Instrument(getStringFromResource(R.string.wind_trumpet_name), Instrument.InstrumentsClasification.WIND, R.drawable.wind_trumpet);
        electricGuitar = new Instrument(getStringFromResource(R.string.electric_guitar_name), Instrument.InstrumentsClasification.ELECTRIC, R.drawable.electric_guitar);
        electricBass = new Instrument(getStringFromResource(R.string.electric_bass_name), Instrument.InstrumentsClasification.ELECTRIC, R.drawable.electric_bass);
    }

    public static List<Instrument> getInstrumentByClassification(Instrument.InstrumentsClasification clasification) {
        List<Instrument> list = new ArrayList<>();
        if (gContext != null) {
            for (Instrument instrument : allInstruments()) {
                if (instrument.getClasification() == clasification)
                    list.add(instrument);
            }
        }
        return list;
    }

    private static String getStringFromResource(int id) {
        return gContext.getResources().getString(id);
    }

    private static Instrument[] allInstruments() {
        return new Instrument[]
                {
                        stringGuitar, stringViolin, percussionPanderet, percussionCajon,
                        windSaxo, windTrumpet, electricGuitar, electricBass
                };
    }
}
