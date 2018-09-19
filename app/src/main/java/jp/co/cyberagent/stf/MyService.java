package jp.co.cyberagent.stf;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MyService extends NotificationListenerService {
    private String Category = "UNKNOWN";


    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.e("Kageboshi", "notificationRemove");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.e("Kageboshi", "notifictionPosted");
        String packageName = sbn.getPackageName();
        Log.e("Kageboshi", packageName);
        if (packageName.equals("com.tencent.mm")) {
            Category = "WECHAT";
        }
        Bundle extras = sbn.getNotification().extras;
        //      String string = extras.getString(Notification.EXTRA_TITLE);
        //      Log.e("Kageboshi",string);
        String string1 = extras.getString(Notification.EXTRA_TEXT);
        //      Log.e("Kageboshi", string1);
        //发送广播
        Intent intent = new Intent();
        intent.setAction("jp.co.cyberagent.stf.receiver");
        intent.putExtra("msg", string1);
        intent.putExtra("flag", Category);
        sendBroadcast(intent);
        //     Log.e("Kageboshi","广播已经发送");
    }
}
