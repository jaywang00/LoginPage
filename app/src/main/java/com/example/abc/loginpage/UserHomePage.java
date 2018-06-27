package com.example.abc.loginpage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserHomePage extends AppCompatActivity {

    private TextView tvWelcome;
    private String userName;
    private SharedPreferences cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        findViews();
        welcome();
    }

    private void findViews() {
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
    }

    private void welcome() {
        Intent intent = getIntent();
        userName = intent.getStringExtra("USER_NAME");
        if(userName != null){
            saveToCache();
        }
        else{
            SharedPreferences cache = getSharedPreferences();
            userName = cache.getString("Cache" , "");
        }
        tvWelcome.setText("Welcome, " + userName);
        //showNotification(userName);
    }

    private void saveToCache() {
        if (cache == null)
            cache = getSharedPreferences();

        SharedPreferences.Editor editor = cache.edit();
        editor.putString("Cache", userName);
    }

    private SharedPreferences getSharedPreferences(){
        return getSharedPreferences("Cache" + userName, 0);
    }

    /*protected void showNotification (String userName) {
        NotificationManager barManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                new Intent(this, UserHomePage.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification barMsg = new Notification.Builder(this)
                // 在訊息面板的標題
                .setContentTitle(userName + "您好，本店最近推出xxxx")
                // 在訊息面板的內容文字
                .setContentText("優惠至2018/06/06")
                // 通知訊息的圖示
                .setSmallIcon(android.R.drawable.ic_menu_info_details)
                // 點擊訊息面版後會自動移除狀態列上的通知訊息
                .setAutoCancel(true)
                // 等待使用者向下撥動狀態列後點擊訊息面版上的訊息才會開啟指定Activity的畫面
                .setContentIntent(pendingIntent)
                .build();
        barManager.notify(0, barMsg);
    }*/

}
