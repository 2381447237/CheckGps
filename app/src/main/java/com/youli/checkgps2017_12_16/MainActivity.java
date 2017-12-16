package com.youli.checkgps2017_12_16;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Handler mHandler;

    Runnable r=new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this,5000);
            if(isOPen(getApplicationContext())){
                Toast.makeText(getApplicationContext(),"GPS可用",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"GPS不可用",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //移除Handler
        //  mHandler.removeCallbacks(r);

        mHandler=new Handler();
        mHandler.post(r);

        Button btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ActivityTwo.class);
                startActivity(i);
            }
        });

    }


    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static final boolean isOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (gps) {
            return true;
        }

        return false;
    }


}