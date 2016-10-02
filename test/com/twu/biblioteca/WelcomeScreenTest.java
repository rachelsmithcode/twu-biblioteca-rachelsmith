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
    public void printsWelcomeMessage() throws Exception {

        String result = WelcomeScreen.WELCOME_MESSAGE + "\n";


        new WelcomeScreen(getInput, testUserList).printWelcome();
        assertEquals((result), outContent.toString());

    }


    @Test
    public void asksForLibNoAndPasswordIfCorrectUserNameIsGiven() throws Exception {

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

        String result = WelcomeScreen.REQUEST_LOGIN_MESSAGE + "\n" +
                WelcomeScreen.ENTER_LIBNO_MESSAGE + "\n" +
                WelcomeScreen.ENTER_PASSWORD_MESSAGE + "\n";


        new WelcomeScreen(getInput, testUserList).requestLogin();
        assertEquals((result), outContent.toString());

    }

    @Test
    public void printsIncorrectLibNoOrPasswordMessageIfIncorrectLibNoEntered() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("IAMINCORRECT"));
            exactly(1).of(user).getLibraryNumber();
            will(returnValue("123-1234"));
            exactly(1).of(user).getLibraryNumber();
            will(returnValue("123-1235"));
        }});

        String result = WelcomeScreen.REQUEST_LOGIN_MESSAGE + "\n" +
                WelcomeScreen.ENTER_LIBNO_MESSAGE + "\n" +
                WelcomeScreen.INVALID_LOGIN_MESSAGE + "\n";


        new WelcomeScreen(getInput, testUserList).requestLogin();
        assertEquals((result), outContent.toString());

    }

    @Test
    public void printsIncorrectLibNoOrPasswordMessageIfIncorrectPasswordEntered() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("123-1234"));
            exactly(1).of(user).getLibraryNumber();
            will(returnValue("123-1234"));
            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("IAmInCorrect"));
            exactly(1).of(user).getUserPassword();
            will(returnValue("CatsCatsEVERYWHERE!"));
        }});

        String result = WelcomeScreen.REQUEST_LOGIN_MESSAGE + "\n" +
                WelcomeScreen.ENTER_LIBNO_MESSAGE + "\n" +
                WelcomeScreen.ENTER_PASSWORD_MESSAGE + "\n" +
                WelcomeScreen.INVALID_LOGIN_MESSAGE + "\n";


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

    @Test
    public void doesNotSetSessionUserIfIncorrectCorrectPasswordIsGivenForUser() throws Exception {

        context.checking(new Expectations() {{

            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("123-1234"));
            exactly(1).of(user).getLibraryNumber();
            will(returnValue("123-1234"));
            exactly(1).of(getInput).returnString(System.in);
            will(returnValue("IAMINCORRECT"));
            exactly(1).of(user).getUserPassword();
            will(returnValue("CatsCatsEVERYWHERE!"));
        }});

        WelcomeScreen welcomeScreen = new WelcomeScreen(getInput, testUserList);
        welcomeScreen.requestLogin();
        welcomeScreen.returnSessionUser();

        assertEquals(welcomeScreen.returnSessionUser(), null);

    }


}
