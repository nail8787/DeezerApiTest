package com.deezer.api.helpers.JsonReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.deezer.api.POJO.Track;

import java.io.File;
import java.io.IOException;

public class JsonReaderTrack {

    public static Track[] tracks;

    public static void getJson(String testDirectory) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            tracks = mapper.readValue(new File(testDirectory + "TracksData.json"), Track[].class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
