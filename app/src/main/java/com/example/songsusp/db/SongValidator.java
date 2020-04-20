package com.example.songsusp.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongValidator {

    /* ERROR CODES */
    public static final int TITLE_ERROR = 0;
    public static final int ARTIST_ERROR = 1;
    public static final int ALBUM_ERROR = 2;
    public static final int DURATION_ERROR = 3;
    public static final int GENRE_ERROR = 4;
    public static final int YEAR_ERROR = 5;


    public static final int MINIMUM_YEAR = 1950;
    public static final int MAXIMUM_YEAR = 2020;


    private static int error;

    public static int getLastError() {
        return error;
    }

    public static boolean isSongValid(final String title, final String artist, final String album, final String duration, final String genre, final int year) {
        if (!isTitleValid(title)) {
            error = TITLE_ERROR;
            return false;
        }

        if (!isArtistValid(artist)) {
            error = ARTIST_ERROR;
            return false;
        }

        if (!isAlbumValid(album)) {
            error = ALBUM_ERROR;
            return false;
        }

        if (!isDurationValid(duration)) {
            error = DURATION_ERROR;
            return false;
        }

        if (!isGenreValid(genre)) {
            error = GENRE_ERROR;
            return false;
        }

        if (!isYearValid(year)) {
            error = YEAR_ERROR;
            return false;
        }

        return true;
    }


    private static boolean isTitleValid(final String title) {
        if (title != null) {
            return title.length() > 0;
        }
        return false;
    }

    private static boolean isArtistValid(final String artist) {
        if (artist != null) {
            return artist.length() > 1;
        }
        return false;
    }

    private static boolean isAlbumValid(final String album) {
        return true;
    }

    private static boolean isDurationValid(final String duration) {

        if (duration == null) {
            return false;
        }

        if (duration.isEmpty()) {
            return true;
        } else {
            String pattern = "^[0-5]?\\d:[0-5]\\d$";
            Pattern p = Pattern.compile(pattern);

            return p.matcher(duration).matches();
        }
    }

    private static boolean isGenreValid(final String genre) {
        return true;
    }

    private static boolean isYearValid(final int year) {
        return (year == 0) || (year >= MINIMUM_YEAR && year <= MAXIMUM_YEAR);
    }
}
