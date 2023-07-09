package com.example.bookapplicationmvvmretrofitroom.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookapplicationmvvmretrofitroom.OnButtonDeleteActionClicked;
import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.adapter.FavoriteAdapter;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;

public class FavoriteViewHolder extends RecyclerView.ViewHolder {
    private TextView vhFavoriteBookAuthorName;
    private ImageView vhFavoriteBookPicture;
    private TextView vhFavoritePublishedYear;
    private TextView vhFavoriteBookTitle;
    private ImageView vhBtnDelete;

    public FavoriteViewHolder(@NonNull View view) {
        super(view);
        vhFavoriteBookAuthorName = view.findViewById(R.id.raw_favorite_author_name);
        vhFavoriteBookPicture = view.findViewById(R.id.raw_favorite_image_display_books);
        vhFavoritePublishedYear = view.findViewById(R.id.raw_favorite_published_year);
        vhFavoriteBookTitle = view.findViewById(R.id.raw_favorite_book_title);
        vhBtnDelete = view.findViewById(R.id.deleteFavori);
    }

    public TextView getVhFavoriteBookAuthorName() {
        return vhFavoriteBookAuthorName;
    }

    public void setVhFavoriteBookAuthorName(TextView vhFavoriteBookAuthorName) {
        this.vhFavoriteBookAuthorName = vhFavoriteBookAuthorName;
    }

    public ImageView getVhFavoriteBookPicture() {
        return vhFavoriteBookPicture;
    }

    public void setVhFavoriteBookPicture(ImageView vhFavoriteBookPicture) {
        this.vhFavoriteBookPicture = vhFavoriteBookPicture;
    }

    public TextView getVhFavoritePublishedYear() {
        return vhFavoritePublishedYear;
    }

    public void setVhFavoritePublishedYear(TextView vhFavoritePublishedYear) {
        this.vhFavoritePublishedYear = vhFavoritePublishedYear;
    }

    public TextView getVhFavoriteBookTitle() {
        return vhFavoriteBookTitle;
    }

    public void setVhFavoriteBookTitle(TextView vhFavoriteBookTitle) {
        this.vhFavoriteBookTitle = vhFavoriteBookTitle;
    }

    public ImageView getVhBtnDelete() {
        return vhBtnDelete;
    }

    public void setVhBtnDelete(ImageView vhBtnDelete) {
        this.vhBtnDelete = vhBtnDelete;
    }

   // public void bind(Item item, FavoriteAdapter.FavoriAdapterInteface favoriAdapterInteface){
   public void bind(Item item, OnButtonDeleteActionClicked onButtonDeleteActionClicked){
        vhFavoriteBookAuthorName.setText(item.getVolumeInfo().getAuthors().get(0));
        vhFavoritePublishedYear.setText(item.getVolumeInfo().getPublishedDate());
        vhFavoriteBookTitle.setText(item.getVolumeInfo().getTitle());
        if(item.getVolumeInfo().imageLinks!=null){
            Glide.with(vhFavoriteBookPicture.getContext())
                    .load(item.getVolumeInfo()
                            .getImageLinks()
                            .getThumbnail())
                    .into(vhFavoriteBookPicture);
        }
        else{
            vhFavoriteBookPicture.setImageResource(R.drawable.ic_error);
        }

      /*  vhBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //favoriAdapterInteface.delete(item);
            }
        });*/

       vhBtnDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onButtonDeleteActionClicked.deleteBook(item);
           }
       });


    }
}
