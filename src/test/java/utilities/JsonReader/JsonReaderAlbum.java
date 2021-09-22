package utilities.JsonReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.POJO.Album;

import java.io.File;
import java.io.IOException;

public class JsonReaderAlbum {

    public static Album[] albums;

    public static void getJson(String testDirectory) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            albums = mapper.readValue(new File(testDirectory + "AlbumData.json"), Album[].class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
