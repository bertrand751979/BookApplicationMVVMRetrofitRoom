package com.example.bookapplicationmvvmretrofitroom.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.bookapplicationmvvmretrofitroom.ApplicationDatabase;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.VolumeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class RepositoryBook {
    public ArrayList<Item> myBookList = new ArrayList<>();
    public ArrayList<Item> myListfavorite = new ArrayList<>();
    public ArrayList<Item> keepMyListFavoriteToDisplay =new ArrayList<>();
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

    public void addToFavori(Item item){
        myListfavorite.add(item);
    }

    public void addFavori(Item item, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabase.getInstance(context).getItemDao().insertFavori(item);

            }
        });
    }

    public void deleteFav(Item item, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDatabase.getInstance(context).getItemDao().deleteFavori(item);
            }
        });
    }


    public boolean isFavorite(Item itemSelected){
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
    }


    public LiveData<List<Item>> getFavoriteBooks (Context context){
        return ApplicationDatabase.getInstance(context).getItemDao().getFavItems();
    }



}
