package shop.timer;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GetProductListener implements ServletContextListener {
    private final int ONE_HOUR_OF_MS=60*60*1000;
    private final int ONE_DAY_OF_MS=24*ONE_HOUR_OF_MS;
    private final int ONE_WEEK_OF_MS=7*ONE_DAY_OF_MS;
    private final int ONE_MONTH_OF_MS=30*ONE_DAY_OF_MS;
    private TimerManager oneHourTimer,oneDayTimer;
    public void contextInitialized(ServletContextEvent sce) {
        GetProductTask oneDayTask = new GetProductTask(sce);
        oneDayTimer=new TimerManager(oneDayTask,ONE_DAY_OF_MS);
        oneDayTimer.start();
    }
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        if(oneHourTimer!=null){
            oneHourTimer.onDestory();
        }
        if(oneDayTimer!=null){
            oneDayTimer.onDestory();
        }
    }
}
