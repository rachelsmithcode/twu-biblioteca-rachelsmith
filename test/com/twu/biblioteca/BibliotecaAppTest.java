package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

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
    public void printsWelcomeMessageAtStart() throws Exception {
        new BibliotecaApp().welcomeMessage();
        assertEquals("Welcome to the Biblioteca Experience\n", outContent.toString());
    }

    @Test
    public void printsAListOfBooks() throws Exception {
        new BibliotecaApp().printBookList();
        assertEquals("Book One\n", outContent.toString());
    }


}
