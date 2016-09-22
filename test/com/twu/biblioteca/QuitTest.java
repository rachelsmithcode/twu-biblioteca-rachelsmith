package com.twu.biblioteca;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class QuitTest {


    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

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
    public void returnsOptionNameAsAString() throws Exception {

        assertEquals(Quit.ITEM_NAME, (new Quit().returnName()));
    }

    @Test
    public void canBeSelectedWhichWillQuitProgram() throws Exception {

        exit.expectSystemExit();
        new Quit().select();

    }

    @Test
    public void printsListsNameToConsole() throws Exception {

        new Quit().printName();
        assertEquals(Quit.ITEM_NAME + "\n", outContent.toString());

    }

    @Test
    public void quitCannotBeCheckedOut() throws Exception {

        new Quit().checkOutBook("Quit");
        assertEquals(Quit.INVALID_SELECTION + "\n", outContent.toString());

    }

    @Test
    public void quitCannotBeReturned() throws Exception {

        new Quit().returnBook("Quit");
        assertEquals(Quit.INVALID_SELECTION + "\n", outContent.toString());

    }

}

