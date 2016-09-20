package com.twu.biblioteca;


public class MenuDisplay {

    Console console;
    GetInput getInput;
    BookList bookList;

    public MenuDisplay(Console con) {
        console = con;
    }

    private void setInput(GetInput input) {
        getInput = input;
    }

    private void setBookList(BookList bkList) {
        bookList = bkList;
    }

    public void printItems(MenuItem menuItem) {

        menuItem.printDescription();

    }

    public void selectItem(GetInput input, BookList bkList) {

        setInput(input);
        setBookList(bkList);
        String string = getInput.returnString();

        if (string != "Book List") {
            console.message("Please select a valid item!");
            selectItem(getInput, bookList);
        } else if (string == "Book List") {
            bookList.printBooks();
        }
    }
}
