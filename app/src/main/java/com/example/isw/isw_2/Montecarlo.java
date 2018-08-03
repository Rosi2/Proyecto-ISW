package com.example.isw.isw_2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Montecarlo extends AppCompatActivity {

    private static final String TAG = "MonteCarlo";
    private LineChart mcChart;

    private AppCompatEditText EditTextAsset;
    private AppCompatEditText EditTextStake;
    private AppCompatEditText EditTextTime;
    private AppCompatEditText EditTextRisk;
    private AppCompatEditText EditTextVolatility;
    public static int Type;
    Button click2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montecarlo);

        mcChart = (LineChart) findViewById(R.id.LineChart);
//        mcChart.setOnChartGestureListener(Montecarlo.this);
//        mcChart.setOnChartValueSelectedListener(Montecarlo.this);

        mcChart.setDragEnabled(true);
        mcChart.setScaleEnabled(true);

        final TextView ValorFinal = (TextView)findViewById(R.id.DataMC);
        click2 = (Button) findViewById(R.id.Button2);
        EditTextAsset = (AppCompatEditText) findViewById(R.id.Asset);
        EditTextStake = (AppCompatEditText) findViewById(R.id.Stake);
        EditTextTime = (AppCompatEditText) findViewById(R.id.Time);
        EditTextRisk = (AppCompatEditText) findViewById(R.id.r);
        EditTextVolatility = (AppCompatEditText) findViewById(R.id.o);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleP);
        toggle.setText("Put");
        toggle.setTextOff("Put");
        toggle.setTextOn("Call");
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Type = 1;
                } else {
                    Type = 0;
                }
            }
        });


        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double AssetValue = Double.parseDouble(EditTextAsset.getText().toString());
                double StakeValue = Double.parseDouble(EditTextStake.getText().toString());
                double VolatilityValue = Double.parseDouble(EditTextVolatility.getText().toString())*
                        Double.parseDouble(EditTextVolatility.getText().toString());
                double TimeValue = Double.parseDouble(EditTextTime.getText().toString());
                double RiskValue = Double.parseDouble(EditTextRisk.getText().toString());

                formulas calc = new formulas(AssetValue, StakeValue, VolatilityValue, TimeValue, RiskValue);
                if(Type == 0) {
                    ValorFinal.setText("MonteCarlo(Put): " + calc.getMonteCarlo2());
                }
                else {
                    ValorFinal.setText("MonteCarlo(Call): " + calc.getMonteCarlo());
                }

                LineDataSet set1 = new LineDataSet(calc.getMonteCarloGraphA(),"Data Set 1");
                set1.setFillAlpha(110);
                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                dataSets.add(set1);

                LineData data = new LineData(dataSets);

                mcChart.setData(data);

            }
        });

    }
}
