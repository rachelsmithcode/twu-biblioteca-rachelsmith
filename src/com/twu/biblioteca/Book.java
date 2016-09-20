package com.twu.biblioteca;


public class Book {

    private String bkTitle;
    private String bkAuthor;
    private String bkYear;
    private Console console;

    public Book(Console con, String title, String author, String year) {
        console = con;
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }


    public void printInfo() {
        printTitle();
        printAuthor();
        printYear();
    }


    private void setTitle(String title) {
        bkTitle = title;
    }

    private void setAuthor(String author) {
        bkAuthor = author;
    }

    private void setYear(String year) {
        bkYear = year;
    }

    private void printTitle() {
        console.message("Title: " + bkTitle);
    }

    public void printAuthor() {
        console.message("Author: " + bkAuthor);
    }

    private void printYear() {
        console.message("Year: " + bkYear);
    }
}
