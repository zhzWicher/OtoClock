package com.android.otoclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TapActivity extends ActivityGroup implements View.OnClickListener {

    private static final String TAG = "TabActivity";
    private Bundle mBundle = new Bundle();
    public LinearLayout ll_container,ll_first,ll_second,ll_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);
        //ll_container用于存放布局
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ll_first = (LinearLayout) findViewById(R.id.ll_first);
        ll_second = (LinearLayout) findViewById(R.id.ll_second);
        ll_third = (LinearLayout)findViewById(R.id.ll_third);

        ll_first.setOnClickListener(this);
        ll_second.setOnClickListener(this);
        ll_third.setOnClickListener(this);
        mBundle.putString("tag",TAG);
        changeView(ll_first);
    }

    //切换标签栏
    private void changeView(View v) {
        ll_first.setSelected(false);
        ll_second.setSelected(false);
        ll_third.setSelected(false);
        v.setSelected(true);
        //根据v确认切换至哪个布局
        if(v == ll_first) {
            toActivity("first",MainActivity.class);
        } else if(v == ll_second){
            toActivity("second",MusicActivity.class);
        } else {

        }
    }

    private void toActivity(String label, Class<?> cls) {
        //创建一个意图，并存入包裹
        Intent intent = new Intent(this,cls).putExtras(mBundle);
        //移除内容框架下所有下级视图
        ll_container.removeAllViews();
        //启动活动并获取该活动的顶级视图
        View v = getLocalActivityManager().startActivity(label,intent).getDecorView();
        //设置内容布局的参数
        v.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //活动顶层视图添加至内容框架
        ll_container.addView(v);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_first:
                changeView(ll_first);
                break;
            case R.id.ll_second:
                changeView(ll_second);
                break;
            case R.id.ll_third:
                changeView(ll_third);
                break;
            default:
                break;
        }
    }
}
