package com.example.songsusp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.example.songsusp.R;
import com.example.songsusp.SongsApplication;
import com.example.songsusp.db.AppDatabase;
import com.example.songsusp.db.entities.Song;
import com.example.songsusp.viewmodel.SongViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MYACTIVITY"; //constant tag 

    private SongViewModel songViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songViewModel = ((SongsApplication) getApplication()).appContainer.songViewModel;

        songViewModel.getSongs().observe(this, songs -> {
            for(Song song : songs) {
                Log.d(TAG, song.getTitle() + " "  + song.getArtist());
            }
        });

       songViewModel.insert(new Song("DKD", "DKD", "DKDKD", 200, "dz", 2004));
    }
}
