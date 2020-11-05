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

public class MainActivity extends AppCompatActivity {

    private TextClock textClock;

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
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
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
}
