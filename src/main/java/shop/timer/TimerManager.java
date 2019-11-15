package shop.timer;


import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerManager {
    private Timer timer;
    private Date date;
    private long period;
    private GetProductTask task;
    //时间间隔
    public TimerManager(GetProductTask task,int period) {
        Calendar calendar = Calendar.getInstance();
        /*** 定制每日2:00执行方法 ***/
        calendar.set(Calendar.HOUR_OF_DAY,2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date=calendar.getTime(); //第一次执行定时任务的时间
        System.out.println(date);
        //如果第一次执行定时任务的时间 小于 当前的时间
        //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
        if (date.before(new Date())) {
            this.date=this.addDay(date,1);
        }
        this.period=period;
        this.task=task;

    }
    public void start(){
        this.timer = new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
        timer.schedule(task,date,period);
    }
    public void onDestory(){
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
    }
    // 增加或减少天数
    private Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
    // 增加或减少秒
    private Date addSecond(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.SECOND, num);
        return startDT.getTime();
    }
}