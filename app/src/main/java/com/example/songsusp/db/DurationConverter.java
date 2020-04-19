package com.example.songsusp.db;

/**
 * Handles conversion of song duration from int to a formatted string since
 * user will input duration in a string format, not in seconds
 */
public class DurationConverter {

    public static int toInt(String duration) {
        if (duration.isEmpty()) {
            return 0;
        }
        int indexOfSeparator = duration.indexOf(':');
        int minutes = Integer.parseInt(duration.substring(0, indexOfSeparator));
        int seconds = Integer.parseInt(duration.substring(indexOfSeparator + 1));
        return minutes * 60 + seconds;
    }

    public static String toString(int duration) {
        if (duration == 0) {
            return "";
        }

        int minutes = duration / 60;
        int seconds = duration % 60;

        // ((minutes / 10 == 0) ? "0" + minutes : minutes )
        return minutes +  ":" + ((seconds / 10 == 0) ? "0" + seconds : seconds);
    }
}
