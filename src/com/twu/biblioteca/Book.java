package com.twu.biblioteca;


public class Book {

    private String bkTitle;
    private String bkAuthor;
    private String bkYear;

    public Book(String title, String author, String year) {
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
        System.out.println("Title: " + bkTitle);
    }

    public void printAuthor() {
        System.out.println("Author: " + bkAuthor);
    }

    private void printYear() {
        System.out.println("Year: " + bkYear);
    }
}
