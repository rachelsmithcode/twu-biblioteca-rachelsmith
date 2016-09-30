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


public class OptionsTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    public final ArrayList<String> testOptionsList = new ArrayList();

    @Before
    public void addItemsToTestOptionsList() {
        testOptionsList.add("Book List");
        testOptionsList.add("Check Out Book");
        testOptionsList.add("Return Book");
        testOptionsList.add("Quit");
    }

    final BookItem bookItem = context.mock(BookItem.class);

    public final ArrayList<BookItem> testBookList = new ArrayList();

    @Before
    public void addItemsToTestBookList() {

        testBookList.add(bookItem);
        testBookList.add(bookItem);
    }

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
    public void testPrintOptions() throws Exception  {
        new Options(testOptionsList, testBookList).printOptions();

        String result = "Book List\n" +
                "Check Out Book\n" +
                "Return Book\n" +
                "Quit\n";

        assertEquals(result, outContent.toString());
    }

    @Test
    public void testGetOptions() throws Exception  {

        assertEquals(testOptionsList, new Options(testOptionsList, testBookList).getOptions());
    }

    @Test
    public void testQuit() throws Exception {

        exit.expectSystemExit();
        new Options(testOptionsList, testBookList).quit();

    }

    @Test
    public void testPrintBookList() throws Exception {

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).isInStock();
            will(returnValue(true));
            exactly(2).of(bookItem).printDetails();

        }});

        new Options(testOptionsList, testBookList).printBookList();

    }

    @Test
    public void testCheckoutBookFromStock() {

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).isInStock();
            will(returnValue(true));
            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            exactly(1).of(bookItem).beCheckedOut();

        }});

        new Options(testOptionsList, testBookList).checkoutBook("Dune");
        assertEquals("Thank you! Enjoy the book\n", outContent.toString());

    }

    @Test
    public void testCannotCheckoutBookThatIsOutOfStock() {

        context.checking(new Expectations() {{

            oneOf(bookItem).isInStock();
            will(returnValue(true));
            oneOf(bookItem).isInStock();
            will(returnValue(false));
            exactly(1).of(bookItem).returnName();
            will(returnValue("Gormengast"));

        }});

        new Options(testOptionsList, testBookList).checkoutBook("Dune");
        assertEquals("That book is not available.\n", outContent.toString());

    }



    @Test
    public void testReturnBookFromBookListThatIsOutOfStock() {

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).isInStock();
            will(returnValue(false));
            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            exactly(1).of(bookItem).beReturned();

        }});

        new Options(testOptionsList, testBookList).returnBook("Dune");
        assertEquals("Thank you for returning the book.\n", outContent.toString());

    }

    @Test
    public void testCannotReturnBookFromBookListThatIsInStockAlready() {

        context.checking(new Expectations() {{

            oneOf(bookItem).isInStock();
            will(returnValue(true));
            oneOf(bookItem).isInStock();
            will(returnValue(false));
            exactly(1).of(bookItem).returnName();
            will(returnValue("Gormengast"));

        }});

        new Options(testOptionsList, testBookList).returnBook("Dune");
        assertEquals("That is not a valid book to return.\n", outContent.toString());
    }


}
