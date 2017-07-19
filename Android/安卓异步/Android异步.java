  很多初入Android或Java开发的新手（我也在内）对Thread、Looper、Handler和Message仍然比较迷惑，衍生的有HandlerThread、java.util.concurrent、Task、AsyncTask由于目前市面上的书籍等资料都没有谈到这些问题，今天就这一问题做更系统性的总结。我们创建的Service、Activity以及Broadcast均是一个主线程处理，这里我们可以理解为UI线程。但是在操作一些耗时操作时，比如I/O读写的大文件读写，数据库操作以及网络下载需要很长时间，为了不阻塞用户界面，出现ANR的响应提示窗口，这个时候我们可以考虑使用Thread线程来解决。
首先使用一个实例来解释几个异步之间的关系：
实例内容：从网络上下载图片
此实例由Sundy讲解Android视频提供的，地址：http://www.verycd.com/topics/2900036/
XML代码：
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="fill_parent" android:layout_height="fill_parent"
        android:orientation="vertical">
        <ScrollView android:layout_width="fill_parent"
                android:layout_height="fill_parent">
                <LinearLayout android:layout_width="fill_parent"
                        android:layout_height="fill_parent" android:orientation="vertical">
                        <ImageView android:id="@+id/imageThreadConcept"
                                android:layout_width="wrap_content" android:layout_height="wrap_content">      </ImageView>
                        <Button android:text="Work Threads" android:id="@+id/buttonWorkThread"
                                android:layout_width="fill_parent" android:layout_height="wrap_content"></Button>
                        <Button android:text="Work Threads2" android:id="@+id/buttonWorkThread2"
                                android:layout_width="fill_parent" android:layout_height="wrap_content"></Button>
                        <Button android:text="Work Threads3" android:id="@+id/buttonWorkThread3"
                                android:layout_width="fill_parent" android:layout_height="wrap_content"></Button>
                        <Button android:text="Work AsyncTask" android:id="@+id/buttonWorkThread4"
                                android:layout_width="fill_parent" android:layout_height="wrap_content"></Button>
                </LinearLayout>
        </ScrollView>
</LinearLayout>
复制代码
Java代码：
public class LoadImageTest extends Activity{
        
        private static final String TAG = "LoadImageTest";
        private ImageView mImageView = null ;
        //从网络上下载图片 .
        private final String IMAGE1_URL = "http://image.91rb.com/200905/27/9/34b5b080ac80661657946eaa51566d03.jpg" ;        
        private final String IMAGE3_URL = "http://m.ztwan.com/wallpaper/UploadPic/2010/8/28/201082811538894.jpg" ;
        private final String IMAGE4_URL = "http://m.ztwan.com/wallpaper/UploadPic/2010/8/28/20108280578236.jpg" ;
        
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                this.setContentView(R.layout.load_imagetest)  ;
                
                mImageView = (ImageView)this.findViewById(R.id.imageThreadConcept) ;
                
                /方法1、直接在UI线程中加载网络图片
                findViewById(R.id.buttonWorkThread).setOnClickListener(new OnClickListener(){
                        
                        
                        public void onClick(View v) {
                                // 这只方式直接在网络中得到一张图片，因数据量比较小，在UI线程中执行，并不会造成用户视觉上的等待，如果数据量庞大，不采用这中方式
                                Drawable drawable = loadImageFromNetwork(IMAGE1_URL);
                    mImageView.setImageDrawable(drawable) ;
                        }})  ;
                
                /方法2、java习惯，在android不推荐使用，使得使用线程不安全
                findViewById(R.id.buttonWorkThread2).setOnClickListener(new OnClickListener(){
                        
                        
                        public void onClick(View v) {
                                // 在Android中是灰常不建议这样做的，这样做极易出现异常
                                new Thread(new Runnable(){
                                
                                        public void run() {
                                                Drawable drawable = loadImageFromNetwork(IMAGE1_URL);
                                                mImageView.setImageDrawable(drawable) ;
                                        }
                                        
                                }).start()  ;
                                
                        }})  ;
                
                //3. load image in new thread , but set imageview by View.post(Runnable) 
                //方法3、创建一个新的线程，( Runnable + Handler.postDelayed(runnable,time) )
                findViewById(R.id.buttonWorkThread3).setOnClickListener(new OnClickListener(){
                        
                        
                        public void onClick(View v) {
                                new Thread(new Runnable(){
                                        Drawable drawable = loadImageFromNetwork(IMAGE3_URL);
                                        
                                        public void run() {
                                                
                                    mImageView.post(new Runnable(){
                                            
                                                        public void run() {
                                                                
                                                                mImageView.setImageDrawable(drawable) ;
                                                        }}) ;
                                        }
                                        
                                }).start()  ;
                                
                        }})  ;
                
