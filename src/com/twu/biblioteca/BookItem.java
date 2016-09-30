package com.twu.biblioteca;


public class BookItem {

    private String itemTitle;
    private String itemAuthor;
    private String itemYear;
    private Boolean inStock = true;

    private void printToConsole(String message) {
        System.out.println(message);
    }

    public BookItem(String title, String author, String year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    public void printDetails() {
        printTitle();
        printAuthor();
        printYear();
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
        printToConsole("Title: " + itemTitle);
    }

    public void printAuthor() {
        printToConsole("Author: " + itemAuthor);
    }

    private void printYear() {
        printToConsole("Year: " + itemYear);
    }

    public String returnName() {
        return itemTitle;
    }

    public void beCheckedOut() {
       inStock = false;
    }

    public void beReturned() {
        inStock = true;
    }

    public Boolean isInStock() {
        return inStock;
    }
}
