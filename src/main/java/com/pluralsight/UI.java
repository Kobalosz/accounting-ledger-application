package com.pluralsight;

import java.util.Scanner;

public class UI {
    static Scanner scanner = new Scanner(System.in);

    public static String userInputString(){
        String userInput = scanner.nextLine().strip().toLowerCase();
        return userInput;
    }

    public static Double userInputDouble(){
        String userInput = scanner.nextLine().strip().toLowerCase();
        Double parsedUserInput = Double.parseDouble(userInput);
        return parsedUserInput;
    }


}
