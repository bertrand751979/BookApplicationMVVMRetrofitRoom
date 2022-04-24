package com.example.bookapplicationmvvmretrofitroom.activities;

import static com.example.bookapplicationmvvmretrofitroom.activities.LoginActivity.BOOK_EXTRA;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;


public class ScrollingActivity extends AppCompatActivity {
    private TextView scTitle;
    private TextView scWriter;
    private TextView scDescription;
    private ImageView scPhoto;
    private TextView scDescPublished;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        item = (Item) getIntent().getSerializableExtra(BOOK_EXTRA);
        //Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewItem();
        setTitle("");
    }

    private void setViewItem(){
        scTitle= findViewById(R.id.desc_book_title);
        scWriter= findViewById(R.id.desc_book_writer);
        scDescription=findViewById(R.id.desc_book_description);
        scPhoto= findViewById(R.id.desc_photo_book);
        scDescPublished =findViewById(R.id.desc_published);
        scTitle.setText(item.getVolumeInfo().getTitle());
        scDescPublished.setText(item.getVolumeInfo().getPublishedDate());
        scWriter.setText(item.getVolumeInfo().getAuthors().get(0));
        scDescription.setText(item.getVolumeInfo().getDescription());
        if(item.getVolumeInfo().imageLinks!=null){
            Glide.with(this)
                    .load(item.getVolumeInfo()
                            .getImageLinks()
                            .getThumbnail())
                    .into(scPhoto);
        }
        else{
            scPhoto.setImageResource(R.drawable.ic_error);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();}
        return super.onOptionsItemSelected(item);
    }

}
