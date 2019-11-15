package shop.util;

public class TimeUtil {
    public static String getTime(long time){
        int minutes = (int)((time % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int)((time % (1000 * 60)) / 1000);
        int mseconds = (int)((time % 1000));
        return minutes+"分"+seconds+"秒"+mseconds+"毫秒";
    }
}
