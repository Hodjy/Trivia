package com.example.trivia.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trivia.ApplicationContext;
import com.example.trivia.R;

import java.util.ArrayList;

public class FoliageAdapter extends RecyclerView.Adapter<FoliageAdapter.FoliageViewHolder> {

    private ArrayList<Foliage> foliages;

    public FoliageAdapter(ArrayList<Foliage> foliages) {
        this.foliages = foliages;
    }

    public class FoliageViewHolder extends RecyclerView.ViewHolder{

        ImageView foliageIv;
        TextView informationTv;

        public FoliageViewHolder(@NonNull View itemView) {
            super(itemView);

            foliageIv = itemView.findViewById(R.id.foliage_layout_foliageIv);
            informationTv = itemView.findViewById(R.id.foliage_layout_informationTv);
        }
    }

    @NonNull
    @Override
    public FoliageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foliage_layout, parent, false);
        FoliageViewHolder foliageViewHolder = new FoliageViewHolder(view);
        return foliageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoliageViewHolder holder, int position) {
        Foliage foliage = foliages.get(position);
        Glide.with(ApplicationContext.getContext()).load(foliage.getImgeUri()).into(holder.foliageIv);
        holder.informationTv.setText(foliage.getFoliageName());
    }

    @Override
    public int getItemCount() {
        return foliages.size();
    }
}
