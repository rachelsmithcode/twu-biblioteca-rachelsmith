package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MovieItemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private MovieItem testMovieOne;
    private MovieItem testMovieTwo;

    private void createTestMovieTwo() {
        testMovieTwo = new MovieItem("Gormengast", "Mervyn Peake", "1950", "5");
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @Before
    public void createMovieBookOne() {
        testMovieOne = new MovieItem("Dune", "Frank Herbert", "1965", "5");
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


    @Test
    public void printsFullMovieDetails() throws Exception {

        createTestMovieTwo();

        String result = "Dune | Frank Herbert | 1965 | 5\n" +
                "Gormengast | Mervyn Peake | 1950 | 5\n";

        testMovieOne.printDetails();
        testMovieTwo.printDetails();
        assertEquals(result, outContent.toString());

    }

    @Test
    public void returnsTitleAsAString() throws Exception {

        assertEquals("Dune", (testMovieOne.getTitle()));
    }

    @Test
    public void returnsDirectorAsAString() throws Exception {

        assertEquals("Frank Herbert", (testMovieOne.getDirector()));
    }

    @Test
    public void returnsYearAsAString() throws Exception {

        assertEquals("1965", (testMovieOne.getYear()));
    }

    @Test
    public void returnsRatingAsAString() throws Exception {

        assertEquals("5", (testMovieOne.getRaiting()));
    }

    @Test
    public void bookCanBeCheckedOutAndMarkedAsNotInStock() throws Exception {

        testMovieOne.beCheckedOut();

        assertEquals(false, (testMovieOne.isInStock()));
    }

    @Test
    public void bookCanBeReturnedAndMarkedInStock() throws Exception {

        testMovieOne.beReturned();

        assertEquals(true, (testMovieOne.isInStock()));
    }




}
