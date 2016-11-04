package com.exercise.nextu.activityfragmentcommunicationw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private MensajeFragment mensajeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit_mensaje);
        mensajeFragment = (MensajeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_mensaje);
    }

    public void onClickBtnMensaje(View view) {
        String mensaje = editText.getText().toString();
        mensajeFragment.getTxtMensaje().setText(mensaje);
    }
}
