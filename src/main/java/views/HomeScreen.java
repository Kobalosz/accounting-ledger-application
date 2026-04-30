package views;

import com.pluralsight.Transaction;
import com.pluralsight.UI;
import repository.TransactionRepository;
import views.LedgerScreen;

import java.time.LocalDate;
import java.time.LocalTime;

public class HomeScreen {

    // Holds a reference to whatever was injected
    private TransactionRepository repo;

    // This is a constructor to inject the repository
    public HomeScreen(TransactionRepository repo) {
        this.repo = repo;
    }

    public void display() {
        boolean isRunning = true;

        while (isRunning) {
          IO.println("D - Deposit");
          IO.println("P - Payment");
          IO.println("L - Ledger");
          IO.println("X - Exit");

            String input = UI.userInputString();

            switch (input) {
                case "d" -> {
                  IO.println("Enter description: ");
                    String desc = UI.userInputString();

                  IO.println("Enter vendor: ");
                    String vendor = UI.userInputString();

                  IO.println("Enter amount: ");
                    double amount = UI.userInputDouble();

                    Transaction t = new Transaction(
                            LocalDate.now(),
                            LocalTime.now(),
                            desc,
                            vendor,
                            amount
                    );

                    repo.save(t); // uses the injected repo
                  IO.println("Deposit saved!");
                }
                case "p" -> {
                  IO.println("Enter description: ");
                    String desc = UI.userInputString();

                  IO.println("Enter vendor: ");
                    String vendor = UI.userInputString();

                  IO.println("Enter amount: ");
                    double amount = UI.userInputDouble();

                    Transaction t = new Transaction(
                            LocalDate.now(),
                            LocalTime.now(),
                            desc,
                            vendor,
                            amount * -1  // negated for payment
                    );

                    repo.save(t);
                  IO.println("Payment saved!");
                }
                case "l" -> {
                    LedgerScreen ledger = new LedgerScreen(repo);
                    ledger.display();
                }
                case "x" -> isRunning = false;
            }
        }
    }
}