                //4. load image in new thread , but set imageview by AsyncTask 
               //方法4、使用AsyncTask，AsyncTask是在Android 1.5后引入的，能够更安全的使用线程，在下面，将会再用一个实例来分析AsyncTask

                findViewById(R.id.buttonWorkThread4).setOnClickListener(new OnClickListener(){
                        
                        
                        public void onClick(View v) {
                                // 不可缺少的异步，当数据量庞大时，耗时的操作时就使用这种方式吧。
                                //IMAGE4_URL 是执行传入的参数
                                new DownloadImageTask().execute(IMAGE4_URL) ;
                                
                        }})  ;
        }
        //Async private class 
        private class DownloadImageTask extends AsyncTask<String, Void, Drawable> {
            /** The system calls this to perform work in a worker thread and
              * delivers it the parameters given to AsyncTask.execute() */
            protected Drawable doInBackground(String... urls) {
                return loadImageFromNetwork(urls[0]);
            }
            
            /** The system calls this to perform work in the UI thread and delivers
              * the result from doInBackground() */
            protected void onPostExecute(Drawable result) {
                mImageView.setImageDrawable(result);
            }
        }
        
        // the Drawable loadImage main function 
        private Drawable loadImageFromNetwork(String imageUrl)
        {
                Drawable drawable = null;
                try {
                        drawable = Drawable.createFromStream(
                                        new URL(imageUrl).openStream(), "image.gif");
                } catch (IOException e) {
                        Log.d(TAG, e.getMessage());
                }
                if (drawable == null) {
                        Log.d(TAG, "null drawable");
                } else {
                        Log.d(TAG, "not null drawable");
                }
                
                return drawable ;
        }
}
复制代码
效果：


在独立线程中进行地理位置编码：本实例来自于Pro Android2 精通Android 2
XML代码：
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="fill_parent" android:layout_height="fill_parent">
        <com.google.android.maps.MapView
                android:id="@+id/geoMap" android:clickable="true"
                android:layout_width="fill_parent" android:layout_height="450px"
                android:apiKey="PUT YOUR MAP API KEY" />
        <LinearLayout android:layout_width="fill_parent"
                android:layout_alignParentBottom="true" android:layout_height="wrap_content"
                android:orientation="vertical">
                <EditText android:layout_width="fill_parent" android:id="@+id/location"
                        android:layout_height="wrap_content" android:text="tian an meng" />
                <Button android:id="@+id/geocodeBtn" android:layout_width="wrap_content"
                        android:layout_height="wrap_content" android:text="Find Location" />
        </LinearLayout>
</RelativeLayout>
复制代码
Java代码：
public class GeocodingDemoActivity extends MapActivity {
        
        public static final String TAG = "GeocodingDemoActivity";
        Geocoder geocoder = null;
        MapController mMapController;
        public MapView mapView;
        ProgressDialog progDialog = null;
        List<Address> addressList = null;

        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.geocode);

                int lat = (int) (31.83659536 * 1000000);
                int lng = (int) (117.1912658 * 1000000);
                mapView = (MapView) findViewById(R.id.geoMap);
                mMapController = mapView.getController();
                GeoPoint curpt = new GeoPoint(lat, lng);
                mMapController.animateTo(curpt);
                mapView.setBuiltInZoomControls(true);
                mMapController.setZoom(15);

                geocoder = new Geocoder(this);
                Button geoBtn = (Button) findViewById(R.id.geocodeBtn);
                
                geoBtn.setOnClickListener(new OnClickListener() {

                        public void onClick(View view) {
                                EditText loc = (EditText) findViewById(R.id.location);
                                String locationName = loc.getText().toString();

                                progDialog = ProgressDialog.show(GeocodingDemoActivity.this,
                                                "Processing...", "Finding Location...", true, false);
                                findLocation(locationName);
                        }
                });

        }
        
        protected boolean isLocationDisplayed() {
                return false;
        }
        protected boolean isRouteDisplayed() {
                return false;
        }

        private void findLocation(final String locationName) {
                //使用一个新的线程来实现地理位置的编码
                Thread thrd = new Thread() {
                        public void run() {
                                try {
                                        // do backgrond work
                                        addressList = geocoder.getFromLocationName(locationName, 5);
                                        // send message to handler to process results
                                        uiCallback.sendEmptyMessage(0);

                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                };
                thrd.start();
        }

        // ui thread callback handler
        private Handler uiCallback = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                        progDialog.dismiss();
                        //打印出本线程的ID
                        Log.i(TAG, "Handler Thread :" + Thread.currentThread().getId());
                        if (addressList != null && addressList.size() > 0) {
                                int lat = (int) (addressList.get(0).getLatitude() * 1000000);
                                int lng = (int) (addressList.get(0).getLongitude() * 1000000);
                                GeoPoint pt = new GeoPoint(lat, lng);
                                mapView.getController().setZoom(15);
                                mapView.getController().setCenter(pt);
                        } else {
                                Dialog foundNothingDlg = new AlertDialog.Builder(
                                                GeocodingDemoActivity.this).setIcon(0)
                                                .setTitle("Failed to Find Location")
                                                .setPositiveButton("Ok", null)
                                                .setMessage("Location Not Found...").create();
                                foundNothingDlg.show();
                        }
                }
        };
}
复制代码
效果：


