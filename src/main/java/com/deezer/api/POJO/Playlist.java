package com.deezer.api.POJO;

public class Playlist {

    private String playlistId;
    private String playlistTitle;

    public Playlist(String playlistId, String playlistTitle) {
        this.playlistId = playlistId;
        this.playlistTitle = playlistTitle;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId='" + playlistId + '\'' +
                ", playlistTitle='" + playlistTitle + '\'' +
                '}';
    }
}
