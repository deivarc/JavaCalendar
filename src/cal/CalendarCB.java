/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Black Beauty
 */

/*
*Zeller’s Rule
*
*F=k+ [(13*m-1)/5] +D+ [D/4] +[C/4]-2*C where
*
*k is  the day of the month.
*m is the month number.
*D is the last two digits of the year.
*C is the first two digits of the year.
*
*month starts from march=1,Jan=11, Feb=12
*
*if month = jan or feb
*   year-=1
*   calculate();
*
*   e.g.
*   F  = 3 + [(13*10-1)/5] + 20+ (20/4) + 20/4 - 2*20
*       = 3 + 129/5 + 30 - 40
*       = 25 -7
*   F   = 18
* F mod 7 = ans
*ans = 4 (Thursday)
*if F = -15 mod 7
*
        double a = (-60)%7;
        if (a<0)
            a+=7;
        System.out.println(a);
 *F =6(Saturday)

No need for zeller as Calendar API does it already

        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.MONTH));
        System.out.println(c.get(Calendar.DATE));
        System.out.println(c.get(Calendar.DAY_OF_WEEK)); 0-6
        System.out.println(c.getFirstDayOfWeek());   1-7
*/
public class CalendarCB {
    public int month; // 0 to 11 // current month// changes according to buttons
    public int day;// current day// does not change// used to find present day
    public int year;// current year // changes according to buttons
    private int[] todaydate; // year|month|day
    public int startday; // "What day does it start on the calendar of your locale?" //  1-7 where 1 is sunday // E.g. 1 (Sunday)
    public int dayofWeek; //1-7 --// "what day does it start on 1st of 'january'?" // E.g. 3 (Tuesday)
    public int blockdays;// how many days to block in the 1st week of the calendar/ index of day to start the calendar for 1st week
    private ArrayList <String> daysAL; // order of days(e.g. monday, tuesday) according to your locale
    private ArrayList<String> calData; // calendar data 7x5 in string
    public int daysinMonth; // days in month
    public boolean todayShown;
// sunday -> 0
    //start day -> 4 wednesday
    // 0-4 = -4 mod 7 = 3 days\
    //3-4= -1mod 7 = 6 days
    // 4-4 =0 days
    
    public CalendarCB() {
        todayShown = true;
        String [] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        daysAL = new ArrayList<String>();
        calData = new ArrayList<String>();
        Calendar rightNow = Calendar.getInstance();
        year=rightNow.get(Calendar.YEAR);
        month=rightNow.get(Calendar.MONTH);
        day=rightNow.get(Calendar.DATE);
        todaydate = new int[]{year,month};
        startday = rightNow.getFirstDayOfWeek();     
        
       
        
        //create daysAL
        int tempday = startday-1; // 0-6
        for (int i=0;i<7;i++) {     
            if (tempday>6){
                tempday=tempday%7;
            }
            daysAL.add(days[tempday]);
            tempday++;
        }
        
        createdayofweek();
        numberOfDaysinM();
        createcalData();
    }
    
    private void numberOfDaysinM () {
        // Get the number of days in that month
        YearMonth yearMonthObject = YearMonth.of(year, month+1);
        daysinMonth = yearMonthObject.lengthOfMonth();   
    }
    private void createblkday () {
        int tempstartday = startday-1;
        int tempdayofweek = dayofWeek-1;
        if (tempdayofweek<tempstartday)
        { 
            blockdays=tempdayofweek-startday+7;
        } else {
            blockdays =tempdayofweek-tempstartday;
        }
    }
    
    private void createdayofweek () {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(year, month, 1);
        dayofWeek=rightNow.get(Calendar.DAY_OF_WEEK);
        createblkday();
    }
    
    private void createcalData() { // for 7x6 hardcoded
       calData.removeAll(calData);
       boolean inputBlank = true;
       int count=1;
       for (int i=0;i<42;i++) {
           if (i==blockdays){
               inputBlank=false;
           }
           if (inputBlank) {
               calData.add("");
               //System.out.print("Blank ");
           } else {
               if (count!=daysinMonth)
               {
                calData.add(String.valueOf(count));
                //System.out.print(count+" ");
                count++;
               } else if (count == daysinMonth){
                   calData.add(String.valueOf(count));
                   //System.out.print(count+" ");
                   inputBlank=true;
               }
           }
       }//end for loop
    }
    public ArrayList<String> getcalData() {
        return calData;       
    }
    
    public ArrayList<String> getdaysAL() {
        return daysAL;
    }
    
    public void calcal () { //recalculation done outside class
        iftodayshown();
        createdayofweek();
        numberOfDaysinM();
        createcalData();
    }

    private void iftodayshown() {
        if (todaydate[0] == year &&
                todaydate[1] == month) {
            todayShown=true;
        } else {
            todayShown=false;
        }
    }
}
