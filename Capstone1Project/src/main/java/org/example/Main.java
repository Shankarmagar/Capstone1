package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Welcome to Ledger Office!!!\n");
        DisplayHome(scanner);
    }

    // Function to display Home
    public static void DisplayHome(Scanner scanner)
    {

        System.out.printf("""
                
                                                   -HOME-
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
        System.out.printf("""
                Please follow the following instructions to proceed:
                'A' All entries
                'D' Deposits
                'P' Payments
                'R' Reports
                'H' Home
                """);
        char userInput = scanner.next().charAt(0);

        if(userInput == 'H' || userInput == 'h')
        {
            DisplayHome(scanner);
        }



    }


    // Function to make Payment
    public static void DoPayment(Scanner scanner)
    {

    }

    //
    public static void DoDeposit(Scanner scanner)
    {

    }
}

