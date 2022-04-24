package com.example.bookapplicationmvvmretrofitroom.model.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Item implements Serializable {
        @PrimaryKey(autoGenerate = true)
        public long identifier;
        @ColumnInfo(name = "volumeInfo")
        public VolumeInfo volumeInfo;

    public Item(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

}
