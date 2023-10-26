package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HomePage {
    public static void showHomeScreen()
    {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;

        while(isValid) {
            System.out.print("""
                                    
                                                       -HOME SCREEN-
                    Please follow the following instructions to proceed:
                    'D' Add Deposit
                    'P' Make Payment
                    'L' Ledger
                    'X' Exit
                    """);

            char userChoice = scanner.next().charAt(0);

            if (userChoice == 'L' || userChoice == 'l') {
                LedgerPage.displayLedger();
            } else if (userChoice == 'P' || userChoice == 'p') {
                doPayment(scanner);
            } else if (userChoice == 'D' || userChoice == 'd') {
                doDeposit(scanner);
            } else if (userChoice == 'X' || userChoice == 'x') {
                isValid = false;
            } else {
               System.out.println("Invalid Choice. Please enter valid option");
            }
        }

    }
    public static void doPayment(Scanner scanner)
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
            FileWriter writer = new FileWriter("Transactions.csv",true);
            String writeLine = String.format("%s|%s|%s|%s|$-%.2f\n",date,time,description,vendor,amount); // "8.985678" -> 8.99
            writer.write(writeLine);
            writer.close();
            System.out.println("Payment transaction is saved!!!");
            showHomeScreen();
        }
        catch(IOException e)
        {
            System.out.println("ERROR;- File is invalid");
        }

    }
    public static void doDeposit(Scanner scanner)
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
            FileWriter writer = new FileWriter("Transactions.csv", true);
            String writeLine = String.format("%s|%s|%s|%s|$%.2f\n",date,time,description,vendor,amount);
            writer.write(writeLine);
            writer.close();
            System.out.println("Your transaction has been recorded...");

            showHomeScreen();

        }
        catch(IOException e)
        {
            System.out.println("ERROR;- File is invalid");
        }
    }
}
