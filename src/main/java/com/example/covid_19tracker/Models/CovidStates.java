package com.example.covid_19tracker.Models;

public class CovidStates {

    private String mCovidStates, mCases, mDeaths, mRecovered, mActive;


    public CovidStates(String mCovidStates, String mCases, String mRecovered, String mDeaths, String mActive) {
        this.mCovidStates = mCovidStates;
        this.mCases = mCases;
        this.mDeaths = mDeaths;
        this.mRecovered = mRecovered;
        this.mActive = mActive;
    }


    public String getmActive() {
        return mActive;
    }

    public String getmCovidStates() {
        return mCovidStates;
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
