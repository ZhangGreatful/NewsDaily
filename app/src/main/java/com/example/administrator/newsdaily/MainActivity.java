package com.example.administrator.newsdaily;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends MyBaseActivity {
    public static final String splash_config = "run";
    public static final String IS_FIRST_RUN  = "isFirstRun";
    private ViewPager mViewPager;
    private int[]           pic   = {R.drawable.pic_1, R.drawable.picture_2,
            R.drawable.picture_3, R.drawable.picture_4};
    private ImageView[]     icons = new ImageView[4];
    private ArrayList<View> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//      判断是否是第一次运行
        SharedPreferences sp = getSharedPreferences(splash_config, MODE_PRIVATE);
        boolean isFirstRun = sp.getBoolean(IS_FIRST_RUN, true);
//        如果不是第一次运行,则直接进行跳转到HomeActivity
        if (!isFirstRun) {
            Intent intent = new Intent(this, HomeAcitivity.class);
            startActivity(intent);
            finish();

        } else {
            setContentView(R.layout.activity_main);
            initView();
        }


    }

    //    保存第一次运行的SP
    private void savedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(splash_config, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_RUN, false);
        editor.apply();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewPager);
        for (int i = 0; i < pic.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(pic[i]);
            mList.add(iv);
        }
        icons[0] = (ImageView) findViewById(R.id.iv_p1);
        icons[1] = (ImageView) findViewById(R.id.iv_p2);
        icons[2] = (ImageView) findViewById(R.id.iv_p3);
        icons[3] = (ImageView) findViewById(R.id.iv_p4);
        setPoint(0);
//        对ViewPager设置适配器
        mViewPager.setAdapter(new MyAdapter(mList));
//        对ViewPager设置监听
        mViewPager.setOnPageChangeListener(listener);

    }

    //      将position和index的点进行关联,设置当前界面的点的透明度和其他界面的点的透明度
    private void setPoint(int index) {
        for (int i = 0; i < icons.length; i++) {
            if (i == index) {
                icons[i].setAlpha(255);
            } else {
                icons[i].setAlpha(100);
            }
        }
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
//      判断当前图片位置,若>=3,则进行activity的跳转,同时保存第一次运行
        @Override
        public void onPageSelected(int position) {
            setPoint(position);
            if (position >= 3) {
                openActivity(ActivityLogo.class);
                savedPreferences();
                finish();
            }

        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private class MyAdapter extends PagerAdapter {
        public MyAdapter(ArrayList<View> list) {
            mList = list;
        }

        /**
         * 返回显示的图片总数
         *
         * @return
         */
        @Override
        public int getCount() {
            if (mList != null) {
                return mList.size();
            }
            return 0;
        }

        /**
         * 确定是否一个带有特殊键对象的图片视图,和instantiateItem(View view, Object object)
         * 返回值相关联
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 初始化position位置的图片,返回一个Object对象
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position), 0);
            return mList.get(position);
        }

        /**
         * 移除position位置的view
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }
    }
}
