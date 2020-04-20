package com.example.songsusp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.songsusp.R;
import com.example.songsusp.SongsApplication;
import com.example.songsusp.db.AppDatabase;
import com.example.songsusp.db.entities.Song;
import com.example.songsusp.viewmodel.SongViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddSongFragment.AddSongListener {

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

        FloatingActionButton fab = findViewById(R.id.addButton);
        fab.setOnClickListener(v -> showAddFragmentDialog());

        addOnItemSwipeDelete(recyclerView, ItemTouchHelper.LEFT);
    }

    private void showAddFragmentDialog() {
        AddSongFragment addSongFragment = AddSongFragment.newInstance();
        addSongFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        addSongFragment.show(getSupportFragmentManager(), "add_fragment");
    }

    @Override
    public void onAddSong(final Song song) {
        songViewModel.insert(song);
    }

    public void onDeleteSong(final int id) { songViewModel.delete(id);}

    private void addOnItemSwipeDelete(RecyclerView recyclerView, int direction) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, direction) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                onDeleteSong(songAdapter.getSong(position).getId());
            }

        }).attachToRecyclerView(recyclerView);
    }
}
