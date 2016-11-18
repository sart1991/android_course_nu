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
    private String favoritePressed;
    private ImageButton btnFavorite1;
    private ImageButton btnFavorite2;

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
        btnFavorite1 = (ImageButton) v.findViewById(R.id.image_btn_favorite_1);
        btnFavorite2 = (ImageButton) v.findViewById(R.id.image_btn_favorite_2);
        if (instrument1.isFavorite()){
            modifyFavoriteIcon(btnFavorite1, R.drawable.ic_star_accent_24dp);
        }
        if (instrument2.isFavorite()){
            modifyFavoriteIcon(btnFavorite2, R.drawable.ic_star_black_24dp);
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
                togleFavorite((ImageButton)view, instrument1);
                generatePersonalizedToast("Instrumento: " + instrument1.getName() + favoritePressed);
                break;
            case R.id.image_btn_play_1:
                generatePersonalizedToast("Instrumento: " + instrument1.getName() + " reproduciendo.");
                break;
            case R.id.image_btn_favorite_2:
                togleFavorite((ImageButton)view, instrument2);
                generatePersonalizedToast("Instrumento: " + instrument2.getName() + favoritePressed);
                break;
            case R.id.image_btn_play_2:
                generatePersonalizedToast("Instrumento: " + instrument2.getName() + " reproduciendo.");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.setTitle(R.string.app_name);
        super.onBackPressed();
    }

    private void modifyFavoriteIcon(ImageButton imageButton, int resource) {
        imageButton.setImageResource(resource);
    }

    private void togleFavorite(ImageButton btnFavorite, Instrument instrument) {
        if (instrument.isFavorite()) {
            instrument.setFavorite(false);
            modifyFavoriteIcon(btnFavorite, R.drawable.ic_star_black_24dp);
            favoritePressed = " ya no es favorito.";
        } else {
            instrument.setFavorite(true);
            modifyFavoriteIcon(btnFavorite, R.drawable.ic_star_accent_24dp);
            favoritePressed = " es ahora favorito.";
        }
    }

    private void generatePersonalizedToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    private String getFavoriteString(boolean favorite) {
        if (favorite) {
            return " es ahora favorito.";
        } else {
            return " ya no es favorito.";
        }
    }
}
