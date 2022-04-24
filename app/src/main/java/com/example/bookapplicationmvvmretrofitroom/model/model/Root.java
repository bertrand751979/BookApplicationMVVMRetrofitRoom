package com.example.bookapplicationmvvmretrofitroom.model.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
public class Root implements Serializable {

    public String kind;
    public int totalItems;
    public List<Item> items;

    public Root(String kind, int totalItems, List<Item> items) {
        this.kind = kind;
        this.totalItems = totalItems;
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
