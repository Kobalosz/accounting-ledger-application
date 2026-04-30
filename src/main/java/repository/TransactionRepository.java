package repository;

import models.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionRepository {
    public List<Transaction> getAll(){
        List<Transaction> transactions = new ArrayList<>();
//        Using this method to close my reader a bit more efficiently (I didn't want to write a finally block)
        try(BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))){
            String line = reader.readLine();
            line = reader.readLine();
            while(line != null){
                String[] splitLine = line.split("\\|");
//                Just gonna write out the variables for better visibility

                LocalDate date = LocalDate.parse(splitLine[0]);
                LocalTime time = LocalTime.parse(splitLine[1]);
                String desc = splitLine[2];
                String vendor = splitLine[3];
                Double amount = Double.parseDouble(splitLine[4]);

//                Woah, actually looks a lot cleaner. You go Imanuel!
                transactions.add(new Transaction(date, time, desc, vendor, amount));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Initially I had just done transactions.reversed() to get the most recent entry, but then I realized that just because it is the most recent entry doesn't mean that it is the latest transaction
        return transactions.stream().sorted(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed()).toList();
    }

    public void save(Transaction transaction){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))){
            writer.write(transaction.getDate().toString() + "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount()
                    );
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Transaction>  getDeposits(){
        return getAll().stream().filter(transaction -> transaction.getAmount()>0).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Transaction> getPayments(){
        return getAll().stream().filter(transaction -> transaction.getAmount()<0).collect(Collectors.toCollection(ArrayList::new));
    }

//    Used variables here so that the return statement was a little less wordy
    public List<Transaction> previousMonth(){
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfMonth());
        return getAll().stream().filter(transaction -> !transaction.getDate().isBefore(firstDay) && !transaction.getDate().isAfter(lastDay)).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Transaction> previousYear(){
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.minusYears(1).withDayOfYear(1);
        LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfYear());
        return getAll().stream()
                .filter(transaction -> !transaction.getDate().isBefore(firstDay) && !transaction.getDate().isAfter(lastDay)).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Transaction> monthToDate(){
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        return getAll().stream()
                .filter(transaction -> !transaction.getDate().isBefore(firstDay) && !transaction.getDate().isAfter(today))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Transaction> yearToDate(){
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfYear(1);
        return getAll().stream()
                .filter(transaction -> !transaction.getDate().isBefore(firstDay) && !transaction.getDate().isAfter(today))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Transaction> customSearch(String vendor, String desc, Double amount ,LocalDate startDate, LocalDate endDate){
        return getAll().stream()
                .filter(transaction -> vendor == null || transaction.getVendor().equalsIgnoreCase(vendor))
                .filter(transaction -> desc == null || transaction.getDescription().equalsIgnoreCase(desc))
                .filter(transaction -> amount == null || Double.compare(transaction.getAmount(), amount) == 0)
                .filter(transaction -> startDate == null || !transaction.getDate().isBefore(startDate))
                .filter(transaction -> endDate == null || !transaction.getDate().isAfter(endDate))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
