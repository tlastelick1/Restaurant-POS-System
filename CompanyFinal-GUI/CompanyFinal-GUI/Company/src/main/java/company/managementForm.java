/**
 * management GUI
 * @author Gordon Agerton
 * @author Trustin Adkins
 * @author Trevor Lastelick
 * @author David Mendez
 */
/**
Creates a GUI interface a user can interact with. The GUI clearly displays all the employees who currently
work for a restaurant. It has existing data on all of them, which can be viewed by clicking on different
tabs (employee jobTitles) and then selecting the employee to view in the Employee List panel. Once selected, The Payment
Info panel represents clocking in and clocking out, as well as the tips made (this does not factor in
their daily wages) for FrontOfHouseEmployee's or the total money made that day for BackOfHouseEmployee's.
Employee Info such as the first name, last name, unique employee ID number, jobTitle, and Wage (money
made per hour) is also displayed in the Employee Info panel. Additional tips can also be added manually via
 the GUI interface. And the gross pay of each employee is calculated via the calculate pay button.
 */
package company;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class managementForm extends JPanel {
    /**All of the elements of the GUI the user will see list
     *The window, buttons, textboxes and tabs are all represented here
     */
    private JPanel mainpanel;
    private JList<Object> allEmployeesList;
    private JList ChefList;
    private JList CashierList;
    private JList WaiterList;
    private JList DeliveryDriverList;
    private JList BartenderList;
    private JButton addEmployeeButton;
    private JButton deleteEmployeeButton;
    private JButton addTipButton;
    private JButton CalculatePayButton;
    private JTextPane EmployeeInfoPane;
    private JTextPane PaymentInfoPane;
    private JFormattedTextField TipTextField;
    private JFormattedTextField DateTextField;
    private JTextField PaymentField;
    private JButton EditButton;
    private JButton aboutButton;

    /**The list of the different types of employees the system manages, the maximum amount is 20 in each list
     *
     */
    private final ArrayList<Employee> allEmployees = new ArrayList<>(20);
    private final ArrayList<BackOfHouseEmployee> chefs = new ArrayList<>(20);
    private final ArrayList<BackOfHouseEmployee> cashiers = new ArrayList<>(20);
    private final ArrayList<FrontOfHouseEmployee> waiters = new ArrayList<>(20);
    private final ArrayList<FrontOfHouseEmployee> drivers = new ArrayList<>(20);
    private final ArrayList<FrontOfHouseEmployee> bartenders = new ArrayList<>(20);

    Employee CurrentEmployee = new Employee();
    /**
     * setAllEmployeesList is responsible for filling the arraylists of containing all employees
     * All employees, regardless of role, are placed in this array list
     * Displays the All Tab on the GUI
     */
    public void setAllEmployeesList() {
        File[] files = new File("company/src/main/java/EmployeeData").listFiles();
        int j = allEmployeesList.getSelectedIndex();

        allEmployees.clear();
        for(int i = 0; i<files.length;i++){
            try {
                allEmployees.add(DataImporter.get("company/src/main/java/EmployeeData/"+files[i].getName()));
            }
            catch(Exception e){
                System.out.println("Couldn't import employee file!");
            }
        }
        allEmployeesList.setListData(allEmployees.toArray());

    }
    /**
     * setBOHList is responsible for filling the arraylists of BackOFHouse employees
     * Cashiers and Chefs are detected and placed in the correct arraylist
     * Displays the Cashier and Chef tab in the GUI
     */
    public void setBOHList() {
        chefs.clear();
        cashiers.clear();
        for (int i = 0; i < allEmployees.size(); i++) {

            if (allEmployees.get(i).getJobTitle().equals("Chef")) {

                chefs.add(new BackOfHouseEmployee(
                        allEmployees.get(i).getfName(),
                        allEmployees.get(i).getlName(),
                        allEmployees.get(i).getID(),
                        allEmployees.get(i).getJobTitle(),
                        allEmployees.get(i).getPaymentInfo()));
                ChefList.setListData(chefs.toArray());
            } else if (allEmployees.get(i).getJobTitle().equals("Cashier")) {

                cashiers.add(new BackOfHouseEmployee(
                        allEmployees.get(i).getfName(),
                        allEmployees.get(i).getlName(),
                        allEmployees.get(i).getID(),
                        allEmployees.get(i).getJobTitle(),
                        allEmployees.get(i).getPaymentInfo()));
                CashierList.setListData(cashiers.toArray());
            }

        }
    }
    /**
     * setFOHList is responsible for filling the arraylists of Front of House employees
     * Delivery Drivers, Bartenders, waiters are detected and placed in the correct arraylist
     * Displays Delivery Driver, Bartender, and Waiter tabs on the GUI
     */
    public void setFOHList() {
        drivers.clear();
        waiters.clear();
        bartenders.clear();
            for (int i = 0; i < allEmployees.size(); i++) {
                if (allEmployees.get(i).getJobTitle().equals("Delivery Driver")) {

                    drivers.add(new FrontOfHouseEmployee(
                            allEmployees.get(i).getfName(),
                            allEmployees.get(i).getlName(),
                            allEmployees.get(i).getID(),
                            allEmployees.get(i).getJobTitle(),
                            allEmployees.get(i).getPaymentInfo()));
                    DeliveryDriverList.setListData(drivers.toArray());
                } else if (allEmployees.get(i).getJobTitle().equals("Waiter")) {

                    waiters.add(new FrontOfHouseEmployee(
                            allEmployees.get(i).getfName(),
                            allEmployees.get(i).getlName(),
                            allEmployees.get(i).getID(),
                            allEmployees.get(i).getJobTitle(),
                            allEmployees.get(i).getPaymentInfo()));
                    WaiterList.setListData(waiters.toArray());
                } else if (allEmployees.get(i).getJobTitle().equals("Bartender")) {

                    bartenders.add(new FrontOfHouseEmployee(
                            allEmployees.get(i).getfName(),
                            allEmployees.get(i).getlName(),
                            allEmployees.get(i).getID(),
                            allEmployees.get(i).getJobTitle(),
                            allEmployees.get(i).getPaymentInfo()));
                    BartenderList.setListData(bartenders.toArray());
                }
            }
        }
    /**
     * Dialog boxes
     * Does not have functionality. Would show "Features are coming soon" pop-up box
     * if 'Add Employee' box was clicked.
     * The 'Add Employee' box is currently disabled.
     * */
    private JDialog dialogBoxAdd,dialogBoxDelete,dialogBoxTip,dialogBoxCalculate;
    private JDialog createDialog(String title){
        JDialog dialogBox = new JDialog(new JFrame(),title,false);
        JLabel label = new JLabel("Feature coming soon.");
        Dimension thingyDimensions = dialogBox.getPreferredSize();
        thingyDimensions.height = 120;
        thingyDimensions.width = 240;
        label.setPreferredSize(thingyDimensions);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialogBox.add(label);
        dialogBox.pack();
        return dialogBox;
    }
    /**
     * Dialog box display function
     *
     * */
    public void showDialogBox(JDialog thingy){
        if(thingy.isShowing()){
            thingy.toFront();
        }
        else{
            thingy.setVisible(true);

        }
        thingy.setLocationRelativeTo(null);
    }
    public managementForm() {

        setAllEmployeesList();
        setBOHList();
        setFOHList();
        setTipTextField();
        setDateTextField();

        dialogBoxAdd = new EmployeeWizard();
        dialogBoxDelete = createDialog("Delete Employee");
        dialogBoxTip = createDialog("Tip Adder");
        dialogBoxCalculate = createDialog("Calculate Wage");
        /**
         * Add Employee Dialog box
         * Does not have functionality. Will add or "hire" another Employee when the time is right.
         * This new employee has all the same functionality as any other pre-existing employee.
         * */
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDialogBox(dialogBoxAdd);
            }
        });
        /**
         * Delete Employee Dialog box
         * */
        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDialogBox(dialogBoxDelete);
            }
        });
        /**
         * Edit Employee Dialog box
         * */
        EditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDialogBox(dialogBoxDelete);
            }
        });

        allEmployeesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /**
         * Represents the All tab which displays a list of all employees who are working at the restaurant.
         * When clicked, displays the list of all employee first and last names in the Employee List pane.
         * When one of the names is clicked (selected), that employee's personal data is displayed in the Employee Info.
         * And in a third pane, Payment Info, that employees summary of work for each day is displayed with more information
         * about a specific day of work.
         * */
        allEmployeesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) throws NullPointerException {
                if (e.getValueIsAdjusting()) {
                    String temp = "";
                    try {
                        CurrentEmployee = allEmployees.get(allEmployeesList.getSelectedIndex());
                        /**
                         * Employee Info pane
                         * */
                        EmployeeInfoPane.setText("First Name : " + allEmployees.get(allEmployeesList.getSelectedIndex()).getfName() +
                                "\nLast Name : " + allEmployees.get(allEmployeesList.getSelectedIndex()).getlName() +
                                "\nID : " + allEmployees.get(allEmployeesList.getSelectedIndex()).getID() +
                                "\nJob : " + allEmployees.get(allEmployeesList.getSelectedIndex()).getJobTitle() +
                                "\nWage : $" + allEmployees.get(allEmployeesList.getSelectedIndex()).getHourlyPay());

                        /**
                         * Employee Payment info
                         * */
                        for (int i = 0; i < allEmployees.get(allEmployeesList.getSelectedIndex()).getPaymentInfoLength(); i++) {
                            temp += allEmployees.get(allEmployeesList.getSelectedIndex()).getPaymentInfo(i);
                            temp += "\n";
                        }
                        PaymentInfoPane.setText(temp);

                    } catch (Exception f) {

                    }
                }
            }


        });
        /**
         * /**
         * Represents the Chef tab which displays a list of all employees who are Chefs working at the restaurant.
         * When clicked, displays the list of all employee first and last names who are Chefs in the Employee List pane.
         * When one of the names is clicked (selected), that employee's personal data is displayed in the Employee Info.
         * And in a third pane, Payment Info, that employees summary of work for each day is displayed with more information
         * about a specific day of work.
         * */

        ChefList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) throws NullPointerException {
                if (e.getValueIsAdjusting()) {
                    String temp = "";
                    try {
                        CurrentEmployee = chefs.get(ChefList.getSelectedIndex());
                        /**
                         * Employee Info pane
                         * */
                        EmployeeInfoPane.setText("First Name : " + chefs.get(ChefList.getSelectedIndex()).getfName() +
                                "\nLast Name : " + chefs.get(ChefList.getSelectedIndex()).getlName() +
                                "\nID : " + chefs.get(ChefList.getSelectedIndex()).getID() +
                                "\nJob : " + chefs.get(ChefList.getSelectedIndex()).getJobTitle() +
                                "\nWage : $" + chefs.get(ChefList.getSelectedIndex()).getHourlyPay());

                        /**
                         * Employee Payment info
                         * */
                        for (int i = 0; i < chefs.get(ChefList.getSelectedIndex()).getPaymentInfoLength(); i++) {
                            temp += chefs.get(ChefList.getSelectedIndex()).getPaymentInfo(i);
                            temp += "\n";
                        }
                        PaymentInfoPane.setText(temp);

                    } catch (Exception f) {

                    }
                }
            }
        });
         /**
         * Represents the Cashier tab which displays a list of all employees who are Cashiers working at the restaurant.
         * When clicked, displays the list of all Cashier employee's first and last names in a pane.
         * When one of the names is clicked (selected), that employee's data is displayed in a separate pane.
         * And in a third pane, that employees summary of work for each day is displayed with more information
         * about a specific day of work.
         * */
        CashierList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) throws NullPointerException {
                if (e.getValueIsAdjusting()) {
                    String temp = "";
                    try {
                        CurrentEmployee = cashiers.get(CashierList.getSelectedIndex());
                        /**
                         * Employee Info pane
                         * */
                        EmployeeInfoPane.setText("First Name : " + cashiers.get(CashierList.getSelectedIndex()).getfName() +
                                "\nLast Name : " + cashiers.get(CashierList.getSelectedIndex()).getlName() +
                                "\nID : " + cashiers.get(CashierList.getSelectedIndex()).getID() +
                                "\nJob : " + cashiers.get(CashierList.getSelectedIndex()).getJobTitle() +
                                "\nWage : $" + cashiers.get(CashierList.getSelectedIndex()).getHourlyPay());

                        /**
                         * Employee Payment info
                         * */
                        for (int i = 0; i < cashiers.get(CashierList.getSelectedIndex()).getPaymentInfoLength(); i++) {
                            temp += cashiers.get(CashierList.getSelectedIndex()).getPaymentInfo(i);
                            temp += "\n";
                        }
                        PaymentInfoPane.setText(temp);

                    } catch (Exception f) {

                    }
                }
            }
        });
        /**
         * Represents the Waiter tab which displays a list of all employees who are Waiters working at the restaurant.
         * When clicked, displays the list of all Waiter employee's first and last names in a pane.
         * When one of the names is clicked (selected), that employee's data is displayed in a separate pane.
         * And in a third pane, that employees summary of work for each day is displayed with more information
         * about a specific day of work.
         * */
        WaiterList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) throws NullPointerException {
                if (e.getValueIsAdjusting()) {
                    String temp = "";
                    try {
                        CurrentEmployee = waiters.get(WaiterList.getSelectedIndex());
                        /**
                         * Employee Info pane
                         * */
                        EmployeeInfoPane.setText("First Name : " + waiters.get(WaiterList.getSelectedIndex()).getfName() +
                                "\nLast Name : " + waiters.get(WaiterList.getSelectedIndex()).getlName() +
                                "\nID : " + waiters.get(WaiterList.getSelectedIndex()).getID() +
                                "\nJob : " + waiters.get(WaiterList.getSelectedIndex()).getJobTitle() +
                                "\nWage : $" + waiters.get(WaiterList.getSelectedIndex()).getHourlyPay());

                        /**
                         * Employee Payment info
                         * */
                        for (int i = 0; i < waiters.get(WaiterList.getSelectedIndex()).getPaymentInfoLength(); i++) {
                            temp += waiters.get(WaiterList.getSelectedIndex()).getPaymentInfo(i);
                            temp += "\n";
                        }
                        PaymentInfoPane.setText(temp);

                    } catch (Exception f) {

                    }
                }
            }
        });
        /**
         * Represents the Delivery Driver tab which displays a list of all employees who are Deliver Drivers working at the restaurant.
         * When clicked, displays the list of all Cashier employee's first and last names in a pane.
         * When one of the names is clicked (selected), that employee's data is displayed in a separate pane.
         * And in a third pane, that employees summary of work for each day is displayed with more information
         * about a specific day of work.
         * */
        DeliveryDriverList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) throws NullPointerException {
                if (e.getValueIsAdjusting()) {
                    String temp = "";
                    try {
                        CurrentEmployee = drivers.get(DeliveryDriverList.getSelectedIndex());
                        /**
                         * Employee Info pane
                         * */
                        EmployeeInfoPane.setText("First Name : " + drivers.get(DeliveryDriverList.getSelectedIndex()).getfName() +
                                "\nLast Name : " + drivers.get(DeliveryDriverList.getSelectedIndex()).getlName() +
                                "\nID : " + drivers.get(DeliveryDriverList.getSelectedIndex()).getID() +
                                "\nJob : " + drivers.get(DeliveryDriverList.getSelectedIndex()).getJobTitle() +
                                "\nWage : $" + drivers.get(DeliveryDriverList.getSelectedIndex()).getHourlyPay());

                        /**
                         * Employee Payment info
                         * */
                        for (int i = 0; i < drivers.get(DeliveryDriverList.getSelectedIndex()).getPaymentInfoLength(); i++) {
                            temp += drivers.get(DeliveryDriverList.getSelectedIndex()).getPaymentInfo(i);
                            temp += "\n";
                        }
                        PaymentInfoPane.setText(temp);

                    } catch (Exception f) {

                    }
                }

            }
        });
        /**
         * Represents the Bartender tab which displays a list of all employees who are Bartender working at the restaurant.
         * When clicked, displays the list of all Bartender employee's first and last names in a pane.
         * When one of the names is clicked (selected), that employee's data is displayed in a separate pane.
         * And in a third pane, that employees summary of work for each day is displayed with more information
         * about a specific day of work.
         * */
        BartenderList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) throws NullPointerException {
                if (e.getValueIsAdjusting()) {
                    String temp = "";
                    try {
                        CurrentEmployee = bartenders.get(BartenderList.getSelectedIndex());

                        /**
                         * Employee Info pane
                         * */
                        EmployeeInfoPane.setText("First Name : " + bartenders.get(BartenderList.getSelectedIndex()).getfName() +
                                "\nLast Name : " + bartenders.get(BartenderList.getSelectedIndex()).getlName() +
                                "\nID : " + bartenders.get(BartenderList.getSelectedIndex()).getID() +
                                "\nJob : " + bartenders.get(BartenderList.getSelectedIndex()).getJobTitle() +
                                "\nWage : $" + bartenders.get(BartenderList.getSelectedIndex()).getHourlyPay());

                        /**
                         * Employee Payment info
                         * */
                        for (int i = 0; i < bartenders.get(BartenderList.getSelectedIndex()).getPaymentInfoLength(); i++) {
                            temp += bartenders.get(BartenderList.getSelectedIndex()).getPaymentInfo(i);
                            temp += "\n";
                        }
                        PaymentInfoPane.setText(temp);

                    } catch (Exception f) {

                    }
                }
            }
        });


        /**
         *Tip Calculator.
         *Links the add tip button to the PaymentInfoCalculator
         *Responsible for display new tip values along with the date and time they have been given
         */
        PaymentInfoCalculator temptip = new PaymentInfoCalculator();
        TipTextField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                temptip.setTip((double) TipTextField.getValue());
            }
        });
        DateTextField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                temptip.setDate((Date) DateTextField.getValue());
            }
        });
        addTipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(temptip + " added to " + CurrentEmployee);

                try {
                    DataExporter exportThisEmployee = new DataExporter(CurrentEmployee, temptip);
                } catch (Exception f) {

                }

                TipTextField.setValue(0.01); //Reset tip to 0.01
                setAllEmployeesList();
                setBOHList();
                setFOHList();

            }
        });
        /**
         * Precondition: Must first select (highlight) an employee from the list provided.
         * Gives calculatePay button in GUI functionality.
         * Calculates the gross money the employee has earned at the restaurant (for tax purposes).
         * @return An updated textFiled just above the calculatePay button.
         */
        CalculatePayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentEmployee.getJobTitle().equals("Chef")||CurrentEmployee.getJobTitle().equals("Cashier")){
                    BackOfHouseEmployee tempEmployee = new BackOfHouseEmployee(CurrentEmployee.getfName(), CurrentEmployee.getlName(), CurrentEmployee.getID(), CurrentEmployee.getJobTitle(), CurrentEmployee.getPaymentInfo());
                    tempEmployee.setPaymentInfo(CurrentEmployee.getPaymentInfo());
                    PaymentField.setText(String.format("$%.2f",tempEmployee.grossPay()));
                }
                else if(CurrentEmployee.getJobTitle().equals("Waiter")||CurrentEmployee.getJobTitle().equals("Delivery Driver")||CurrentEmployee.getJobTitle().equals("Bartender")){
                    FrontOfHouseEmployee tempEmployee = new FrontOfHouseEmployee(CurrentEmployee.getfName(), CurrentEmployee.getlName(), CurrentEmployee.getID(), CurrentEmployee.getJobTitle(), CurrentEmployee.getPaymentInfo());
                    tempEmployee.setPaymentInfo(CurrentEmployee.getPaymentInfo());
                    PaymentField.setText(String.format("$%.2f",tempEmployee.grossPay()));
                }

            }
        });

    }

     /**
     * Sets the text in the panel to whatever isn't there
     */
    public void setTipTextField(){
        TipTextField.setValue(0.01);
        TipTextField.setColumns(10);
    }
    public void setDateTextField(){
        Calendar currentDate = Calendar.getInstance();
        DateTextField.setValue(currentDate.getTime());
    }


        public static void main(String [] args){
        JFrame frame = new JFrame("Management Program");
        frame.setLocationRelativeTo(null);//https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        frame.setContentPane(new managementForm().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        TipTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        DateTextField = new JFormattedTextField(DateFormat.getDateInstance());
    }

    public void setData(EmployeeWizard data) {
    }

    public void getData(EmployeeWizard data) {
    }

    public boolean isModified(EmployeeWizard data) {
        return false;
    }
}

