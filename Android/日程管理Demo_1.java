安卓日程管理器

关键部分源码解释如下：
（1）程序启动界面主要代码如下：
[java] view plain copy
new CountDownTimer(2000L, 1000L)  
{  
    public void onFinish()  
    {  
  
       //启动界面淡入淡出效果  
       Intentintent = new Intent();  
       intent.setClass(SplashActivity.this, CalendarActivity.class);  
       startActivity(intent);  
       overridePendingTransition(R.anim.fade_in, R.anim.fade_out);  
       finish();  
  
    }  
  
    public void onTick(longparamLong)  
    {  
    }  
}  
tart();  


（2）关于界面的主要代码如下：
 
[java] view plain copy
//初始化关于按钮  
 aboutBtn = (Button)findViewById(R.id.about_back_btn);  
  
 //关闭关于页面  
 aboutBtn.setOnClickListener(new View.OnClickListener() {  
     
    @Override  
    public void onClick(View v) {  
       // TODOAuto-generated method stub  
       finish();  
    }  
});  

（3）日程定时的界面代码主要如下：
[java] view plain copy
/** 
 * 日程定时提醒界面 
 */  
public class AlarmAlert extends Activity {  
    private Dialog builder;  
    private MediaPlayer mp;  
    private Vibrator vibrator;  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
       requestWindowFeature(Window.FEATURE_NO_TITLE);  
       setContentView(R.layout.reminder);  
        mp = new MediaPlayer();  
        //控制响铃震动时长  
        new Thread(){  
           public void run(){  
              try {  
                  Thread.sleep(30000);  
                  if(mp!=null){  
                      mp.stop();  
                      vibrator.cancel();   
                   }else if(vibrator!=null){  
                      vibrator.cancel();  
                   }  
              } catch (InterruptedException e) {  
                  // TODOAuto-generated catch block  
                  e.printStackTrace();  
              }  
           }  
       }.start();  
        try {  
        //播放当前默认铃声  
           mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));  
            mp.prepare();  
            mp.setLooping(true);  
            mp.start();  
       } catch (IllegalArgumentException e) {  
           e.printStackTrace();  
       } catch (SecurityException e) {  
           e.printStackTrace();  
       } catch (IllegalStateException e) {  
           e.printStackTrace();  
       } catch (IOException e) {  
           e.printStackTrace();  
       }  
         
       //设置震动  
       vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);  
       long [] pattern = {700,1300,700,1300};    
        vibrator.vibrate(pattern,2);  
        Stringcontent=getIntent().getExtras().getString("content");  
        TextViewdialog_content=(TextView) findViewById(R.id.dialog_content);  
       Buttondialog_button_cancel=(Button)findViewById(R.id.dialog_button_cancel);  
       dialog_content.setText(content);  
       dialog_button_cancel.setOnClickListener(new OnClickListener() {  
            
           public void onClick(View v) {  
              finish();  
           }  
       });  
    }  
     
    //页面销毁时释放申请的资源  
    @Override  
    protected void onStop() {  
        ScheduleView.setAlart(AlarmAlert.this);  
        if(mp!=null){  
           mp.stop();  
           vibrator.cancel();   
        }else if(vibrator!=null){  
           vibrator.cancel();  
        }  
        super.onStop();  
    }  
     
}  


（4）日期程序开机自启动
[java] view plain copy
/** 
 * 开自自动启动日程管理软件，获取安卓系统开机广播 
 */  
public class BootReceiver extends BroadcastReceiver {  
    public void onReceive(Context context, Intent intent) {  
        Stringaction = intent.getAction();  
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {  
        ScheduleView.setAlart(context);  
        }  
    }  
}  


（5）日程管理主界面。
CalendarActivity含有很多余日程管理有关的属性的设置，比如日期的跳转，界面的重绘，年月日的显示，当前日期的显示，对话框对象等。属性信息的代码如下所示：
[java] view plain copy
private ViewFlipper flipper = null;  
private GestureDetector gestureDetector = null;  
private CalendarView calV = null;  
private GridView gridView = null;//显示日期的网格布局  
private BorderText topText = null;  
private Drawable draw = null;  
private static int jumpMonth = 0;      //每次滑动，增加或减去一个月,默认为0（即显示当前月）  
private static int jumpYear = 0;       //滑动跨越一年，则增加或者减去一年,默认为0(即当前年)  
private int year_c = 0; //年  
private int month_c = 0;//月  
private int day_c = 0;//日  
private String currentDate = "";//当前日期  
private ScheduleDAO dao = null;//数据库借口  
private ScheduleVO scheduleVO;  
private String[] scheduleIDs;  
private ArrayList<String> scheduleDate;//日期数组列表  
private Dialog builder;  
private ScheduleVO scheduleVO_del;  