下面详解  AsyncTask的使用，学习一个方法前，应该很仔细的看官方的文档，这样我们会对它的机制更熟悉一些，再参考一些具体的实例，加深理解，
下面的一个实例使用AsyncTask加载一个网页的内容，并可有进度条显示加载的进度。实例来源于网络，作者还没找到：
XML代码：
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical" android:layout_width="fill_parent"
        android:layout_height="fill_parent">
        <Button android:id="@+id/execute" android:layout_width="fill_parent"
                android:layout_height="wrap_content" android:text="execute" />
        <Button android:id="@+id/cancel" android:layout_width="fill_parent"
                android:layout_height="wrap_content" android:enabled="false"
                android:text="cancel" />
        <ProgressBar android:id="@+id/progress_bar"
                android:layout_width="fill_parent" android:layout_height="wrap_content"
                android:progress="0" android:max="100"
                style="?android:attr/progressBarStyleHorizontal" />
        <ScrollView android:layout_width="fill_parent"
                android:layout_height="wrap_content">
                <TextView android:id="@+id/text_view" android:layout_width="fill_parent"
                        android:layout_height="wrap_content" />
        </ScrollView>
</LinearLayout>
复制代码
Java代码：
public class AsyncTaskTset extends Activity {
        
        private static final String TAG = "ASYNC_TASK";
        private static final String URL = "http://www.google.com.hk/";
        private Button execute;
        private Button cancel;
        private ProgressBar progressBar;
        private TextView textView;
        
