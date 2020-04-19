package com.example.songsusp;

import androidx.lifecycle.LiveData;

import com.example.songsusp.db.AppDatabase;
import com.example.songsusp.db.entities.Song;

import java.util.List;

public class SongRepository {
    private LiveData<List<Song>> songs;

    private final AppDatabase appDatabase;

    SongRepository(final AppDatabase appDatabase) {
        this.appDatabase = appDatabase;

        songs = this.appDatabase.getSongDao().getAllSongs();
    }

    public LiveData<List<Song>> getSongs() {
        return songs;
    }

    /**
     * Methods accessing the database
     */
    public void insertSong(Song song) {
        // TODO:
    }

    public void updateSong(Song song) {
        // TODO:
    }

    public void deleteSong(int id) {
        // TODO:
    }

}
