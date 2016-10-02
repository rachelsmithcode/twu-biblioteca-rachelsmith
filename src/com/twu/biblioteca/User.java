package com.twu.biblioteca;


public class User {

    private String userLibraryNumber;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhonenumber;

    public User(String libNo, String password, String name, String email, String phoneNo) {
        setLibraryNumber(libNo);
        setUserPassword(password);
        setUserName(name);
        setUserEmail(email);
        setUserPhoneNumber(phoneNo);
    }

    private void printToConsole(String message) {
        System.out.println(message);
    }

    public void printDetails() {
        printToConsole("Name: " + getUserName());
        printToConsole("Email: " + getUserEmail());
        printToConsole("Telephone Number: " + getUserPhoneNumber());
    }

    private void setLibraryNumber(String libNo) {
        userLibraryNumber = libNo;
    }

    private void setUserPassword(String password) {
        userPassword = password;
    }

    private void setUserName(String name) {
        userName = name;
    }

    private void setUserEmail(String email) {userEmail = email;}

    private void setUserPhoneNumber(String phoneNo) {userPhonenumber = phoneNo;}

    public String getLibraryNumber() {
        return userLibraryNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {return userEmail;}

    public String getUserPhoneNumber() {return userPhonenumber;}


}
