package com.example.bookapplicationmvvmretrofitroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapplicationmvvmretrofitroom.OnButtonDeleteActionClicked;
import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.viewHolder.FavoriteViewHolder;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {

    private ArrayList<Item> listFavoriteAdapter = new ArrayList<>();
    private OnButtonDeleteActionClicked onButtonDeleteActionClicked;

    public FavoriteAdapter(ArrayList<Item> listFavoriteAdapter, OnButtonDeleteActionClicked onButtonDeleteActionClicked) {
        this.listFavoriteAdapter = listFavoriteAdapter;
        this.onButtonDeleteActionClicked = onButtonDeleteActionClicked;
    }

    public void setListFavoriteAdapter(ArrayList<Item> listFavoriteAdapter) {
        this.listFavoriteAdapter = listFavoriteAdapter;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_display_favorite_books,parent,false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(listFavoriteAdapter.get(position), onButtonDeleteActionClicked);
    }

    @Override
    public int getItemCount() {
        return listFavoriteAdapter.size();
    }
}
