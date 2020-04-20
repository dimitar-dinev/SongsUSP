package com.example.songsusp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


    private SongAdapter songAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        songAdapter = new SongAdapter();
        recyclerView.setAdapter(songAdapter);


        songViewModel = ((SongsApplication) getApplication()).appContainer.songViewModel;

        songViewModel.getSongs().observe(this, songs -> songAdapter.setSongsList(songs));
    }
}
