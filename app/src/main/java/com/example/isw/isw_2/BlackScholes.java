package com.example.isw.isw_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class BlackScholes extends AppCompatActivity {

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
        setContentView(R.layout.activity_blackscholes);

        final TextView ValorFinal = (TextView)findViewById(R.id.DataBS);
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
                double VolatilityValue = Double.parseDouble(EditTextVolatility.getText().toString());
                double TimeValue = Double.parseDouble(EditTextTime.getText().toString());
                double RiskValue = Double.parseDouble(EditTextRisk.getText().toString());

                formulas calc = new formulas(AssetValue, StakeValue, VolatilityValue, TimeValue, RiskValue);
                if(Type == 0) {
                    ValorFinal.setText("BlackScholes(Put): " + calc.getBlackScholesSolutionP());
                }
                else {
                    ValorFinal.setText("BlackScholes(Call): " + calc.getBlackScholesSolutionC());
                }
            }
        });

    }

}