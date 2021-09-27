package com.deezer.api.POJO;

public class Album {

    private String albumId;
    private String albumName;
    private String artist;

    /**
     * No args constructor for use in serialization
     *
     */
    public Album() {
    }

    /**
     *
     * @param albumName
     * @param artist
     * @param albumId
     */
    public Album(String albumId, String albumName, String artist) {
        super();
        this.albumId = albumId;
        this.albumName = albumName;
        this.artist = artist;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}