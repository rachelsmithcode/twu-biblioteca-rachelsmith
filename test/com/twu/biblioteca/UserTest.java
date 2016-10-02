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

public class UserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private User testUserOne;
    private User testUserTwo;

    private void createTestUserTwo() {
        testUserTwo = new User("123-1234", "IAmAPassWord", "Rachel Smith", "rachel@email.com", "020-0-000-0000");
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void createTestUserOne() {
        testUserOne = new User("123-1235", "IAmAnotherPassWord", "Cat Smith", "cat@email.com", "020-0-000-0000");
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


    @Test
    public void printUserDetails() throws Exception {

        createTestUserTwo();

        String result = "Name: Cat Smith\n" +
                "Email: cat@email.com\n" +
                "Telephone Number: 020-0-000-0000\n" +
                "Name: Rachel Smith\n" +
                "Email: rachel@email.com\n" +
                "Telephone Number: 020-0-000-0000\n";

        testUserOne.printDetails();
        testUserTwo.printDetails();
        assertEquals(result, outContent.toString());

    }

    @Test
    public void returnsLibraryNumberAsAString() throws Exception {

        assertEquals("123-1235", (testUserOne.getLibraryNumber()));
    }

    @Test
    public void returnsPasswordAsAString() throws Exception {

        assertEquals("IAmAnotherPassWord", (testUserOne.getUserPassword()));
    }

    @Test
    public void returnsUserNameAsAString() throws Exception {

        assertEquals("Cat Smith", (testUserOne.getUserName()));
    }

    @Test
    public void returnsUserEmailAsAString() throws Exception {

        assertEquals("cat@email.com", (testUserOne.getUserEmail()));
    }

    @Test
    public void returnsUserPhoneAsAString() throws Exception {

        assertEquals("020-0-000-0000", (testUserOne.getUserPhoneNumber()));
    }



}
