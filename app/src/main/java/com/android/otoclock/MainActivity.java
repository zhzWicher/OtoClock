package com.android.otoclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity implements SettingFragment.CallBackListener {

    public TextClock textClock;

    private Button navButton;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.fengjing);

        textClock = (TextClock) findViewById(R.id.text_clock);
        navButton = (Button) findViewById(R.id.nav_button);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        //时钟字体设置
        SpannableString ss = new SpannableString("HH:mm:ss");
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(0.5f), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textClock.setFormat12Hour(ss);


        //侧滑栏
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void changeColor(int color) {
        switch (color){
            case 0:
                SpannableString ss = new SpannableString("HH:mm:ss");
                ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                ss.setSpan(new ForegroundColorSpan(Color.BLACK), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                ss.setSpan(new RelativeSizeSpan(0.5f), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                textClock.setFormat12Hour(ss);
                break;
            case 1:
                SpannableString ss1 = new SpannableString("HH:mm:ss");
                ss1.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                ss1.setSpan(new ForegroundColorSpan(Color.BLUE), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                ss1.setSpan(new RelativeSizeSpan(0.5f), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                textClock.setFormat12Hour(ss1);
                break;
            case 2:
                SpannableString ss2 = new SpannableString("HH:mm:ss");
                ss2.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                ss2.setSpan(new ForegroundColorSpan(Color.YELLOW), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                ss2.setSpan(new RelativeSizeSpan(0.5f), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                textClock.setFormat12Hour(ss2);
                break;
            case 3:
                SpannableString ss3 = new SpannableString("HH:mm:ss");
                ss3.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                ss3.setSpan(new ForegroundColorSpan(Color.GREEN), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                ss3.setSpan(new RelativeSizeSpan(0.5f), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                textClock.setFormat12Hour(ss3);
                break;
            case 4:
                SpannableString ss4 = new SpannableString("HH:mm:ss");
                ss4.setSpan(new ForegroundColorSpan(Color.RED), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                ss4.setSpan(new ForegroundColorSpan(Color.RED), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                ss4.setSpan(new RelativeSizeSpan(0.5f), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                textClock.setFormat12Hour(ss4);
                break;
            default:
                break;
        }
    }
}
