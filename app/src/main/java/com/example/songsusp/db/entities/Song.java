package com.example.songsusp.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.songsusp.db.DurationConverter;

@Entity(tableName = "Song")
public class Song {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String artist;

    private String album;

    private int duration;

    private String genre;

    private int year;

    public Song(String title, String artist, String album, int duration, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.genre = genre;
        this.year = year;
    }

    public Song(String title, String artist, String album, String duration, String genre, int year) {
        this(title, artist, album, DurationConverter.toInt(duration), genre, year);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    public String getDurationFormatted() { return DurationConverter.toString(duration); }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }
}
