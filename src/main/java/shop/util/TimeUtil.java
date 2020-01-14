package shop.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String getTime(long time){
        int minutes = (int)((time % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int)((time % (1000 * 60)) / 1000);
        int mseconds = (int)((time % 1000));
        return minutes+"分"+seconds+"秒"+mseconds+"毫秒";
    }
    public static Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
}
