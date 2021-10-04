package com.deezer.api.DAO;

import com.deezer.api.POJO.Playlist;
import com.deezer.api.helpers.ConnectionFactory;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlaylistDAO implements PostgresDAO<Playlist> {
    @Override
    public Playlist getById(BigInteger id){
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from playlists where playlist_id=" + id.toString());
            while (rs.next()){
                return new Playlist(String.valueOf(rs.getLong("playlist_id")), rs.getString("title"));
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
    public void insert(Playlist playlist) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO playlists (playlist_id, title) VALUES (" + playlist.getPlaylistId() + ",'" + playlist.getPlaylistTitle() + "');");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Playlist playlist) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE playlists SET name ='" + playlist.getPlaylistTitle() + "'WHERE playlist_id=" + playlist.getPlaylistId() + ";");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Playlist playlist) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM playlists WHERE playlist_id=" +
                    playlist.getPlaylistId() + ";");
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
            statement.executeUpdate("DELETE FROM playlists;");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Playlist> getAll(){
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        ArrayList<Playlist> playlistList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from playlists");
            while (rs.next()){
                playlistList.add(new Playlist(Integer.toString(rs.getInt("playlist_id")), rs.getString("title")));
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
