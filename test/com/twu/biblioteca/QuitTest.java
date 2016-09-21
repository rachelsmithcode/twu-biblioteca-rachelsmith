package com.twu.biblioteca;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class QuitTest {


    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

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
    public void returnsListNameAsAString() throws Exception {

        assertEquals("Quit", (new Quit().returnName()));
    }

    @Test
    public void canBeSelectedWhichWillQuitProgram() throws Exception {

        exit.expectSystemExit();
        new Quit().select();

    }

    @Test
    public void printsListsNameToConsole() throws Exception {

        new Quit().printName();
        assertEquals("Quit\n", outContent.toString());

    }

}

