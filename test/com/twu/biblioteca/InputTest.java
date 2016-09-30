package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class InputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream in = new ByteArrayInputStream("Test".getBytes());

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }


    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }



    @Test
    public void getsUserInputAndReturnsString() throws Exception {

        System.setIn(in);

        assertEquals("Test", (new Input().returnString(in)));

    }

    @Test
    public void printsThePromptWhenRequestingUserInput() throws Exception {

        System.setIn(in);

        new Input().returnString(in);
        assertEquals(Input.PROMPT, outContent.toString());


    }


}