package com.example.covid_19tracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covid_19tracker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    Context context;
    LayoutInflater layoutInflater;
    OnActivityClickListener mOnActivityClickListener;


    public RecyclerViewAdapter(Context context, List<String> titles, List<Integer> images, OnActivityClickListener onActivityClickListener) {
        this.titles = titles;
        this.images = images;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mOnActivityClickListener = onActivityClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.info_page_card_view, parent, false);
        return new ViewHolder(view, mOnActivityClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.icon.setImageResource(images.get(position));
        holder.ActivityName.setText(titles.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public interface OnActivityClickListener {
        void onNoteClick(int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icon;
        TextView ActivityName;
        OnActivityClickListener onActivityClickListener;


        public ViewHolder(@NonNull View itemView, OnActivityClickListener onActivityClickListener) {
            super(itemView);
            icon = itemView.findViewById(R.id.ActivityIconImage);
            ActivityName = itemView.findViewById(R.id.ActivityName);
            this.onActivityClickListener = onActivityClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onActivityClickListener.onNoteClick(getAdapterPosition());

        }
    }
}
