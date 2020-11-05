package com.android.otoclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {

    private TextClock textClock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.youmu);

        textClock = (TextClock) findViewById(R.id.text_clock);
        SpannableString ss = new SpannableString("HH:mm:ss");
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(0.5f), 6, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textClock.setFormat12Hour(ss);
    }
}
