package todolist;

import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.GregorianCalendar;

enum Priority {

    Very_Low, Low, Medium, High, Very_High
}


public class ToDo {

    private Priority priority;
    private String description;
    private Date date = new Date();
    private Calendar cal = new GregorianCalendar();

    public ToDo(String desc, Priority p, Date d) {
        description = desc;
        priority = p;
        date = d;

    }

    
    public ToDo(String desc, String p, String d) {
        //this.date = new Calendar();
        description = desc;
        if (p.equals("Very Low")) {
            priority = Priority.Very_Low;
        } else if (p.equals("Low")) {
            priority = Priority.Low;
        } else if (p.equals("Medium")) {
            priority = Priority.Medium;
        } else if (p.equals("High")) {
            priority = Priority.High;
        } else {
            priority = Priority.Very_High;
        }

        int Month = Integer.parseInt(d.substring(0, 2)) - 1; //Java starts months at 0... 
        int Day = Integer.parseInt(d.substring(3, 5));
        int Year = Integer.parseInt(d.substring(6, 10));
        System.out.println(Month);
        System.out.println(Day);
        System.out.println(Year);
        //System.out.println(p);
        //System.out.println(priority);
        cal.set(Year, Month, Day);
        date = cal.getTime();
        //date.setMonth(Month);
        //date.setYear(Year);
        System.out.println(date.getMonth());
        System.out.println(date.getDate());
        System.out.println(date.getYear());

    }

    
    public Calendar getDate() {
        return cal;
    }

    public String getDateStr() {
        return date.getMonth() + "/" + date.getDate() + "/" + date.getYear();
    }

    
    public String getDesc() {
        return description;
    }

    
    public Priority getPriority() {
        return priority;
    }

    public String getPriorityStr() {

        if (priority.equals(Priority.Very_Low)) {
            return "Very Low";
        }
        if (priority.equals(Priority.Low)) {
            return "Low";
        }
        if (priority.equals(Priority.Medium)) {
            return "Medium";
        }
        if (priority.equals(Priority.High)) {
            return "High";
        }
        return "Very High";
    }

    
    @Override
    public String toString() {
        return description + "\t" + getDateStr() + "\t" + getPriorityStr();

    }
}
