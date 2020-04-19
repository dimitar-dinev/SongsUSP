package com.example.songsusp;

import android.app.Application;

import com.example.songsusp.db.AppDatabase;
import com.example.songsusp.viewmodel.SongViewModel;

public class AppContainer {

    private AppDatabase appDatabase;
    private SongRepository songRepository;
    private AppExecutors appExecutors;
    public SongViewModel songViewModel;

    public AppContainer(Application application) {
        this.appExecutors = new AppExecutors();
        this.appDatabase = AppDatabase.getInstance(application.getApplicationContext());

        this.songRepository = new SongRepository(this.appDatabase, this.appExecutors);

        this.songViewModel = new SongViewModel(songRepository);
    }
}
