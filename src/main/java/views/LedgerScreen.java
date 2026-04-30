package views;

import com.pluralsight.UI;
import repository.TransactionRepository;

import java.time.LocalDate;

public class LedgerScreen {

    private TransactionRepository repo;

    public LedgerScreen(TransactionRepository repo) {
        this.repo = repo; // same instance passed from HomeScreen
    }

    public void display() {
        boolean isRunning = true;

        while (isRunning) {
            Display.showLedgerMenu();

            String input = UI.userInputString();

            switch (input) {
                case "a" -> Display.showTransactionTable(repo.getAll());
                case "d" -> Display.showTransactionTable(repo.getDeposits());
                case "p" -> Display.showTransactionTable(repo.getPayments());
                case "r"->  handleReportSearch();
                case "h" -> isRunning = false;
                default -> Display.showError("Invalid Option, try again");
            }
        }
    }

    private void handleReportSearch() {
        boolean isRunning = true;
        while(isRunning){
            Display.showReportsMenu();
            String input = UI.userInputString();

            switch (input) {
                case "1" -> Display.showTransactionTable(repo.previousYear());
                case "2" -> Display.showTransactionTable(repo.previousMonth());
                case "3" -> Display.showTransactionTable(repo.yearToDate());
                case "4" -> Display.showTransactionTable(repo.monthToDate());
                case "5" -> handleVendorSearch();
                case "6" -> handleCustomSearch();
                case "h" -> isRunning = false;
                default -> Display.showError("Invalid Option, try again");
            }

        }
    }

    private void handleVendorSearch() {
      Display.prompt("Enter Vendor: ");
        String vendor = UI.userInputString();
        Display.showTransactionTable(repo.customSearch(vendor, null, null, null, null));
    }

    private void handleCustomSearch() {

        Display.promptOptional("Vendor");
        String vendor = UI.userInputString();

        Display.promptOptional("Description");
        String desc = UI.userInputString();

        Display.promptOptional("Amount");
        String amountInput = UI.userInputString();
        Double amount = amountInput.isEmpty() ? null : Double.parseDouble(amountInput);

        Display.promptOptional("Start Date");
        String startDateInput = UI.userInputString();
        LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput);

        Display.promptOptional("End Date");
        String endDateInput = UI.userInputString();
        LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);
        

        // Convert empty string to null so the filter skips it
        Display.showTransactionTable(repo.customSearch(vendor.isEmpty()? null : vendor, desc.isEmpty() ? null : desc, amount, startDate, endDate));
    }
}