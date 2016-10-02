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


public class WelcomeScreenTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    ArrayList<User> testUserList = new ArrayList<User>();
    final Input getInput = context.mock(Input.class);
    final User user = context.mock(User.class);

    @Before
    public void createTestArray() {
        testUserList.add(user);
        testUserList.add(user);
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
    public void asksForUserNameAndPasswordIfCorrectUserNameIsGiven() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("123-1234"));
            exactly(1).of(user).getLibraryNumber();
            will(returnValue("123-1234"));
            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("CatsCatsEVERYWHERE!"));
            exactly(1).of(user).getUserPassword();
            will(returnValue("CatsCatsEVERYWHERE!"));
        }});

        String result =
                "\nWelcomeScreen to the Biblioteca Experience\n\n" +
        "Please login to see options:\n\n" +
        "Enter username:\n" +
        "Enter password:\n";


    new WelcomeScreen(getInput, testUserList).requestLogin();
    assertEquals((result), outContent.toString());

    }


    @Test
    public void setsSessionUserIfCorrectPasswordIsGivenForUser() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("123-1234"));
            exactly(1).of(user).getLibraryNumber();
            will(returnValue("123-1234"));
            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("CatsCatsEVERYWHERE!"));
            exactly(1).of(user).getUserPassword();
            will(returnValue("CatsCatsEVERYWHERE!"));
        }});

        WelcomeScreen welcomeScreen = new WelcomeScreen(getInput, testUserList);
        welcomeScreen.requestLogin();
        welcomeScreen.returnSessionUser();

        assertEquals(welcomeScreen.returnSessionUser(), user);


    }

//    @Test
//    public void setsSessionUserIfCorrectPasswordIsGivenForUser() throws Exception {
//
//        String testUserName = "Rachel Smith";
//        String testUserPassword = "123-1234";
//
//        new WelcomeScreen(getInput, testUserList).login(testUserName, testUserPassword);
//        assertEquals((WelcomeScreen.WELCOME_MESSAGE + "\n"), outContent.toString());
//
//    }


}
