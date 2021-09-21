package utilities;

public class Artist {

    private String artistName;
    private long artistId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Artist() {
    }

    /**
     *
     * @param artistName
     * @param artistId
     */
    public Artist(String artistName, long artistId) {
        super();
        this.artistName = artistName;
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

}