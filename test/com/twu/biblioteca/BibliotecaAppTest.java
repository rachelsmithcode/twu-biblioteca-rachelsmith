package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


import org.jmock.Expectations;
import org.jmock.Mockery;

import org.jmock.lib.legacy.ClassImposteriser;



public class BibliotecaAppTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

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
    public void printsAListOfTwoBooks() throws Exception {

        final BookList books = context.mock(BookList.class);

        context.checking(new Expectations() {{
            oneOf (books).printBooks();
        }});

        new BibliotecaApp().printBookList(books);
    }



}
