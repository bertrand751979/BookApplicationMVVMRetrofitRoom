package com.example.bookapplicationmvvmretrofitroom.fragment;

import static android.content.ContentValues.TAG;
import static com.example.bookapplicationmvvmretrofitroom.activities.LoginActivity.BOOK_EXTRA;
import static com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook.db;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.bookapplicationmvvmretrofitroom.activities.LoginActivity;
import com.example.bookapplicationmvvmretrofitroom.activities.ScrollingActivity;
import com.example.bookapplicationmvvmretrofitroom.adapter.BookAdapter;
import com.example.bookapplicationmvvmretrofitroom.adapter.FavoriteAdapter;
import com.example.bookapplicationmvvmretrofitroom.model.model.ImageLinks;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.VolumeInfo;
import com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook;
import com.example.bookapplicationmvvmretrofitroom.viewModel.DisplayBooksFragmentViewModel;
import com.example.bookapplicationmvvmretrofitroom.viewModel.FavoriteDisplayBooksFragmentViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DisplayBooksFragment extends Fragment {
    private ArrayList<Item>myBookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private DisplayBooksFragmentViewModel viewModelDisplay;
    private ArrayList<Item>displayBook = new ArrayList<>();
    private Item item;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayBook = RepositoryBook.getInstance().myBookList;
        viewModelDisplay = new ViewModelProvider(this).get(DisplayBooksFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
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
                if(setIfFavoriteOrNotAction(item)==true){
                    Toast.makeText(getContext(), "deja favoris", Toast.LENGTH_SHORT).show();
                    return;
                }
                addMovieToFirabase(item);
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

    public void addMovieToFirabase(Item it) {
       if(setIfFavoriteOrNotAction(it)==true) {
            Toast.makeText(getContext(), "Déja dans la liste des favoris", Toast.LENGTH_SHORT).show();
            return;
        }
            Map<List, Item> favoryBooks = new HashMap<>();
            favoryBooks = new HashMap<>();
            favoryBooks.put(RepositoryBook.getInstance().getMyBookList(), it);
            it.getVolumeInfo().setFavorite(true);
            db.collection("volumes")
                    .add(it.getVolumeInfo())
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            RepositoryBook.getInstance().myListfavorite2.clear();

                            //Log.d("volumes",documentReference.getId());
                            //Log.d("author", String.valueOf((it.getVolumeInfo().getAuthors())));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Une erreure", e);
                        }
                    });
    }



   /* private boolean setIfFavory(Item itemSelected){
        boolean check= false;
        for(Item item2:RepositoryBook.getInstance().getMyListfavorite2()) {
                if (item.getVolumeInfo().getPublishedDate()==(itemSelected.getVolumeInfo().getPublishedDate())&&
                item.getVolumeInfo().getTitle().equals(itemSelected.getVolumeInfo().getTitle())) {
                    check = true;
                }
            Toast.makeText(getContext(), " deja ajouté liste des favoris", Toast.LENGTH_SHORT).show();
        }
        return check;
    }*/

    //verifier s'il est dans les favoris
    public static  boolean setIfFavoriteOrNotAction(Item itemSelected){
        boolean check= false;
        for(Item item2:RepositoryBook.getInstance().myListfavorite2){
            if (item2.getVolumeInfo().getTitle().equals(itemSelected.getVolumeInfo().getTitle())&&(item2.getVolumeInfo().getPublishedDate().equals(itemSelected.getVolumeInfo().getPublishedDate()))){
                check = true;
            }
        }
        return check;
    }

    // Configurer Les coeurs
    private void settingHeartIfFavoriteBook(Item favItem) {
        if(setIfFavoriteOrNotAction(favItem)){
            Toast.makeText(getContext(), "favoris", Toast.LENGTH_SHORT).show();
            //descFavory.setImageResource(R.drawable.ic_star_full);
        } else {
            Toast.makeText(getContext(), "Pas favoris", Toast.LENGTH_SHORT).show();
            //descFavory.setImageResource(R.drawable.ic_star_empty);
        }
    }

   /* private boolean checkIfFavorite(){
        boolean res= false;
        for(Item itm:RepositoryBook.getInstance().myListfavorite)
            if(setIfFavoriteOrNotAction(itm)==true){
                res=true;
                Toast.makeText(getContext(), "deja Favoris", Toast.LENGTH_SHORT).show();
            }
        return res;
    }*/







   /* private void settingStarIfFavoriteMovie(Item favItem) {
        if(setIfFavoriteOrNotAction(favItem)){
            descFavory.setImageResource(R.drawable.ic_star_full);
        } else {
            descFavory.setImageResource(R.drawable.ic_star_empty);
        }
    }*/




}
