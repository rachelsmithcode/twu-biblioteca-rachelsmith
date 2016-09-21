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

public class MenuListTest {


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
            oneOf(menuItem).returnName().toLowerCase();
            will(returnValue("Book List"));
            oneOf(menuItem).select();
            allowing(getInput).returnString();
            will(returnValue("Book List"));
            allowing(menuItem).returnName().toLowerCase();
            will(returnValue("Book List"));
            allowing(menuItem).select();


        }});

        long start = System.currentTimeMillis();
        long end = start + 60*10000; // 60 seconds * 1000 ms/sec
        while (System.currentTimeMillis() < end)
        {
            new MenuList(testList).selectItem(getInput);
        }
    }


}







//    @Test
//    public void returnsErrorMessageToAnyOtherStringRequest() throws Exception {
//
//        final GetInput getInput = context.mock(GetInput.class);
//        final ConsoleObject console = context.mock(ConsoleObject.class);
//        final List list = context.mock(List.class);
//
//        context.checking(new Expectations() {{
//
//            oneOf(getInput).returnString();
//            will(returnValue("I am a cat"));
//            oneOf(console).printMessage("Please select a valid item!");
//            oneOf(getInput).returnString();
//            will(returnValue("Book List"));
//            oneOf(list).printBooks();
//
//        }});
//
//        new MenuList(console).selectItem(getInput, list);
//    }

















