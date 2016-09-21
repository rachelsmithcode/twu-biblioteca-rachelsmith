package com.twu.biblioteca;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class BookItemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private BookItem testItemOne;
    private BookItem testItemTwo;

    private void createTestBookOne() {
        testItemOne = new BookItem("Dune", "Frank Herbert", "1965");
    }

    private void createTestBookTwo() {
        testItemTwo = new BookItem("Gormengast", "Mervyn Peake", "1950");
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
    public void printsFullBookDetails() throws Exception {

        createTestBookOne();
        createTestBookTwo();

        String result = "Title: Dune\n" +
                        "Author: Frank Herbert\n" +
                        "Year: 1965\n" +
                        "Title: Gormengast\n" +
                        "Author: Mervyn Peake\n" +
                        "Year: 1950\n";

        testItemOne.printDetails();
        testItemTwo.printDetails();
        assertEquals(result, outContent.toString());

    }

    @Test
    public void returnsListNameAsAString() throws Exception {

        createTestBookOne();

        assertEquals("Dune", (testItemOne.returnName()));
    }

    @Test
    public void bookCanBeMarkedAsCheckedOut() throws Exception {

        createTestBookOne();
        testItemOne.checkOutBook();

        assertEquals(true, (testItemOne.checkedOut()));
    }


}
