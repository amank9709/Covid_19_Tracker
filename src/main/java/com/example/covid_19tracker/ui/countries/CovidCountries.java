package com.example.covid_19tracker.ui.countries;

import java.util.Comparator;

public class CovidCountries {


    public static final Comparator<CovidCountries> BY_NAME_ASCENDING = new Comparator<CovidCountries>() {
        @Override
        public int compare(CovidCountries covidCountries, CovidCountries t1) {
            return covidCountries.getmCOvidCountry().compareTo(t1.getmCOvidCountry());
        }
    };
    public static final Comparator<CovidCountries> BY_NAME_DESCENDING = new Comparator<CovidCountries>() {
        @Override
        public int compare(CovidCountries covidCountries, CovidCountries t1) {
            return t1.getmCOvidCountry().compareTo(covidCountries.getmCOvidCountry());
        }
    };
    public static final Comparator<CovidCountries> BY_MOST_AFFECTED = new Comparator<CovidCountries>() {
        @Override
        public int compare(CovidCountries covidCountries, CovidCountries t1) {
            return Integer.compare(Integer.parseInt(t1.getmCases()), Integer.parseInt(covidCountries.getmCases()));
        }
    };
    public static final Comparator<CovidCountries> BY_MOST_DEATHS = new Comparator<CovidCountries>() {
        @Override
        public int compare(CovidCountries covidCountries, CovidCountries t1) {
            return Integer.compare(Integer.parseInt(t1.getmDeaths()), Integer.parseInt(covidCountries.getmDeaths()));
        }
    };
    public static final Comparator<CovidCountries> BY_MOST_RECOVERED = new Comparator<CovidCountries>() {
        @Override
        public int compare(CovidCountries covidCountries, CovidCountries t1) {
            return Integer.compare(Integer.parseInt(t1.getmRecovered()), Integer.parseInt(covidCountries.getmRecovered()));
        }
    };
    String mActive;
    private String mCOvidCountry;
    private String mCases;
    private String mDeaths;
    private String mRecovered;
    private String mCountryFlag;

    public CovidCountries(String mCOvidCountry, String mCases, String mDeaths, String mRecovered, String mCountryFlag, String mActive) {
        this.mCountryFlag = mCountryFlag;
        this.mCOvidCountry = mCOvidCountry;
        this.mCases = mCases;
        this.mDeaths = mDeaths;
        this.mRecovered = mRecovered;
        this.mActive = mActive;
    }

    public String getmActive() {
        return mActive;
    }

    public String getmCountryFlag() {
        return mCountryFlag;
    }

    public String getmCOvidCountry() {
        return mCOvidCountry;
    }

    public String getmCases() {
        return mCases;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }

}