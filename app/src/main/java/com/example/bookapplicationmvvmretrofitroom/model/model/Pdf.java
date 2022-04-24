package com.example.bookapplicationmvvmretrofitroom.model.model;

import java.io.Serializable;

public class Pdf implements Serializable {
        public boolean isAvailable;
        public String acsTokenLink;

    public Pdf(boolean isAvailable, String acsTokenLink) {
        this.isAvailable = isAvailable;
        this.acsTokenLink = acsTokenLink;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }
}
