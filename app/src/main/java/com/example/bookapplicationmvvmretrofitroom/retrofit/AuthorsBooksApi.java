package com.example.bookapplicationmvvmretrofitroom.retrofit;

import com.example.bookapplicationmvvmretrofitroom.model.model.Root;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class AuthorsBooksApi {
    public interface AuthorsBooksService{
        @GET("volumes")
        Call<Root> getRoot(@Query("q") String q);
    }
    private final static String BASE_URL="https://www.googleapis.com/books/v1/";
    private static AuthorsBooksApi INSTANCE = null;
    private AuthorsBooksApi(){}
    public static AuthorsBooksApi getInstance(){
        if(INSTANCE == null){
            INSTANCE = new AuthorsBooksApi();
        }
        return INSTANCE;
    }

    public Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
