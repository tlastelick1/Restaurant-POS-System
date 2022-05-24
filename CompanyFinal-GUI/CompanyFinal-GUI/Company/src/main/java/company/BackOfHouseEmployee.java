package company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
The BackOfHouseEmployee class represents the jobTitles of Cashier and Chef. It does not handle any tips.
void simDay() function exports standard information about a day of work for an employee who doesn't receive tips.
double grossPay() method calculates the gross pay (calculates total money made of every day worked) of a non-tipped employee.
*/
public class BackOfHouseEmployee extends Employee implements simulateDay {
    private ArrayList<String> employeePaymentInfo = new ArrayList<String>(10);  // a string of information containing various stats about an employees day at work
    /**
     * Constructor calls superClass' constructor. Initializes BackOfHouseEmployee with a first name
     * last name, unique ID number, and their specific job
     * @param fName first name of the employee
     * @param lName last name of the employee
     * @param id the unique identifier of an employer
     * @param jobTitle the employees job title
     */
    public BackOfHouseEmployee (String fName, String lName, String id, String jobTitle, ArrayList<String> employeePaymentInfo)
    {
        super(fName, lName, id, jobTitle,employeePaymentInfo);
    }

    public void setPaymentInfo(ArrayList<String> paymentInfo){
        System.out.println(paymentInfo.size());
        this.employeePaymentInfo = paymentInfo;
    }

    @Override
    public void simDay() {
        /**
         * Precondition: Can only be generated for an employee of class BackOfHouseEmployee
         * This function simulates us having existing information (presumably from a database) to work with.
         * It generates a string of information containing the date, the tip, and hours worked for an employee in a day.
         * This information is appended onto the end of an existing profile for any given BackOfHouseEmployee.
         */

        try{

            File fn = new File("company/src/main/java/EmployeeData/"+getfName()+getlName()+getID()+".dat");
            FileWriter myWriter = new FileWriter(fn, true);
            int hoursWorked = rand.nextInt((8 - 4) + 1) + 4;//8 hours is the maximum, 4 hours is the minimum
            double wageAmount = getHourlyPay() * hoursWorked;
            myWriter.write(String.valueOf(calendar.getTime())+" "+wageAmount+" Hours worked: "+hoursWorked+"\n");
            myWriter.close();
        }
        catch (IOException e){
            System.out.println("An error occurred while simulating a day for Back of House Employee!");
            e.printStackTrace();
        }
    }

    /**
     * Precondition: The employee must be a BackOfHouseEmployee.
     * This program takes a String of information. Sorts it and finds the calculated wage value of each day (oneTip String).
     * It accumulates the gross wage made every day and returns the gross wage made at the restaurant.
     * @local_variable int spaceCount keeps track of delimiter spaces within files.
     * @local_variable String oneTip represents a string (one entire line) of information.
     * regarding tips earned on specific calendar days among other information.
     * @local_variable String stringDollarValue holds the value of the tip in one oneTip String as a St
     * @local_variable double doubleDollarValue holds the stringDollarValue after it has been parsed into a dou
     * @local_variable double gross accumulates the gross pay of each day for the entirety of the employees career.
     * @return The total gross pay of the employee for his entirety at the restaurant (for tax purposes).
     */
    // public double grossPay()
    @Override
    public double grossPay()
    {
        //BackOfHouseEmployee tempEmployee = new BackOfHouseEmployee();
        int spaceCount = 0; // Keeps track of spaces inside oneTip string.
        String oneTip = ""; // Represents a days work of information as a String.
        String stringDollarValue = "";    // A string value to be parsed into a double (represents the tip).
        double doubleDollarValue = 0;   // Holds the amount of tips in each line of String.
        double gross = 0;   // Accumulates the daily money earned from every array

        // Cycle through each (all) the employee's work days
        for (int i=0; i < employeePaymentInfo.size(); i++)
        {
            oneTip = employeePaymentInfo.get(i); // One tip equals an entire string.

            // Cycle through String oneTip
            for (int j=0; j<oneTip.length(); j++)
            {
                // Count the spaces in the String
                if (oneTip.charAt(j) == ' '){
                    spaceCount++;
                }

                // Takes just the value of the tip as a String
                if (spaceCount == 6) {
                    stringDollarValue += oneTip.charAt(j);
                }

            }   // end cycling through oneTip character by character

            doubleDollarValue = Double.parseDouble(stringDollarValue);  // convert tip to double value
            gross += doubleDollarValue;    // accumulate gross pay made each day

            // reset values for the next iteration of oneTip
            spaceCount = 0;
            stringDollarValue = "";
        }

        return Double.parseDouble(String.format("%.2f",gross));
    }
}
