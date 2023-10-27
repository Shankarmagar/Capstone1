# Capstone Project - *Accounting Ledger*

Submitted by: **Shankar Ale Magar**

**Accounting Ledger** is a ledger which keeps track of payment and deposit transactions and display the records of transactions


## Required Features

The following **required** functionality is completed:

- [x] **Home Screen**
- [x] **Ledger Screen**
- [x] **Records Screen**
- [x] **Prompt user for the deposit information and save it to the csv file**
- [x] **Prompt user for payment information and save it in the file.**
- [x] **Display options for Ledger screen**
- [x] **Display Report screen with Options**
- [x] **Display all entries, All deposits transactions, Payments transactions**
- [x] **Inside Report User can able to run custom search for Month to Date, Previous Month, Year To Date, Previous Year, Search by Vendor**


### Screenshots

 | Home Screen | Ledger Screen | Report Screen
 | ----------- | ------------- | ------------- |
 | <img src="https://github.com/Shankarmagar/Capstone1/blob/main/images/Screenshot%202023-10-24%20at%2011.19.10%E2%80%AFAM.png" width = 300> | <img src="https://github.com/Shankarmagar/Capstone1/blob/main/images/Screenshot%202023-10-24%20at%2011.19.30%E2%80%AFAM.png" width = 300> | <img src = "https://github.com/Shankarmagar/Capstone1/blob/main/images/Screenshot%202023-10-24%20at%2011.19.42%E2%80%AFAM.png" width = 300>

<img src="https://github.com/Shankarmagar/Capstone1/blob/main/images/Untitled-2.png">


```

  The HomePage class represents the home screen of a financial application.
  It allows users to perform actions like making payments, adding deposits, and accessing the ledger.
 
public class HomePage {
    
    
     Display the home screen menu and handle user interactions.
     
    public static void showHomeScreen() {
        // ... [code for displaying the menu and handling user input]
    }

    
     Perform a payment transaction and save it to the ledger.
     
      @param scanner The scanner for user input.
     
    public static void doPayment(Scanner scanner) {
        // ... [code for collecting payment transaction details and saving to a file]
    }

    
     Perform a deposit transaction and save it to the ledger.
     
     @param scanner The scanner for user input.
     
    public static void doDeposit(Scanner scanner) {
        // ... [code for collecting deposit transaction details and saving to a file]
    }
}


 The LedgerPage class represents the ledger screen of a financial application.
 It allows users to view different types of transactions and reports.
 
public class LedgerPage {

    
     Display the ledger screen and handle user interactions.
     
    public static void displayLedger() {
        // ... [code for displaying the ledger menu and handling user input]
    }

    
     Show all deposit transactions from a list of entries.
     
     @param listOfEntries The list of transactions.
     
    public static void showDeposit(ArrayList<Transaction> listOfEntries) {
        // ... [code for displaying all deposit transactions]
    }

    
      Get a list of transactions from a CSV file.
     
     @return An ArrayList of Transaction objects.
     
    public static ArrayList<Transaction> getMyEntries() {
        // ... [code for reading transactions from a CSV file]
        return res;
    }

    
      Show all transactions from a list of entries.
     
      @param listOfEntries The list of transactions.
     
    public static void showAll(ArrayList<Transaction> listOfEntries) {
        // ... [code for displaying all transactions]
    }

    
     Show all payment transactions from a list of entries.
     
     @param listOfentries The list of transactions.
     
    public static void showPayments(ArrayList<Transaction> listOfentries) {
        // ... [code for displaying payment transactions]
    }
}


  The ReportPage class represents the reporting functionality of a financial application.
  It allows users to generate various financial reports.
 
public class ReportPage {


      Display the report screen and handle user interactions.
     
      @param listsOfEntries The list of transactions.
     
    public static void showReportScreen(ArrayList<Transaction> listsOfEntries) {
        // ... [code for displaying the report menu and handling user input]
    }

    
      Display all transactions for the current month.
     
      @param myLists      The list of transactions.
      @param CurrentDate  The current date.
     
    public static void displayMonthToDate(ArrayList<Transaction> myLists, String CurrentDate) {
        // ... [code for displaying transactions for the current month]
    }

    
     Display all transactions for the previous month.
     
     @param myLists      The list of transactions.
     @param currentDate  The current date.
     
    public static void displayPreviousMonth(ArrayList<Transaction> myLists, String currentDate) {
        // ... [code for displaying transactions for the previous month]
    }

    
     Display all transactions for the current year.
     
     @param myLists      The list of transactions.
     @param currentDate  The current date.
    
    public static void displayYearToDate(ArrayList<Transaction> myLists, String currentDate) {
        // ... [code for displaying transactions for the current year]
    }

    
     Display all transactions for the previous year.
     
     @param myLists      The list of transactions.
     @param currentDate  The current date.
     
    public static void displayPreviousYear(ArrayList<Transaction> myLists, String currentDate) {
        // ... [code for displaying transactions for the previous year]
    }

    
      Display transactions by a specific vendor.
     
      @param myLists   The list of transactions.
      @param scanner   The scanner for user input.
     
    public static void displayByVendor(ArrayList<Transaction> myLists, Scanner scanner) {
        // ... [code for displaying transactions by vendor]
    }
}

```

### Input Constraints
## Date
- Date should be in format dd/MM/yyyy
- Cannot be in dd-MM-yyyy
- date should not be more than 31 
- Month cannot be more than 12
- Date cannot the in future
- No character except for '/'

## Time
- should be in format HH:mm
- hour cannot be more than 24
- min cannot be more than 59
- Cannot contain character except for :

## Amount
- Cannot be string 
- Should not include - at front
- No dollar sign

### Mathematical Logic

## Sorting the transaction
- Customize collections sort 
- check Date first then if only if date is equal time
- Apply loop from last 

## Payment transactions
- Check if amount is less than 0 to determine payment transactions

## Deposit transactions
- Check if amount is more than 0 to determine deposit transactions

## Month to Date
- LocalDateTime to grab current date and picking only MM/yyyy from dd/MM/yyyy
- Comparing with each transactions and display if match

## Previous Month
- LocalDateTime to grab current date and picking only MM from dd/MM/yyyy
- Then Subtracting by 1 to find previous month
- Then comparing MM-1/yyyy with each transactions

## Year to date
- LocalDateTime to grab current time and picking only yyyy from dd/MM/yyyy 
- Then comparing it with each transactions

## Previous year
- LocalDateTime to grab current time and picking only yyyy from dd/MM/yyyy 
- Then subtracting 1 from yyyy to get previous year
- Then comparing yyyy-1 with each transactions

## Search vendor
- Hash Set to check re occurrence of vendor name and display all the vendor options 
- Ask user to choose options
- comparing user input with each transaction.getVendor()


## Unique code
```
public class AccountLedger {
    public static void main(String[] args) {

        //Welcome Page
        System.out.print("Welcome to Ledger Office!!!\n");
        //Call the Home Screen Page
        HomePage.showHomeScreen();
    }
}

```
This code is my unique code because it wraps everything



## Notes

Sorting my array lists based on date and time was a bit challenging in this project.
Stacking because of recursive calls and solved with by returning from the function to avoid recursive calls.

