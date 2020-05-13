package com.example.covid_19tracker.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.covid_19tracker.R;
import com.example.covid_19tracker.State_Details;
import com.example.covid_19tracker.ui.countries.CovidCountries;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CovidCountryAdapter extends RecyclerView.Adapter<CovidCountryAdapter.ViewHolder> implements Filterable {

    private List<CovidCountries> covidCountriess;
    private List<CovidCountries> covidCountriesFull;
    private Context context;
    private Filter covidCountriesFillter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<CovidCountries> filteredCovidCountries = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredCovidCountries.addAll(covidCountriesFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (CovidCountries itemCovidCountry : covidCountriesFull) {
                    if (itemCovidCountry.getmCOvidCountry().toLowerCase().contains(filterPattern)) {
                        filteredCovidCountries.add(itemCovidCountry);
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredCovidCountries;
            return results;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            covidCountriess.clear();
            covidCountriess.addAll((List) filterResults.values);
            notifyDataSetChanged();

        }
    };

    public CovidCountryAdapter(List<CovidCountries> covidCountries, Context context) {
        this.covidCountriess = covidCountries;
        this.context = context;
        covidCountriesFull = new ArrayList<>(covidCountries);
    }

    @NonNull
    @Override
    public CovidCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_countries_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CovidCountryAdapter.ViewHolder holder, int position) {
        final CovidCountries covidCountries = covidCountriess.get(position);
        holder.Totalcases.setText(String.format("%,d", Integer.valueOf(covidCountries.getmCases())));
        holder.CountryName.setText(covidCountries.getmCOvidCountry());
        holder.TotalDeaths.setText(String.format("%,d", Integer.valueOf(covidCountries.getmDeaths())));
        holder.TotalRecovered.setText(String.format("%,d", Integer.valueOf(covidCountries.getmRecovered())));
        holder.TotalActive.setText(String.format("%,d", Integer.valueOf(covidCountries.getmActive())));
        Glide.with(context).load(covidCountries
                .getmCountryFlag())
                .apply(new RequestOptions().override(240, 240))
                .into(holder.FlagImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, State_Details.class);
                intent.putExtra("CountryName", covidCountries.getmCOvidCountry());
                context.startActivity(intent);
                Animatoo.animateZoom(context);

            }
        });


    }

    @Override
    public int getItemCount() {
        return covidCountriess.size();
    }

    @Override
    public Filter getFilter() {
        return covidCountriesFillter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Totalcases, CountryName, TotalRecovered, TotalDeaths, TotalActive;
        ImageView FlagImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Totalcases = itemView.findViewById(R.id.CasesCountTv);
            CountryName = itemView.findViewById(R.id.CountryNameTv);
            TotalRecovered = itemView.findViewById(R.id.RecoveredCountTv);
            TotalDeaths = itemView.findViewById(R.id.DeathsCountTv);
            FlagImage = itemView.findViewById(R.id.FlagImageView);
            TotalActive = itemView.findViewById(R.id.ActiveCountTv);

        }


    }
}
