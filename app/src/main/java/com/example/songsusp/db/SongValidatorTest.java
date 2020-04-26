package com.example.songsusp.db;

import org.testng.annotations.Test;

import static org.junit.Assert.*;


public class SongValidatorTest {

    @Test
    public void isSongValid() {

        // Duration tests

        assertTrue(SongValidator.isSongValid("t", "ko", "ko", "3:05", "genre", 2000));

//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "03:05", "genre", 2000)).isTrue();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "60:15", "genre", 2000)).isTrue();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "608:15", "genre", 2000)).isFalse();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "68:105", "genre", 2000)).isFalse();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "68:1", "genre", 2000)).isFalse();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "3:1", "genre", 2000)).isFalse();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", null, "genre", 2000)).isFalse();


        // Year
        assertTrue(SongValidator.isSongValid("t", "ko", "ko", "3:05", "genre", SongValidator.MINIMUM_YEAR));

//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "3:05", "genre", SongValidator.MAXIMUM_YEAR)).isTrue();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "3:05", "genre", 0)).isTrue();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "3:05", "genre", SongValidator.MINIMUM_YEAR - 1)).isFalse();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "3:05", "genre", SongValidator.MAXIMUM_YEAR + 1)).isFalse();
//        assertThat(SongValidator.isSongValid("t", "ko", "ko", "3:05", "genre", 4)).isFalse();
    }

}