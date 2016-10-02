package com.twu.biblioteca;

public class MainMenu {

    private static Options options;
    private static Input inputStream;
    private Boolean validSelection;

    public static String CHECKOUT_MESSAGE = "What is the title of the item you wish to check out?";
    public static String RETURN_MESSAGE = "What is the title of the item you wish to return?";
    public static String INVALID_MESSAGE = "Please select a valid item!\n";

    public MainMenu(Options menuOptions, Input getInput) {
        options = menuOptions;
        inputStream = getInput;
    }

    public void printMainOptions() {

        options.printOptions();

    }

    private void printToConsole(String message) {
        System.out.println(message);
    }

    public void menuAction() {

        String input = requestInput();
        selectMenuOption(input);

    }

    private void selectMenuOption(String input) {
        validSelection = false;
        checkIfUserDetails(input);
        checkIfPrintList(input);
        checkIfCheckOutBookItem(input);
        checkIfReturnBookItem(input);
        checkIfCheckOutMovieItem(input);
        checkIfReturnMovieItem(input);
        checkIfQuit(input);
        checkIfValidSelection();
    }

    private void checkIfUserDetails(String input) {
        if (textChecker(input, "user") || textChecker(input, "details")) {
            validSelection = true;
            options.printUserDetails();
        }
    }

    private void checkIfPrintList(String input) {
        if (textChecker(input, "list") && textChecker(input, "book")) {
            validSelection = true;
            options.printBookList();
        } else if (textChecker(input, "list") && textChecker(input, "movie")) {
            validSelection = true;
            options.printMovieList();
        }
    }

    private void checkIfCheckOutBookItem(String input) {
        if (textChecker(input, "checkout") && textChecker(input, "book")) {
            printToConsole(CHECKOUT_MESSAGE);
            String titleInput = requestInput();
            validSelection = true;
            options.checkoutBook(titleInput);
        }
    }

    private void checkIfCheckOutMovieItem(String input) {
        if (textChecker(input, "checkout") && textChecker(input, "movie")) {
            printToConsole(CHECKOUT_MESSAGE);
            String titleInput = requestInput();
            validSelection = true;
            options.checkoutMovie(titleInput);
        }
    }

    private void checkIfReturnBookItem(String input) {
        if (textChecker(input, "return") && textChecker(input, "book")) {
            printToConsole(RETURN_MESSAGE);
            String titleInput = requestInput();
            validSelection = true;
            options.returnBook(titleInput);
        }
    }

    private void checkIfReturnMovieItem(String input) {
        if (textChecker(input, "return") && textChecker(input, "movie")) {
            printToConsole(RETURN_MESSAGE);
            String titleInput = requestInput();
            validSelection = true;
            options.returnMovie(titleInput);
        }
    }

    private void checkIfQuit(String input) {
        if (textChecker(input, "quit")) {
            validSelection = true;
            options.quit();
        }
    }

    private void checkIfValidSelection() {
        if (!validSelection) {
            printToConsole(INVALID_MESSAGE);
        }
    }

    private Boolean textChecker(String input, String keyWord) {
        String checkInput = input.toLowerCase();
        String checkKeyWord = keyWord.toLowerCase();
        if (checkInput.contains(checkKeyWord)) {
            return true;
        } else {
            return false;
        }
    }

    private String requestInput() {
        return inputStream.returnString(System.in);
    }


}
