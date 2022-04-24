package com.example.bookapplicationmvvmretrofitroom.model.model;

import java.io.Serializable;

public class Offer implements Serializable {
        public int finskyOfferType;
        public ListPrice listPrice;
        public RetailPrice retailPrice;
        public boolean giftable;

    public Offer(int finskyOfferType, ListPrice listPrice, RetailPrice retailPrice, boolean giftable) {
        this.finskyOfferType = finskyOfferType;
        this.listPrice = listPrice;
        this.retailPrice = retailPrice;
        this.giftable = giftable;
    }

    public int getFinskyOfferType() {
        return finskyOfferType;
    }

    public void setFinskyOfferType(int finskyOfferType) {
        this.finskyOfferType = finskyOfferType;
    }

    public ListPrice getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice listPrice) {
        this.listPrice = listPrice;
    }

    public RetailPrice getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(RetailPrice retailPrice) {
        this.retailPrice = retailPrice;
    }

    public boolean isGiftable() {
        return giftable;
    }

    public void setGiftable(boolean giftable) {
        this.giftable = giftable;
    }
}
