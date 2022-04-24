package com.example.bookapplicationmvvmretrofitroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookapplicationmvvmretrofitroom.ToDescription;
import com.example.bookapplicationmvvmretrofitroom.AddtoFavorite;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.viewHolder.BookViewHolder;
import com.example.bookapplicationmvvmretrofitroom.R;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private ArrayList<Item> listAdapter = new ArrayList<>();
    private AddtoFavorite addtoFavorite;
    private ToDescription toDescription;

    public BookAdapter(ToDescription toDescription,AddtoFavorite addtoFavorite) {
        //this.listAdapter = listAdapter;
        this.toDescription =toDescription;
        this.addtoFavorite =addtoFavorite;
    }

    public void setListAdapter(ArrayList<Item> listAdapter) {
        this.listAdapter = listAdapter;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_display_books,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(listAdapter.get(position), toDescription, addtoFavorite);
    }

    @Override
    public int getItemCount() {
        return listAdapter.size();
    }
}
