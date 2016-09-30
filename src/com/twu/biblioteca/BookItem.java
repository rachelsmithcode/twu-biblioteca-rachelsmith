package com.twu.biblioteca;


public class BookItem {

    private String itemTitle;
    private String itemAuthor;
    private String itemYear;
    private Boolean inStock = true;

    public BookItem(String title, String author, String year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    public void printDetails() {
        printAsColumn(getTitle(), getAuthor(), getYear());
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

    public String getTitle() {
        return itemTitle;
    }

    public String getAuthor() {
        return itemAuthor;
    }

    public String getYear() {
        return itemYear;
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

    private void printAsColumn(String title, String author, String year) {
        System.out.print(title + " | " + author + " | " + year + "\n");
    }
}
