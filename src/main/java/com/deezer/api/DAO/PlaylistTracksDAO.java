package com.deezer.api.DAO;

import com.deezer.api.POJO.PlaylistTracks;
import com.deezer.api.helpers.ConnectionFactory;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlaylistTracksDAO implements PostgresDAO<PlaylistTracks> {
    @Override
    public PlaylistTracks getById(BigInteger id){
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from playlist_tracks where track_id=" + id.toString());
            while (rs.next()){
                return new PlaylistTracks(String.valueOf(rs.getLong("playlist_id")), rs.getString("track_id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(PlaylistTracks playlistTracks) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO playlist_tracks (playlist_id, track_id) VALUES (" + playlistTracks.getPlaylistId() + ",'" + playlistTracks.getTrackId() + "');");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlaylistTracks playlistTracks) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE playlist_tracks SET track_id ='" + playlistTracks.getTrackId() + "'WHERE playlist_id=" + playlistTracks.getPlaylistId() + ";");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PlaylistTracks playlistTracks) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM playlist_tracks WHERE playlist_id=" +
                    playlistTracks.getPlaylistId() + ";");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM playlist_tracks;");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<PlaylistTracks> getAll(){
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        ArrayList<PlaylistTracks> playlistList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from playlist_tracks");
            while (rs.next()){
                playlistList.add(new PlaylistTracks(Integer.toString(rs.getInt("playlist_id")), rs.getString("track_id")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return playlistList;
    }
}

