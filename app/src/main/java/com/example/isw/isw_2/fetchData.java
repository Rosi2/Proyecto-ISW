package com.example.isw.isw_2;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class fetchData extends AsyncTask<Void,Void,Void> {




    String data = "";
    String MetaData = "";
    String TimeSeries = "";
    String TimeSeriesQuery = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+ listofAssets.StockName + "&outputsize="+ listofAssets.DataSize +"&apikey=8QV3FD6AP6MIDLX5");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line!=null){
                line = bufferedReader.readLine();
                data = data+line;

            }

            JSONObject JO = new JSONObject(data).getJSONObject("Time Series (Daily)");
            Iterator<?> keys = JO.keys();

            while(keys.hasNext()){
                String key = (String)keys.next();
                if ( JO.get(key) instanceof JSONObject ) {
                    MetaData = key;
                    JSONObject xx = new JSONObject(JO.get(key).toString());
                    TimeSeries = xx.getString("1. open") + "  " + xx.getString("2. high") + "  " + xx.getString("3. low") + "  " + xx.getString("4. close")/* + "  " + xx.getString("5. volume")*/ + "\n";
                    TimeSeriesQuery = TimeSeriesQuery + MetaData + ":  "  + TimeSeries;
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        listofAssets.data.setText(this.TimeSeriesQuery);
    }
}
