package com.example.eanatramobile.modele;

public class Cours {
    String title ;
    String urlVideo ;
    String shortDescription ;
    String content ;
    String[] category ;

    public Cours() {
    }

    public Cours(String title, String urlVideo, String shortDescription, String[] category) {
        this.title = title;
        this.urlVideo = urlVideo;
        this.shortDescription = shortDescription;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }
}
