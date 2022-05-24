
package company;

import java.util.Calendar;
import java.util.Date;

/**
 * the PaymentInfoCalculator class is responsible for displaying how much tips have been earned on a specific date.
 */

public class PaymentInfoCalculator {
    private Calendar inputCalendar;
    private Date inputDate;
    private Double amount;
    public PaymentInfoCalculator(Date inputDate, Double amount){
        this.inputDate = inputDate;
        this.amount = amount;
    }
    public PaymentInfoCalculator(){
        this.inputCalendar = Calendar.getInstance();
        this.amount = 0.0;
    }
    public void setDate(Date inputDate){
        this.inputCalendar.setTime(inputDate);
    }
    public void setTip(Double amount){
        this.amount = amount*1.0;
    }
    public Calendar getDate(){
        return inputCalendar;
    }
    public double getTip(){
        return amount;
    }

    @Override
    public String toString() {
        return inputCalendar.getTime()+" "+amount;
    }
}
