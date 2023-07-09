package com.example.bookapplicationmvvmretrofitroom.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.VolumeInfo;
import com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook;

import java.util.List;

public class FavoriteDisplayBooksFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Item>> myFavoriteItemsList = new MutableLiveData<>();
    public LiveData<List<Item>> itemLiveData = myFavoriteItemsList;

    /*public void toPostMyFavoriteItemsList(){
        myFavoriteItemsList.postValue(RepositoryBook.getInstance().getMyBookList());
    }*/




}
