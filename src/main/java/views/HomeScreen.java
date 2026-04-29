package views;
import com.pluralsight.UI;

public class HomeScreen {


    public static String renderScreen() {
        IO.println("==============================================");
        IO.println("--=========== Welcome to Budgie! ===========--");
        IO.println("==============================================");
        IO.println();
        IO.println("Please select your option:");
        IO.println("D) Add Deposit");
        IO.println("P) Make payment");
        IO.println("L) Ledger");
        IO.println("X) Exit");
        return UI.userInputString();
    }
}