手指滑动可以切换日期的显示，比如当前日期的前一天和后一天，主要的代码如下（类似翻页效果）：
[java] view plain copy
//手指滑动选择日期效果  
public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
       float velocityY) {  
   int gvFlag = 0;         //每次添加gridview到viewflipper中时给的标记  
   if (e1.getX() - e2.getX() > 50) {  
       //像左滑动  
       addGridView();   //添加一个gridView  
       jumpMonth++;     //下一个月  
  
       calV = new CalendarView(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);  
       gridView.setAdapter(calV);  
       addTextToTopTextView(topText);  
       gvFlag++;  
       flipper.addView(gridView, gvFlag);  
       this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));  
       this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out));  
       this.flipper.showNext();  
       flipper.removeViewAt(0);  
       returntrue;  
   } else if (e1.getX() - e2.getX() < -50) {  
       //向右滑动  
       addGridView();   //添加一个gridView  
       jumpMonth--;     //上一个月  
  
       calV = new CalendarView(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);  
       gridView.setAdapter(calV);  
       gvFlag++;  
       addTextToTopTextView(topText);  
       flipper.addView(gridView,gvFlag);  
  
       this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_in));  
       this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out));  
       this.flipper.showPrevious();  
       flipper.removeViewAt(0);  
       returntrue;  
   }  
   returnfalse;  
}  


创建日程管理主界面的菜单选项以及对相应的菜单选项的事件处理函数。
[java] view plain copy
//创建菜单  
public boolean onCreateOptionsMenu(Menu menu) {  
  
   menu.add(0,menu.FIRST, menu.FIRST, "今天");  
   menu.add(0,menu.FIRST+1, menu.FIRST, "跳转");  
   menu.add(0,menu.FIRST+2, menu.FIRST+2, "聊天");  
   menu.add(0, menu.FIRST+3, menu.FIRST+3, "设置");  
   return super.onCreateOptionsMenu(menu);  
}  
  
//选择菜单  
public boolean onMenuItemSelected(int featureId, MenuItem item) {  
   switch (item.getItemId()){  
   case Menu.FIRST:  
       //跳转到今天  
       int xMonth = jumpMonth;  
       int xYear = jumpYear;  
       int gvFlag =0;  
       jumpMonth = 0;  
       jumpYear = 0;  
       addGridView();   //添加一个gridView  
       year_c = Integer.parseInt(currentDate.split("-")[0]);  
       month_c = Integer.parseInt(currentDate.split("-")[1]);  
       day_c = Integer.parseInt(currentDate.split("-")[2]);  
       calV = new CalendarView(this, getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);  
       gridView.setAdapter(calV);  
       addTextToTopTextView(topText);  
       gvFlag++;  
       flipper.addView(gridView,gvFlag);  
       if(xMonth == 0 && xYear == 0){  
           //nothing to do  
       }else if((xYear == 0 && xMonth >0) ||xYear >0){  
          this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));  
          this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out));  
          this.flipper.showNext();  
       }else{  
          this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_in));  
          this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out));  
          this.flipper.showPrevious();  
       }  
       flipper.removeViewAt(0);  
       break;  
   case Menu.FIRST+1:  
  
       new DatePickerDialog(this, newOnDateSetListener() {  
  
  
          public void onDateSet(DatePicker view, int year, int monthOfYear,  
                 int dayOfMonth) {  
              //1901-1-1 ----> 2049-12-31  
              if(year < 1901 || year > 2049){  
                 //不在查询范围内  
                 new AlertDialog.Builder(CalendarActivity.this).setTitle("错误日期").setMessage("跳转日期范围(1901/1/1-2049/12/31)").setPositiveButton("确认", null).show();  
              }else{  
                 int gvFlag = 0;  
                 addGridView();   //添加一个gridView  
                 calV = new CalendarView(CalendarActivity.this, CalendarActivity.this.getResources(),year,monthOfYear+1,dayOfMonth);  
                 gridView.setAdapter(calV);  
                 addTextToTopTextView(topText);  
                 gvFlag++;  
                 flipper.addView(gridView,gvFlag);  
                 if(year == year_c && monthOfYear+1 == month_c){  
                     //nothing to do  
                 }  
                 if((year == year_c && monthOfYear+1 > month_c) || year > year_c ){  
                     CalendarActivity.this.flipper.setInAnimation(AnimationUtils.loadAnimation(CalendarActivity.this,R.anim.push_left_in));  
                     CalendarActivity.this.flipper.setOutAnimation(AnimationUtils.loadAnimation(CalendarActivity.this,R.anim.push_left_out));  
                     CalendarActivity.this.flipper.showNext();  
                 }else{  
                     CalendarActivity.this.flipper.setInAnimation(AnimationUtils.loadAnimation(CalendarActivity.this,R.anim.push_right_in));  
                     CalendarActivity.this.flipper.setOutAnimation(AnimationUtils.loadAnimation(CalendarActivity.this,R.anim.push_right_out));  
                     CalendarActivity.this.flipper.showPrevious();  
                 }  
                 flipper.removeViewAt(0);  
                 //跳转之后将跳转之后的日期设置为当期日期  
                 year_c = year;  
                 month_c = monthOfYear+1;  
                 day_c = dayOfMonth;  
                 jumpMonth = 0;  
                 jumpYear = 0;  
              }  
          }  
       },year_c, month_c-1, day_c).show();  
   break;  
  
   case Menu.FIRST+2:  
       Intentintent1 = new Intent(CalendarActivity.this, ChatActivity.class);  
   startActivity(intent1);  
   break;  
  
   case Menu.FIRST+3:  
       Intentintent2 = new Intent(CalendarActivity.this, SettingsActivity.class);  
   startActivity(intent2);  
   break;  
   }  
  
   return super.onMenuItemSelected(featureId, item);  
}  


