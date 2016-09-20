package com.twu.biblioteca;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
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
    Console console;

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    private void createTestBookOne() {
        testBookOne = new Book(console, "Dune", "Frank Herbert", "1965");
    }

    private void createTestBookTwo() {
        testBookTwo = new Book(console, "Gormengast", "Mervyn Peake", "1950");
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
        console = context.mock(Console.class);
        createTestBookOne();
        createTestBookTwo();

        context.checking(new Expectations() {{
            oneOf (console).message("Title: Dune");
            oneOf (console).message("Author: Frank Herbert");
            oneOf (console).message("Year: 1965");
            oneOf (console).message("Title: Gormengast");
            oneOf (console).message("Author: Mervyn Peake");
            oneOf (console).message("Year: 1950");
        }});

        testBookOne.printInfo();
        testBookTwo.printInfo();

    }

}
