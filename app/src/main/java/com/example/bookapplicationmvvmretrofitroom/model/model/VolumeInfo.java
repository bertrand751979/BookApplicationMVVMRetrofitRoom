package com.example.bookapplicationmvvmretrofitroom.model.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
public class VolumeInfo implements Serializable {
        @PrimaryKey(autoGenerate = true)
        private long id;
        @ColumnInfo(name = "title")
        public String title;
        @ColumnInfo(name = "authors")
        public ArrayList<String> authors;
        @ColumnInfo(name = "publishedDate")
        public String publishedDate;
        @ColumnInfo(name = "description")
        public String description;
        @ColumnInfo(name = "imageLinks")
        public ImageLinks imageLinks;
        //private ArrayList<IndustryIdentifier> industryIdentifiers;
        //public ReadingModes readingModes;
        public int pageCount;
        public String printType;
        public String maturityRating;
        public boolean allowAnonLogging;
        public String contentVersion;
        public String language;
        public String previewLink;
        public String infoLink;
        public String canonicalVolumeLink;
        public String subtitle;
        public String publisher;

        //public PanelizationSummary panelizationSummary;

        public ArrayList<String> categories;

    public VolumeInfo(String title, ArrayList<String> authors, String publishedDate, int pageCount, String printType, String maturityRating,ImageLinks imageLinks, boolean allowAnonLogging, String contentVersion, String language, String previewLink, String infoLink, String canonicalVolumeLink, String subtitle, String description, String publisher, ArrayList<String> categories) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.pageCount = pageCount;
        this.printType = printType;
        this.maturityRating = maturityRating;
        this.allowAnonLogging = allowAnonLogging;
        this.contentVersion = contentVersion;
        this.language = language;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.canonicalVolumeLink = canonicalVolumeLink;
        this.subtitle = subtitle;
        this.description = description;
        this.publisher = publisher;
        this.categories = categories;
        this.imageLinks = imageLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public boolean isAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
