在Android中常见的几种刷新方式有 Timer，Alarm和handler。在widgetapp更新中，alarm用的比较多，有人讲用alarm比较省电。
但笔者使用后发现用alarm有时候反而会使系统反应变慢了。更好的方法推荐使用handler。

第一种：Timer
Timer这种方式最为常见，起个定时刷新的任务，不用的时候cancel掉，置为空即可。
示例代码：
Timer timer = new Timer( );
TimerTask task = new TimerTask( ) {
public void run ( ) {
Message message = new Message( );
message.what = 1;
handler.sendMessage(message);
}
};
final Handler handler = new Handler( ) {
public void handleMessage(Message msg) {
switch (msg.what) {
case 1:
log.e("Timer","Timer");
update( );
break;
}
第二种：Alarm
示例代码：
开始计时
Intent intent = new Intent(widgetUpdate);
refreshIntent = PendingIntent.getBroadcast(pContext, 0, intent, 0);
alarm = (AlarmManager)pContext.getSystemService(Context.ALARM_SERVICE);
alarm.setRepeating(AlarmManager.RTC, 0, 1000, refreshIntent);//每秒刷新1次
停止计时
if (alarm!=null) {
     alarm.cancel(refreshIntent);
     refreshIntent.cancel();
     refreshIntent = null;
  alarm = null;
}

第三种：handler
private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
    public void run () {
    update();
    handler.postDelayed(this,1000); 
    }
    };

开始计时
handler.removeCallbacks(runnable);
handler.postDelayed(runnable,1000); 
停止计时
handler.removeCallbacks(runnable);
第三种代码看起来也非常的简洁，推荐使用。