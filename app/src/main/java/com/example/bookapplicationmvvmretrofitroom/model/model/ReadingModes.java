package com.example.bookapplicationmvvmretrofitroom.model.model;

import java.io.Serializable;

public class ReadingModes implements Serializable {

        public boolean text;
        public boolean image;

    public ReadingModes(boolean text, boolean image) {
        this.text = text;
        this.image = image;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
