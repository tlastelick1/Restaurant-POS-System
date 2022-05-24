package company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to export the new payment information to an Employee object's .dat file
 * so that when the program is launched again, we can read from the data
 * from the files.
 */

public class DataExporter {
    Employee CurrentEmployee;
    File FileName;
    PaymentInfoCalculator tempTip;
    FileWriter myWriter;

    public DataExporter(){
    }
    public DataExporter(Employee CurrentEmployee, PaymentInfoCalculator tempTip) throws IOException {
        /**
         * This function will export the new payment info to a .dat file in the EmployeeData folder for the selected Employee.
         * @param CurrentEmployee This is the employee object that we will be reading data from.
         * @param tempTip This is the list of tips that the employee currently has that will be written to the file.
         */
        this.CurrentEmployee = CurrentEmployee;
        this.tempTip = tempTip;

        try{
            FileName = new File("company/src/main/java/EmployeeData/"+CurrentEmployee.getfName()+CurrentEmployee.getlName()+CurrentEmployee.getID()+".dat");
            myWriter = new FileWriter(FileName, true);
            myWriter.write(tempTip.toString()+" Hours worked: "+0+"\n");
        }
        catch (Exception e){
            System.out.println("An error occurred when exporting data to a file!");
            e.printStackTrace();
        }


        myWriter.close();
    }

}
