package com.twu.biblioteca;

public class Welcome extends ConsoleObject {

    public static String WELCOME_MESSAGE = "\nWelcome to the Biblioteca Experience\n" +
                             "Please select an option:\n";

    public void printWelcome() {
        printToConsole(WELCOME_MESSAGE);

    }
}
