package company;

import java.util.Calendar;
import java.util.Random;
/**the simulateDay interface is used to  simulate a day's earning of tips on the given day
* This is used by FOH and BOH employees in their calculations
*/

public interface simulateDay{
    Calendar calendar = Calendar.getInstance(); //The given day tips are earned (today and now)
    Random rand = new Random(); //A random number which FOH will use to generate tips, and BOH will use it to generate hours worked

    void simDay(); //Each type of employee simulates their day in a different way, this method will ensure they do it

}
