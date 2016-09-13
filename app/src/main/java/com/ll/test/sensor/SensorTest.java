package com.ll.test.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by LL on 2016/9/13.
 * 使用传感器是有的要manifest注册权限
 */
public class SensorTest extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    //    传感器
    private void sensor(Context context) {
//        SensorManager.STANDARD_GRAVITY重力加速
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
//        多个传感器，硬件传感器一般在顶部，虚拟传感器一般在底部，螺旋仪
        List<Sensor> allSensors1 = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
//        没有就传空
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    //    选择范围最大，耗电最少的光传感器，和校正够的螺旋仪（可能不存在）
    private void use(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> lightSensors = sensorManager.getSensorList(Sensor.TYPE_LIGHT);
        List<Sensor> gyroscopes = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
        Sensor bastLightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//        通常是最佳的，典型一致
        Sensor correctedGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (bastLightSensor != null) {
            for (Sensor lightSensor : lightSensors) {
                float range = lightSensor.getMaximumRange();
                float power = lightSensor.getPower();
                if (range >= bastLightSensor.getMaximumRange()) {
                    if ((power < bastLightSensor.getPower() || range > bastLightSensor.getMaximumRange())) {
//                        最佳光传感器
                        bastLightSensor = lightSensor;
                    }
                }
            }
        }
        if (gyroscopes != null && gyroscopes.size() > 1) {
//            修正过的
            correctedGyro = gyroscopes.get(gyroscopes.size() - 1);
        }
    }

    final SensorEventListener myOrientationListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent sensorEvent) {
//            sensorEvent.sensor响应的传感器
//            sensorEvent.accuracy传感器的精度low medium high unreliable
//            低需要校准，平均水准，高精度，不可靠
//            SensorManager.SENSOR_STATUS_ACCURACY_HIGH
//            sensorEvent.timestamp发生的时间（纳秒）
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                float headingAngle = sensorEvent.values[0];
                float pitchAngle = sensorEvent.values[1];
                float rollAngle = sensorEvent.values[2];
//                改变
                // TODO Apply the orientation changes to your application.
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//            精度的变化
        }
    };

    private void deprecatedSensorListener(Context context) {
        /**
         * Listing 12-8: Determining orientation using the deprecated orientation Sensor
         */
        SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        int sensorType = Sensor.TYPE_ORIENTATION;
        sm.registerListener(myOrientationListener,//监听
                sm.getDefaultSensor(sensorType),//对象
                SensorManager.SENSOR_DELAY_NORMAL);//频率
//        一般比指定的要快，有时也慢
//        SensorManager.SENSOR_DELAY_FASTEST最快
//        SensorManager.SENSOR_DELAY_GAME适合游戏
//        SensorManager.SENSOR_DELAY_NORMAL默认
//        SensorManager.SENSOR_DELAY_UI更新UI的速度
//        不用试注销
        sm.unregisterListener(myOrientationListener);
//        在onresume注册onpause注销
    }

    private void findScreenOrientation(Context context) {
        /**
         * Listing 12-3: Finding the screen orientation relative to the natural orientation
         */
        String windowSrvc = Context.WINDOW_SERVICE;
        WindowManager wm = ((WindowManager) context.getSystemService(windowSrvc));
        Display display = wm.getDefaultDisplay();
        int rotation = display.getRotation();
//        当前屏幕的自然方向
        switch (rotation) {
            case (Surface.ROTATION_0):
                break; // Natural
            case (Surface.ROTATION_90):
                break; // On its left side
            case (Surface.ROTATION_180):
                break; // Updside down
            case (Surface.ROTATION_270):
                break; // On its right side
            default:
                break;
        }
    }

    final SensorEventListener mySensorEventListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent sensorEvent) {
            // TODO Monitor Sensor changes.
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float xAxis_laterlA = sensorEvent.values[0];
                float yAxis_longitudinalA = sensorEvent.values[1];
                float zAxis_verticalA = sensorEvent.values[2];
//                速度变化
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO React to a change in Sensor accuracy.
        }
    };

    private void registerAccelerometer(Context context) {
        /**
         * Listing 12-4: Listening to changes to the default accelerometer
         */
        SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //加速度
        int sensorType = Sensor.TYPE_ACCELEROMETER;
        sm.registerListener(mySensorEventListener,
                sm.getDefaultSensor(sensorType),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void uodateGUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                更新ui
            }
        });
    }

    private void star() {
        Timer updateTimer = new Timer("up");
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                uodateGUI();
            }
        }, 0, 100);
    }


}
