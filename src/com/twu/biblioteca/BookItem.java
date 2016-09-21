package com.twu.biblioteca;


public class BookItem extends ConsoleObject {

    private String itemTitle;
    private String itemAuthor;
    private String itemYear;

    public BookItem(String title, String author, String year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    private void setTitle(String title) {
        itemTitle = title;
    }

    private void setAuthor(String author) {
        itemAuthor = author;
    }

    private void setYear(String year) {
        itemYear = year;
    }

    private void printTitle() {
        printMessage("Title: " + itemTitle);
    }

    public void printAuthor() {
        printMessage("Author: " + itemAuthor);
    }

    private void printYear() {
        printMessage("Year: " + itemYear);
    }

    public void printDetails() {
        printTitle();
        printAuthor();
        printYear();
    }
}
