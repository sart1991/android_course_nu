package com.example.sergioalejandro.textinputlayoutexerciseu3w4;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_picker_result);

        final TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.input_layout);
        if (textInputLayout.getEditText() != null) {
            textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (textInputLayout.getEditText().getText().length() > 10) {
                        textInputLayout.setError("This can't be more then 10 characters long");
                    }
                }
            });
        }
    }


    public void onClickDate(View view) {
        DatePickerDialog datePicker = new DatePickerDialog(this, this, 2000, 1, 1);
        datePicker.show();
    }

    public void onClickTime(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 12, 12, false);
        timePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        textView.setText(String.format("%1$04d/%2$02d/%3$02d", year, month, day));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        textView.setText(String.format("%1$02d:%2$02d", hour, minute));
    }
}
