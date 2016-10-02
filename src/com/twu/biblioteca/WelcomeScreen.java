package com.twu.biblioteca;

import java.util.ArrayList;

public class WelcomeScreen {

    private void printToConsole(String message) {
        System.out.println(message);
    }

    public static String REQUEST_LOGIN_MESSAGE = "Please login to see options:\n";

    public static String INVALID_LOGIN_MESSAGE = "\nYour username or password is does not match our records\n";

    public static String WELCOME_MESSAGE = "\nWelcomeScreen to the Biblioteca Experience\n";

    private static ArrayList<User> validUsers;

    private static User sessionUser = null ;

    private static Input input;

    public WelcomeScreen(Input getInput, ArrayList<User> users) {
        input = getInput;
        validUsers = users;
    }

    public void requestLogin() {
        printWelcome();
        printToConsole(REQUEST_LOGIN_MESSAGE);
        enterLibNo();
    }

    private void enterLibNo() {
        printToConsole("Enter username:");
        String libNo = input.returnString(System.in);
        checkLibNo(libNo);
    }

    private void checkLibNo(String libNo) {
        Boolean validUserName = false;

        for (int i = 0; i < validUsers.size(); i++) {
            User user = validUsers.get(i);
            String userLibNo = user.getLibraryNumber();
            if (userLibNo.equals(libNo)) {
                enterPassword(user);
                validUserName = true;
                break;
            }
        }
        if (!validUserName) {
            printToConsole(INVALID_LOGIN_MESSAGE);
            enterLibNo();
        }
    }

    private void checkpassword(User user, String enteredPassword) {

        String userPassword = user.getUserPassword();
        if (userPassword.equals(enteredPassword)) {
            sessionUser = user;

        } else {
            printToConsole(INVALID_LOGIN_MESSAGE);
            enterPassword(user);
        }
    }

    private void enterPassword(User user) {
        printToConsole("Enter password:");
        String enteredPassword = input.returnString(System.in);
        checkpassword(user, enteredPassword);
    }

    private void printWelcome() {
        printToConsole(WELCOME_MESSAGE);

    }

    public User returnSessionUser() {
        if(sessionUser != null) {
            return sessionUser;
        } else {
            return null;
        }
    }


}
