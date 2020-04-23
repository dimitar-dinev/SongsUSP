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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.songsusp.R;
import com.example.songsusp.SongsApplication;
import com.example.songsusp.db.AppDatabase;
import com.example.songsusp.db.entities.Song;
import com.example.songsusp.ui.utils.KeyboardUtils;
import com.example.songsusp.viewmodel.SongViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddSongFragment.AddSongListener, SongAdapter.onSongClickListener, EditSongFragment.EditSongListener {

    static final String TAG = "MYACTIVITY";

    public enum SongFilter {
        TITLE, ARTIST, GENRE
    }

    public static SongFilter currentFilter = SongFilter.TITLE;

    private SongViewModel songViewModel;
    private SongAdapter songAdapter;

    private SearchView searchView;
    private MenuItem menuCurrentCheckedFilter;

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

        songAdapter.setListener(this);

        addOnItemSwipeDelete(recyclerView, ItemTouchHelper.LEFT);
    }

    @Override
    public void onSongClick(Song song) {
        EditSongFragment songFragment = EditSongFragment.newInstance(song);
        songFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        View v = getCurrentFocus();
        if (v != null) {
            v.clearFocus();
        }

        songFragment.show(getSupportFragmentManager(), "edit_fragment");
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

    @Override
    public void onEditSong(final Song song) {
        songViewModel.update(song);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        menuCurrentCheckedFilter = menu.findItem(R.id.menu_filterTitle);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        configureSearchView(searchItem);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_filterTitle:
                onNewFilterChecked(item, SongFilter.TITLE);
                return true;

            case R.id.menu_filterArtist:
                onNewFilterChecked(item, SongFilter.ARTIST);
                return true;

            case R.id.menu_filterGenre:
                onNewFilterChecked(item, SongFilter.GENRE);
                return true;

            case R.id.app_bar_search:
                searchView.requestFocus();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onNewFilterChecked(final MenuItem selectedMenuItem, final SongFilter selectedFilter ) {
        if (currentFilter != selectedFilter) {
            menuCurrentCheckedFilter.setIcon(null);
            selectedMenuItem.setIcon(R.drawable.ic_check);
            menuCurrentCheckedFilter = selectedMenuItem;
            currentFilter = selectedFilter;
            songAdapter.getFilter().filter(searchView.getQuery());
        }
    }

    private void configureSearchView(final MenuItem searchItem) {
        searchView = (SearchView) searchItem.getActionView();
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                songAdapter.getFilter().filter(newText);
                return false;
            }
        });

        searchView.setOnQueryTextFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                KeyboardUtils.showKeyboard(MainActivity.this, view.findFocus());
            } else {
                KeyboardUtils.hideKeyboard(MainActivity.this);
            }
        });


        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                songAdapter.getFilter().filter(null);
                KeyboardUtils.hideKeyboard(MainActivity.this);
                return true;
            }
        });
    }
}
