package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GetInputTest {

    //Find a way to input test in a test, so far no good.

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

        assertEquals("Test", (new GetInput().returnString(in)));

    }

    @Test
    public void printsThePromptWhenRequestingUserInput() throws Exception {

        System.setIn(in);

        new GetInput().returnString(in);
        assertEquals(GetInput.PROMPT, outContent.toString());


    }


}