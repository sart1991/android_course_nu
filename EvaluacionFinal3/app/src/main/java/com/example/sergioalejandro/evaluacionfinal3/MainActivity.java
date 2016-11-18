package com.example.sergioalejandro.evaluacionfinal3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergioalejandro.evaluacionfinal3.communication.ICommunication;
import com.example.sergioalejandro.evaluacionfinal3.model.Instrument;
import com.example.sergioalejandro.evaluacionfinal3.service.InstrumentsService;

public class MainActivity extends AppCompatActivity implements ICommunication.IInstrumentsList, ICommunication.IInstrumentsDetails, View.OnClickListener{

    private int position = 0;
    private View view;
    private Instrument instrument1;
    private Instrument instrument2;

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
        DetailsClasificationFragment fragment = new DetailsClasificationFragment();
        if(findViewById(R.id.fragment_container) != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null).commit();
        } else {
            onDetailsFragmentViewCreated(view);
        }
    }


    @Override
    public void onDetailsFragmentViewCreated(View v) {
        view = v;
        InstrumentsService.setContext(this);
        ((TextView)v.findViewById(R.id.text_clasification_description)).setText(InstrumentsService.getClasificationContent(position));
        instrument1 = InstrumentsService.getClasificationInstruments(position).get(0);
        instrument2 = InstrumentsService.getClasificationInstruments(position).get(1);
        ((ImageView)v.findViewById(R.id.details_image_view_content_1)).setImageResource(instrument1.getImageSrc());
        ((ImageView)v.findViewById(R.id.details_image_view_content_2)).setImageResource(instrument2.getImageSrc());
        ImageButton btnFavorite1 = (ImageButton) v.findViewById(R.id.image_btn_favorite_1);
        ImageButton btnFavorite2 = (ImageButton) v.findViewById(R.id.image_btn_favorite_2);
        if (instrument1.isFavorite()){
            modifyIcon(btnFavorite1);
        }
        if (instrument2.isFavorite()){
            modifyIcon(btnFavorite2);
        }
        btnFavorite1.setOnClickListener(this);
        btnFavorite2.setOnClickListener(this);
        v.findViewById(R.id.image_btn_play_1).setOnClickListener(this);
        v.findViewById(R.id.image_btn_play_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_btn_favorite_1:
                togleFavorite(instrument1);
                modifyIcon((ImageButton)view);
                generatePersonalizedToast("Instrumento: " + instrument1.getName() + " es ahora favorito.");
                break;
            case R.id.image_btn_play_1:
                break;
            case R.id.image_btn_favorite_2:
                togleFavorite(instrument2);
                modifyIcon((ImageButton)view);
                break;
            case R.id.image_btn_play_2:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.setTitle(R.string.app_name);
        super.onBackPressed();
    }

    private void modifyIcon(ImageButton imageButton) {
        imageButton.setImageResource(R.drawable.ic_star_accent_24dp);
    }

    private void togleFavorite(Instrument instrument) {
        instrument.setFavorite(!instrument.isFavorite());
    }

    private void generatePersonalizedToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }
}
