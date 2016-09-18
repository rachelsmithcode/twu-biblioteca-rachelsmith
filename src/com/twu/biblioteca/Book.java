package com.twu.biblioteca;

/**
 * Created by RachelSmith on 18/09/2016.
 */
public class Book {

    private String title;
    private String author;
    private String year;


    public void setTitle(String t) {
        title = t;
    }

    public void printTitle() {
        System.out.println("Title: " + title);
    }

    public void setAuthor(String a) {
        author = a;
    }

    public void printAuthor() {
        System.out.println("Author: " + author);
    }

    public void setYear(String y) {
        year = y;
    }

    public void printYear() {
        System.out.println("Year: " + year);
    }
}
