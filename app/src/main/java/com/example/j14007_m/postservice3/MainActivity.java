package com.example.j14007_m.postservice3;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager mLocationManager = null;

    double latitude;
    double longitude;

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GPSサービス取得
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // GPSから位置情報を取得する設定
        boolean gpsFlg = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("debug", "GPS:" + latitude + "  " + longitude);
                AsyncHttp post = new AsyncHttp(String.valueOf(latitude), String.valueOf(longitude));
                post.execute();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // GPSから取得を開始する
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
//        // 緯度
//        Log.d("debug","Latitude:"+location.getLatitude());
//
//        // 経度
//        Log.d("debug","Latitude:"+location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}