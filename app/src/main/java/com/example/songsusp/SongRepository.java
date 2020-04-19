package com.example.songsusp;

import androidx.lifecycle.LiveData;

import com.example.songsusp.db.AppDatabase;
import com.example.songsusp.db.entities.Song;

import java.util.List;

public class SongRepository {
    private LiveData<List<Song>> songs;

    private final AppDatabase appDatabase;
    private final AppExecutors appExecutors;

    SongRepository(final AppDatabase appDatabase, final AppExecutors appExecutors) {
        this.appDatabase = appDatabase;
        this.appExecutors = appExecutors;

        songs = this.appDatabase.getSongDao().getAllSongs();
    }

    public LiveData<List<Song>> getSongs() {
        return songs;
    }

    /**
     * Methods accessing the database
     */
    public void insertSong(Song song) {
        appExecutors.diskIO().execute(() -> appDatabase.getSongDao().insert(song));
    }

    public void updateSong(Song song) {
        appExecutors.diskIO().execute(() -> appDatabase.getSongDao().update(song));
    }

    public void deleteSong(int id) {
        appExecutors.diskIO().execute(() -> appDatabase.getSongDao().delete(id));
    }

}
