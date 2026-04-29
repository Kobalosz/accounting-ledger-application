package com.pluralsight;

import views.HomeScreen;

import java.time.temporal.TemporalAmount;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        boolean isRunning = true;
        while(isRunning){
            String homeScreenOptions = HomeScreen.renderScreen();
//Drew inspiration from Omar's online-store application to format my application

            switch (homeScreenOptions){
                case "x" -> isRunning = false;
                case "d" ->;
                case "p" ->;
                case "l" ->;


            }
        }
    }
}
