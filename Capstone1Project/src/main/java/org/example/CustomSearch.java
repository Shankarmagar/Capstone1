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
        System.out.println("Enter the Start Date in DD/MM/YYYY format: ");
        String startDate = scanner.nextLine();
        boolean repeat = CheckInput.checkDate(startDate);

        while(!repeat)
        {
            System.out.println("Invalid Input see Format and re- enter.");
            System.out.println("Enter the Start Date in DD/MM/YYYY format: ");
            startDate = scanner.nextLine();
            repeat = CheckInput.checkDate(startDate);
        }

        System.out.println("Enter the End Date in DD/MM/YYYY format: ");
        String endDate = scanner.nextLine();
        boolean rerun = CheckInput.checkDate(endDate);

        while(!rerun)
        {
            System.out.println("Invalid Input see Format and re- enter.");
            System.out.println("Enter the End Date in DD/MM/YYYY format: ");
            endDate = scanner.nextLine();
            rerun = CheckInput.checkDate(endDate);
        }

        String description = "";
        char c = askUser("Do you want to add Description(Y/N): ", scanner);
        if(c == 'y' || c== 'Y') {
            System.out.println("Enter the description: ");
            description = scanner.nextLine();
            scanner.nextLine();
        }

        String vendor = "";
        c = askUser("Do you want to add vendor(Y/N): ", scanner);
        if(c =='y'|| c== 'Y') {
            System.out.println("Enter the vendor: ");
            vendor = scanner.nextLine();
            scanner.nextLine();
        }

        double amt = 0.00;
        c = askUser("Do you want to add Amount (Y/N): ", scanner);
        if(c =='y'|| c== 'Y') {
            System.out.println("Enter the Amount: ");
            amt = scanner.nextDouble();
        }

        doSearch(startDate,endDate,description,vendor,amt,myLists);


    }

    public static void doSearch(String startDate, String endDate, String description, String vendor, double amt, ArrayList<Transaction> myLists) {
        for (int i = myLists.size() - 1; i >= 0; i--) {
            boolean showValue = startDate.isEmpty() || endDate.isEmpty() || isWithinRange(myLists.get(i).getDate(), startDate, endDate);  // Assume the transaction meets all criteria by default

            if (!description.isEmpty() && !myLists.get(i).getDescription().equals(description)) {
                showValue = false;
            }
            if (!vendor.isEmpty() && !myLists.get(i).getVendor().equals(vendor)) {
                showValue = false;
            }
            if (amt != 0.00 && amt != myLists.get(i).getAmount()) {
                showValue = false;
            }

            if (showValue) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
            }
        }
    }


    public static boolean isWithinRange(String currentDate, String startDate, String endDate) {
        // Split the input dates
        String[] cD = currentDate.split("/");
        String[] sD = startDate.split("/");
        String[] eD = endDate.split("/");

        // Ensure that the input dates are in the correct format (day, month, year)
        if (cD.length != 3 || sD.length != 3 || eD.length != 3) {
            throw new IllegalArgumentException("Input dates must be in the format 'dd/mm/yyyy'");
        }

        // Parse the day, month, and year as integers
        int currentDay = Integer.parseInt(cD[0]);
        int currentMonth = Integer.parseInt(cD[1]);
        int currentYear = Integer.parseInt(cD[2]);

        int startDay = Integer.parseInt(sD[0]);
        int startMonth = Integer.parseInt(sD[1]);
        int startYear = Integer.parseInt(sD[2]);

        int endDay = Integer.parseInt(eD[0]);
        int endMonth = Integer.parseInt(eD[1]);
        int endYear = Integer.parseInt(eD[2]);

        // Check if the current date is within the date range
        if (currentYear >= startYear && currentYear <= endYear) {
            if (currentYear == startYear && currentMonth < startMonth) {
                return false; // Current date is before the start year/month.
            }
            if (currentYear == endYear && currentMonth > endMonth) {
                return false; // Current date is after the end year/month.
            }

            if (currentYear == startYear && currentMonth == startMonth && currentDay < startDay) {
                return false; // Current date is before the start date.
            }
            return currentYear != endYear || currentMonth != endMonth || currentDay <= endDay; // Current date is after the end date.
        }

        return false;
    }



    public static char askUser(String prompt, Scanner scanner)
   {
       System.out.println(prompt);
       return scanner.next().charAt(0);
   }

}

