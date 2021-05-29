package com.demo.widget.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

import com.demo.R;
import com.demo.http.ApiService;
import com.demo.http.HttpClient;
import com.demo.http.base.BaseObserver;
import com.orhanobut.logger.Logger;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
//        Logger.d("----------------999999999999");
        return super.dispatchTouchEvent(ev);
    }


    @Override public boolean onTouchEvent(MotionEvent event) {
//        Logger.d("++++++++++++++++++++++++++++++++");
        return true;
    }
}
