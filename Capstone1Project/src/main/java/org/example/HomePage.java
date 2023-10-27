package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HomePage {
    //Show the Home Screen menu
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
                    
                    Please choose your option: 
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

    //Save the Payment transactions
    public static void doPayment(Scanner scanner)
    {

        System.out.println("Enter the Date in DD/MM/YYYY format: ");
        String date = scanner.nextLine();
        date = scanner.nextLine();
        boolean repeat = CheckInput.checkDate(date);

        while(!repeat)
        {
            System.out.println("Invalid Input see Format and re- enter.");
            System.out.println("Enter the Date in DD/MM/YYYY format: ");
            date = scanner.nextLine();
            repeat = CheckInput.checkDate(date);
        }

        System.out.println("Enter the time in HH:mm format: ");
        String time= scanner.nextLine();

        boolean rerun = CheckInput.checkTime(time);
        while(!rerun)
        {
            System.out.println("You have invalid input read format and re- enter");
            System.out.println("Enter the time in HH:mm format: ");
            time= scanner.nextLine();
            if(CheckInput.checkTime(time))
            {
                rerun = true;
            }
            else{
                rerun = false;
            }
        }

        System.out.println("Enter the Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the receiver name: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount: ");
        double res = 0.00;
        boolean s = CheckInput.checkAmount(res);
        while(s) {
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                res = amount;
                if (amount >= 0) {
                    res = amount;
                    s = false; // Exit the loop when a non-negative amount is entered
                } else {
                    System.out.println("Amount cannot be negative. Re-enter a non-negative amount.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input please re-enter");
                scanner.nextLine();
            }
        }

        try {
            FileWriter writer = new FileWriter("Transactions.csv",true);
            String writeLine = String.format("%s|%s|%s|%s|$-%.2f\n",date.trim(),time.trim(),description.trim(),vendor.trim(),res); // "8.985678" -> 8.99
            writer.write(writeLine);
            writer.close();
            System.out.println("Payment transaction is saved!!!");
        }
        catch(IOException e)
        {
            System.out.println("ERROR;- File is invalid");
        }

    }

    //Save the deposit transactions
    public static void doDeposit(Scanner scanner)
    {

        System.out.println("Enter the Date in DD/MM/YYYY format: ");
        String date = scanner.nextLine();
        date = scanner.nextLine();
        boolean repeat = CheckInput.checkDate(date);

        while(!repeat)
        {
            System.out.println("Invalid Input see Format and re- enter.");
            System.out.println("Enter the Date in DD/MM/YYYY format: ");
            date = scanner.nextLine();
            repeat = CheckInput.checkDate(date);
        }

        System.out.println("Enter the time in HH:mm format: ");
        String time= scanner.nextLine();

        boolean rerun = CheckInput.checkTime(time);
        while(!rerun)
        {

            System.out.println("You have invalid input read format and re- enter");
            System.out.println("Enter the time in HH:mm format: ");
            time= scanner.nextLine();
            if(CheckInput.checkTime(time))
            {
                rerun = true;
            }
            else{
                rerun = false;
            }
        }
        System.out.println("Enter the Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the Vendor name: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount: ");
        double res = 0.00;
        boolean s = CheckInput.checkAmount(res);
        while(s) {
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                res = amount;
                if (amount >= 0) {
                    res = amount;
                    s = false; // Exit the loop when a non-negative amount is entered
                } else {
                    System.out.println("Amount cannot be negative. Re-enter a non-negative amount.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Re - enter");
                scanner.nextLine();
            }
        }



        //writing in the csv
        try {
            FileWriter writer = new FileWriter("Transactions.csv", true);
            String writeLine = String.format("%s|%s|%s|%s|$%.2f\n",date,time,description,vendor,res);
            writer.write(writeLine);
            writer.close();
            System.out.println("Your transaction has been recorded...");
        }
        //throws exception if file doesn't exist
        catch(IOException e)
        {
            System.out.println("ERROR;- File is invalid");
        }
    }
}
