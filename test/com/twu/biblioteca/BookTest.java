package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by RachelSmith on 18/09/2016.
 */
public class BookTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void printsTitleOfBook() throws Exception {
        Book book = new Book();
        book.setTitle("Dune");
        book.printTitle();
        assertEquals("Title: Dune\n", outContent.toString());
    }

    @Test
    public void printsAuthorOfBook() throws Exception {
        Book book = new Book();
        book.setAuthor("Frank Herbert");
        book.printAuthor();
        assertEquals("Author: Frank Herbert\n", outContent.toString());
    }

    @Test
    public void printsYearOfBook() throws Exception {
        Book book = new Book();
        book.setYear("1965");
        book.printYear();
        assertEquals("Year: 1965\n", outContent.toString());
    }

}
