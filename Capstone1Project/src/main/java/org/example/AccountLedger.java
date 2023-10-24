package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class AccountLedger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Ledger Office!!!\n");
        DisplayHome(scanner);
    }

    // Function to display Home
    public static void DisplayHome(Scanner scanner)
    {

        System.out.print("""
                
                                                   -HOME SCREEN-
                Please follow the following instructions to proceed:
                'D' Add Deposit
                'P' Make Payment
                'L' Ledger
                'X' Exit
                """);

        char userChoice = scanner.next().charAt(0);

        if(userChoice == 'L' || userChoice == 'l')
        {
            DisplayLedger(scanner);
        }
        else if(userChoice == 'P' || userChoice == 'p')
        {
            DoPayment(scanner);
        }
        else if(userChoice == 'D' || userChoice == 'd')
        {
            DoDeposit(scanner);
        }
    }

    //Function to display Ledger
    public static void DisplayLedger(Scanner scanner)
    {
        System.out.print("""
                                                 -LEDGER SCREEN-
                Please follow the following instructions to proceed:
                'A' All entries
                'D' Deposits
                'P' Payments
                'R' Reports
                'H' Home
                """);
        char userInput = scanner.next().charAt(0);
        ArrayList<Transaction> myLists = getMyEntries();
        myLists.sort(new DateComparator());
        if(userInput == 'H' || userInput == 'h')
        {
            DisplayHome(scanner);
        }
        else if(userInput == 'D'|| userInput == 'd')
        {
            ShowDeposit(myLists);
            AskUserToContinueOrExit(scanner);
        }
        else if(userInput == 'A'|| userInput == 'a')
        {
            ShowAll(myLists);
            AskUserToContinueOrExit(scanner);
        }
        else if(userInput == 'P' || userInput == 'p')
        {
            ShowPayments(myLists);
            AskUserToContinueOrExit(scanner);
        }
        else if(userInput == 'R' || userInput == 'r')
        {
            ShowReportScreen(scanner, myLists);
            AskUserToContinueOrExit(scanner);
        }
    }


    // Function to make Payment
    public static void DoPayment(Scanner scanner)
    {
        System.out.println("Enter the Date in DD/MM/YYYY format: ");
        String date = scanner.nextLine();
        date = scanner.nextLine();

        System.out.println("Enter the time in HH:mm format: ");
        String time= scanner.nextLine();

        System.out.println("Enter the Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the receiver name: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount to make payment: ");
        double amount = scanner.nextDouble();

        try {
            FileWriter writer = new FileWriter("Transactions.csv");
            String writeLine = String.format("%s|%s|%s|%s|$-%.2f",date,time,description,vendor,amount); // "8.985678" -> 8.99
            writer.write(writeLine);
            writer.close();
            System.out.println("Payment transaction is saved!!!");
            DisplayHome(scanner);
        }
        catch(IOException e)
        {
            System.out.println("ERROR;- File is invalid");
        }

    }

    //
    public static void DoDeposit(Scanner scanner)
    {
        //FileWriter writer = new FileWriter("Transactions.csv");
        System.out.println("Enter the Date in DD/MM/YYYY format: ");
        String date = scanner.nextLine();
        date = scanner.nextLine();

        System.out.println("Enter the time in HH:mm format: ");
        String time= scanner.nextLine();

        System.out.println("Enter the Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the Vendor name: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount: ");
        double amount = scanner.nextDouble();

        try {
            FileWriter writer = new FileWriter("Transactions.csv");
            String writeLine = String.format("%s|%s|%s|%s|$%.2f",date,time,description,vendor,amount);
            writer.write(writeLine);
            writer.close();
            System.out.println("Your transaction has been recorded...");

            DisplayHome(scanner);

        }
        catch(IOException e)
        {
            System.out.println("ERROR;- File is invalid");
        }
    }

    public static void ShowDeposit(ArrayList<Transaction> listOfEntries)
    {
     System.out.print("""
                                -All Deposit Transactions-
                                    
             """);
        System.out.printf("%-16s %-10s %-35s %-30s %-10s%n", "Date", "Time", "Description", "Vendor", "Amt");

        for(Transaction entry: listOfEntries)
     {
         if(entry.getAmount()>0)
         {
             String s = String.format("%-16s %-10s %-35s %-30s %.2f", entry.getDate(), entry.getTime(), entry.getDescription(), entry.getVendor(), entry.getAmount());
             System.out.println(s);
         }
     }
    }
    public static ArrayList<Transaction> getMyEntries()
    {
       ArrayList<Transaction> res = new ArrayList<>();
       try {
           FileReader fileName = new FileReader("Transactions.csv");
           BufferedReader readMyfile = new BufferedReader(fileName);
           String lines = readMyfile.readLine();
           while((lines = readMyfile.readLine()) != null)
           {
             String [] data = lines.split("\\|");
             String d = data[0];
             String t = data[1];
             String desc = data[2];
             String vendor = data[3];
             double amount = Double.parseDouble(data[4]);

             res.add(new Transaction(d,t,desc,vendor,amount));
           }
       }
       catch (IOException e)
       {
           System.out.println("ERROR- Invalid File!!!");
       }
       return res;
    }

    public static void ShowAll(ArrayList<Transaction> listOfEntries)
    {
        System.out.print("""
                                -All Transactions-
                                    
             """);
        System.out.printf("%-16s %-10s %-35s %-30s %-10s%n", "Date", "Time", "Description", "Vendor", "Amt");

        for(int i= listOfEntries.size()-1; i>=0; i--)
        {
            String s = String.format("%-16s %-10s %-35s %-30s %.2f", listOfEntries.get(i).getDate(), listOfEntries.get(i).getTime(), listOfEntries.get(i).getDescription(), listOfEntries.get(i).getVendor(), listOfEntries.get(i).getAmount());
            System.out.println(s);
        }
    }

    public static void ShowPayments(ArrayList<Transaction> listOfentries)
    {
        System.out.print("""
                                -All Payments Transactions-
                                    
             """);
        System.out.printf("%-16s %-10s %-35s %-30s %-10s%n", "Date", "Time", "Description", "Vendor", "Amt");

        for(int i= listOfentries.size()-1; i>=0; i--)
        {
            if(listOfentries.get(i).getAmount()<0) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", listOfentries.get(i).getDate(), listOfentries.get(i).getTime(), listOfentries.get(i).getDescription(), listOfentries.get(i).getVendor(), listOfentries.get(i).getAmount());
                System.out.println(s);
            }
        }
    }

    public static void ShowReportScreen(Scanner scanner, ArrayList<Transaction> listsOfEntries)
    {
        System.out.print("""
                                                 -REPORT SCREEN-
                Please follow the following instructions to proceed:
                 1) Month to Date
                 2) Previous Month
                 3) Year To Date
                 4) Previous Year
                 5) Search by Vendor
                 0) Back
                """);
        System.out.println("Please choose your option: ");
        int usersInput = scanner.nextInt();

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String currentDate = currentTime.format(formatting);

        if(usersInput == 1)
        {
            DisplayMonthToDate(listsOfEntries, currentDate);
        }
        else if(usersInput == 2)
        {
            DisplayPreviousMonth(listsOfEntries, currentDate);
        }
        else if (usersInput == 3)
        {
            DisplayYearToDate(listsOfEntries, currentDate);
        }
        else if(usersInput == 4)
        {
           DisplayPreviousYear(listsOfEntries, currentDate);
        }
        else if(usersInput == 5)
        {
           DisplayByVendor(listsOfEntries, scanner);
        }
        else if(usersInput == 0)
        {
            DisplayLedger(scanner);
        }

    }

    public static void DisplayMonthToDate(ArrayList<Transaction> myLists, String CurrentDate)
    {
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getDate().substring(3).equals(CurrentDate.substring(3))) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
            }
        }
    }

    public static void DisplayPreviousMonth(ArrayList<Transaction> myLists, String currentDate)
    {
        int currentMonth = Integer.parseInt(currentDate.substring(3,5));
        String previousMonth = String.format("%02d/%s",currentMonth-1,currentDate.substring(6));
        System.out.println("PreviousMonth: "+ previousMonth);
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getDate().substring(3).equals(previousMonth)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
            }
        }
    }

    public static void DisplayYearToDate(ArrayList<Transaction> myLists, String currentDate)
    {
        String currentYear = currentDate.substring(6);
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getDate().substring(6).equals(currentYear)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
            }
        }
    }

    public static void DisplayPreviousYear(ArrayList<Transaction>myLists, String currentDate)
    {
        int currentYear = Integer.parseInt(currentDate.substring(6));
        String previousYear = ""+(currentYear-1);
        System.out.println("PreviousYear: "+ previousYear);
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getDate().substring(6).equals(previousYear)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
            }
        }
    }

    public static void DisplayByVendor(ArrayList<Transaction>myLists, Scanner scanner)
    {
        DisplayVendorOptions(myLists);
        System.out.println("Enter the vendor name: ");
        String vendorInput = scanner.nextLine();
        vendorInput = scanner.nextLine();
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getVendor().equals(vendorInput)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
            }
        }


    }

    public static void DisplayVendorOptions (ArrayList<Transaction>myLists)
    {
        System.out.println("Following vendor are appear in your ledger: ");
        StringBuilder res = new StringBuilder();
        HashSet<String> s = new HashSet<>();
        for (Transaction myList : myLists) {
            if (!s.contains(myList.getVendor())) {
                String Add = myList.getVendor()+ "      ";
                res.append(Add);
                s.add(myList.getVendor());
            }
        }
        System.out.println(res.toString());
    }

    public static void AskUserToContinueOrExit(Scanner scanner)
    {
        System.out.println("Do you want to continue or exit: ");
        {
            char answer = scanner.next().charAt(0);
            if(answer == 'y'|| answer == 'Y')
            {
                DisplayLedger(scanner);
            }
        }
    }

}


