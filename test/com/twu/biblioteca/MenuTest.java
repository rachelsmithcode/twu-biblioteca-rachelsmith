package com.twu.biblioteca;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    final MenuOption menuOption = context.mock(MenuOption.class);
    ArrayList<MenuOption> testList = new ArrayList<MenuOption>();
    final GetInput getInput = context.mock(GetInput.class);

    @Before
    public void createTestArray() {
        testList.add(menuOption);
        testList.add(menuOption);
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
    public void printsTheNameOfEachMenuItemInArrayList() throws Exception {

        context.checking(new Expectations() {{

            exactly(2).of(menuOption).printName();

        }});

        new Menu(testList).printList();
    }

    @Test
    public void youCanSelectTheBookListFromTheMenuItemArrayList() throws Exception {

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("Book List"));
            oneOf(menuOption).returnName();
            will(returnValue("Book List"));
            oneOf(menuOption).select();

        }});

            new Menu(testList).menuAction(getInput);
    }

    @Test
    public void youCanSelectQuitFromTheMenuItemArrayList() throws Exception {

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("Quit"));
            oneOf(menuOption).returnName();
            will(returnValue("Book List"));
            oneOf(menuOption).returnName();
            will(returnValue("Quit"));
            oneOf(menuOption).select();

        }});

        new Menu(testList).menuAction(getInput);
    }

    @Test
    public void ifUserInputIsRecievedThatDoesNotMatchAnItemAnErrorMessageWillDisplay() throws Exception {

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("I am a cat"));
            oneOf(menuOption).returnName();
            will(returnValue("Book List"));
            oneOf(menuOption).returnName();
            will(returnValue("Quit"));

        }});

        new Menu(testList).menuAction(getInput);
        assertEquals("Please select a valid item!\n", outContent.toString());
    }


    @Test
    public void youCanCheckOutABookFromTheBookListArrayInTheMenuItemArrayList() throws Exception {

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("Dune"));
            oneOf(menuOption).returnName();
            will(returnValue("Book List"));
            oneOf(menuOption).checkOutBook("Dune");

        }});

        new Menu(testList).checkOutItem(getInput);
    }

    @Test
    public void youCanReturnABookFromTheBookListArrayInTheMenuItemArrayList() throws Exception {

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("Dune"));
            oneOf(menuOption).returnName();
            will(returnValue("Book List"));
            oneOf(menuOption).returnBook("Dune");

        }});

        new Menu(testList).returnItem(getInput);
    }



}
















