package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomSearch {
    public static void showScreenForCustomSearch(ArrayList<Transaction> myLists)
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Please Enter the value for custom search:
                """);
        System.out.println("Enter the Date in DD/MM/YYYY format: ");
        String startDate = scanner.nextLine();
        boolean repeat = CheckInput.checkDate(startDate);

        while(!repeat)
        {
            System.out.println("Invalid Input see Format and re- enter.");
            System.out.println("Enter the Date in DD/MM/YYYY format: ");
            startDate = scanner.nextLine();
            repeat = CheckInput.checkDate(startDate);
        }

        System.out.println("Enter the Date in DD/MM/YYYY format: ");
        String endDate = scanner.nextLine();
        endDate = scanner.nextLine();
        boolean rerun = CheckInput.checkDate(endDate);

        while(!rerun)
        {
            System.out.println("Invalid Input see Format and re- enter.");
            System.out.println("Enter the Date in DD/MM/YYYY format: ");
            endDate = scanner.nextLine();
            rerun = CheckInput.checkDate(endDate);
        }


        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the Amount: ");
        double amt = scanner.nextDouble();

        doSearch(startDate,endDate,description,vendor,amt,LedgerPage.getMyEntries());


    }

    public static void doSearch(String startDate, String endDate, String description, String vendor, double amt, ArrayList<Transaction> myLists)
    {
        boolean ShowValue = false;

        for(int i =myLists.size()-1; i>=0; i--)
        {
            System.out.println(myLists.get(i).getDate());
            if(!startDate.isEmpty() && !endDate.isEmpty() && isWithinRange(myLists.get(i).getDate(),startDate,endDate))
            {
             ShowValue = true;
            }
            if(!description.isEmpty() && myLists.get(i).getDescription().equals(description))
            {
                ShowValue = true;
            }
            if(!vendor.isEmpty() && myLists.get(i).getVendor().equals(vendor))
            {
                ShowValue = true;
            }
            if(amt == myLists.get(i).getAmount() )
            {
                ShowValue = true;
            }
            if(ShowValue)
            {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
            }
        }


    }

    public static boolean isWithinRange(String currentDate, String startDate, String endDate)
    {

        boolean resultIsTrue = false;
        String [] cD = currentDate.split("/");
        String [] sD = startDate.split("/");
        String [] eD = endDate.split("/");

        //checking year
        if(Double.parseDouble(cD[2])>=Double.parseDouble(sD[2]) && Double.parseDouble(cD[2])<=Double.parseDouble(eD[2])
        && Double.parseDouble(cD[1])>=Double.parseDouble(sD[1]) && Double.parseDouble(cD[1])<=Double.parseDouble(eD[1])
                && Double.parseDouble(cD[0])>=Double.parseDouble(sD[0]) && Double.parseDouble(cD[0])<=Double.parseDouble(eD[0])
        )
        {
         resultIsTrue = true;
        }

        return resultIsTrue;
    }

}
