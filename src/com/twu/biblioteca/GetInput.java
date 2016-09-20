package com.twu.biblioteca;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class GetInput {

    public String returnString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.toString();
    }
}
