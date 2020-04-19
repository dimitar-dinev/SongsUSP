package com.example.songsusp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.example.songsusp.db.AppDatabase;
import com.example.songsusp.db.entities.Song;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MYACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppDatabase database = AppDatabase.getInstance(this);

        database.getSongDao().getAllSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                for(Song song : songs) {
                    Log.d(TAG, song.getTitle() + " "  + song.getArtist());
                }
            }
        });


    }
}
