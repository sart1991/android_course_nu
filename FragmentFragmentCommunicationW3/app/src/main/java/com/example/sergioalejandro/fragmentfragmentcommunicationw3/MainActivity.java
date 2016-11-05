package com.example.sergioalejandro.fragmentfragmentcommunicationw3;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Communicator{

    private ReceptorFragment receptorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_receptor);
        if (fragment instanceof ReceptorFragment) {
            receptorFragment = (ReceptorFragment) fragment;
        }
    }

    @Override
    public void onClickBtnEnviar(String mensaje) {
        if (receptorFragment != null) {
            receptorFragment.setFragmentReceptorText(mensaje);
        }
    }
}