        private MyTask mTask;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask);
        
        execute = (Button) findViewById(R.id.execute);
        execute.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                                //注意每次需new一个实例,新建的任务只能执行一次,否则会出现异常
                                mTask = new MyTask();
                                mTask.execute(URL);
                                
                                execute.setEnabled(false);
                                cancel.setEnabled(true);
                        }
                });
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                                //取消一个正在执行的任务,onCancelled方法将会被调用
                                mTask.cancel(true);
                        }
                });
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView = (TextView) findViewById(R.id.text_view);
        
    }
    //三个参数
    /**
     * 1、Params 启动任务执行的输入参数，比如HTTP请求的URL
     * 2、Progress 后台任务执行的百分比
     * 3、Result 后台执行任务最终返回的结果，比如String，也可以是一个image
     */
    private class MyTask extends AsyncTask<String, Integer, String> {
            //onPreExecute方法用于在执行后台任务前做一些UI操作
            protected void onPreExecute() {
                    Log.i(TAG, "onPreExecute() called");
                    textView.setText("loading...");
            }
            
            //doInBackground方法内部执行后台任务,不可在此方法内修改UI
                protected String doInBackground(String... params) {
                        Log.i(TAG, "doInBackground(Params... params) called");
                        try {
                                HttpClient client = new DefaultHttpClient();
                                HttpGet get = new HttpGet(params[0]);
                                HttpResponse response = client.execute(get);
                                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                                        HttpEntity entity = response.getEntity();
                                        InputStream is = entity.getContent();
                                        long total = entity.getContentLength();
                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        byte[] buf = new byte[1024];
                                        int count = 0;
                                        int length = -1;
                                        while ((length = is.read(buf)) != -1) {
                                                baos.write(buf, 0, length);
                                                count += length;
                                                //调用publishProgress公布进度,最后onProgressUpdate方法将被执行
                                                publishProgress((int) ((count / (float) total) * 100));
                                                //为了演示进度,休眠500毫秒
                                                Thread.sleep(500);
                                        }
                                        return new String(baos.toByteArray(), "gb2312");
                                }
                        } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                        }
                        return null;
                }
                
                //onProgressUpdate方法用于更新进度信息
            protected void onProgressUpdate(Integer... progresses) {
                        Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
                        progressBar.setProgress(progresses[0]);
                        textView.setText("loading..." + progresses[0] + "%");
            }
            
                //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
                protected void onPostExecute(String result) {
                        Log.i(TAG, "onPostExecute(Result result) called");
                        textView.setText(result);
                        
                        execute.setEnabled(true);
                        cancel.setEnabled(false);
                }
                
                //onCancelled方法用于在取消执行中的任务时更改UI
                protected void onCancelled() {
                        Log.i(TAG, "onCancelled() called");
                        textView.setText("cancelled");
                        progressBar.setProgress(0);
                        
                        execute.setEnabled(true);
                        cancel.setEnabled(false);
                }
    }
}
复制代码
一、补充知识点：
摘录于：http://www.android123.com.cn/androidkaifa/422.html
1. 对于线程中的刷新一个View为基类的界面，可以使用postInvalidate()方法在线程中来处理，其中还提供了一些重写方法比如postInvalidate(intleft,int top,int right,int bottom) 来刷新一个矩形区域，以及延时执行，比如postInvalidateDelayed(long delayMilliseconds)或postInvalidateDelayed(long delayMilliseconds,intleft,int top,int right,int bottom) 方法，其中第一个参数为毫秒
2. 当然推荐的方法是通过一个Handler来处理这些，可以在一个线程的run方法中调用handler对象的 postMessage或sendMessage方法来实现，Android程序内部维护着一个消息队列，会轮训处理这些，如果你是Win32程序员可以很好理解这些消息处理，不过相对于Android来说没有提供 PreTranslateMessage这些干涉内部的方法。
3. Looper又是什么呢? ，其实Android中每一个Thread都跟着一个Looper，Looper可以帮助Thread维护一个消息队列，但是Looper和Handler没有什么关系，我们从开源的代码可以看到Android还提供了一个Thread继承类HanderThread可以帮助我们处理，在HandlerThread对象中可以通过getLooper方法获取一个Looper对象控制句柄，我们可以将其这个Looper对象映射到一个Handler中去来实现一个线程同步机制，Looper对象的执行需要初始化Looper.prepare方法就是昨天我们看到的问题，同时推出时还要释放资源，使用Looper.release方法。
4.Message 在Android是什么呢? 对于Android中Handler可以传递一些内容，通过Bundle对象可以封装String、Integer以及Blob二进制对象，我们通过在线程中使用Handler对象的sendEmptyMessage或sendMessage方法来传递一个Bundle对象到Handler处理器。对于Handler类提供了重写方法handleMessage(Message msg) 来判断，通过msg.what来区分每条信息。将Bundle解包来实现Handler类更新UI线程中的内容实现控件的刷新操作。相关的Handler对象有关消息发送sendXXXX相关方法如下，同时还有postXXXX相关方法，这些和Win32中的道理基本一致，一个为发送后直接返回，一个为处理后才返回 .
5. java.util.concurrent对象分析，对于过去从事Java开发的程序员不会对Concurrent对象感到陌生吧，他是JDK 1.5以后新增的重要特性作为掌上设备，我们不提倡使用该类，考虑到Android为我们已经设计好的Task机制，这里不做过多的赘述，相关原因参考下面的介绍:
6. 在Android中还提供了一种有别于线程的处理方式，就是Task以及AsyncTask，从开源代码中可以看到是针对Concurrent的封装，开发人员可以方便的处理这些异步任务。

二、可能涉及到得面试题：handler机制的原理
andriod提供了 Handler 和 Looper 来满足线程间的通信。Handler 先进先出原则。Looper类用来管理特定线程内对象之间的消息交换(Message Exchange)。
1）Looper: 一个线程可以产生一个Looper对象，由它来管理此线程里的Message Queue(消息队列)。
2）Handler: 你可以构造Handler对象来与Looper沟通，以便push新消息到Message Queue里；或者接收Looper从Message Queue取出)所送来的消息。
3） Message Queue(消息队列):用来存放线程放入的消息。
4）线程：UI thread 通常就是main thread，而Android启动程序时会替它建立一个Message Queue。
如要要更加深入的了解，可以参考Sundy的视频讲解。
