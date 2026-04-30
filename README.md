# 📒 Accounting Ledger
This is my first ever README! Note that I had assistance from claude to write this out! 

This is a Java console application for tracking personal financial transactions. Supports deposits, payments, persistent CSV storage, and a full suite of predefined and custom search filters — all presented through a color-formatted terminal interface.

## 📋 Features

- **Deposits** —> Record incoming transactions with auto-stamped date and time
- **Payments** —> Record outgoing transactions, automatically stored as negative amounts
- **Persistent Storage** —> All transactions saved to and loaded from `transactions.csv`
- **Ledger View** —> Browse all transactions, deposits only, or payments only in a formatted table
- **Predefined Searches** —> Filter by Month to Date, Previous Month, Year to Date, Previous Year, or Vendor
- **Custom Search** —> Filter by any combination of vendor, description, amount, start date, and end date
- **Color-coded Display** —> Deposits in green, payments in red, net totals calculated per result set
- **Input Validation** —> Graceful handling of invalid menu options, non-numeric amounts, and malformed dates

---

## 🗂️ Project Structure

```
src/
└── main/
    └── java/
        ├── com/pluralsight/
        │   ├── Main.java                  Entry point — wires dependency injection
        │   └── Transaction.java           Data model (POJO)
        ├── repository/
        │   └── TransactionRepository.java CSV read/write and all search logic
        ├── views/
        │   ├── HomeScreen.java            Main menu — deposit, payment, ledger routing
        │   ├── LedgerScreen.java          Ledger menu — display and search screens
        │   └── Display.java               All formatted console output and ASCII art
        └── util/
            ├── UI.java                    Scanner input utility
            └── Colors.java                ANSI escape code constants

transactions.csv                           Persistent transaction storage
```

---

## 💾 CSV Format

Transactions are stored in `transactions.csv` at the project root using pipe-delimited fields (ooo sounds fancy!):

```
date|time|description|vendor|amount
```

**Example:**
```
2026-04-30|14:20|Direct deposit|Employer|2500.00
2026-04-30|09:15|Grocery run|Whole Foods|-85.50
2026-03-14|10:00|Freelance payment|Client Co|750.00
```

| Field       | Type        | Format          |
|-------------|-------------|-----------------|
| date        | LocalDate   | `yyyy-MM-dd`    |
| time        | LocalTime   | `HH:mm`         |
| description | String      | Free text       |
| vendor      | String      | Free text       |
| amount      | double      | Positive = deposit, Negative = payment |

---

## 🧭 Navigation

```
Home Screen
├── [D] Make a Deposit
├── [P] Make a Payment
├── [L] View Ledger
│   ├── [A] All Transactions
│   ├── [D] Deposits Only
│   ├── [P] Payments Only
│   └── [R] Reports & Search
│       ├── [1] Previous Year
│       ├── [2] Previous Month
│       ├── [3] Year to Date
│       ├── [4] Month to Date
│       ├── [5] Search by Vendor
│       ├── [6] Custom Search
│       └── [H] Back
└── [X] Exit
```

---

## 🏗️ Architecture & Design Decisions

### Repository Pattern
All CSV read/write logic is centralized in `TransactionRepository`. No other class in the application touches the file directly — if the storage mechanism ever changed (e.g. switching from CSV to a database), only the repository would need updating. (I got to learn about this concept through my peer [Omar](https://github.com/BrianMiranda8), another talented developer!)

### Dependency Injection
`TransactionRepository` is instantiated once in `Main.java` and injected into `HomeScreen`, which passes the same instance down to `LedgerScreen`. No screen creates its own repository — this keeps the codebase loosely coupled and easy to test.

### Separation of Concerns
| Layer        | Responsibility                          |
|--------------|-----------------------------------------|
| `Transaction`| Shape of the data — nothing else        |
| `Repository` | Reading, writing, filtering data        |
| `Screens`    | User routing and flow logic             |
| `Display`    | All console output and formatting       |
| `UI`         | All console input via Scanner           |

### Streams for Filtering
All search methods use Java Streams with null-safe short-circuit filtering. Passing `null` for any custom search field skips that filter entirely — no combinatorial if/else branching required.

### Immutable Results
All repository methods return `List<Transaction>` via `.toList()`, producing an immutable result. Search results are for display only — they cannot be accidentally modified by the calling code.

---


### Seeding Sample Data

To test all search filters, add the following to `transactions.csv` at the project root:

```
2026-04-15|09:30|Direct deposit|Employer|2500.00
2026-04-03|14:22|Grocery run|Whole Foods|-85.50
2026-04-20|11:05|Electric bill|Duke Energy|-120.00
2026-03-14|10:00|Freelance payment|Client Co|750.00
2026-03-22|16:45|Gas station|Shell|-45.00
2026-03-01|09:00|Rent|Landlord|-1200.00
2026-01-10|08:30|Bonus|Employer|1000.00
2026-02-14|19:30|Restaurant|Olive Garden|-67.00
2025-11-20|12:00|Consulting|Client Co|500.00
2025-12-31|09:00|Year end bonus|Employer|3000.00
```

---

## 🛠️ Technologies Used

| Technology         | Purpose                            |
|--------------------|------------------------------------|
| Java 21            | Core language                      |
| `java.time`        | LocalDate, LocalTime, TemporalAdjusters |
| Java Streams API   | Filtering and sorting transactions |
| `java.io`          | BufferedReader / BufferedWriter for CSV I/O |
| ANSI Escape Codes  | Terminal color and formatting      |
| Maven              | Build and dependency management    |

---

## 👤 Author

**Imanuel** — Built as a capstone project exploring Java OOP, the Repository Pattern, Dependency Injection, and the Java Streams API.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).