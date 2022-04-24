package com.example.bookapplicationmvvmretrofitroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.viewHolder.FavoriteViewHolder;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {

    public interface FavoriAdapterInteface {
        void delete(Item item);
    }


    private ArrayList<Item> listFavoriteAdapter = new ArrayList<>();
    private FavoriAdapterInteface favoriAdapterInteface;

   // public FavoriteAdapter() {
        //this.listFavoriteAdapter = listFavoriteAdapter;
  //  }


    public FavoriteAdapter(FavoriAdapterInteface favoriAdapterInteface) {
        this.favoriAdapterInteface = favoriAdapterInteface;
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
        holder.bind(listFavoriteAdapter.get(position), favoriAdapterInteface);
    }

    @Override
    public int getItemCount() {
        return listFavoriteAdapter.size();
    }
}
