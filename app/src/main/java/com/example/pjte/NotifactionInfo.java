package com.example.pjte;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author djc
 * @time 2024/1/7/007  8:27
 * @desc
 **/

public class NotifactionInfo extends Activity {
   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_notify_info);

      findViewById(R.id.to_second).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            startActivity(new Intent(NotifactionInfo.this,NotifactionInfo2.class));
//            int taskId = getTaskId();
            gettif();
         }
      });
   }


   public  String getTopActivity(){
      ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);

//      ActivityManager.RunningTaskInfo runningTaskInfo = am.getRunningTasks(1).get(0);
//      runningTaskInfo.
//      runningTaskInfo.topActivity.g
//      runningTaskInfo.t
      ComponentName cn = am.getRunningTasks(1).get(0).topActivity;

      cn.getClassName();
      //        Log.d("Chunna.zheng", "pkg:"+cn.getPackageName());//包名
      //        Log.d("Chunna.zheng", "cls:"+cn.getClassName());//包名加类名

      return cn.getClass().getSimpleName();
   }


//   public List<Activity> getAllActivitys(){
//      List<Activity> list=new ArrayList<>();
//      try {
//         Class<?> activityThread=Class.forName("android.app.ActivityThread");
//         Method currentActivityThread=activityThread.getDeclaredMethod("currentActivityThread");
//         currentActivityThread.setAccessible(true);
//         //获取主线程对象
//         Object activityThreadObject=currentActivityThread.invoke(null);
//         Field mActivitiesField = activityThread.getDeclaredField("mActivities");
//         mActivitiesField.setAccessible(true);
//         Map<Object,Object> mActivities = (Map<Object,Object>) mActivitiesField.get(activityThreadObject);
//         for (Map.Entry<Object,Object> entry:mActivities.entrySet()){
//            Object value = entry.getValue();
//            Class<?> activityClientRecordClass = value.getClass();
//            Field activityField = activityClientRecordClass.getDeclaredField("activity");
//            activityField.setAccessible(true);
//            Object o = activityField.get(value);
//            list.add((Activity) o);
//         }
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//      return list;
//   }

//
//
//      public  String getTaskAffinity(Class<?> activityClass) throws Exception {
//         Field field = Class.forName("android.app.Activity").getDeclaredField("mFragments");
//         field.setAccessible(true);
//
//         Object fragmentsObject = field.get(activityClass);
//         if (fragmentsObject != null && fragmentsObject instanceof FragmentManagerImpl) {
//            FragmentManagerImpl fragmentManager = (FragmentManagerImpl) fragmentsObject;
//
//            Field mHostField = FragmentManagerImpl.class.getDeclaredField("mHost");
//            mHostField.setAccessible(true);
//
//            Object hostObject = mHostField.get(fragmentManager);
//            if (hostObject != null && hostObject instanceof Activity) {
//               ComponentName componentName = ((Activity) hostObject).getCallingPackage();
//
//               return componentName.getClassName();
//            }
//         }
//
//         return ""; // Return empty string if task affinity cannot be retrieved
//      }
//

    @RequiresApi(api = Build.VERSION_CODES.Q)
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
                      Log.d("NotifactionInfo", "activiy gettask.id: " + NotifactionInfo.this.getTaskId());
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
