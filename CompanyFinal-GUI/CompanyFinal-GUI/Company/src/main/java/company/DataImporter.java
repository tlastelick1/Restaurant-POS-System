/*
 * Employee info input class
 * @author Gordon Agerton
 * @version 1.0
 * @since May 1st, 2021
 * Note:
 */
package company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to read data from the files in EmployeeData and put them in an Employee object.
 * Each attribute for the employee object will be set to what is read from the file.
 */

public class DataImporter {

    public static Employee get(String Filename) throws FileNotFoundException {
        File file = new File(Filename);
        Scanner scan = new Scanner(file);
        Employee tempEmployee = new Employee();
        ArrayList<String> employeePaymentInfo = new ArrayList<String>(10);
        System.out.println("Loaded : "+Filename);
        tempEmployee.setfName(scan.nextLine());
        tempEmployee.setlName(scan.nextLine());
        tempEmployee.setID(scan.nextLine());
        tempEmployee.setJobTitle(scan.nextLine());
        tempEmployee.setHourlyPay(scan.nextDouble());
        scan.nextLine();
        scan.nextLine();
        int i = 0;
        while(scan.hasNext()){
            employeePaymentInfo.add(i,scan.nextLine());
            i++;
        }
        tempEmployee.setPaymentInfo(employeePaymentInfo);

        return tempEmployee;

    }
}
