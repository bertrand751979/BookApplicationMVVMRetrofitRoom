package com.example.bookapplicationmvvmretrofitroom.fragment;

import static android.content.ContentValues.TAG;

import static com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook.db;

import android.os.Bundle;
import android.util.Log;
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

import com.example.bookapplicationmvvmretrofitroom.OnButtonDeleteActionClicked;
import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.adapter.FavoriteAdapter;
import com.example.bookapplicationmvvmretrofitroom.model.model.ImageLinks;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.VolumeInfo;
import com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook;
import com.example.bookapplicationmvvmretrofitroom.viewModel.FavoriteDisplayBooksFragmentViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteDisplayBooksFragment extends Fragment {
    private ArrayList<Item> displayFavoriteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    public FavoriteDisplayBooksFragmentViewModel viewModelFavorite;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Favoris: ");
        //displayFavoriteList= RepositoryBook.getInstance().getMyListfavorite();
        //viewModelFavorite = new ViewModelProvider(this).get(FavoriteDisplayBooksFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        //RepositoryBook.getInstance().getMyListfavorite().clear();
        toGetFavList();
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
        OnButtonDeleteActionClicked onButtonDeleteActionClicked = new OnButtonDeleteActionClicked() {
            @Override
            public void deleteBook(Item item) {
            toDeleteMyMovies(item);
            }
        };
        favoriteAdapter = new FavoriteAdapter(RepositoryBook.getInstance().myListfavorite,onButtonDeleteActionClicked);
        favoriteAdapter.setListFavoriteAdapter(RepositoryBook.getInstance().myListfavorite);
        recyclerView.setAdapter(favoriteAdapter);
    }

    public  void toGetFavList(){
        db.collection("volumes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Item item = new Item();
                                item.setDocumentId(document.getId());
                                Map<String,ImageLinks> imageLinkMap = new HashMap<>();
                                if(document.getData().get("imageLinks")!=null){
                                imageLinkMap= (Map<String, ImageLinks>) document.getData().get("imageLinks");
                                }
                                ImageLinks imageLinks = new ImageLinks();
                                VolumeInfo volumeInfo = new VolumeInfo();
                                volumeInfo.setImageLinks((imageLinks));
                                volumeInfo.getImageLinks().setThumbnail(String.valueOf(imageLinkMap.get("thumbnail")));
                                volumeInfo.setTitle(String.valueOf(document.getData().get("title")));
                                volumeInfo.setAuthors((ArrayList<String>) document.getData().get("authors"));
                                volumeInfo.setPublishedDate((String) document.getData().get("publishedDate"));
                                item.setVolumeInfo(volumeInfo);
                                RepositoryBook.getInstance().myListfavorite.add(item);

                            }
                            favoriteAdapter.setListFavoriteAdapter(RepositoryBook.getInstance().myListfavorite);
                            Toast.makeText(getContext(), "nombre de livres favoris"+ RepositoryBook.getInstance().myListfavorite.size(), Toast.LENGTH_SHORT).show();
                            Log.d("taille.", String.valueOf(RepositoryBook.getInstance().myListfavorite.size()));
                            RepositoryBook.getInstance().myListfavorite2=RepositoryBook.getInstance().myListfavorite;

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    private void toDeleteMyMovies(Item itm){
        db.collection("volumes").document(itm.getDocumentId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        RepositoryBook.getInstance().myListfavorite.remove(itm);
                        favoriteAdapter.setListFavoriteAdapter(RepositoryBook.getInstance().myListfavorite);
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }
}

