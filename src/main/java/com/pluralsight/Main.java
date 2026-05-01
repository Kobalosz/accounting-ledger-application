package com.pluralsight;

import models.Transaction;
import repository.TransactionRepository;
import views.HomeScreen;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        TransactionRepository repository = new TransactionRepository();
        HomeScreen homeScreen = new HomeScreen(repository);
        homeScreen.display();

    }
}
