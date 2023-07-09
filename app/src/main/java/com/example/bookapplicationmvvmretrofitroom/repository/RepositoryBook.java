package com.example.bookapplicationmvvmretrofitroom.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.bookapplicationmvvmretrofitroom.ApplicationDatabase;
import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.adapter.FavoriteAdapter;
import com.example.bookapplicationmvvmretrofitroom.model.model.ImageLinks;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.VolumeInfo;
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
import java.util.concurrent.Executors;

public class RepositoryBook {
    public ArrayList<Item> myBookList = new ArrayList<>();
    public ArrayList<Item> myListfavorite = new ArrayList<>();
    public ArrayList<Item> myListfavorite2 = new ArrayList<>();
    public ArrayList<Item> myListfavorite3 = new ArrayList<>();

    public ArrayList<Item> keepMyListFavoriteToDisplay =new ArrayList<>();
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Item item;

    private RepositoryBook(){}
    private static RepositoryBook INSTANCE = null;
    public static RepositoryBook getInstance(){
        if (INSTANCE == null){
            INSTANCE = new RepositoryBook();
        }
        return INSTANCE;
    }

    public ArrayList<Item> getMyBookList() {
        return myBookList;
    }

    public void setMyBookList(ArrayList<Item> myBookList) {
        this.myBookList = myBookList;
    }

    public ArrayList<Item> getMyListfavorite() {
        return myListfavorite;
    }

    public void setMyListfavorite(ArrayList<Item> myListfavorite) {
        this.myListfavorite = myListfavorite;
    }

    public ArrayList<Item> getMyListfavorite3() {
        return myListfavorite3;
    }

    public void setMyListfavorite3(ArrayList<Item> myListfavorite3) {
        this.myListfavorite3 = myListfavorite3;
    }

    public ArrayList<Item> getMyListfavorite2() {
        return myListfavorite2;
    }

    public void setMyListfavorite2(ArrayList<Item> myListfavorite2) {
        this.myListfavorite2 = myListfavorite2;
    }

    public void addToFavori(Item item){
        myListfavorite.add(item);
    }

   /* public void addFavori(Item item, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabase.getInstance(context).getItemDao().insertFavori(item);
            }
        });
    }*/

  /*  public void deleteFav(Item item, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabase.getInstance(context).getItemDao().deleteFavori(item);
            }
        });
    }*/


  /*  public boolean isFavorite(Item itemSelected){
        boolean result =false;
        for(Item itemfav:myListfavorite){
                    if(itemSelected.getVolumeInfo().getTitle().equals(itemfav.getVolumeInfo().getTitle())){
                        if(itemSelected.getVolumeInfo().getDescription()!=null){
                            if(itemSelected.getVolumeInfo().getDescription().equals(itemfav.getVolumeInfo().getDescription())) {
                                result = true;
                            }
                    }
            }
        }
        return result;
    }*/


 /*   public LiveData<List<Item>> getFavoriteBooks (Context context){
        return ApplicationDatabase.getInstance(context).getItemDao().getFavItems();
    }*/


    public boolean setIfFavoriteOrNotAction(Item itemSelected){
        boolean check= false;
        for(Item item2:RepositoryBook.getInstance().myListfavorite2){
            if (item2.getVolumeInfo().getTitle().equals(itemSelected.getVolumeInfo().getTitle())&&(item2.getVolumeInfo().getPublishedDate().equals(itemSelected.getVolumeInfo().getPublishedDate()))){
                check = true;
            }
        }
        return check;
    }



    public void settingStarIfFavoriteMovie(ImageView img,Item favItem) {
        if(RepositoryBook.getInstance().setIfFavoriteOrNotAction(favItem)){
            img.setImageResource(R.drawable.ic_baseline_favorite);
        } /*else {
            btnFavory.setImageResource(R.drawable.ic_favorite_empty);
        }*/
    }




}
