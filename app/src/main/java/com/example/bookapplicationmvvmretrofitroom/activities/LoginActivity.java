package com.example.bookapplicationmvvmretrofitroom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookapplicationmvvmretrofitroom.retrofit.AuthorsBooksApi;
import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.Root;
import com.example.bookapplicationmvvmretrofitroom.repository.RepositoryBook;
import com.example.bookapplicationmvvmretrofitroom.viewModel.LoginActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editWriter;
    private EditText editTitle;
    private Button btnToSearch;
    private Button btnToLibrary;
    public static String BOOK_EXTRA = "book_extra";
    public LoginActivityViewModel viewModelLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModelLoginActivity = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        editWriter = findViewById(R.id.edit_writer);
        editTitle = findViewById(R.id.edit_title);
        btnToSearch = findViewById(R.id.btnSearch);
        btnToLibrary = findViewById(R.id.btnToFavorite);
        btnToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTitle.getText().toString().equalsIgnoreCase("")){
                }else{ callService();
                }
            }
        });
        btnToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        observer();

    }

    public void callService(){
        AuthorsBooksApi.AuthorsBooksService service = AuthorsBooksApi.getInstance().getClient().create(AuthorsBooksApi.AuthorsBooksService.class);
        Call<Root> call= service.getRoot(editTitle.getText().toString());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                updateView(response);
                //Toast.makeText(LoginActivity.this,"Acces a l'API", Toast.LENGTH_SHORT).show();
                //Log.d("La taille de la liste", String.valueOf(RepositoryBook.getInstance().getMyBookList().size()));


            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erreur", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateView(Response<Root> response) {
        if (response.isSuccessful() && response.body().getItems() != null) {
            RepositoryBook.getInstance().myBookList=(ArrayList<Item>)response.body().getItems();
            Toast.makeText(LoginActivity.this, "taille"+RepositoryBook.getInstance().myBookList.size(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Reponse du serveur", Toast.LENGTH_SHORT).show();
        }
    }

    public void observer(){
        viewModelLoginActivity.getBeginingListfavoriteLiveData(LoginActivity.this).observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                RepositoryBook.getInstance().myListfavorite= (ArrayList<Item>) items;
            }
        });

    }

}
