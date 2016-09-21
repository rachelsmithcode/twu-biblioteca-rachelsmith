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
    public void printsTheDetailsOfEachBookInArrayList() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).checkedOut();
            will(returnValue(false));
            exactly(2).of(bookItem).printDetails();

        }});

        new BookList(testList).printList();
    }

    @Test
    public void printsListsNameToConsole() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        new BookList(testList).printName();
        assertEquals("Book List\n", outContent.toString());

    }

    @Test
    public void returnsListNameAsAString() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        assertEquals("Book List", (new BookList(testList).returnName()));
    }

    @Test
    public void canBeSelectedAndPrintsDetailsForAllBooksInArrayList() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).checkedOut();
            will(returnValue(false));
            exactly(2).of(bookItem).printDetails();

        }});

        new BookList(testList).select();
    }

    @Test
    public void aCheckedOutBookShouldNotAppearWhenBookListIsSelected() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).checkedOut();
            will(returnValue(true));
            oneOf(bookItem).checkedOut();
            will(returnValue(false));

            oneOf(bookItem).printDetails();

        }});

        new BookList(testList).select();
    }

    @Test
    public void canCheckOutABookByTitleFromBooksInArrayList() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).checkOutBook();
            oneOf(bookItem).checkedOut();
            will(returnValue(false));

        }});

        new BookList(testList).checkOut("Dune");
    }

    @Test
    public void printsConfirmationMessageToConsoleOnSuccessfulCheckout() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).checkOutBook();
            oneOf(bookItem).checkedOut();
            will(returnValue(false));

        }});

        new BookList(testList).checkOut("Dune");
        assertEquals("Thank you! Enjoy the book\n", outContent.toString());

    }

    @Test
    public void printsErrorMessageIfBookIsAlreadyCheckedOut() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).checkedOut();
            will(returnValue(true));

        }});

        new BookList(testList).checkOut("Dune");
        assertEquals("That book is not available.\n", outContent.toString());

    }

    @Test
    public void printsErrorMessageIfBookTitleIsNotInTheArray() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Dune"));

        }});

        new BookList(testList).checkOut("I am a cat");
        assertEquals("That book is not available.\n", outContent.toString());

    }

    @Test
    public void canReturnABookByTitleFromBooksInArrayList() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).beReturned();
            oneOf(bookItem).checkedOut();
            will(returnValue(true));

        }});

        new BookList(testList).returnBook("Dune");
    }

    @Test
    public void printsConfirmationMessageToConsoleOnSuccessfulReturn() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Gormengast"));
            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).beReturned();
            oneOf(bookItem).checkedOut();
            will(returnValue(true));

        }});

        new BookList(testList).returnBook("Dune");
        assertEquals("Thank you for returning the book.\n", outContent.toString());

    }

    @Test
    public void printsErrorMessageIfReturnedBookWasNotCheckedOut() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Dune"));
            oneOf(bookItem).checkedOut();
            will(returnValue(false));

        }});

        new BookList(testList).returnBook("Dune");
        assertEquals("That is not a valid book to return.\n", outContent.toString());

    }

    @Test
    public void printsErrorMessageIfReturnedBookTitleIsNotInTheArray() throws Exception {

        final BookItem bookItem = context.mock(BookItem.class);

        ArrayList<BookItem> testList = new ArrayList();
        testList.add(bookItem);

        context.checking(new Expectations() {{

            oneOf(bookItem).returnName();
            will(returnValue("Dune"));

        }});

        new BookList(testList).returnBook("I am a cat");
        assertEquals("That is not a valid book to return.\n", outContent.toString());

    }


}

