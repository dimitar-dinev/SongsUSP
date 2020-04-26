package com.example.songsusp.db;

import org.junit.Test;

import static org.junit.Assert.*;

public class DurationConverterTest {

    @Test
    public void toInt() {
        assertEquals(0, DurationConverter.toInt(""));
        assertEquals(180, DurationConverter.toInt("3:00"));
        assertEquals(181, DurationConverter.toInt("3:01"));
        assertEquals(180, DurationConverter.toInt("03:00"));
        assertEquals(181, DurationConverter.toInt("03:01"));
    }

    @Test
    public void testToString() {
        assertEquals("", DurationConverter.toString(0));
        assertEquals("0:01", DurationConverter.toString(1));
        assertEquals("3:00", DurationConverter.toString(180));
        assertEquals("3:01", DurationConverter.toString(181));
    }
}