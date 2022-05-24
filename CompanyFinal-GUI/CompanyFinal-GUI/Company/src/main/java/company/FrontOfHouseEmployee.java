package company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
The FrontOfHouseEmployee class represents the jobTitles of Waiter, deliveryDriver, and Bartender.
It is unique in the fact that this class handles tips.
void simDay() function exports standard information about a day of work for any employee who receives tips.
Therefore, it exports tips in its generated report.
double grossPay() method calculates the gross pay (calculates total money made of every day worked) of a tipped employee.

*/

public class FrontOfHouseEmployee extends Employee implements simulateDay{
    private ArrayList<String> employeePaymentInfo = new ArrayList<String>(10);  // a string of information containing various stats about an employees day at work
    /**
     * Constructor calls superClass' constructor. Initializes FrontOfHouseEmployee with a first name
     * last name, unique ID number, and their specific job
     * @param fName first name of the employee
     * @param lName last name of the employee
     * @param id the unique identifier of an employer
     * @param jobTitle the employees job title
     */
    public FrontOfHouseEmployee (String fName, String lName, String id, String jobTitle, ArrayList<String> employeePaymentInfo)
    {
        super(fName, lName, id, jobTitle, employeePaymentInfo);
    }

    public void setPaymentInfo(ArrayList<String> paymentInfo){
        this.employeePaymentInfo = paymentInfo;
    }

    @Override
    public void simDay(){
        /**
         * Precondition: Can only be generated for an employee of class FrontOfHouseEmployee
         * This function simulates us having existing information (presumably from a database) to work with.
         * It generates a string of information containing the date, the tip, and hours worked for an employee in a day.
         * This information is append onto the end of an existing profile for any given FrontOfHouseEmployee.
         */
        int tipUpperbound = 10; // Max amount of tip dollar amount
        int tipLowerbound = 1; // Min amount of tip
        double tipAmount = 0; // Current tip amount
        int numOfTips = rand.nextInt((10-3) + 1) + 3; //Sets the number of generated tips between 3-10
        int maxHours = 3;
        int minHours = 1;
        int hoursWorked = 0;

        //Write to the filename
        try{
            File fn = new File("company/src/main/java/EmployeeData/"+getfName()+getlName()+getID()+".dat"); // Find the file name that was passed in.
            FileWriter myWriter = new FileWriter(fn, true);
            for (int i = 0; i < numOfTips; i++) {
                tipAmount = rand.nextInt((tipUpperbound - tipLowerbound) + 1) + tipLowerbound; // Set tipAmount to a random number between 1-50
                hoursWorked = rand.nextInt((maxHours - minHours) + 1) + minHours; // Set hoursWorked to a random number between 4-8
                myWriter.write(String.valueOf(calendar.getTime())+" "+tipAmount+" Hours worked: "+hoursWorked+"\n"); // Write the line to the file.
            }
            myWriter.close();
        }
        catch(IOException e){
            System.out.println("An error occurred while simulating a day for Front of House Employee!");
            e.printStackTrace();
        }
    }

    /**
     * Precondition: The employee must be a FrontOfHouseEmployee.
     * This program takes a String of information. Sorts it and finds the tip and the hours worked and
     * keeps an accumulator for both of them, and, along with this class' hourly wage calculates the gross pay.
     * @local_variable int spaceCount keeps track of delimiter spaces within files.
     * @local_variable String oneTip represents a string (one entire line) of information.
     * regarding tips earned on specific calendar days among other information.
     * @local_variable String stringDollarValue holds the value of the tip in one oneTip String as a String
     * @local_variable String stringHoursWorked holds the value of the hours worked in one oneTip String as a String
     * @local_variable double doubleHoursWorked holds the stringHoursWorked after it has been parsed into a double.
     * @local_variable double doubleDollarValue holds the stringDollarValue after it has been parsed into a double.
     * @local_variable double totalHours is an accumulator specifically for all the hours worked by an employee ever.
     * @local_variable double totalTips is an accumulator specifically for every tip earned by an employee ever.
     * @return The total gross pay of the employee (for tax purposes).
     */
    @Override
    public double grossPay()
    {
        int spaceCount = 0; // Keeps track of spaces inside oneTip string.
        String oneTip = ""; // Represents a days work of information as a String
        String stringDollarValue = "";    // A string value to be parsed into a double (represents the tip).
        String stringHoursWorked = "";  // A string value to be parsed into a double (represents the hours worked).
        double doubleHoursWorked = 0;   // Holds the total amount of hours worked as a double.
        double doubleDollarValue = 0;   // Holds the amount of tips in each line of String.
        double totalTips = 0;   // Accumulates the tips from every array (will be the total tips this person made).
        double totalHours = 0;  // Accumulates total hours worked for the company.
        // Cycle through each (all) the employee's work days
        for (int i=0; i < this.getPaymentInfoLength(); i++)
        {
            oneTip = this.getPaymentInfo(i); // One tip equals an entire string.

            // Cycle through String oneTip
            for (int j=0; j<oneTip.length(); j++)
            {
                // Count the spaces in the String
                if (oneTip.charAt(j) == ' '){
                    spaceCount++;
                }

                // Takes just the value of the tip as a String
                if (spaceCount == 6){
                    stringDollarValue += oneTip.charAt(j);
                }

                // Takes just the value of the hours worked as a String
                if (spaceCount == 9){
                    stringHoursWorked += oneTip.charAt(j);
                }

            }   // end cycling through oneTip character by character

            doubleDollarValue = Double.parseDouble(stringDollarValue);  // convert tip to double value
            doubleHoursWorked = Double.parseDouble(stringHoursWorked);  // convert hours worked to double value
            totalTips += doubleDollarValue; // accumulate tip value
            totalHours += doubleHoursWorked;

            // reset values for the next iteration of oneTip
            spaceCount = 0;
            stringDollarValue = "";
            stringHoursWorked = "";
        }

        double gross = (totalHours * getHourlyPay()) + totalTips;   // calculate gross income
        return Double.parseDouble(String.format("%.2f",gross));
    }


}   // end FrontOfHouseEmployee
