package com.exercises.sart1991.exbiografiadejugadores;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText fullName;
    private EditText country;
    private EditText club;
    private EditText age;
    private TextView txtFullName;
    private TextView txtCountry;
    private TextView txtClub;
    private TextView txtAge;
    private Button btnSubmit;
    private Spinner spinnerBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View spinner = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner, toolbar, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinner, lp);

        fullName = (EditText) findViewById(R.id.edit_full_name);
        country = (EditText) findViewById(R.id.edit_country);
        club = (EditText) findViewById(R.id.edit_club);
        age = (EditText) findViewById(R.id.edit_age);
        txtFullName = (TextView) findViewById(R.id.txt_full_name);
        txtCountry = (TextView) findViewById(R.id.txt_country);
        txtClub = (TextView) findViewById(R.id.txt_club);
        txtAge = (TextView) findViewById(R.id.txt_age);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtFullName.setText(fullName.getText());
                txtCountry.setText(country.getText());
                txtClub.setText(club.getText());
                txtAge.setText(age.getText());
            }
        });


        spinnerBar = (Spinner) spinner.findViewById(R.id.toolbar_spinner);
        final String[] certificacion = {"Certificaciones", "Android", "iOS", "Web"};
        MySpinnerAdapter adapter = new MySpinnerAdapter(this, certificacion);
        spinnerBar.setAdapter(adapter);
    }
}

class MySpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] list;

    MySpinnerAdapter(Context context, String[] list) {
        super(context, R.layout.toolbar_spinner_item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.toolbar_spinner_item, null);
        }

        TextView spinText = (TextView) convertView.findViewById(R.id.txt_spinner);
        spinText.setText(getItem(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.toolbar_spinner_item_dropdown, null);
        }

        TextView spinText = (TextView) convertView.findViewById(R.id.txt_spinner);
        spinText.setText(getItem(position));
        return convertView;
    }
}
