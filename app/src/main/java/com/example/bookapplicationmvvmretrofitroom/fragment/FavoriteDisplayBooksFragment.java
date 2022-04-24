package com.example.bookapplicationmvvmretrofitroom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.adapter.FavoriteAdapter;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook;
import com.example.bookapplicationmvvmretrofitroom.viewModel.FavoriteDisplayBooksFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDisplayBooksFragment extends Fragment {
    private ArrayList<Item> displayFavoriteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    public FavoriteDisplayBooksFragmentViewModel viewModelFavorite;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayFavoriteList= RepositoryBook.getInstance().getMyListfavorite();
        viewModelFavorite = new ViewModelProvider(this).get(FavoriteDisplayBooksFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_favorite_books, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.recyclerview_display_favorite_books);
        setViewItem();
    }


    private void setViewItem(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        favoriteAdapter = new FavoriteAdapter(new FavoriteAdapter.FavoriAdapterInteface() {
            @Override
            public void delete(Item item) {
                viewModelFavorite.deleteBookFavorir(item,getContext());
                Toast.makeText(FavoriteDisplayBooksFragment.this.getContext(), "Supprimé", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(favoriteAdapter);

        viewModelFavorite.getFavoriteList(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                favoriteAdapter.setListFavoriteAdapter(new ArrayList<>(items));
                RepositoryBook.getInstance().myListfavorite= (ArrayList<Item>) items;

            }
        });
    }

}

