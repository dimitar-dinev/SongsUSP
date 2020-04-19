package com.example.songsusp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.songsusp.SongRepository;
import com.example.songsusp.db.entities.Song;

import java.util.List;

public class SongViewModel extends ViewModel {

    private LiveData<List<Song>> songs;
    private final SongRepository repository;

    public SongViewModel(SongRepository repository) {
        this.repository = repository;

        songs  = repository.getSongs();
    }

    public LiveData<List<Song>> getSongs() {
        return songs;
    }

    public void insert(Song song) {
        repository.insertSong(song);
    }

    public void update(Song song) {
        repository.updateSong(song);
    }

    public void delete(int id) { repository.deleteSong(id);}
}
