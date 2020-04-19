package com.example.songsusp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Set up the threads of the app
public class AppExecutors {
    private Executor mDiskIO;

    private AppExecutors(Executor diskIO) {
        this.mDiskIO = diskIO;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor());
    }

    public Executor diskIO() {
        return mDiskIO;
    }
}
