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

public class MenuListTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Test
    public void printsTheNameOfEachMenuItemInArrayList() throws Exception {

        final MenuItem menuItem = context.mock(MenuItem.class);

        ArrayList<MenuItem> testList = new ArrayList<MenuItem>();
        testList.add(menuItem);
        testList.add(menuItem);

        context.checking(new Expectations() {{

            exactly(2).of(menuItem).printName();

        }});

        new MenuList(testList).printList();
    }

    @Test
    public void youCanSelectTheBookListFromTheMenuItemArrayList() throws Exception {

        final MenuItem menuItem = context.mock(MenuItem.class);
        final GetInput getInput = context.mock(GetInput.class);

        ArrayList<MenuItem> testList = new ArrayList<MenuItem>();
        testList.add(menuItem);
        testList.add(menuItem);

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("Book List"));
            oneOf(menuItem).returnName();
            will(returnValue("Book List"));
            oneOf(menuItem).select();

        }});

            new MenuList(testList).selectItem(getInput);
    }

    @Test
    public void youCanSelectQuitFromTheMenuItemArrayList() throws Exception {

        final MenuItem menuItem = context.mock(MenuItem.class);
        final GetInput getInput = context.mock(GetInput.class);

        ArrayList<MenuItem> testList = new ArrayList<MenuItem>();
        testList.add(menuItem);
        testList.add(menuItem);

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("Quit"));
            oneOf(menuItem).returnName();
            will(returnValue("Book List"));
            oneOf(menuItem).returnName();
            will(returnValue("Quit"));
            oneOf(menuItem).select();

        }});

        new MenuList(testList).selectItem(getInput);
    }

    @Test
    public void ifUserInputIsRecievedThatDoesNotMatchAnItemAnErrorMessageWillDisplay() throws Exception {

        final MenuItem menuItem = context.mock(MenuItem.class);
        final GetInput getInput = context.mock(GetInput.class);

        ArrayList<MenuItem> testList = new ArrayList<MenuItem>();
        testList.add(menuItem);
        testList.add(menuItem);

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("I am a cat"));
            oneOf(menuItem).returnName();
            will(returnValue("Book List"));
            oneOf(menuItem).returnName();
            will(returnValue("Quit"));

        }});

        new MenuList(testList).selectItem(getInput);
        assertEquals("Please select a valid item!\n", outContent.toString());
    }


    @Test
    public void youCanCheckOutABookFromTheBookListArrayInTheMenuItemArrayList() throws Exception {

        final MenuItem menuItem = context.mock(MenuItem.class);
        final GetInput getInput = context.mock(GetInput.class);

        ArrayList<MenuItem> testList = new ArrayList<MenuItem>();
        testList.add(menuItem);
        testList.add(menuItem);

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("Dune"));
            oneOf(menuItem).returnName();
            will(returnValue("Book List"));
            oneOf(menuItem).checkOut("Dune");

        }});

        new MenuList(testList).checkOutItem(getInput);
    }

    @Test
    public void youCanReturnABookFromTheBookListArrayInTheMenuItemArrayList() throws Exception {

        final MenuItem menuItem = context.mock(MenuItem.class);
        final GetInput getInput = context.mock(GetInput.class);

        ArrayList<MenuItem> testList = new ArrayList<MenuItem>();
        testList.add(menuItem);
        testList.add(menuItem);

        context.checking(new Expectations() {{

            oneOf(getInput).returnString();
            will(returnValue("Dune"));
            oneOf(menuItem).returnName();
            will(returnValue("Book List"));
            oneOf(menuItem).returnBook("Dune");

        }});

        new MenuList(testList).returnItem(getInput);
    }



}
