CalendarConvert类主要实现了对日期的转换，比如农历和阳历等的转换。CalendarView类主要实现了对日程管理主页面的日期的每月的日期的显示，日程信息的存储的数据库接口等。主要代码如下：
[java] view plain copy
private ScheduleDAO dao = null;  
    private boolean isLeapyear = false;  //是否为闰年  
    private int daysOfMonth = 0;      //某月的天数  
    private int dayOfWeek = 0;        //具体某一天是星期几  
    private int lastDaysOfMonth = 0;  //上一个月的总天数  
    private Context context;  
    private String[] dayNumber = new String[49];  //一个gridview中的日期存入此数组中  
    private static String week[] = {"SUN","MON","TUE","WED","THU","FRI","SAT"};  
    private SpecialCalendar sc = null;  
    private LunarCalendar lc = null;  
    private Resources res = null;  
    private Drawable drawable = null;  
     
    private String currentYear = "";  
    private String currentMonth = "";  
    private String currentDay = "";  
     
    private SimpleDateFormat sdf = newSimpleDateFormat("yyyy-M-d");  
    private int currentFlag = -1;     //用于标记当天  
    private int[] schDateTagFlag = null;  //存储当月所有的日程日期  
     
    private String showYear = "";   //用于在头部显示的年份  
    private String showMonth = "";  //用于在头部显示的月份  
    private String animalsYear = "";  
    private String leapMonth = "";   //闰哪一个月  
    private String cyclical = "";   //天干地支  
    //系统当前时间  
    private String sysDate = "";   
    private String sys_year = "";  
    private String sys_month = "";  
    private String sys_day = "";  
     
    //日程时间(需要标记的日程日期)  
    private String sch_year = "";  
    private String sch_month = "";  
    private String sch_day = "";  
     
 //-----------------------------------------------------------------------  
    public View getView(int position, ViewconvertView, ViewGroup parent) {  
   
       if(convertView == null){  
           convertView= LayoutInflater.from(context).inflate(R.layout.calender, null);  
        }  
       TextViewtextView = (TextView) convertView.findViewById(R.id.tvtext);  
       String d = dayNumber[position].split("\\.")[0];  
       String dv = dayNumber[position].split("\\.")[1];  
   
       SpannableStringsp = new SpannableString(d+"\n"+dv);  
       sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  
       sp.setSpan(new RelativeSizeSpan(1.0f) , 0, d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  
       if(dv != null || dv != ""){  
           sp.setSpan(new RelativeSizeSpan(0.75f), d.length()+1, dayNumber[position].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  
       }  
   
       textView.setText(sp);  
       textView.setTextColor(Color.GRAY);  
       if(position<7){  
           //设置周  
           textView.setTextColor(Color.BLACK);  
           drawable = res.getDrawable(R.drawable.week_top);  
           textView.setBackgroundDrawable(drawable);  
       }  
        
       if (position < daysOfMonth + dayOfWeek+7 &&position >= dayOfWeek+7) {  
           // 当前月信息显示  
           textView.setTextColor(Color.BLACK);// 当月字体设黑  
           drawable = res.getDrawable(R.drawable.item);  
   
       }  
       if(schDateTagFlag != null && schDateTagFlag.length >0){  
           for(int i = 0; i < schDateTagFlag.length; i++){  
              if(schDateTagFlag[i] == position){  
                  //设置日程标记背景  
                  textView.setBackgroundResource(R.drawable.mark);  
              }  
           }  
       }  
       if(currentFlag == position){  
           //设置当天的背景  
           drawable = res.getDrawable(R.drawable.current_day_bgc);  
           textView.setBackgroundDrawable(drawable);  
           textView.setTextColor(Color.WHITE);  
       }  
       return convertView;  
    }  
     
    //得到某年的某月的天数且这月的第一天是星期几  
    public void getCalendar(int year, int month){  
       isLeapyear = sc.isLeapYear(year);              //是否为闰年  
       daysOfMonth = sc.getDaysOfMonth(isLeapyear, month);  //某月的总天数  
       dayOfWeek = sc.getWeekdayOfMonth(year, month);      //某月第一天为星期几  
       lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month-1);  //上一个月的总天数  
       Log.d("DAY", isLeapyear+" ======  "+daysOfMonth+"  ============  "+dayOfWeek+"  =========   "+lastDaysOfMonth);  
       getweek(year,month);  
    }  
     
    //将一个月中的每一天的值添加入数组dayNuber中  
    private void getweek(int year,int month) {  
       int j = 1;  
       int flag = 0;  
       StringlunarDay = "";  
        
       //得到当前月的所有日程日期(这些日期需要标记)  
       dao = new ScheduleDAO(context);  
       ArrayList<ScheduleDateTag>dateTagList = dao.getTagDate(year,month);  
       if(dateTagList != null && dateTagList.size() > 0){  
           schDateTagFlag = new int[dateTagList.size()];  
       }  
        
       for (int i = 0; i < dayNumber.length; i++) {  
           // 周一  
           if(i<7){  
              dayNumber[i]=week[i]+"."+" ";  
           }  
           else if(i < dayOfWeek+7){  //前一个月  
              int temp = lastDaysOfMonth - dayOfWeek+1-7;  
              lunarDay= lc.getLunarDate(year, month-1, temp+i,false);  
              dayNumber[i] = (temp + i)+"."+lunarDay;  
           }else if(i < daysOfMonth + dayOfWeek+7){   //本月  
              Stringday = String.valueOf(i-dayOfWeek+1-7);  //得到的日期  
              lunarDay= lc.getLunarDate(year, month, i-dayOfWeek+1-7,false);  
              dayNumber[i] = i-dayOfWeek+1-7+"."+lunarDay;  
              //对于当前月才去标记当前日期  
              if(sys_year.equals(String.valueOf(year))&& sys_month.equals(String.valueOf(month))&& sys_day.equals(day)){  
                  //笔记当前日期  
                  currentFlag = i;  
              }  
               
              //标记日程日期  
              if(dateTagList != null && dateTagList.size() > 0){  
                  for(int m = 0; m < dateTagList.size(); m++){  
                     ScheduleDateTagdateTag = dateTagList.get(m);  
                     int matchYear = dateTag.getYear();  
                     int matchMonth = dateTag.getMonth();  
                     int matchDay = dateTag.getDay();  
                     if(matchYear == year && matchMonth == month && matchDay ==Integer.parseInt(day)){  
                         schDateTagFlag[flag] = i;  
                         flag++;  
                     }  
                  }  
              }  
               
              setShowYear(String.valueOf(year));  
              setShowMonth(String.valueOf(month));  
              setAnimalsYear(lc.animalsYear(year));  
              setLeapMonth(lc.leapMonth == 0?"":String.valueOf(lc.leapMonth));  
              setCyclical(lc.cyclical(year));  
           }else{   //下一个月  
              lunarDay= lc.getLunarDate(year, month+1, j,false);  
              dayNumber[i] = j+"."+lunarDay;  
              j++;  
           }  
       }  
         
        String abc= "";  
        for(int i = 0; i < dayNumber.length; i++){  
         abc =abc+dayNumber[i]+":";  
        }  
    }  


    接收闹钟提醒的广播的代码如下（主要在CallAlarm类中）：
[java] view plain copy
/** 
 * 接收提醒广播 
 */  
public class CallAlarm extends BroadcastReceiver {  
    @Override  
    public void onReceive(Context context, Intent intent) {  
        String content=intent.getExtras().getString("content");  
        intent.putExtra("content", content);  
        intent.putExtra("time", intent.getExtras().getLong("time"));  
       intent.setClass(context,AlarmAlert.class);  
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
       context.startActivity(intent);  
    }  
}  


ScheduleAll模块中主要显示所有日程，获取所有日程的代码如下：
[java] view plain copy
//得到所有的日程信息  
public void getScheduleAll(){  
   schList = dao.getAllSchedule();  
   if(schList != null){  
       for (ScheduleVO vo : schList) {  
          Stringcontent = vo.getScheduleContent();  
          int startLine = content.indexOf("\n");  
          if(startLine > 0){  
              content= content.substring(0, startLine)+"...";  
          }else if(content.length() > 30){  
              content= content.substring(0, 30)+"...";  
          }  
          scheduleInfo = CalendarConstant.sch_type[vo.getScheduleTypeID()]+"\n"+vo.getScheduleDate()+"\n"+content;  
          scheduleID = vo.getScheduleID();  
          createInfotext(scheduleInfo, scheduleID);  
       }  
   }else{  
       scheduleInfo = "没有日程";  
       createInfotext(scheduleInfo,-1);  
   }  
}  


CalendarConstant模块中主要包含了与日程管理相关常量的一些设置，代码如下：
[java] view plain copy
public class CalendarConstant {  
    public final static String[] sch_type = { "会议", "约会", "电话", "纪念日", "生日", "课程", "其他" }; // 日程类型  
    public final static String[] remind = {"提醒一次","隔10分钟","隔30分钟","隔一小时","每天重复","每周重复","每月重复","每年重复"};  
}  

DBOpenHelper和ScheduleDAO主要是提供了访问手机数据库的接口，并创建了两张表，SQL语句如下：
[java] view plain copy
//建立日程表  
db.execSQL("CREATE TABLE IF NOT EXISTS schedule(scheduleIDinteger primary key autoincrement,scheduleTypeID integer,remindIDinteger,scheduleContent text,scheduleDate text,time text,alartimeinteger)");  
//日程提醒时间表  
db.execSQL("CREATE TABLE IF NOT EXISTSscheduletagdate(tagID integer primary key autoincrement,year integer,monthinteger,day integer,scheduleID integer)");  


    保存日程信息的代码如下：
[java] view plain copy
public int save(ScheduleVO scheduleVO){  
   //dbOpenHelper = new DBOpenHelper(context,"schedules.db");  
   SQLiteDatabasedb = dbOpenHelper.getWritableDatabase();  
   ContentValuesvalues = new ContentValues();  
   values.put("scheduleTypeID", scheduleVO.getScheduleTypeID());  
   values.put("remindID", scheduleVO.getRemindID());  
   values.put("scheduleContent", scheduleVO.getScheduleContent());  
   values.put("scheduleDate", scheduleVO.getScheduleDate());  
   values.put("time", scheduleVO.getTime());  
   values.put("alartime", scheduleVO.getAlartime());  
   db.beginTransaction();  
   int scheduleID = -1;  
   try{  
       db.insert("schedule", null, values);  
       Cursor cursor = db.rawQuery("select max(scheduleID) fromschedule", null);  
       if(cursor.moveToFirst()){  
           scheduleID= (int) cursor.getLong(0);  
       }  
       cursor.close();  
       db.setTransactionSuccessful();  
   }finally{  
       db.endTransaction();  
       db.close();  
   }  
    return scheduleID;  
}  


 
查询某一条日程信息代码如下;
  
[java] view plain copy
public ScheduleVO getScheduleByID(Context context,int scheduleID){  
  //  dbOpenHelper= new DBOpenHelper(context, "schedules.db");  
     SQLiteDatabasedb = dbOpenHelper.getWritableDatabase();  
     Cursor cursor= db.query("schedule", new String[]{"scheduleID","scheduleTypeID","remindID","scheduleContent","scheduleDate","time","alartime"}, "scheduleID=?", new String[]{String.valueOf(scheduleID)}, null, null, null,null);  
     if(cursor.moveToFirst()){  
         int schID = cursor.getInt(cursor.getColumnIndex("scheduleID"));  
         int scheduleTypeID = cursor.getInt(cursor.getColumnIndex("scheduleTypeID"));  
         int remindID = cursor.getInt(cursor.getColumnIndex("remindID"));  
         StringscheduleContent = cursor.getString(cursor.getColumnIndex("scheduleContent"));  
         StringscheduleDate = cursor.getString(cursor.getColumnIndex("scheduleDate"));  
         Stringtime=cursor.getString(cursor.getColumnIndex("time"));  
         long alartime=cursor.getLong(cursor.getColumnIndex("alartime"));  
         cursor.close();  
         db.close();  
         return newScheduleVO(schID,scheduleTypeID,remindID,scheduleContent,scheduleDate,time,alartime);  
     }  
     cursor.close();  
     db.close();  
     return null;  
      
  }  

 
查询所有的日程信息的代码如下：
[java] view plain copy
public ArrayList<ScheduleVO> getAllSchedule(){  
   ArrayList<ScheduleVO>list = new ArrayList<ScheduleVO>();  
   //dbOpenHelper = new DBOpenHelper(context,"schedules.db");  
   SQLiteDatabasedb = dbOpenHelper.getWritableDatabase();  
   Cursor cursor= db.query("schedule", new String[]{"scheduleID","scheduleTypeID","remindID","scheduleContent","scheduleDate","time","alartime"}, null, null, null, null, "scheduleID desc",null);  
   while(cursor.moveToNext()){  
       int scheduleID = cursor.getInt(cursor.getColumnIndex("scheduleID"));  
       int scheduleTypeID = cursor.getInt(cursor.getColumnIndex("scheduleTypeID"));  
       int remindID = cursor.getInt(cursor.getColumnIndex("remindID"));  
       StringscheduleContent = cursor.getString(cursor.getColumnIndex("scheduleContent"));  
       StringscheduleDate = cursor.getString(cursor.getColumnIndex("scheduleDate"));  
       Stringtime=cursor.getString(cursor.getColumnIndex("time"));  
       long alartime=cursor.getLong(cursor.getColumnIndex("alartime"));  
       ScheduleVOvo = newScheduleVO(scheduleID,scheduleTypeID,remindID,scheduleContent,scheduleDate,time,alartime);  
       list.add(vo);  
   }  
   cursor.close();  
   db.close();  
   if(list != null && list.size() > 0){  
       return list;  
   }  
   return null;  
    
}  

 
删除日程信息的主要代码如下：
[java] view plain copy
public void delete(intscheduleID){  
   //dbOpenHelper = new DBOpenHelper(context,"schedules.db");  
   SQLiteDatabasedb = dbOpenHelper.getWritableDatabase();  
   db.beginTransaction();  
   try{  
       db.delete("schedule", "scheduleID=?", new String[]{String.valueOf(scheduleID)});  
       db.delete("scheduletagdate", "scheduleID=?", new String[]{String.valueOf(scheduleID)});  
       db.setTransactionSuccessful();  
   }finally{  
       db.endTransaction();  
       db.close();  
   }  
}  


 
[java] view plain copy
public ArrayList<ScheduleDateTag> getTagDate(int currentYear, int currentMonth){  
   ArrayList<ScheduleDateTag>dateTagList = new ArrayList<ScheduleDateTag>();  
   //dbOpenHelper = new DBOpenHelper(context,"schedules.db");  
   SQLiteDatabasedb = dbOpenHelper.getReadableDatabase();  
   Cursor cursor= db.query("scheduletagdate", new String[]{"tagID","year","month","day","scheduleID"}, "year=? and month=?", new String[]{String.valueOf(currentYear),String.valueOf(currentMonth)},null, null, null);  
   while(cursor.moveToNext()){  
       int tagID = cursor.getInt(cursor.getColumnIndex("tagID"));  
       int year = cursor.getInt(cursor.getColumnIndex("year"));  
       int month = cursor.getInt(cursor.getColumnIndex("month"));  
       int day = cursor.getInt(cursor.getColumnIndex("day"));  
       int scheduleID = cursor.getInt(cursor.getColumnIndex("scheduleID"));  
       ScheduleDateTagdateTag = newScheduleDateTag(tagID,year,month,day,scheduleID);  
       dateTagList.add(dateTag);  
       }  
   cursor.close();  
   db.close();  
   if(dateTagList != null && dateTagList.size() > 0){  
       return dateTagList;  
   }  
   return null;  
}  