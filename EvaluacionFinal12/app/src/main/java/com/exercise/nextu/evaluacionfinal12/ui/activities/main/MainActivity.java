package com.exercise.nextu.evaluacionfinal12.ui.activities.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.exercise.nextu.evaluacionfinal12.R;
import com.exercise.nextu.evaluacionfinal12.data.models.DayOfWeek;
import com.exercise.nextu.evaluacionfinal12.ui.adapters.DayOfWeekSpinnerAdapter;
import com.exercise.nextu.evaluacionfinal12.utils.DaysOfWeekUtil;
import com.exercise.nextu.evaluacionfinal12.utils.Util;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainViewModel viewModel;

    private HorizontalBarChart barChart;
    private Spinner spinnerDays, spinnerMovement;
    private DayOfWeekSpinnerAdapter spinnerAdapter;
    private FloatingActionButton fab;
    private EditText editValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        setUp();
    }

    private void setUp() {
        bindViews();
        initialViewsState();
        setUpInitialData();
    }

    private void bindViews() {
        barChart = findViewById(R.id.hBarChart_mainActivity_chart);
        spinnerDays = findViewById(R.id.spinner_mainActivity_dayOfWeek);
        spinnerAdapter = new DayOfWeekSpinnerAdapter(this);
        spinnerMovement = findViewById(R.id.spinner_mainActivity_typeOfMovement);
        fab = findViewById(R.id.fab);
        TextInputLayout tilValue = findViewById(R.id.til_mainActivity_value);
        editValue = tilValue.getEditText();
    }

    private void initialViewsState() {
        IAxisValueFormatter xAxisFormatter = new BarChartFormatter(this);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);
        YAxis yAxisL = barChart.getAxisLeft();
        yAxisL.setAxisMinimum(0);
        yAxisL.setAxisMaximum(1000000);
        YAxis yAxisR = barChart.getAxisRight();
        yAxisR.setEnabled(false);
        barChart.setData(viewModel.getBarData());
        spinnerDays.setAdapter(spinnerAdapter);
        ArrayAdapter<CharSequence> movementAdapter = ArrayAdapter
                .createFromResource(this, R.array.type_of_movement,
                        android.R.layout.simple_spinner_item);
        movementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMovement.setAdapter(movementAdapter);
        fab.setOnClickListener(onFabClick);
    }

    private void setUpInitialData() {
        Log.i(TAG, "setUpInitialData");

        viewModel.getLiveDaysOfWeek().observe(this, new Observer<List<DayOfWeek>>() {
            @Override
            public void onChanged(@Nullable List<DayOfWeek> daysOfWeek) {
                Log.i(TAG, "onChanged: daysOfWeek List " + daysOfWeek);
                if (daysOfWeek == null) return;
                List<BarEntry> entriesFromDays = new ArrayList<>();
                for (DayOfWeek d : daysOfWeek) {
                    float position = d.getId() + 1;
                    entriesFromDays.add(new BarEntry(position, d.getBalance()));
                }
                BarDataSet set = (BarDataSet) viewModel.getBarData().getDataSetByIndex(0);
                set.setValues(entriesFromDays);

                // Erase everything above
                barChart.notifyDataSetChanged();
                barChart.invalidate();
                spinnerAdapter.setDaysOfWeek(daysOfWeek);
                spinnerAdapter.notifyDataSetChanged();
            }
        });
    }

    private View.OnClickListener onFabClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DayOfWeek dayOfWeek = (DayOfWeek) spinnerDays.getSelectedItem();
            String typeOfMovement = (String) spinnerMovement.getSelectedItem();
            String valueString = editValue.getText().toString();
            boolean validValue = Util.isNumeric(valueString);
            if (validValue) {
                int value = Integer.parseInt(valueString);
                if (typeOfMovement.equals(getString(R.string._egress))) {
                    dayOfWeek.setBalance(dayOfWeek.getBalance() - value);
                } else {
                    dayOfWeek.setBalance(dayOfWeek.getBalance() + value);
                }
                viewModel.dayOfWeekChanged(dayOfWeek);
            } else {
                showSnackError(R.string.mainActivity_messageError_invalidValue);
            }
        }
    };

    private void showSnackError(int resMessage) {
        showSnackError(getString(resMessage));
    }

    private void showSnackError(String message) {
        showSnackBar(message, android.R.color.holo_red_dark);
    }

    private void showSnackBar(String message, int resColor) {
        Snackbar snack = Snackbar.make(fab, message, Snackbar.LENGTH_LONG);
        snack.getView().setBackgroundResource(resColor);
        snack.show();
    }
}
