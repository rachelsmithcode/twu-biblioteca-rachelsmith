package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class BookTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Book testBookOne;
    private Book testBookTwo;

    private void createTestBookOne() {
        testBookOne = new Book("Dune", "Frank Herbert", "1965");
    }

    private void createTestBookTwo() {
        testBookTwo = new Book("Gormengast", "Mervyn Peake", "1950");
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


    @Test
    public void printsFullBookDetails() throws Exception {
        createTestBookOne();
        createTestBookTwo();

        String testResult = "Title: Dune\n" + "Author: Frank Herbert\n" + "Year: 1965\n"
                + "Title: Gormengast\n" + "Author: Mervyn Peake\n" + "Year: 1950\n";

        testBookOne.printInfo();
        testBookTwo.printInfo();
        assertEquals(testResult, outContent.toString());
    }

}
