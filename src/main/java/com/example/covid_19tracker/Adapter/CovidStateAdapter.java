package com.example.covid_19tracker.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid_19tracker.Models.CovidStates;
import com.example.covid_19tracker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CovidStateAdapter extends RecyclerView.Adapter<CovidStateAdapter.ViewHolder> {

    String Phone;
    String country;
    private List<CovidStates> covidStates;
    //private List<CovidStates> covidCountriesFull;
    private Context context;

    public CovidStateAdapter(List<CovidStates> cavedStates, Context context, String country) {
        this.covidStates = cavedStates;
        this.context = context;
        this.country = country;
        //covidCountriesFull = new ArrayList<>(cavedStates);
    }

    @NonNull
    @Override
    public CovidStateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_wise_cardview, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull CovidStateAdapter.ViewHolder holder, int position) {
        final CovidStates covidStates = this.covidStates.get(position);
        if (country.equals("USA")) {
            holder.TotalCases.setText(String.format("%,d", Integer.valueOf(covidStates.getmCases())));
            holder.StateName.setText(covidStates.getmCovidStates());
            holder.TotalDeaths.setText(String.format("%,d", Integer.valueOf(covidStates.getmDeaths())));
            holder.TotalActive.setText(String.format("%,d", Integer.valueOf(covidStates.getmActive())));
            int recovered = Integer.parseInt(covidStates.getmCases()) - Integer.parseInt(covidStates.getmActive()) - Integer.parseInt(covidStates.getmDeaths());
            holder.TotalRecovered.setText("NULL");

        } else if (country.equals("Japan")) {
            holder.TotalCases.setText(String.format("%,d", Integer.valueOf(covidStates.getmCases())));
            holder.StateName.setText(covidStates.getmCovidStates());
            holder.TotalDeaths.setText(String.format("%,d", Integer.valueOf(covidStates.getmDeaths())));
            holder.TotalActive.setText("NULL");
            holder.TotalRecovered.setText("NULL");


        } else {
            holder.TotalCases.setText(String.format("%,d", Integer.valueOf(covidStates.getmCases())));
            holder.StateName.setText(covidStates.getmCovidStates());
            holder.TotalDeaths.setText(String.format("%,d", Integer.valueOf(covidStates.getmDeaths())));
            holder.TotalActive.setText(String.format("%,d", Integer.valueOf(covidStates.getmActive())));
            holder.TotalRecovered.setText(String.format("%,d", Integer.valueOf(covidStates.getmRecovered())));

        }


    }


    @Override
    public int getItemCount() {
        return covidStates.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView TotalCases, TotalRecovered, TotalDeaths, StateName, TotalActive;
        TextView HelplineNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            TotalCases = itemView.findViewById(R.id.CasesCountTv);
            TotalRecovered = itemView.findViewById(R.id.RecoveredCountTv);
            TotalDeaths = itemView.findViewById(R.id.DeathsCountTv);
            StateName = itemView.findViewById(R.id.StateNameTv);
            TotalActive = itemView.findViewById(R.id.ActiveCountTv);


        }


    }
}
