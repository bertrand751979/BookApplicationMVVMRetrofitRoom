package com.example.bookapplicationmvvmretrofitroom.fragment;

import static com.example.bookapplicationmvvmretrofitroom.activities.LoginActivity.BOOK_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookapplicationmvvmretrofitroom.ApplicationDatabase;
import com.example.bookapplicationmvvmretrofitroom.ItemDao;
import com.example.bookapplicationmvvmretrofitroom.ToDescription;
import com.example.bookapplicationmvvmretrofitroom.AddtoFavorite;
import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.activities.ScrollingActivity;
import com.example.bookapplicationmvvmretrofitroom.adapter.BookAdapter;
import com.example.bookapplicationmvvmretrofitroom.adapter.FavoriteAdapter;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook;
import com.example.bookapplicationmvvmretrofitroom.viewModel.DisplayBooksFragmentViewModel;
import com.example.bookapplicationmvvmretrofitroom.viewModel.FavoriteDisplayBooksFragmentViewModel;

import java.util.ArrayList;
import java.util.List;


public class DisplayBooksFragment extends Fragment {
    private ArrayList<Item>myBookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private DisplayBooksFragmentViewModel viewModelDisplay;
    private ArrayList<Item>displayBook = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RepositoryBook.getInstance().getFavoriteBooks(getContext());
        displayBook = RepositoryBook.getInstance().myBookList;
        viewModelDisplay = new ViewModelProvider(this).get(DisplayBooksFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
            bookAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_books, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_display_books);
        setViewItem();
    }

    private void setViewItem() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ToDescription toDescription = new ToDescription() {
            @Override
            public void description(Item item) {
                Intent intent = new Intent(DisplayBooksFragment.this.getContext(), ScrollingActivity.class);
                intent.putExtra(BOOK_EXTRA,item);
                startActivity(intent);
                Toast.makeText(DisplayBooksFragment.this.getContext(), "Vers description", Toast.LENGTH_SHORT).show();
            }
        };
        AddtoFavorite addtoFavorite = new AddtoFavorite() {
            @Override
            public void favorite(Item item) {
                viewModelDisplay.addToFavorite(item,getContext());
            }
        };
        bookAdapter = new BookAdapter(toDescription, addtoFavorite);
        recyclerView.setAdapter(bookAdapter);
        viewModelDisplay.itemLiveData.observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                bookAdapter.setListAdapter(new ArrayList<>(items));
            }
        });
        viewModelDisplay.toPostMyItemsList();
    }



}
