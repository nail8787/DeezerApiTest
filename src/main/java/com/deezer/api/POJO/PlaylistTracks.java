package com.deezer.api.POJO;

public class PlaylistTracks {

    private String playlistId;
    private String trackId;

    public PlaylistTracks(String playlistId, String trackId) {
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId='" + playlistId + '\'' +
                ", trackId='" + trackId + '\'' +
                '}';
    }
}
