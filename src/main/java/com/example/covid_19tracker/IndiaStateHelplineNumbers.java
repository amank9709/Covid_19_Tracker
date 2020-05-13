package com.example.covid_19tracker;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.covid_19tracker.Adapter.MyExpandableListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class IndiaStateHelplineNumbers extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private String TAG = "HEllo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_helpline_numbers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("State Helpline Numbers");
        expandableListView = findViewById(R.id.expandableListView);

        FetchNumbers();


    }

    private void FetchNumbers() {

        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        List<String> HelplineNumbers = new ArrayList<>();
        String url = "https://api.rootnet.in/covid19-in/contacts/Regional";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.d(TAG, "onResponse: " + response);


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject dataObject = jsonObject.getJSONObject("data");
                        JSONObject contactsObject = dataObject.getJSONObject("contacts");

                        JSONArray regionalArray = contactsObject.getJSONArray("regional");
                        for (int i = 0; i < regionalArray.length(); i++) {
                            JSONObject numberObject = regionalArray.getJSONObject(i);
                            listDataHeader.add(numberObject.getString("loc"));
                            HelplineNumbers.add(numberObject.getString("number"));
                        }
                        for (int j = 0; j < HelplineNumbers.size(); j++) {
                            listHash.put(listDataHeader.get(j), Collections.singletonList(HelplineNumbers.get(j)));

                        }

                        MyExpandableListAdapter myExpandableListAdapter = new MyExpandableListAdapter(IndiaStateHelplineNumbers.this, listDataHeader, listHash);
                        expandableListView.setAdapter(myExpandableListAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();


                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(IndiaStateHelplineNumbers.this, "Error : " + error, Toast.LENGTH_SHORT).show();

            }

        });

        Volley.newRequestQueue(IndiaStateHelplineNumbers.this).add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
