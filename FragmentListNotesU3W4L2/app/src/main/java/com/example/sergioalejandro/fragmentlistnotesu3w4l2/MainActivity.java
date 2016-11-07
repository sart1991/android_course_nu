package com.example.sergioalejandro.fragmentlistnotesu3w4l2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sergioalejandro.fragmentlistnotesu3w4l2.interfacecommunication.ICommunication;

public class MainActivity extends AppCompatActivity implements ICommunication.INoteList {

    private EditText fragmentEditTitle;
    private EditText fragmentEditContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.view_fragment_list_notes) == null)
            getFragmentManager().beginTransaction()
            .add(R.id.container_for_fragments, new ListNotesFragment())
            .commit();
    }

    @Override
    public void noteSelected(int position) {
        Toast.makeText(this, "Note: " + position, Toast.LENGTH_LONG).show();
    }
}
