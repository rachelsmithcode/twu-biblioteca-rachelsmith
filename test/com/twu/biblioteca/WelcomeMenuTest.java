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


public class WelcomeMenuTest {

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

        String result = WelcomeMenu.WELCOME_MESSAGE + "\n";


        new WelcomeMenu(getInput, testUserList).printWelcome();
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

        String result = WelcomeMenu.REQUEST_LOGIN_MESSAGE + "\n" +
                WelcomeMenu.ENTER_LIBNO_MESSAGE + "\n" +
                WelcomeMenu.ENTER_PASSWORD_MESSAGE + "\n";


        new WelcomeMenu(getInput, testUserList).requestLogin();
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

        String result = WelcomeMenu.REQUEST_LOGIN_MESSAGE + "\n" +
                WelcomeMenu.ENTER_LIBNO_MESSAGE + "\n" +
                WelcomeMenu.INVALID_LOGIN_MESSAGE + "\n";


        new WelcomeMenu(getInput, testUserList).requestLogin();
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

        String result = WelcomeMenu.REQUEST_LOGIN_MESSAGE + "\n" +
                WelcomeMenu.ENTER_LIBNO_MESSAGE + "\n" +
                WelcomeMenu.ENTER_PASSWORD_MESSAGE + "\n" +
                WelcomeMenu.INVALID_LOGIN_MESSAGE + "\n";


        new WelcomeMenu(getInput, testUserList).requestLogin();
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

        WelcomeMenu welcomeMenu = new WelcomeMenu(getInput, testUserList);
        welcomeMenu.requestLogin();
        welcomeMenu.returnSessionUser();

        assertEquals(welcomeMenu.returnSessionUser(), user);

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

        WelcomeMenu welcomeMenu = new WelcomeMenu(getInput, testUserList);
        welcomeMenu.requestLogin();
        welcomeMenu.returnSessionUser();

        assertEquals(welcomeMenu.returnSessionUser(), null);

    }


}
