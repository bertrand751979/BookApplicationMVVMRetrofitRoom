package com.example.bookapplicationmvvmretrofitroom.viewHolder;

import static android.content.ContentValues.TAG;


import static com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook.db;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.bookapplicationmvvmretrofitroom.ApplicationDatabase;
import com.example.bookapplicationmvvmretrofitroom.ToDescription;
import com.example.bookapplicationmvvmretrofitroom.AddtoFavorite;
import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.adapter.BookAdapter;
import com.example.bookapplicationmvvmretrofitroom.model.model.ImageLinks;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.VolumeInfo;
import com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private TextView vhBookAuthorName;
    private TextView vhPublishedYear;
    private TextView vhBookTitle;
    private ImageView vhFavoriteImg;
    private TextView vhDescription;
    private MaterialCardView materialCard;
    private ImageView vhImageBook;
    private ImageView btnFavory;
    private BookAdapter bookAdapter;

    public BookViewHolder(@NonNull View view) {
        super(view);
        vhImageBook =  view.findViewById(R.id.raw_book_picture);
        vhBookAuthorName = view.findViewById(R.id.raw_author_name);
        vhPublishedYear = view.findViewById(R.id.raw_published_year);
        vhBookTitle = view.findViewById(R.id.raw_book_title);
        //vhFavoriteImg = view.findViewById(R.id.raw_img_favorite);
        vhDescription = view.findViewById(R.id.raw_description);
        materialCard = view.findViewById(R.id.raw_materialCard);
        btnFavory = view.findViewById(R.id.raw_img_favorite);
    }

    public TextView getVhBookAuthorName() {
        return vhBookAuthorName;
    }

    public void setVhBookAuthorName(TextView vhBookAuthorName) {
        this.vhBookAuthorName = vhBookAuthorName;
    }

    public TextView getVhPublishedYear() {
        return vhPublishedYear;
    }

    public void setVhPublishedYear(TextView vhPublishedYear) {
        this.vhPublishedYear = vhPublishedYear;
    }

    public TextView getVhBookTitle() {
        return vhBookTitle;
    }

    public void setVhBookTitle(TextView vhBookTitle) {
        this.vhBookTitle = vhBookTitle;
    }

    public ImageView getVhFavoriteImg() {
        return vhFavoriteImg;
    }

    public void setVhFavoriteImg(ImageView vhFavoriteImg) {
        this.vhFavoriteImg = vhFavoriteImg;
    }

    public TextView getVhDescription() {
        return vhDescription;
    }

    public void setVhDescription(TextView vhDescription) {
        this.vhDescription = vhDescription;
    }

    public ImageView getBtnFavory() {
        return btnFavory;
    }

    public void setBtnFavory(ImageView btnFavory) {
        this.btnFavory = btnFavory;
    }

    public void bind(Item item, ToDescription toDescription, AddtoFavorite addtoFavorite){
        ArrayList<Item> list = RepositoryBook.getInstance().myListfavorite2;
        ArrayList<Item> book = RepositoryBook.getInstance().myBookList;
        vhBookAuthorName.setText(item.getVolumeInfo().getAuthors().get(0));
        vhPublishedYear.setText(item.getVolumeInfo().getPublishedDate());
        vhDescription.setText(item.getVolumeInfo().getDescription());
        vhBookTitle.setText(item.getVolumeInfo().getTitle());
        btnFavory.setImageResource(R.drawable.ic_favorite_empty);

        for(Item itt : RepositoryBook.getInstance().myListfavorite2){
            Log.d("vh", String.valueOf(list.size()));
            Log.d("book", String.valueOf(book.size()));
            if  ((itt.getVolumeInfo().getTitle().equals(item.getVolumeInfo().getTitle()))&&
                    (itt.getVolumeInfo().getPublishedDate().equals(item.getVolumeInfo().getPublishedDate()))){
                Log.d("titre 1", String.valueOf(itt.getVolumeInfo().getId()));
                btnFavory.setImageResource(R.drawable.ic_baseline_favorite);
            }
        }


        materialCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDescription.description(item);
            }
        });
        if(item.getVolumeInfo().imageLinks!=null){
        Glide.with(vhImageBook.getContext())
                .load(item.getVolumeInfo()
                .getImageLinks()
                        .getThumbnail())
                        .into(vhImageBook);
        }
        else{
            vhImageBook.setImageResource(R.drawable.ic_error);
        }
        btnFavory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFavory.setImageResource(R.drawable.ic_baseline_favorite);
                    addtoFavorite.favorite(item);
                    Log.d(" fav",item.getVolumeInfo().getTitle());
                }
        });

        /*for (Item itt : RepositoryBook.getInstance().myListfavorite2) {
            Log.d("vh", String.valueOf(list.size()));
            Log.d("titre 1", itt.getVolumeInfo().getTitle());
            if(item.getVolumeInfo().getPublishedDate() == itt.getVolumeInfo().getPublishedDate()) {
                btnFavory.setImageResource(R.drawable.ic_baseline_favorite);
            }
        }*/

    }


}
