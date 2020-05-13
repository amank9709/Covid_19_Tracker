package com.example.covid_19tracker.ui.countries;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19tracker.Adapter.CovidCountryAdapter;
import com.example.covid_19tracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

public class CountriesFragment extends Fragment {

    private static final String TAG = CountriesFragment.class.getSimpleName();
    CovidCountryAdapter covidCountryAdapter;
    private RecyclerView recyclerView;
    private List<CovidCountries> covidCountriess;
    private ProgressBar progressBar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_countries, container, false);
        setHasOptionsMenu(true);

        progressBar = root.findViewById(R.id.CountryPageProgressBar);
        recyclerView = root.findViewById(R.id.CountriesRecyclerView);
        recyclerView.setItemAnimator(new ScaleInBottomAnimator());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        covidCountriess = new ArrayList<>();

        getDataFromServer(4);
        return root;
    }

    private void showRecyclerView() {

        covidCountryAdapter = new CovidCountryAdapter(covidCountriess, getActivity());
        recyclerView.setAdapter(covidCountryAdapter);
    }

    private void getDataFromServer(int i) {

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

        String url = "https://corona.lmao.ninja/v2/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    covidCountriess.clear();

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            JSONObject countryInfo = data.getJSONObject("countryInfo");
                            covidCountriess.add(new CovidCountries(data.getString("country"),
                                    data.getString("cases"),
                                    data.getString("deaths"),
                                    data.getString("recovered"),
                                    countryInfo.getString("flag"),
                                    data.getString("active")));
                        }

                        if (i == 0) {
                            Collections.sort(covidCountriess, CovidCountries.BY_NAME_DESCENDING);

                        } else if (i == 1) {
                            Collections.sort(covidCountriess, CovidCountries.BY_MOST_AFFECTED);

                        } else if (i == 2) {
                            Collections.sort(covidCountriess, CovidCountries.BY_MOST_DEATHS);

                        } else if (i == 3) {
                            Collections.sort(covidCountriess, CovidCountries.BY_MOST_RECOVERED);

                        } else {
                            Collections.sort(covidCountriess, CovidCountries.BY_NAME_ASCENDING);

                        }
                        showRecyclerView();
                        recyclerView.setVisibility(View.VISIBLE);

                        progressBar.setVisibility(View.GONE);

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

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItem sortData = menu.findItem(R.id.SortMenu);
        sortData.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                showMenuDialog();
                return false;
            }
        });
        SearchView searchView = new SearchView(getActivity());
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (covidCountryAdapter != null) {
                    covidCountryAdapter.getFilter().filter(s);
                }
                return true;
            }
        });

        searchItem.setActionView(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void showMenuDialog() {

        String[] options = {"Sort by Name", "Mostly Affected", "Most Deaths", "Most Recovered"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Sort By");
        dialog.setIcon(R.drawable.iconfinder_globe);
        dialog.setItems(options, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getDataFromServer(i);

            }
        });

        dialog.show();


    }
}
