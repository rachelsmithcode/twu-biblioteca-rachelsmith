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

public class BookListTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    final BookItem bookItem = context.mock(BookItem.class);
    ArrayList<BookItem> testList = new ArrayList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void createTestArray() {

        testList.add(bookItem);

    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void printsListsNameToConsole() throws Exception {

        new BookList(testList).printName();
        assertEquals("Book List\n", outContent.toString());

    }

    @Test
    public void returnsListNameAsAString() throws Exception {

        assertEquals("Book List", (new BookList(testList).returnName()));
    }

    @Test
    public void canBeSelectedAndPrintsDetailsForAllBooksInArrayList() throws Exception {

        testList.add(bookItem);

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).isInStock();
            will(returnValue(false));
            exactly(2).of(bookItem).printDetails();

        }});

        new BookList(testList).select();
    }

    @Test
    public void aCheckedOutBookShouldNotAppearWhenBookListIsSelected() throws Exception {

        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).isInStock();
            will(returnValue(true));
            oneOf(bookItem).isInStock();
            will(returnValue(false));

            oneOf(bookItem).printDetails();

        }});

        new BookList(testList).select();
    }

    @Test
    public void canCheckOutABookByTitleFromBooksInArrayList() throws Exception {

        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).beCheckedOut();
            oneOf(bookItem).isInStock();
            will(returnValue(false));

        }});

        new BookList(testList).checkOutBook("Dune");
    }

    @Test
    public void printsConfirmationMessageToConsoleOnSuccessfulCheckout() throws Exception {

        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).beCheckedOut();
            oneOf(bookItem).isInStock();
            will(returnValue(false));

        }});

        new BookList(testList).checkOutBook("Dune");
        assertEquals("Thank you! Enjoy the book\n", outContent.toString());

    }

    @Test
    public void printsErrorMessageIfBookIsAlreadyCheckedOut() throws Exception {

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).isInStock();
            will(returnValue(true));

        }});

        new BookList(testList).checkOutBook("Dune");
        assertEquals("That book is not available.\n", outContent.toString());

    }

    @Test
    public void printsErrorMessageIfBookTitleIsNotInTheArray() throws Exception {

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Dune"));

        }});

        new BookList(testList).checkOutBook("I am a cat");
        assertEquals("That book is not available.\n", outContent.toString());

    }

    @Test
    public void canReturnABookByTitleFromBooksInArrayList() throws Exception {

        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).beReturned();
            oneOf(bookItem).isInStock();
            will(returnValue(true));

        }});

        new BookList(testList).returnBook("Dune");
    }

    @Test
    public void printsConfirmationMessageToConsoleOnSuccessfulReturn() throws Exception {

        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).beReturned();
            oneOf(bookItem).isInStock();
            will(returnValue(true));

        }});

        new BookList(testList).returnBook("Dune");
        assertEquals("Thank you for returning the book.\n", outContent.toString());

    }

    @Test
    public void printsErrorMessageIfReturnedBookWasNotCheckedOut() throws Exception {

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).isInStock();
            will(returnValue(false));

        }});

        new BookList(testList).returnBook("Dune");
        assertEquals("That is not a valid book to return.\n", outContent.toString());

    }

    @Test
    public void printsErrorMessageIfReturnedBookTitleIsNotInTheArray() throws Exception {

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Dune"));

        }});

        new BookList(testList).returnBook("I am a cat");
        assertEquals("That is not a valid book to return.\n", outContent.toString());

    }


}

