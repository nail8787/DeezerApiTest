package com.deezer.api.stepdefs;

import com.deezer.api.DAO.PlaylistDAO;
import com.deezer.api.DAO.PlaylistTracksDAO;
import com.deezer.api.DAO.TrackDAO;
import com.deezer.api.POJO.Playlist;
import com.deezer.api.POJO.PlaylistTracks;
import com.deezer.api.POJO.Track;
import com.deezer.api.endpoints.EndPoints;
import com.deezer.api.helpers.playlistFactory;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;

import java.math.BigInteger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class addTracksToPlaylistDataBaseSteps {
    String playlist_id;

    private void addTrackDb(String trackName, String trackId) {
        TrackDAO trackDAO = new TrackDAO();
        trackDAO.insert(new Track(trackId, trackName));
        PlaylistTracksDAO playlistTracksDAO = new PlaylistTracksDAO();
        playlistTracksDAO.insert(new PlaylistTracks(playlist_id, trackId));
    }

    private void addTrackApi(String trackName, String trackId) {
        given().param("songs", trackId).pathParam("playlistId", playlist_id)
                .when().post(EndPoints.playlistTracks)
                .then().assertThat().body(equalTo("true"));
        System.out.println("Track \"" + trackName + "\" added to playlist");
    }

    private void checkTrackApi(String trackId) {
        given().pathParam("playlistId", playlist_id)
                .when().get(EndPoints.playlist)
                .then().assertThat().body("tracks.data.id", hasItem(Integer.parseInt(trackId)));
    }

    private void checkTrackDb(String trackId) {
        PlaylistTracksDAO playlistTracksDAO = new PlaylistTracksDAO();
        PlaylistTracks playlistTracks = playlistTracksDAO.getById(new BigInteger(trackId));
        Assert.assertEquals("Playlist doesn't have this track", playlist_id, playlistTracks.getPlaylistId());
        Assert.assertEquals("Track is not added to playlist's tracks", trackId, playlistTracks.getTrackId());
    }

    @Дано("Создан плейлист с названием {string} и добавлен в базу данных")
    public void playlistCreatedDb(String playlist_name) {
        playlist_id = playlistFactory.createPlaylist(playlist_name);
        PlaylistDAO playlistDAO = new PlaylistDAO();
        playlistDAO.insert(new Playlist(playlist_id, playlist_name));
        Playlist playlist = playlistDAO.getById(new BigInteger(playlist_id));
        Assert.assertEquals("Playlist is not added to database", playlist_id, playlist.getPlaylistId());
    }

    @Когда("Добавить песни {string}-id={string} и {string}-id={string} в плейлист")
    public void добавитьПесниIdИIdВПлейлист(String track1Name, String track1Id, String track2Name, String track2Id) {
        addTrackApi(track1Name, track1Id);
        addTrackApi(track2Name, track2Id);
        addTrackDb(track1Name, track1Id);
        addTrackDb(track2Name, track2Id);
    }

    @Тогда("Песни One More Time-id={string} и NO FUN-id={string} отображаются в плейлисте")
    public void песниIdИIdОтображаютсяВПлейлисте(String track1Id, String track2Id) {
        checkTrackApi(track1Id);
        checkTrackApi(track2Id);
        checkTrackDb(track1Id);
        checkTrackDb(track2Id);
    }
}
