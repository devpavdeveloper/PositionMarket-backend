package by.psu.utility;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.Math.abs;

public class Util {
    final static long DAY_MILLIS = 86400000*2;

    public enum dateFormatTypes{DATE_ONLY, TIME_ONLY}

    public static boolean isOneDayAway(Date order, Date currentOrder){
        return (abs(order.getTime() - currentOrder.getTime()) <= DAY_MILLIS);
    }
    public static boolean isOverTime(long expiryTime){
        Calendar cal = Calendar.getInstance();
        return expiryTime - cal.getTime().getTime() <= 0;
    }

    public static Date incrementDateByHours(Date date, int hours){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        return cal.getTime();
    }

    public static String convertDateToReadableLocalFormat(Date date, dateFormatTypes dft){
        Locale locale = new Locale("ru", "RU");
        DateFormat df = null;
        switch(dft){
            case DATE_ONLY:{
                df = DateFormat.getDateInstance(DateFormat.LONG, locale);
                break;
            }
            case TIME_ONLY:{
                df = DateFormat.getTimeInstance(DateFormat.SHORT, locale);
                break;
            }
        }
        return df.format(date);
    }
}
