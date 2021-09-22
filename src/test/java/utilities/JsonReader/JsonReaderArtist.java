package utilities.JsonReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.POJO.Artist;

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
