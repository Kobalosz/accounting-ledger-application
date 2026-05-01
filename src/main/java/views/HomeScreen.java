package views;

import models.Transaction;
import com.pluralsight.UI;
import repository.TransactionRepository;
import java.time.LocalDate;
import java.time.LocalTime;

public class HomeScreen {

    // Holds a reference to whatever was injected, in this case the repository!
    private TransactionRepository repo;

    // This is a constructor to inject the repository
    public HomeScreen(TransactionRepository repo) {
        this.repo = repo;
    }

    public void display() {
        boolean isRunning = true;

        while (isRunning) {
            Display.showAppHeader();
            Display.showHomeMenu();

            String input = UI.userInputString();

            switch (input) {
                case "d" -> {
                  Display.prompt("Enter Description: ");
                    String desc = UI.userInputString();

                  Display.prompt("Enter Vendor: ");
                    String vendor = UI.userInputString();

                  Display.prompt("Enter Amount: ");
                    double amount = UI.userInputDouble();

                    Transaction t = new Transaction(
                            LocalDate.now(),
                            LocalTime.now(),
                            desc,
                            vendor,
                            amount
                    );

                    repo.save(t); // uses the injected repo
                  Display.showSuccess("Transaction Saved Successfully!");
                }
                case "p" -> {
                  Display.prompt("Enter Description: ");
                    String desc = UI.userInputString();

                  Display.prompt("Enter Vendor: ");
                    String vendor = UI.userInputString();

                  Display.prompt("Enter Amount: ");
                    double amount = UI.userInputDouble();

                    Transaction t = new Transaction(
                            LocalDate.now(),
                            LocalTime.now(),
                            desc,
                            vendor,
                            amount * -1  // negated for payment
                    );

                    repo.save(t);
                  Display.prompt("Transaction Saved Successfully!");
                }
                case "l" -> {
                    LedgerScreen ledger = new LedgerScreen(repo);
                    ledger.display();
                }
                case "x" -> isRunning = false;
                default -> Display.showError("Invalid Input, try again");
            }
        }
    }
}