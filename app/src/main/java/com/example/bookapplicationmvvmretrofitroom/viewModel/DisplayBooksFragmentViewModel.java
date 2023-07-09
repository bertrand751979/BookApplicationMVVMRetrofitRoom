package com.example.bookapplicationmvvmretrofitroom.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.VolumeInfo;
import com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook;

import java.util.List;

public class DisplayBooksFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Item>> myItemsList = new MutableLiveData<>();
    public LiveData<List<Item>> itemLiveData = myItemsList;


    public void toPostMyItemsList(){
        myItemsList.postValue(RepositoryBook.getInstance().getMyBookList());
    }

    public void toAddToFavorite(Item item){
        RepositoryBook.getInstance().addToFavori(item);
        toPostMyItemsList();
    }

    public void addToFavorite(Item item, Context context){
        //RepositoryBook.getInstance().addFavori(item,context);
    }








}
