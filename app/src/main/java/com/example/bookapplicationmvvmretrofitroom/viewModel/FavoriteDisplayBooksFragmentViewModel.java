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
    //private MutableLiveData<List<Item>>myFavoriteList = new MutableLiveData<>();
    //public LiveData<List<Item>> favoriteLiveData = myFavoriteList;

    public  LiveData<List<Item>> getFavoriteList(Context context){
        return RepositoryBook.getInstance().getFavoriteBooks(context);
    }

   // public void toPostFavoriteList(){
    //    myFavoriteList.postValue(RepositoryBook.getInstance().getMyListfavorite());
    //}

    public void deleteBookFavorir(Item item, Context context){
        RepositoryBook.getInstance().deleteFav(item, context);
    }


}
