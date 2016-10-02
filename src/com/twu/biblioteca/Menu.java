package com.twu.biblioteca;

public class Menu {

    private static Options options;
    private static Input inputStream;
    private Boolean validSelection;

    public Menu(Options menuOptions, Input getInput) {
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
        if ((input.contains("User")) || (input.contains("user"))) {
            validSelection = true;
            options.printUserDetails();
        }
    }

    private void checkIfPrintList(String input) {
        if ((input.contains("List") && input.contains("Book")) || (input.contains("list") && input.contains("book"))) {
            validSelection = true;
            options.printBookList();
        } else if ((input.contains("List") && input.contains("Movie")) || (input.contains("list") && input.contains("movie"))) {
            validSelection = true;
            options.printMovieList();
        }
    }

    private void checkIfCheckOutBookItem(String input) {
        if ((input.contains("Checkout")  && input.contains("Book")) || (input.contains("checkout") && input.contains("book")) ) {
            printToConsole("What is the title of the book you wish to check out?");
            String titleInput = requestInput();
            validSelection = true;
            options.checkoutBook(titleInput);
        }
    }

    private void checkIfCheckOutMovieItem(String input) {
        if ((input.contains("Checkout") && input.contains("Movie"))  || (input.contains("checkout") && input.contains("movie")) ) {
            printToConsole("What is the title of the movie you wish to check out?");
            String titleInput = requestInput();
            validSelection = true;
            options.checkoutMovie(titleInput);
        }
    }

    private void checkIfReturnBookItem(String input) {
        if ((input.contains("Return") && input.contains("Book"))  || (input.contains("return") && input.contains("book")) ) {
            printToConsole("What is the title of the book you wish to return?");
            String titleInput = requestInput();
            validSelection = true;
            options.returnBook(titleInput);
        }
    }

    private void checkIfReturnMovieItem(String input) {
        if ((input.contains("Return") && input.contains("Movie"))  || (input.contains("return") && input.contains("movie")) ) {
            printToConsole("What is the title of the movie you wish to return?");
            String titleInput = requestInput();
            validSelection = true;
            options.returnMovie(titleInput);
        }
    }

    private void checkIfQuit(String input) {
        if (input.contains("Quit") || input.contains("quit")) {
            validSelection = true;
            options.quit();
        }
    }

    private void checkIfValidSelection() {
        if (!validSelection) {
            printToConsole("Please select a valid item!\n");
        }
    }

    private String requestInput() {
        return inputStream.returnString(System.in);
    }


}
