package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GetInput {


    public static String PROMPT = "\n" +
            ">> ";

    public String returnString(InputStream in) {

        String inputLine = null;
        System.out.print(PROMPT);
        try {
            BufferedReader is = new BufferedReader(
                    new InputStreamReader(in));
            inputLine = is.readLine();
            if (inputLine.length() == 0 ) return null;
        } catch (IOException e) {
            System.out.println("IOException:" + e);
        }
        return inputLine;
    }


}
