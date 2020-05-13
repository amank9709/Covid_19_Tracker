package com.example.covid_19tracker.ui.Info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.covid_19tracker.Adapter.RecyclerViewAdapter;
import com.example.covid_19tracker.FaqActivity;
import com.example.covid_19tracker.HowToProtectYourself;
import com.example.covid_19tracker.IndiaStateHelplineNumbers;
import com.example.covid_19tracker.R;
import com.example.covid_19tracker.StateWiseDetails;
import com.example.covid_19tracker.WebActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.android.volley.VolleyLog.TAG;

public class InfoFragment extends Fragment implements RecyclerViewAdapter.OnActivityClickListener {


    private RecyclerView recyclerView;
    private List<String> ActivityName;
    private List<Integer> images;
    private RecyclerViewAdapter recyclerViewAdapter;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_info, container, false);

        InitilizeComponents();
        showData();


        return root;
    }

    private void showData() {

        ActivityName.add("About Covid-19");
        ActivityName.add("FAQ");
        ActivityName.add("How to Protect Yourself");
        ActivityName.add("Mythbusters");
        ActivityName.add("View StateWise Details ");
        ActivityName.add("State helpline Numbers");


        images.add(R.drawable.about);
        images.add(R.drawable.faq);
        images.add(R.drawable.patient);
        images.add(R.drawable.spellbook);
        images.add(R.drawable.iconfinder_globe);
        images.add(R.drawable.phone);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), ActivityName, images, this::onNoteClick);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void InitilizeComponents() {
        recyclerView = root.findViewById(R.id.InfoRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ActivityName = new ArrayList<>();
        images = new ArrayList<>();
    }


    @Override
    public void onNoteClick(int position) {

        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://www.who.int/emergencies/diseases/novel-coronavirus-2019")
                        .putExtra("Name", ActivityName.get(position)));
                Animatoo.animateSlideUp(getActivity());
                break;
            case 1:
                startActivity(new Intent(getActivity(), FaqActivity.class));
                Animatoo.animateSlideUp(getActivity());
                break;
            case 2:
                startActivity(new Intent(getActivity(), HowToProtectYourself.class));
                Animatoo.animateSlideUp(getActivity());
                break;
            case 3:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url", "https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public/myth-busters")
                        .putExtra("Name", ActivityName.get(position)));
                Animatoo.animateSlideUp(getActivity());
                break;
            case 4:
                startActivity(new Intent(getActivity(), StateWiseDetails.class));
                Animatoo.animateSlideUp(getActivity());
                break;
            case 5:
                startActivity(new Intent(getActivity(), IndiaStateHelplineNumbers.class));
                Animatoo.animateSlideUp(getActivity());
                break;

            default:
                Toast.makeText(getActivity(), "Wrong position Selected", Toast.LENGTH_SHORT).show();

        }
        Log.d(TAG, "onNoteClick: " + position);

    }
}