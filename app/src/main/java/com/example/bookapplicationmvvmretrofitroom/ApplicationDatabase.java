package com.example.bookapplicationmvvmretrofitroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bookapplicationmvvmretrofitroom.converters.Converters;
import com.example.bookapplicationmvvmretrofitroom.converters.VolumeInfoConverter;
import com.example.bookapplicationmvvmretrofitroom.model.model.Item;
import com.example.bookapplicationmvvmretrofitroom.model.model.VolumeInfo;


@Database(entities = {Item.class},version = 1)
@TypeConverters({Converters.class, VolumeInfoConverter.class})
public abstract class ApplicationDatabase extends RoomDatabase {
    private static ApplicationDatabase INSTANCE;
    public abstract ItemDao getItemDao();
    public static synchronized ApplicationDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "volumeInfo_app").build();
        }
        return INSTANCE;
    }
}
