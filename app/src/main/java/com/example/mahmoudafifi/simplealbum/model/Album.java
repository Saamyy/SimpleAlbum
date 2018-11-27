package com.example.mahmoudafifi.simplealbum.model;

public class Album {
    private String albumId;
    private String title;
    private String thumbnailUrl;
    private String ImagesCount;

    public Album(String albumId, String title, String thumbnailUrl, String imagesCount) {
        this.albumId = albumId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        ImagesCount = imagesCount;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImagesCount() {
        return ImagesCount;
    }

    public void setImagesCount(String imagesCount) {
        ImagesCount = imagesCount;
    }
}
