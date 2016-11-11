package com.example.sergioalejandro.evaluacionfinal3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergioalejandro.evaluacionfinal3.communication.ICommunication;
import com.example.sergioalejandro.evaluacionfinal3.model.Instrument;
import com.example.sergioalejandro.evaluacionfinal3.service.InstrumentsService;

public class MainActivity extends AppCompatActivity implements ICommunication.IInstrumentsList, ICommunication.IInstrumentsDetails{

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InstrumentsService.setContext(this);
        if (findViewById(R.id.fragment_container) != null) {
            ListInstrumentsFragment fragment = new ListInstrumentsFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    public void onInstrumentSelected(int position) {
        this.position = position;
        this.setTitle(InstrumentsService.getClasificationTitle(position));
        if(findViewById(R.id.fragment_container) != null) {
            DetailsClasificationFragment fragment = new DetailsClasificationFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }


    @Override
    public void onDetailsFragmentViewCreated(View v) {
        ((TextView)v.findViewById(R.id.text_clasification_description)).setText(InstrumentsService.getClasificationContent(position));
        Instrument instrument1 = InstrumentsService.getClasificationInstruments(position).get(0);
        Instrument instrument2 = InstrumentsService.getClasificationInstruments(position).get(1);
        ((ImageView)v.findViewById(R.id.details_image_view_content_1)).setImageResource(instrument1.getImageSrc());
        ((ImageView)v.findViewById(R.id.details_image_view_content_2)).setImageResource(instrument2.getImageSrc());
        if (instrument1.isFavorite()){
            ((ImageButton)v.findViewById(R.id.image_btn_favorite_1)).setImageResource(R.drawable.ic_star_accent_24dp);
        }
        if (instrument2.isFavorite()){
            ((ImageButton)v.findViewById(R.id.image_btn_favorite_2)).setImageResource(R.drawable.ic_star_accent_24dp);
        }
    }
}
