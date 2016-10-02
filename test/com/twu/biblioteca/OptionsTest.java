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
        testOptionsList.add("Movie List");
        testOptionsList.add("Check Out Book");
        testOptionsList.add("Return Book");
        testOptionsList.add("Quit");
    }

    final User user = context.mock(User.class);

    final BookItem bookItem = context.mock(BookItem.class);

    final MovieItem movieItem = context.mock(MovieItem.class);

    public final ArrayList<BookItem> testBookList = new ArrayList();

    public final ArrayList<MovieItem> testMovieList = new ArrayList();

    @Before
    public void addItemsToTestBookList() {

        testBookList.add(bookItem);
        testBookList.add(bookItem);
    }

    @Before
    public void addItemsToTestMovieList() {

        testMovieList.add(movieItem);
        testMovieList.add(movieItem);
        testMovieList.add(movieItem);
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
        new Options(testOptionsList, testBookList, testMovieList, user).printOptions();

        String result = "Book List\n" +
                "Movie List\n" +
                "Check Out Book\n" +
                "Return Book\n" +
                "Quit\n";

        assertEquals(result, outContent.toString());
    }

    @Test
    public void testGetOptions() throws Exception  {

        assertEquals(testOptionsList, new Options(testOptionsList, testBookList, testMovieList, user).getOptions());
    }

    @Test
    public void testQuit() throws Exception {

        exit.expectSystemExit();
        new Options(testOptionsList, testBookList, testMovieList, user).quit();

    }

    @Test
    public void testPrintBookList() throws Exception {

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).isInStock();
            will(returnValue(true));
            exactly(2).of(bookItem).printDetails();

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).printBookList();

    }

    @Test
    public void testPrintUserDetails() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(user).printDetails();

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).printUserDetails();

    }

    @Test
    public void testPrintMovieList() throws Exception {

        context.checking(new Expectations() {{

            exactly(3).of(movieItem).isInStock();
            will(returnValue(true));
            exactly(3).of(movieItem).printDetails();

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).printMovieList();

    }

    @Test
    public void testCheckoutBookFromStock() throws Exception {

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).isInStock();
            will(returnValue(true));
            oneOf(bookItem).getTitle();
            will(returnValue("Gormengast"));
            oneOf(bookItem).getTitle();
            will(returnValue("Dune"));
            exactly(1).of(bookItem).beCheckedOut(exactly(1).of(user).getUserName());

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).checkoutBook("Dune");
        assertEquals("Thank you! Enjoy the book\n", outContent.toString());

    }

    @Test
    public void testCannotCheckoutBookThatIsOutOfStock() throws Exception {

        context.checking(new Expectations() {{

            oneOf(bookItem).isInStock();
            will(returnValue(true));
            oneOf(bookItem).isInStock();
            will(returnValue(false));
            exactly(1).of(bookItem).getTitle();
            will(returnValue("Gormengast"));

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).checkoutBook("Dune");
        assertEquals("That book is not available.\n", outContent.toString());

    }

    @Test
    public void testCheckoutMovieFromStock() throws Exception {

        context.checking(new Expectations() {{

            exactly(3).of(movieItem).isInStock();
            will(returnValue(true));
            oneOf(movieItem).getTitle();
            will(returnValue("Die Hard"));
            oneOf(movieItem).getTitle();
            will(returnValue("Dark Crystal"));
            oneOf(movieItem).getTitle();
            will(returnValue("Spy"));
            exactly(1).of(movieItem).beCheckedOut(exactly(1).of(user).getUserName());


        }});

        new Options(testOptionsList, testBookList, testMovieList, user).checkoutMovie("Die Hard");
        assertEquals("Thank you! Enjoy the movie\n", outContent.toString());

    }

    @Test
    public void testCannotCheckoutMovieThatIsOutOfStock() throws Exception {

        context.checking(new Expectations() {{

            oneOf(movieItem).isInStock();
            will(returnValue(true));
            oneOf(movieItem).isInStock();
            will(returnValue(false));
            oneOf(movieItem).isInStock();
            will(returnValue(false));
            exactly(1).of(movieItem).getTitle();
            will(returnValue("Dark Crystal"));

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).checkoutMovie("Die Hard");
        assertEquals("That movie is not available.\n", outContent.toString());

    }



    @Test
    public void testReturnBookFromBookListThatIsOutOfStock() throws Exception {

        context.checking(new Expectations() {{

            exactly(2).of(bookItem).isInStock();
            will(returnValue(false));
            oneOf(bookItem).getTitle();
            will(returnValue("Gormengast"));
            oneOf(bookItem).getTitle();
            will(returnValue("Dune"));
            exactly(1).of(bookItem).beReturned();

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).returnBook("Dune");
        assertEquals("Thank you for returning the book.\n", outContent.toString());

    }

    @Test
    public void testCannotReturnBookFromBookListThatIsInStockAlready() throws Exception {

        context.checking(new Expectations() {{

            oneOf(bookItem).isInStock();
            will(returnValue(true));
            oneOf(bookItem).isInStock();
            will(returnValue(false));
            exactly(1).of(bookItem).getTitle();
            will(returnValue("Gormengast"));

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).returnBook("Dune");
        assertEquals("That is not a valid book to return.\n", outContent.toString());
    }

    @Test
    public void testReturnMovieFromMovieListThatIsOutOfStock() throws Exception {

        context.checking(new Expectations() {{

            exactly(3).of(movieItem).isInStock();
            will(returnValue(false));
            oneOf(movieItem).getTitle();
            will(returnValue("Die Hard"));
            oneOf(movieItem).getTitle();
            will(returnValue("Dark Crystal"));
            oneOf(movieItem).getTitle();
            will(returnValue("Spy"));
            exactly(1).of(movieItem).beReturned();

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).returnMovie("Die Hard");
        assertEquals("Thank you for returning the movie.\n", outContent.toString());

    }

    @Test
    public void testCannotReturnMovieFromMovieListThatIsInStockAlready() throws Exception {

        context.checking(new Expectations() {{

            oneOf(movieItem).isInStock();
            will(returnValue(true));
            oneOf(movieItem).isInStock();
            will(returnValue(true));
            oneOf(movieItem).isInStock();
            will(returnValue(false));
            exactly(1).of(movieItem).getTitle();
            will(returnValue("Spy"));

        }});

        new Options(testOptionsList, testBookList, testMovieList, user).returnMovie("Die Hard");
        assertEquals("That is not a valid movie to return.\n", outContent.toString());
    }


}
