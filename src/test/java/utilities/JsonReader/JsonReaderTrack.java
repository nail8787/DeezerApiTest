package utilities.JsonReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.POJO.Track;

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
