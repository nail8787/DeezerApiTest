package com.deezer.api.helpers;

import com.deezer.api.helpers.JsonReader.JsonReaderAlbum;
import com.deezer.api.helpers.JsonReader.JsonReaderArtist;
import com.deezer.api.helpers.JsonReader.JsonReaderTrack;
import com.deezer.api.POJO.Album;
import com.deezer.api.POJO.Artist;
import com.deezer.api.POJO.Track;

public class Searching {

    public static long findArtistByName(String trackName) {
        long artistId = -1;
        for (Artist current : JsonReaderArtist.artists) {
            if (current.getArtistName().equals(trackName))
                artistId = current.getArtistId();
        }
        return artistId;
    }

    public static String findTrackByName(String trackName) {
        String trackId = "";
        for (Track current : JsonReaderTrack.tracks) {
            if (current.getTrackName().equals(trackName))
                trackId = current.getTrackId();
        }
        return trackId;
    }

    public static String findAlbumByName(String albumName) {
        String albumId = "";
        for (Album current : JsonReaderAlbum.albums) {
            if (current.getAlbumName().equals(albumName))
                albumId = current.getAlbumId();
        }
        return albumId;
    }
}
