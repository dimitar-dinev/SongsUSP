package com.example.songsusp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.example.songsusp.db.AppDatabase;
import com.example.songsusp.db.entities.Song;
import com.example.songsusp.viewmodel.SongViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MYACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase database = AppDatabase.getInstance(this);

        SongRepository songRepository = new SongRepository(database);

        SongViewModel songViewModel = new SongViewModel(songRepository);

        songViewModel.getSongs().observe(this, songs -> {
            for(Song song : songs) {
                Log.d(TAG, song.getTitle() + " "  + song.getArtist());
            }
        });

    }
}
