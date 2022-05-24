/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

import java.util.ArrayList;

/*
//The Employee Class encapsulate all workers within a restaurant. Our restaurant has Chefs, Cashiers,
Waiters, Bartenders, and deliveryDrivers. It has standard setters and getters for it's member variables.
employeePaymentInfo is a special member variable that holds a String that represents a generated piece of information
about a day of work for every employee.
*/

public class Employee implements simulateDay {
    private String fName; // First name
    private String lName; // Last name
    private String id; // Employee ID
    private String jobTitle; // Employee Job
    private double hourlyPay; // Wage
    private ArrayList<String> employeePaymentInfo = new ArrayList<String>(10);  // a string of information containing various stats about
                                                                                     // an employees day at work

    /**
     * Initializes employee
     */
    public Employee() {
        this.fName = "Blank";
        this.lName = "Blank";
        this.id = "0000";
        this.jobTitle = "Blank";
    }

    /**
     * Initializes FrontOfHouseEmployee with a first name, last name, unique ID number, and their specific job.
     * @param fName First name of the employee.
     * @param lName Last name of the employee.
     * @param id The unique identifier of an employer.
     * @param jobTitle The employees job title.
     */
    public Employee(String fName, String lName, String id, String jobTitle, ArrayList<String> employeePaymentInfo) {
        this.fName = fName;
        this.lName = lName;
        this.id = id;
        this.jobTitle = jobTitle;
        this.employeePaymentInfo = employeePaymentInfo;
    }


    /**
     * Sets the first name of an employee.
     * @param fName employee's first name.
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * Sets the last name of an employee.
     * @param lName employee's last name.
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * Sets the unique identification number of an employee (can only be one per person).
     * @param id short for IDentification.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Sets the job title of the employee. Employee's can only have one job title.
     * @param jobTitle Is only going to be "Chef", "Cashier", "Waiter", "Bartender", or "deliverDriver".
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Sets the wages of an employee. Unique to the jobtitle.
     * @param hourlyPay Is another way of saying wage
     */
    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    /**
     * Gets an employees first name.
     * @return Employees first name given to him at birth.
     */
    public String getfName() {
        return fName;
    }

    /**
     * Gets an employees last name.
     * @return Employees legal last name (married women).
     */
    public String getlName() {
        return lName;
    }

    /**
     * Gets an employees unique IDentification number
     * @return ID number of the employee
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the array of payments for this object to a list of payments from another Employee.
     * @param stuff
     */
    public void setPaymentInfo(ArrayList<String> stuff)
    {
        this.employeePaymentInfo = stuff;
    }

    /**
     * The String of information that describes a day of work for an employee.
     * @param i is used to grab the index of a String that represents a day of work for an employee.
     * @return a String that represents information about a days work for any given employee.
     */
    public String getPaymentInfo(int i) {
        return employeePaymentInfo.get(i);
    }
    /**
     * The Array that holds Strings of information that describes a day of work for an employee
     * @return arrayList of payments
     */
    public ArrayList<String> getPaymentInfo() {
        return employeePaymentInfo;
    }

    /**
     * Represents the amount of days an employee has worked.
     * @return The number of days an employee has worked.
     */
    public int getPaymentInfoLength() {
        return employeePaymentInfo.size();
    }

    /**
     * Gets an employees job title.
     * @return An employees job title.
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Gets the wage of an employee.
     * @return  The amount of money an employee is paid every hour.
     */
    public double getHourlyPay() {
        if(hourlyPay < 7.25)
            return 7.25;
        else
            return hourlyPay;
    }

    /**
     * Will be overriden. Used for debugging purposes.
     */
    public void simDay(){
        System.out.println("Debugging, Debugging, Debugging");
    }

    /**
     * Returns the first and last name of an employee.
     * @return The first and last name concactenated together with a space inbetween.
     */
    @Override
    public String toString() {
        return fName + ' ' + lName;
    }

    /**
     * Will be overriden in FrontOfHouseEmployee and BackOfHouseEmployee
     * Once overriden, will calculate the total gross pay of an employee for their entirety of time spent at
     * the restaurant.
     * If the employee version of grossPay() is called, a message of moral support is elicited
     * and is used for de-bugging purposes.
     * @return Nothing. Only terminates.
     */
    public double grossPay()
    {
        System.out.println("You're doing great!");
        System.out.println("But if you're seeing this message. You did something wrong.");
        System.out.println("Don't get discouraged! You've made great progress!");
        return 0;
    }

}