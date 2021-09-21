package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.Track;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static Track[] tracks;

    public static void getJson(String testDirectory) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            tracks = mapper.readValue(new File(testDirectory + "TracksData.json")
                    , Track[].class);
//            for (int i = 0; i < tracks.length; i++){
//                System.out.println(tracks[i].getTrackName() + " - id = " + tracks[i].getTrackId());
//            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
