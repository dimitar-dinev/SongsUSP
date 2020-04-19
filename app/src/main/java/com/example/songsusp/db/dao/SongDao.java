package com.example.songsusp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.songsusp.db.entities.Song;

import java.util.List;

@Dao
public interface SongDao {

    @Insert
    public void insert(Song song);

    @Insert
    public void insert(List<Song> songs);

    @Update
    public void update(Song song);

    @Query("SELECT * from Song")
    public LiveData<List<Song>> getAllSongs();

    @Query("DELETE FROM Song WHERE id = :id")
    public void delete(int id);
}
