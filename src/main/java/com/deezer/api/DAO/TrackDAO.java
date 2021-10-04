package com.deezer.api.DAO;

import com.deezer.api.POJO.Track;
import com.deezer.api.helpers.ConnectionFactory;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrackDAO implements PostgresDAO<Track> {
    @Override
    public Track getById(BigInteger id){
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from tracks where track_id=" + id.toString());
            while (rs.next()){
                return new Track(Integer.toString(rs.getInt("track_id")), rs.getString("name"));
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
    public void insert(Track track) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO tracks (track_id, name) VALUES (" + track.getTrackId() + ",'" + track.getTrackName() + "');");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Track track) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE tracks SET name ='" + track.getTrackName() + "'WHERE track_id=" + track.getTrackId() + ";");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Track track) {
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM tracks WHERE track_id=" +
                    track.getTrackId() + ";");
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
            statement.executeUpdate("DELETE FROM tracks;");
            connection.close(); //Возможно недостаточно лишь тут закрывать, в случае исключения
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Track> getAll(){
        ConnectionFactory connFactory = new ConnectionFactory();
        Connection connection = connFactory.getConnection();
        ArrayList<Track> trackList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from tracks");
            while (rs.next()){
                trackList.add(new Track(Integer.toString(rs.getInt("track_id")), rs.getString("name")));
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
        return trackList;
    }
}
