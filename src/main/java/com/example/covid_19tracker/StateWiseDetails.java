package com.example.covid_19tracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.covid_19tracker.Adapter.CovidStateAdapter;
import com.example.covid_19tracker.Models.CovidStates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StateWiseDetails extends AppCompatActivity {
    private static final String TAG = "Constant";
    private RecyclerView recyclerView;
    private Spinner spinner;
    private TextView textView;
    private CovidStateAdapter covidStateAdapter;
    private List<CovidStates> covidStates;
    private ProgressBar StateWiseRecyclerProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_wise_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        InitializeComponents();
        setTitle("State Wise Details");

        setSpinner();
        recyclerView = findViewById(R.id.StateWiseDetailsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void InitializeComponents() {
        spinner = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.StateWiseDetailsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StateWiseRecyclerProgressBar = findViewById(R.id.StateWiseHomeProgressBar);
        textView = findViewById(R.id.TextView);

        covidStates = new ArrayList<>();

    }

    private void setSpinner() {
        List<String> COUNTRIES = new ArrayList<String>();
        COUNTRIES.add("India");
        COUNTRIES.add("USA");
        COUNTRIES.add("Japan");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, COUNTRIES);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    getIndiaData("India");
                } else if (i == 1) {
                    getUSAData("USA");

                } else if (i == 2) {
                    getJapanData("Japan");

                } else if (i == 3) {
                    getFranceData();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getFranceData() {
    }

    private void getJapanData(String country) {


        recyclerView.setVisibility(View.INVISIBLE);
        StateWiseRecyclerProgressBar.setVisibility(View.VISIBLE);
        String url = "https://covid19-japan-web-api.now.sh/api//v1/prefectures";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);

                    try {
                        covidStates.clear();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject stateObject = jsonArray.getJSONObject(i);
                            covidStates.add(new CovidStates(stateObject.getString("name_en"),
                                    stateObject.getString("cases"),
                                    stateObject.getString("pcr"),
                                    stateObject.getString("deaths"),
                                    stateObject.getString("name_ja")));
                        }
                        covidStateAdapter = new CovidStateAdapter(covidStates, StateWiseDetails.this, country);
                        recyclerView.setAdapter(covidStateAdapter);
                        recyclerView.setVisibility(View.VISIBLE);
                        StateWiseRecyclerProgressBar.setVisibility(View.INVISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onResponse: " + error);
            }

        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void getUSAData(String country) {


        recyclerView.setVisibility(View.INVISIBLE);
        StateWiseRecyclerProgressBar.setVisibility(View.VISIBLE);
        String url = "https://corona.lmao.ninja/v2/states?sort&yesterday";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);

                    try {
                        covidStates.clear();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject stateObject = jsonArray.getJSONObject(i);
                            covidStates.add(new CovidStates(stateObject.getString("state"),
                                    stateObject.getString("cases"),
                                    stateObject.getString("tests"),
                                    stateObject.getString("deaths"),
                                    stateObject.getString("active")));
                        }
                        covidStateAdapter = new CovidStateAdapter(covidStates, StateWiseDetails.this, country);
                        recyclerView.setAdapter(covidStateAdapter);
                        recyclerView.setVisibility(View.VISIBLE);
                        StateWiseRecyclerProgressBar.setVisibility(View.INVISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onResponse: " + error);
            }

        });

        Volley.newRequestQueue(this).add(stringRequest);


    }


    private void getIndiaData(String country) {
        recyclerView.setVisibility(View.INVISIBLE);


        StateWiseRecyclerProgressBar.setVisibility(View.VISIBLE);
        String url = "https://api.rootnet.in/covid19-in/unofficial/covid19india.org/statewise";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);

                    try {
                        covidStates.clear();
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONArray StateWiseArray = data.getJSONArray("statewise");
                        for (int i = 0; i < StateWiseArray.length(); i++) {
                            JSONObject tempData = StateWiseArray.getJSONObject(i);

                            covidStates.add(new CovidStates(tempData.getString("state"),
                                    tempData.getString("confirmed"),
                                    tempData.getString("recovered"),
                                    tempData.getString("deaths"),
                                    tempData.getString("active")));
                        }
                        covidStateAdapter = new CovidStateAdapter(covidStates, StateWiseDetails.this, country);
                        recyclerView.setAdapter(covidStateAdapter);
                        recyclerView.setVisibility(View.VISIBLE);
                        StateWiseRecyclerProgressBar.setVisibility(View.INVISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onResponse: " + error);
            }

        });

        Volley.newRequestQueue(this).add(stringRequest);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }


}
