package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerPage {

    //displaying Ledger
    public static void displayLedger()
    {

        boolean isValid = true;
        Scanner scanner = new Scanner(System.in);

        while(isValid) {
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
            if (userInput == 'H' || userInput == 'h') {
                isValid = false;
                return;

            } else if (userInput == 'D' || userInput == 'd') {
                showDeposit(myLists);


            } else if (userInput == 'A' || userInput == 'a') {
                showAll(myLists);

            } else if (userInput == 'P' || userInput == 'p') {
                showPayments(myLists);

            } else if (userInput == 'R' || userInput == 'r') {
                ReportPage.showReportScreen(myLists);
            } else {
                System.out.println("Invalid input. re-enter the input");
            }

        }

    }

    //Showing all deposits
    public static void showDeposit(ArrayList<Transaction> listOfEntries)
    {
        System.out.print("""
                                -All Deposit Transactions-
                                    
             """);
        System.out.printf("%-16s %-10s %-35s %-30s %-10s%n", "Date", "Time", "Description", "Vendor", "Amt");

        for(Transaction entry: listOfEntries)
        {
            if(entry.getAmount()>0)
            {
                String s = String.format("%-16s %-10s %-35s %-30s $%.2f", entry.getDate(), entry.getTime(), entry.getDescription(), entry.getVendor(), entry.getAmount());
                System.out.println(s);
            }
        }
    }

    //Getting the data
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
                double amount = Double.parseDouble(data[4].substring(1));

                res.add(new Transaction(d,t,desc,vendor,amount));
            }
        }
        catch (IOException e)
        {
            System.out.println("ERROR- Invalid File!!!");
        }
        return res;
    }


    //Showing the list of entries
    public static void showAll(ArrayList<Transaction> listOfEntries)
    {
        System.out.print("""
                                -All Transactions-
                                    
             """);
        System.out.printf("%-16s %-10s %-35s %-30s %-10s%n", "Date", "Time", "Description", "Vendor", "Amt");

        for(int i= listOfEntries.size()-1; i>=0; i--)
        {
            String s = String.format("%-16s %-10s %-35s %-30s $%.2f", listOfEntries.get(i).getDate(), listOfEntries.get(i).getTime(), listOfEntries.get(i).getDescription(), listOfEntries.get(i).getVendor(), listOfEntries.get(i).getAmount());
            System.out.println(s);
        }
    }

    //Showing all the payments
    public static void showPayments(ArrayList<Transaction> listOfentries)
    {
        System.out.print("""
                                -All Payments Transactions-
                                    
             """);
        System.out.printf("%-16s %-10s %-35s %-30s %-10s%n", "Date", "Time", "Description", "Vendor", "Amt");

        for(int i= listOfentries.size()-1; i>=0; i--)
        {
            if(listOfentries.get(i).getAmount()<0) {
                String s = String.format("%-16s %-10s %-35s %-30s $%.2f", listOfentries.get(i).getDate(), listOfentries.get(i).getTime(), listOfentries.get(i).getDescription(), listOfentries.get(i).getVendor(), listOfentries.get(i).getAmount());
                System.out.println(s);
            }
        }
    }

    public static void AskUser(Scanner scanner, boolean isValid)
    {
        System.out.println("Do you want to continue :  ");
        char ans = scanner.next().charAt(0);
        if(ans == 'n' || ans=='N')
        {
            isValid = false;
            return;
        }

    }
}
