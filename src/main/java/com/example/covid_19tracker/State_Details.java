package com.example.covid_19tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.covid_19tracker.Adapter.CovidStateAdapter;
import com.example.covid_19tracker.Models.CovidStates;
import com.example.covid_19tracker.Models.TimeSeries;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class State_Details extends AppCompatActivity {

    private static final String TAG = State_Details.class.getSimpleName();
    private TextView Cases, Critical, TodayCases, Deaths, TodayDeaths, Active, Recovered, CasesPerOneMillion, DeathsPerOneMillion, CountryName, LastUpdated_time;
    private TextView CvRecovered, CvDeaths, CvMixed;
    private Intent intent;
    private ImageView FlagImageView;
    private String countryName;
    private List<CovidStates> covidStates;
    private CovidStateAdapter covidStateAdapter;
    private ProgressBar BarChartProgressBar, DetailCvProBar;
    private BarChart barChart;
    private CardView RecoveredCv, DeathsCv, ConfirmedCv;
    private String[] urls = {"Confirmed", "Deaths", "Recovered"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state__details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = getIntent();

        countryName = intent.getStringExtra("CountryName");
        setTitle(countryName);


        InitializeComponents();
        setClickListner();
        FillDataInCardView();
        showStateCharts(urls[0]);

    }

    private void setClickListner() {


        ConfirmedCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStateCharts(urls[0]);
            }
        });


        RecoveredCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStateCharts(urls[2]);
            }
        });

        DeathsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStateCharts(urls[1]);
            }
        });
    }


    private String getDate(long millisecond) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd-MM-yyyy hh:mm:ss aaa");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);

        return formatter.format(calendar.getTime());
    }

    private void FillDataInCardView() {

        DetailCvProBar.setVisibility(View.VISIBLE);
        CountryName.setText(countryName);
        String url = "https://corona.lmao.ninja/v2/countries/";
        String FinalUrl = url + countryName;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, FinalUrl, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Active.setText("ACTIVE : " + jsonObject.getString("active"));
                    Cases.setText("CASES : " + jsonObject.getString("cases"));
                    Deaths.setText("DEATHS : " + jsonObject.getString("deaths"));
                    Critical.setText("CRITICAL : " + jsonObject.getString("critical"));
                    Recovered.setText("RECOVERED : " + jsonObject.getString("recovered"));
                    TodayCases.setText("TODAY CASES : " + jsonObject.getString("todayCases"));
                    TodayDeaths.setText("TODAY DEATHS : " + jsonObject.getString("todayDeaths"));
                    CasesPerOneMillion.setText("CASES PER ONE MILLION : " + jsonObject.getString("casesPerOneMillion"));
                    DeathsPerOneMillion.setText("DEATHS PER ONE MILLION : " + jsonObject.getString("deathsPerOneMillion"));
                    LastUpdated_time.setText("Last Updated" + "\n" + getDate(jsonObject.getLong("updated")));

                    JSONObject countryInfo = jsonObject.getJSONObject("countryInfo");
                    Glide.with(State_Details.this).load(countryInfo.getString("flag"))
                            .apply(new RequestOptions().override(240, 240))
                            .into(FlagImageView);

                    DetailCvProBar.setVisibility(View.INVISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error Response", error.toString());
                DetailCvProBar.setVisibility(View.INVISIBLE);

            }

        });

        queue.add(stringRequest);
    }


    private void InitializeComponents() {

        Cases = findViewById(R.id.dc_cases);
        Critical = findViewById(R.id.dc_critical);
        TodayCases = findViewById(R.id.dc_today_cases);
        Deaths = findViewById(R.id.dc_Deaths);
        TodayDeaths = findViewById(R.id.dc_toady_deaths);
        Active = findViewById(R.id.dc_Active);
        Recovered = findViewById(R.id.dc_Recovered);
        CasesPerOneMillion = findViewById(R.id.dc_cases_pom);
        DeathsPerOneMillion = findViewById(R.id.dc_deaths_pom);
        FlagImageView = findViewById(R.id.dc_CountryFlagIv);
        CountryName = findViewById(R.id.dc_CountryNameTv);
        LastUpdated_time = findViewById(R.id.dc_LastUpdated_time);
        covidStates = new ArrayList<>();
        DetailCvProBar = findViewById(R.id.DetailCardViewProgressBar);
        BarChartProgressBar = findViewById(R.id.StateChartProgressBar);
        barChart = findViewById(R.id.StateDetailBarChart);
        RecoveredCv = findViewById(R.id.RecoveredCV);
        DeathsCv = findViewById(R.id.DeathsCV);
        ConfirmedCv = findViewById(R.id.MixedCV);
        CvRecovered = findViewById(R.id.CvRecoverdTv);
        CvDeaths = findViewById(R.id.CvDeathsTv);
        CvMixed = findViewById(R.id.CvMixedTv);


    }


    private void showStateCharts(String type) {

        barChart.setVisibility(View.INVISIBLE);
        BarChartProgressBar.setVisibility(View.VISIBLE);
        String url = "https://disease.sh/v2/historical/" + countryName + "?lastdays=30";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);

                    try {

                        ArrayList<String> datesArrayList = new ArrayList<>();
                        ArrayList<TimeSeries> statusArrayList = new ArrayList<>();

                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject timelineObject = jsonObject.getJSONObject("timeline");
                        JSONObject variableObject;
                        if (type.equals("Confirmed")) {
                            variableObject = timelineObject.getJSONObject("cases");
                        } else if (type.equals("Deaths")) {
                            variableObject = timelineObject.getJSONObject("deaths");
                        } else {
                            variableObject = timelineObject.getJSONObject("recovered");
                        }
                        Iterator iterator = variableObject.keys();
                        while (iterator.hasNext()) {
                            String key = (String) iterator.next();
                            TimeSeries timeSeries = new TimeSeries(variableObject.getInt(key));
                            statusArrayList.add(timeSeries);
                            datesArrayList.add(key);
                        }
                        BarChartProgressBar.setVisibility(View.GONE);
                        displayChart(datesArrayList, statusArrayList, type);
                        barChart.setVisibility(View.VISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(State_Details.this, (CharSequence) e, Toast.LENGTH_SHORT).show();
                        BarChartProgressBar.setVisibility(View.GONE);

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onResponse: " + error);
                Toast.makeText(State_Details.this, "Data Not Retrieved : " + error, Toast.LENGTH_SHORT).show();
                BarChartProgressBar.setVisibility(View.INVISIBLE);

            }

        });

        Volley.newRequestQueue(this).

                add(stringRequest);


    }


    private void displayChart(ArrayList<String> datesArrayList, ArrayList<TimeSeries> statusArrayList, String type) {

        ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
        for (int i = 0; i < datesArrayList.size(); i++) {
            barEntryArrayList.add(new BarEntry(statusArrayList.get(i).getValue(), i));
        }
        BarDataSet barDataSet = new BarDataSet(barEntryArrayList, type);

        if (type.equals("Recovered")) {
            barDataSet.setColor(Color.GREEN);
        } else if (type.equals("Deaths")) {
            barDataSet.setColor(Color.RED);
        } else {
            barDataSet.setColor(Color.DKGRAY);
        }


        BarData barData = new BarData(datesArrayList, barDataSet);
        barChart.setData(barData);
        barChart.animateY(3000);


    }


    /**
     * private void showMixedChart(String type) {
     * <p>
     * barChart.setVisibility(View.INVISIBLE);
     * BarChartProgressBar.setVisibility(View.VISIBLE);
     * String url = "https://covid2019-api.herokuapp.com/v2/timeseries/" + type;
     * <p>
     * StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
     *
     * @Override public void onResponse(String response) {
     * if (response != null) {
     * Log.e(TAG, "onResponse: " + response);
     * <p>
     * try {
     * <p>
     * ArrayList<String> RecoveredDatesArrayList = new ArrayList<>();
     * ArrayList<String> DeathsDatesArrayList = new ArrayList<>();
     * ArrayList<String> ConfirmedDatesArrayList = new ArrayList<>();
     * ArrayList<TimeSeries> RecoveredStatusArrayList = new ArrayList<>();
     * ArrayList<TimeSeries> DeathsStatusArrayList = new ArrayList<>();
     * ArrayList<TimeSeries> ConfirmedStatusArrayList = new ArrayList<>();
     * <p>
     * JSONObject jsonObject = new JSONObject(response);
     * JSONArray jsonArray = jsonObject.getJSONArray("data");
     * for (int i = 0; i < jsonArray.length(); i++) {
     * JSONObject stateObject = jsonArray.getJSONObject(i);
     * if (countryName.equals(stateObject.getString("Country/Region"))) {
     * JSONArray timeSeriesArray = stateObject.getJSONArray("TimeSeries");
     * for (int j = 0; j < timeSeriesArray.length(); j++) {
     * JSONObject timeSeriesObject = timeSeriesArray.getJSONObject(j);
     * <p>
     * int confirmed = timeSeriesObject.getInt("value");
     * String date = timeSeriesObject.getString("date");
     * TimeSeries timeSeries = new TimeSeries(confirmed);
     * if (type.equals("Confirmed")) {
     * ConfirmedStatusArrayList.add(timeSeries);
     * ConfirmedDatesArrayList.add(date);
     * } else if (type.equals("Deaths")) {
     * DeathsStatusArrayList.add(timeSeries);
     * DeathsDatesArrayList.add(date);
     * } else {
     * RecoveredStatusArrayList.add(timeSeries);
     * RecoveredDatesArrayList.add(date);
     * }
     * <p>
     * BarChartProgressBar.setVisibility(View.GONE);
     * <p>
     * <p>
     * }
     * displayMixedChart(ConfirmedDatesArrayList, ConfirmedStatusArrayList, RecoveredDatesArrayList, RecoveredStatusArrayList, DeathsDatesArrayList, DeathsStatusArrayList, type);
     * barChart.setVisibility(View.VISIBLE);
     * <p>
     * }
     * }
     * <p>
     * } catch (JSONException e) {
     * e.printStackTrace();
     * Toast.makeText(State_Details.this, (CharSequence) e, Toast.LENGTH_SHORT).show();
     * BarChartProgressBar.setVisibility(View.GONE);
     * <p>
     * }
     * }
     * <p>
     * }
     * }, new Response.ErrorListener() {
     * @Override public void onErrorResponse(VolleyError error) {
     * Log.e(TAG, "onResponse: " + error);
     * Toast.makeText(State_Details.this, "Data Not Retrieved", Toast.LENGTH_SHORT).show();
     * BarChartProgressBar.setVisibility(View.INVISIBLE);
     * <p>
     * }
     * <p>
     * });
     * <p>
     * Volley.newRequestQueue(this).add(stringRequest);
     * <p>
     * }
     * <p>
     * private void displayMixedChart(ArrayList<String> confirmedDatesArrayList, ArrayList<TimeSeries> confirmedStatusArrayList, ArrayList<String> recoveredDatesArrayList, ArrayList<TimeSeries> recoveredStatusArrayList, ArrayList<String> deathsDatesArrayList, ArrayList<TimeSeries> deathsStatusArrayList, String type) {
     * ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
     * for (int i = 0; i < confirmedDatesArrayList.size(); i++) {
     * barEntryArrayList.add(new BarEntry(confirmedStatusArrayList.get(i).getValue(), i));
     * }
     * BarDataSet ConfirmedBarDataSet = new BarDataSet(barEntryArrayList, type);
     * <p>
     * for (int i = 0; i < recoveredDatesArrayList.size(); i++) {
     * barEntryArrayList.add(new BarEntry(recoveredStatusArrayList.get(i).getValue(), i));
     * }
     * BarDataSet RecoveredBarDataSet = new BarDataSet(barEntryArrayList, type);
     * <p>
     * for (int i = 0; i < deathsDatesArrayList.size(); i++) {
     * barEntryArrayList.add(new BarEntry(deathsStatusArrayList.get(i).getValue(), i));
     * }
     * BarDataSet DeathsBarDataSet = new BarDataSet(barEntryArrayList, type);
     * <p>
     * <p>
     * BarData barData = new BarData(confirmedDatesArrayList, ConfirmedBarDataSet);
     * barData.addDataSet(RecoveredBarDataSet);
     * barData.addDataSet(DeathsBarDataSet);
     * barChart.setData(barData);
     * <p>
     * barChart.animateY(3000);
     * <p>
     * <p>
     * }
     */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateZoom(this);
    }

}
