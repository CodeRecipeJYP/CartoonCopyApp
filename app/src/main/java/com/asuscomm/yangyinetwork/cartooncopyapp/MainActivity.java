package com.asuscomm.yangyinetwork.cartooncopyapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
//import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int MAX_PAGE = 3;
    private PermissionListener mPermissionlistener;
    private ViewPager mPager;
//    private CirclePageIndicator mPagerIndicator;
//    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Dialog띄울때 통신시에 대기기간길때 ANR발생시
//        mContext = this;

        setTedPermission();
    }

    private void setTedPermission() {
        Log.d(TAG, "setTedPermission: ");

        mPermissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                setController();
                // go next stage
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this, PermissionActivity.class));
            }
        };


        TedPermission.with(this)
                .setPermissionListener(mPermissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void setController() {
        Log.d(TAG, "setController: ");

        mPager = findViewById(R.id.pager);
//        mPagerIndicator = findViewById(R.id.indicator);

        mPager.setAdapter(new PagerAdapterClass(MainActivity.this));
//        mPagerIndicator.setViewPager(mPager);
//        mPagerIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

    private class PagerAdapterClass extends PagerAdapter {

        private LayoutInflater mInflater;

        public PagerAdapterClass(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }
    }

}
