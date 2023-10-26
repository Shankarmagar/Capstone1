package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ReportPage {
    public static void showReportScreen(ArrayList<Transaction> listsOfEntries)
    {

        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
        while(isValid)
        {
            System.out.print("""
                                                 -REPORT SCREEN-
                Please follow the following instructions to proceed:
                 1) Month to Date
                 2) Previous Month
                 3) Year To Date
                 4) Previous Year
                 5) Search by Vendor
                 6) Custom Search
                 0) Back
                """);
            System.out.println("Please choose your option: ");
            int usersInput = scanner.nextInt();

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String currentDate = currentTime.format(formatting);

            if(usersInput == 1)
            {
                displayMonthToDate(listsOfEntries, currentDate);
            }
            else if(usersInput == 2)
            {
                displayPreviousMonth(listsOfEntries, currentDate);
            }
            else if (usersInput == 3)
            {
                displayYearToDate(listsOfEntries, currentDate);
            }
            else if(usersInput == 4)
            {
                displayPreviousYear(listsOfEntries, currentDate);
            }
            else if(usersInput == 5)
            {
                displayByVendor(listsOfEntries, scanner);
            }
            else if(usersInput == 6)
            {
                CustomSearch.showScreenForCustomSearch(listsOfEntries);
            }
            else if(usersInput == 0)
            {
                return;
            }
        }

        }

    public static void displayMonthToDate(ArrayList<Transaction> myLists, String CurrentDate)
    {
        boolean isEmpty = true;
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getDate().substring(3).equals(CurrentDate.substring(3))) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                isEmpty = false;
            }
        }
        if(isEmpty)
        {
            System.out.println("You don't any transactions in this month");
        }
    }

    public static void displayPreviousMonth(ArrayList<Transaction> myLists, String currentDate)
    {
        int currentMonth = Integer.parseInt(currentDate.substring(3,5));
        String previousMonth = String.format("%02d/%s",currentMonth-1,currentDate.substring(6));
        System.out.println("PreviousMonth: "+ previousMonth);
        boolean isEmpty = true;
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getDate().substring(3).equals(previousMonth)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                isEmpty = false;
            }

        }
        if(isEmpty)
        {
            System.out.println("You do not have any transactions on previous month");
        }
    }

    public static void displayYearToDate(ArrayList<Transaction> myLists, String currentDate)
    {
        String currentYear = currentDate.substring(6);
        boolean isEmpty = true;
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getDate().substring(6).equals(currentYear)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                isEmpty = false;
            }
        }
        if(isEmpty)
        {
            System.out.println("You do not have any transactions for this year.");
        }
    }
    public static void displayPreviousYear(ArrayList<Transaction>myLists, String currentDate)
    {
        int currentYear = Integer.parseInt(currentDate.substring(6));
        String previousYear = ""+(currentYear-1);
        System.out.println("PreviousYear: "+ previousYear);
        boolean isEmpty = true;
        for(int i= myLists.size()-1; i>=0; i--)
        {
            if(myLists.get(i).getDate().substring(6).equals(previousYear)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                isEmpty = false;
            }
        }
        if(isEmpty)
        {
            System.out.println("You do not have any transactions on previous year");
        }
    }

    public static void displayByVendor(ArrayList<Transaction>myLists, Scanner scanner)
    {
        displayVendorOptions(myLists);
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

    public static void displayVendorOptions(ArrayList<Transaction>myLists)
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
}
