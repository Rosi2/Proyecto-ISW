package com.example.isw.isw_2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class listofAssets extends AppCompatActivity {

    Button click;
    public static TextView data;
    private AppCompatEditText mDisplayName;
    private AppCompatEditText mDisplayDataSize;
    public static String StockName;
    public static String DataSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        click = (Button) findViewById(R.id.Button1);
        data = (TextView) findViewById(R.id.Data1);

        mDisplayName = (AppCompatEditText) findViewById(R.id.stock_name);
        mDisplayDataSize = (AppCompatEditText) findViewById(R.id.size);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData process = new fetchData();
                process.execute();
                StockName = mDisplayName.getText().toString();
                DataSize = mDisplayDataSize.getText().toString();
            }
        });

    }
}
