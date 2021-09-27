package com.deezer.api.helpers.JsonReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.deezer.api.POJO.Artist;

import java.io.File;
import java.io.IOException;

public class JsonReaderArtist {

    public static Artist[] artists;

    public static void getJson(String testDirectory) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            artists = mapper.readValue(new File(testDirectory + "ArtistData.json"), Artist[].class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
