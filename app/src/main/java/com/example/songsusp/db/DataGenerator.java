package com.example.songsusp.db;

import com.example.songsusp.db.entities.Song;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    static List<Song> generateSongs() {
        List<Song> songs = new ArrayList<>();

        songs.add(new Song("Toosie Slide", "Drake", "Toosie Slide", 240, "Hip-Hop", 2020));

        songs.add(new Song("No More Parties In LA", "Kanye West", "The Life Of Pablo", 240, "Hip-Hop", 2016));

        songs.add(new Song("Starboy", "The Weeknd", "Starboy", 286, "Hip-Hop", 2016));

        songs.add(new Song("Nonstop", "Drake", "Scorpion", 201, "Hip-Hop", 2018));

        songs.add(new Song("Alone Again", "The Weeknd", "After Hours", 174, "R&B", 2020));

        songs.add(new Song("Nothing Else Matters", "Metallica", "The Black Album", 165, "Heavy Metal", 1990));

        songs.add(new Song("Real Friends", "Kanye West", "The Life Of Pablo", 276, "Hip-Hop", 2016));

        songs.add(new Song("The Unforgiven", "Metallica", "The Black Album", 265, "Heavy Metal", 1990));

        songs.add(new Song("The Less I Know The Better", "Tame Impala", "Currents", 265, "Psychedelic Rock", 2015));

        return songs;
    }
}
