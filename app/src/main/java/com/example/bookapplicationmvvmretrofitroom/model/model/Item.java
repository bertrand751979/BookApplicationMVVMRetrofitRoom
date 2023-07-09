package com.example.bookapplicationmvvmretrofitroom.model.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Item implements Serializable {
        @PrimaryKey(autoGenerate = true)
        public long identifier;
        public String id;
        @ColumnInfo(name = "volumeInfo")
        public VolumeInfo volumeInfo;
        private String documentId;


    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

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
