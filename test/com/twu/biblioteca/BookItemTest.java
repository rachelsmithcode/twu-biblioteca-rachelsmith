package com.twu.biblioteca;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class BookItemTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    final User user = context.mock(User.class);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private BookItem testBookOne;
    private BookItem testBookTwo;

    private void createTestBookTwo() {
        testBookTwo = new BookItem("Gormengast", "Mervyn Peake", "1950");
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @Before
    public void createTestBookOne() {
        testBookOne = new BookItem("Dune", "Frank Herbert", "1965");
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


    @Test
    public void printsFullBookDetails() throws Exception {

        createTestBookTwo();

        String result = "Dune | Frank Herbert | 1965\n" +
                        "Gormengast | Mervyn Peake | 1950\n";

        testBookOne.printDetails();
        testBookTwo.printDetails();
        assertEquals(result, outContent.toString());

    }

    @Test
    public void returnsTitleAsAString() throws Exception {

        assertEquals("Dune", (testBookOne.getTitle()));
    }

    @Test
    public void returnsAuthorAsAString() throws Exception {

        assertEquals("Frank Herbert", (testBookOne.getAuthor()));
    }

    @Test
    public void returnsYearAsAString() throws Exception {

        assertEquals("1965", (testBookOne.getYear()));
    }

    @Test
    public void bookCanBeCheckedOutAndMarkedAsNotInStock() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(user).getUserName();
            will(returnValue("Rachel Smith"));

        }});

        testBookOne.beCheckedOut(user.getUserName());

        assertEquals(false, (testBookOne.isInStock()));
    }

    @Test
    public void bookCanBeReturnedAndMarkedInStock() throws Exception {

        testBookOne.beReturned();

        assertEquals(true, (testBookOne.isInStock()));
    }


}
