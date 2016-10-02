package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MainMenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    final Options options = context.mock(Options.class);
    ArrayList<String> testList = new ArrayList<String>();
    final Input getInput = context.mock(Input.class);

    @Before
    public void createTestArray() {
        testList.add("Book List");
        testList.add("Movie List");
        testList.add("Checkout");
        testList.add("Return");
        testList.add("Quit");
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
    public void printsMainOptions() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(options).printOptions();

        }});

        new MainMenu(options, getInput).printMainOptions();
    }

    @Test
    public void selectMenuOptionQuit() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Quit"));
            exactly(1).of(options).getOptions();
            will(returnValue(testList));
            exactly(1).of(options).quit();

        }});

        new MainMenu(options, getInput).menuAction();

    }

    @Test
    public void selectMenuOptionPrintBookList() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Book List"));
            exactly(1).of(options).getOptions();
            will(returnValue(testList));
            exactly(1).of(options).printBookList();

        }});

        new MainMenu(options, getInput).menuAction();

    }

    @Test
    public void selectMenuOptionPrintMovieList() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Movie List"));
            exactly(1).of(options).getOptions();
            will(returnValue(testList));
            exactly(1).of(options).printMovieList();

        }});

        new MainMenu(options, getInput).menuAction();

    }

    @Test
    public void selectMenuOptionCheckoutBook() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Checkout Book"));
            exactly(1).of(options).getOptions();
            will(returnValue(testList));
            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Dune"));
            exactly(1).of(options).checkoutBook("Dune");


        }});

        new MainMenu(options, getInput).menuAction();
        String result = MainMenu.CHECKOUT_MESSAGE;
        Assert.assertThat(outContent.toString(), CoreMatchers.containsString(result));
    }

    @Test
    public void selectMenuOptionCheckoutMovie() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Checkout Movie"));
            exactly(1).of(options).getOptions();
            will(returnValue(testList));
            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Die Hard"));
            exactly(1).of(options).checkoutMovie("Die Hard");


        }});

        new MainMenu(options, getInput).menuAction();
        String result = MainMenu.CHECKOUT_MESSAGE;
        Assert.assertThat(outContent.toString(), CoreMatchers.containsString(result));

    }

    @Test
    public void selectMenuOptionReturnBook() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Return Book"));
            exactly(1).of(options).getOptions();
            will(returnValue(testList));
            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Dune"));
            exactly(1).of(options).returnBook("Dune");


        }});

        new MainMenu(options, getInput).menuAction();
        String result = MainMenu.RETURN_MESSAGE;
        Assert.assertThat(outContent.toString(), CoreMatchers.containsString(result));

    }

    @Test
    public void selectMenuOptionReturnMovie() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Return Movie"));
            exactly(1).of(options).getOptions();
            will(returnValue(testList));
            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("Die Hard"));
            exactly(1).of(options).returnMovie("Die Hard");


        }});

        new MainMenu(options, getInput).menuAction();
        String result = MainMenu.RETURN_MESSAGE;
        Assert.assertThat(outContent.toString(), CoreMatchers.containsString(result));

    }


    @Test
    public void selectMenuOptionInValidMenuOption() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("I am a cat"));
            exactly(1).of(options).getOptions();
            will(returnValue(testList));

        }});

        new MainMenu(options, getInput).menuAction();
        String result = MainMenu.INVALID_MESSAGE;
        Assert.assertThat(outContent.toString(), CoreMatchers.containsString(result));

    }

}















