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
          IO.println("A - All transactions");
          IO.println("D - Deposits");
          IO.println("P - Payments");
          IO.println("r - Reports");
          IO.println("H - Back to home");

            String input = UI.userInputString();

            switch (input) {
                case "a" -> repo.getAll().forEach(System.out::println);
                case "d" -> repo.getDeposits().forEach(System.out::println);
                case "p" -> repo.getPayments().forEach(System.out::println);
                case "r"-> handleReportSearch();
                case "h" -> isRunning = false;
            }
        }
    }

    private void handleReportSearch() {
      IO.println("Select a search query");
      IO.println("(1) Previous Year");
      IO.println("(2) Previous Month");
      IO.println("(3) Year to Date");
      IO.println("(4) Month to Date");
      IO.println("(5) Search by Vendor");
      IO.println("(6) Custom");

        String input = UI.userInputString();

        switch (input) {
            case "1" -> repo.previousYear().forEach(System.out::println);
            case "2" -> repo.previousMonth().forEach(System.out::println);
            case "3" -> repo.yearToDate().forEach(System.out::println);
            case "4" -> repo.monthToDate().forEach(System.out::println);
            case "5" -> handleVendorSearch();
            case "6" -> handleCustomSearch();
        }
    }

    private void handleVendorSearch() {
      IO.println("Enter vendor name: ");
        String vendor = UI.userInputString();
        repo.customSearch(vendor, null, null, null, null)
                .forEach(System.out::println);
    }

    private void handleCustomSearch() {

        IO.println("Vendor (leave blank to skip): ");
        String vendor = UI.userInputString();

        IO.println("Description (leave blank to skip): ");
        String desc = UI.userInputString();

        IO.println("Amount (leave blank to skip): ");
        String amountInput = UI.userInputString();
        Double amount = amountInput.isEmpty() ? null : Double.parseDouble(amountInput);

        IO.println("Start Date (leave blank to skip) :");
        String startDateInput = UI.userInputString();
        LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput);

        IO.println("End Date (leave blank to skip) :");
        String endDateInput = UI.userInputString();
        LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);
        

        // Convert empty string to null so the filter skips it
        repo.customSearch(
                vendor.isEmpty() ? null : vendor,
                desc.isEmpty()   ? null : desc,
                amount,
                startDate,
                endDate
        ).forEach(System.out::println);
    }
}