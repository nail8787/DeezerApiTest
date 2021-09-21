package utilities;

public class Track {

    private String trackId;
    private String trackName;

    /**
     * No args constructor for use in serialization
     */
    public Track() {
    }

    /**
     * @param trackId
     * @param trackName
     */
    public Track(String trackId, String trackName) {
        this.trackId = trackId;
        this.trackName = trackName;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }


}
