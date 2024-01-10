package com.example.pjte;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author djc
 * @time 2024/1/7/007  8:27
 * @desc
 **/

public class NotifactionInfo2 extends Activity {
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_notify_info2);
      gettif();
   }


   public void gettif(){
      String taskAffinity = getClass().getName(); // 默认情况下，任务亲和性与类名相同
      int taskId = getTaskId();
      if (taskId != -1) {
         ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
         if (activityManager != null) {
            try {
               List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(5);
               for (ActivityManager.RunningTaskInfo task : runningTasks) {

                  if (task.id == taskId && task.topActivity!=null) {
                     ComponentName componentName = task.topActivity;
                     String packageName = componentName.getPackageName();
                     String className = componentName.getClassName();

                     // 输出包名、类名及任务亲和性
                     Log.d("NotifactionInfo", "package name: " + packageName);
                     Log.d("NotifactionInfo", "class name: " + className);
                     Log.d("NotifactionInfo", "task affinity: " + taskAffinity);
                     Log.d("NotifactionInfo", "task task.description: " + task.description);
                     Log.d("NotifactionInfo", "task task.description: " + task.describeContents());
                     Log.d("NotifactionInfo", "task task.toString: " + task.toString());
                     Log.d("NotifactionInfo", "task task.id: " + task.id);
                     Log.d("NotifactionInfo", "tasks get(0) task.id: " + runningTasks.get(0).id);
                     Log.d("NotifactionInfo", "activiy gettask.id: " + NotifactionInfo2.this.getTaskId());
                     Log.d("NotifactionInfo", "task task.description: " + task.numActivities);
                     break;
                  }
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      } else {
         Log.e("TAG", "Failed to retrieve the task id");
      }
   }
}
