package com.example.covid_19tracker.ui.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.covid_19tracker.Models.TimeSeries;
import com.example.covid_19tracker.R;
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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {


    private static final String TAG = HomeFragment.class.getSimpleName();
    View root;
    private TextView India_Total_Case, India_Total_Recovered, India_Total_Deaths, LastUpdatesIndia;
    private TextView Global_Total_Case, Global_Total_Recovered, Global_Total_Deaths, LastUpdatedGlobal;
    private ProgressBar GlobalCountPB, IndiaCountPB, ChartProgressBar;
    private CardView ConfirmedCvGb, RecoveredCvGb, DeathsCvGb, ConfirmedCv, RecoveredCv, DeathsCv;
    private String[] urls = {"Confirmed", "Deaths", "Recovered"};
    private String[] country = {"all", "India"};
    private BarChart barChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);
        InitializeComponents();
        setClickListners();
        getGlobalData();
        getIndiaData();
        showGlobalChart(urls[0], country[0]);


        return root;
    }

    private void setClickListners() {

        ConfirmedCvGb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGlobalChart(urls[0], country[0]);
            }
        });


        RecoveredCvGb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGlobalChart(urls[2], country[0]);
            }
        });

        DeathsCvGb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGlobalChart(urls[1], country[0]);
            }
        });

        DeathsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGlobalChart(urls[1], country[1]);
            }
        });

        ConfirmedCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGlobalChart(urls[0], country[1]);
            }
        });

        RecoveredCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGlobalChart(urls[2], country[1]);
            }
        });
    }

    private void InitializeComponents() {

        India_Total_Case = root.findViewById(R.id.IndiaTotalCasesTV);
        India_Total_Recovered = root.findViewById(R.id.IndiaTotalRecoveredTV);
        India_Total_Deaths = root.findViewById(R.id.IndiaTotalDeathTV);
        Global_Total_Case = root.findViewById(R.id.TotalCasesTV);
        Global_Total_Recovered = root.findViewById(R.id.TotalRecoveredTV);
        IndiaCountPB = root.findViewById(R.id.IndiaCountHomeProgressBar);
        GlobalCountPB = root.findViewById(R.id.GlobalCountHomeProgressBar);
        Global_Total_Deaths = root.findViewById(R.id.TotalDeathTV);
        barChart = root.findViewById(R.id.HomePageBarChart);
        ChartProgressBar = root.findViewById(R.id.HomePageChartProgressBar);
        ConfirmedCvGb = root.findViewById(R.id.ConfirmedCvGb);
        RecoveredCvGb = root.findViewById(R.id.RecoveredCVGb);
        DeathsCvGb = root.findViewById(R.id.DeathsCvGb);
        ConfirmedCv = root.findViewById(R.id.ConfirmedCvv);
        RecoveredCv = root.findViewById(R.id.RecoveredCvv);
        DeathsCv = root.findViewById(R.id.DeathsCv);


        LastUpdatedGlobal = root.findViewById(R.id.LastUpdateTimeGlobal);
        LastUpdatesIndia = root.findViewById(R.id.LastUpdateTimeIndia);
    }


    private String getDate(long millisecond) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd-MM-yyyy hh:mm:ss aaa");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);

        return formatter.format(calendar.getTime());
    }

    private void getIndiaData() {

        IndiaCountPB.setVisibility(View.VISIBLE);
        SetIndiaInvisible();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/countries/india";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    India_Total_Case.setText(String.format("%,d", jsonObject.getInt("cases")));
                    India_Total_Recovered.setText(String.format("%,d", jsonObject.getInt("recovered")));
                    India_Total_Deaths.setText(String.format("%,d", jsonObject.getInt("deaths")));
                    LastUpdatesIndia.setText("Last Updated : " + getDate(jsonObject.getLong("updated")));
                    IndiaCountPB.setVisibility(View.INVISIBLE);

                    SetIndiaVisible();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error Response", error.toString());
                IndiaCountPB.setVisibility(View.INVISIBLE);

            }

        });

        queue.add(stringRequest);
    }

    private void getGlobalData() {

        GlobalCountPB.setVisibility(View.VISIBLE);
        SetGlobalInvisible();

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Global_Total_Case.setText(String.format("%,d", jsonObject.getInt("cases")));
                    Global_Total_Recovered.setText(String.format("%,d", jsonObject.getInt("recovered")));
                    Global_Total_Deaths.setText(String.format("%,d", jsonObject.getInt("deaths")));
                    LastUpdatedGlobal.setText("Last Updated : " + getDate(jsonObject.getLong("updated")));
                    GlobalCountPB.setVisibility(View.INVISIBLE);
                    SetGlobalVisible();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error Response", error.toString());
                GlobalCountPB.setVisibility(View.INVISIBLE);

            }

        });

        queue.add(stringRequest);
    }


    private void showGlobalChart(String type, String s) {

        barChart.setVisibility(View.INVISIBLE);
        ChartProgressBar.setVisibility(View.VISIBLE);
        String url = "https://disease.sh/v2/historical/" + s + "?lastdays=30";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);

                    try {

                        ArrayList<String> datesArrayList = new ArrayList<>();
                        ArrayList<TimeSeries> statusArrayList = new ArrayList<>();

                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject variableObject;
                        if (s.equals("all")) {
                            if (type.equals("Confirmed")) {
                                variableObject = jsonObject.getJSONObject("cases");
                            } else if (type.equals("Deaths")) {
                                variableObject = jsonObject.getJSONObject("deaths");
                            } else {
                                variableObject = jsonObject.getJSONObject("recovered");
                            }

                        } else {
                            JSONObject timelineObject = jsonObject.getJSONObject("timeline");
                            if (type.equals("Confirmed")) {
                                variableObject = timelineObject.getJSONObject("cases");
                            } else if (type.equals("Deaths")) {
                                variableObject = timelineObject.getJSONObject("deaths");
                            } else {
                                variableObject = timelineObject.getJSONObject("recovered");
                            }
                        }


                        Iterator iterator = variableObject.keys();
                        while (iterator.hasNext()) {
                            String key = (String) iterator.next();
                            TimeSeries timeSeries = new TimeSeries(variableObject.getInt(key));
                            statusArrayList.add(timeSeries);
                            datesArrayList.add(key);
                        }
                        ChartProgressBar.setVisibility(View.GONE);
                        displayChart(datesArrayList, statusArrayList, type);
                        barChart.setVisibility(View.VISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        ChartProgressBar.setVisibility(View.GONE);

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onResponse: " + error);
                Toast.makeText(getActivity(), "Data Not Retrieved : " + error, Toast.LENGTH_SHORT).show();
                ChartProgressBar.setVisibility(View.INVISIBLE);

            }

        });

        Volley.newRequestQueue(getActivity()).add(stringRequest);


    }

    private void displayChart(ArrayList<String> datesArrayList, ArrayList<TimeSeries> statusArrayList, String type) {

        ArrayList<BarEntry> BarEntryArrayList = new ArrayList<>();

        for (int i = 0; i < datesArrayList.size(); i++) {
            BarEntryArrayList.add(new BarEntry(statusArrayList.get(i).getValue(), i));

        }
        BarDataSet barDataSet = new BarDataSet(BarEntryArrayList, type);

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


    public void SetGlobalInvisible() {


        ConfirmedCvGb.setVisibility(View.INVISIBLE);
        RecoveredCvGb.setVisibility(View.INVISIBLE);
        DeathsCvGb.setVisibility(View.INVISIBLE);
        LastUpdatedGlobal.setVisibility(View.INVISIBLE);

    }

    public void SetIndiaInvisible() {

        ConfirmedCv.setVisibility(View.INVISIBLE);
        RecoveredCv.setVisibility(View.INVISIBLE);
        DeathsCv.setVisibility(View.INVISIBLE);
        LastUpdatesIndia.setVisibility(View.INVISIBLE);


    }

    public void SetGlobalVisible() {
        ConfirmedCvGb.setVisibility(View.VISIBLE);
        RecoveredCvGb.setVisibility(View.VISIBLE);
        DeathsCvGb.setVisibility(View.VISIBLE);
        LastUpdatedGlobal.setVisibility(View.VISIBLE);


    }

    public void SetIndiaVisible() {
        ConfirmedCv.setVisibility(View.VISIBLE);
        RecoveredCv.setVisibility(View.VISIBLE);
        DeathsCv.setVisibility(View.VISIBLE);
        LastUpdatesIndia.setVisibility(View.VISIBLE);


    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        infoItem.setOnMenuItemClickListener(menuItem -> {

            ShowInfoDialog();

            return false;
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void ShowInfoDialog() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setTitle("Select Content Language");

        dialog.setContentView(R.layout.information_dialog);
        //  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView textView = dialog.findViewById(R.id.text);

        textView.setText(R.string.Important);
        ImageView button = dialog.findViewById(R.id.CloseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }


}