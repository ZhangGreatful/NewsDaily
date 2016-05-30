package com.example.administrator.newsdaily;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class MyBaseActivity extends FragmentActivity {
    // 公共的。。。。 Toast  全屏  宽高  OpenActivity
    private       Toast toast;
    public static int   screenW, screenH;
    public Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenW = getWindowManager().getDefaultDisplay().getWidth();
        screenH = getWindowManager().getDefaultDisplay().getHeight();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void showToast(int resId) {
        showToast(getString(resId));
    }

    public void showToast(String msg) {
        if (toast == null)
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }

    //      打开Acitivity
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, null);
    }

    public void openActivity(Class<?> pClass, Bundle bundle) {
        openActivity(pClass, bundle, null);
    }

    public void openActivity(Class<?> pClass, Bundle bundle, Uri uri) {
//        设置跳转
        Intent intent = new Intent(this, pClass);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
        // 增加动画
        overridePendingTransition(R.anim.anim_activity_right_in,
                R.anim.anim_activity_bottom_out);
    }

    public void openActivity(String action) {
        openActivity(action, null, null);
    }

    public void openActivity(String action, Bundle bundle) {
        openActivity(action, bundle, null);
    }

    public void openActivity(String action, Bundle bundle, Uri uri) {
        Intent intent = new Intent(action);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
        // 增加动画
        overridePendingTransition(R.anim.anim_activity_right_in,
                R.anim.anim_activity_bottom_out);
    }

    //结束当前动画
    public void myFinish() {
        super.finish();
        overridePendingTransition(R.anim.anim_activity_bottom_in,
                R.anim.anim_activity_right_out);
    }



}
