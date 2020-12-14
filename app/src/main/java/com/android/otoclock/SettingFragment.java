package com.android.otoclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.ParcelUuid;
import android.os.SystemClock;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.Calendar;


public class SettingFragment extends Fragment {

    private RadioGroup radioGroup;

    private Switch aSwitch;

    private RadioButton radioButton1;

    private RadioButton radioButton2;

    private RadioButton radioButton3;

    private RadioButton radioButton4;

    private RadioButton radioButton5;

    private static final int BLACK = 0;

    private static final int BLUE = 1;

    private static final int YELLOW = 2;

    private static final int GREEN = 3;

    private static final int RED = 4;
    private String minuteStr;
    private String hourStr;
    private int checked = 0;



    private TextClock textClock;

    private CallBackListener callBackListener;

    private AlarmManager alarmManager;

    private Button btnSelect;

    Calendar currentTime = Calendar.getInstance();

    private String ALARM_EVENT = "com.android.otoclock.AlarmReciever";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("fragment","create");
        final View view = inflater.inflate(R.layout.fragment_setting,container,false);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        aSwitch = (Switch) view.findViewById(R.id.switch_button);
        radioButton1 = (RadioButton) view.findViewById(R.id.radio_black);
        radioButton2 = (RadioButton) view.findViewById(R.id.radio_blue);
        radioButton3 = (RadioButton) view.findViewById(R.id.radio_yellow);
        radioButton4 = (RadioButton) view.findViewById(R.id.radio_green);
        radioButton5 = (RadioButton) view.findViewById(R.id.radio_red);
        btnSelect = (Button) view.findViewById(R.id.select_clock);
        callBackListener = (CallBackListener) getActivity();
        textClock = (TextClock) view.findViewById(R.id.text_clock1);
        alarmManager = (AlarmManager) getActivity().getSystemService(Service.ALARM_SERVICE);

        
        //第三方字体设置
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/ziti.ttf");
        btnSelect.setTypeface(typeface);
        radioButton1.setTypeface(typeface);
        radioButton2.setTypeface(typeface);
        radioButton3.setTypeface(typeface);
        radioButton4.setTypeface(typeface);
        radioButton5.setTypeface(typeface);
        //时钟字体设置
        SpannableString ss = new SpannableString("HH:mm");
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textClock.setFormat12Hour(ss);

        //如果已设置闹钟，则会获取闹钟数据
        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        checked = prefs.getInt("CHECK",0);
        if (checked == 1) {
            int minute = prefs.getInt("MINUTE",0);
            int hour = prefs.getInt("HOUR",0);
            int alarmCounts = 0;
            aSwitch.setChecked(true);
            //广播意图
            Intent intent = new Intent(ALARM_EVENT);
            intent.setComponent(new ComponentName("com.android.otoclock","com.android.otoclock.AlarmReceiver"));

            //创建PendingIntent
            PendingIntent pi = PendingIntent.getBroadcast(getActivity(),alarmCounts++,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY,hour);
            c.set(Calendar.MINUTE,minute);
            long time = c.getTimeInMillis();
            long realTime = System.currentTimeMillis();
            if(realTime <= time) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                } else {
                    alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
                }
            }
            if (minute < 10) {
                minuteStr = "0" + minute;
            } else {
                minuteStr = String.valueOf(minute);
            }
            if (hour < 10) {
                hourStr = "0" + hour;
            } else {
                hourStr = String.valueOf(hour);
            }
            SpannableString ss1 = new SpannableString(hourStr + ":" + minuteStr);
            ss1.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            textClock.setFormat12Hour(ss1);
        }



        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), 0, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int alarmCounts = 0;
                        //广播意图
                        Intent intent = new Intent(ALARM_EVENT);
                        intent.setComponent(new ComponentName("com.android.otoclock","com.android.otoclock.AlarmReceiver"));

                        //创建PendingIntent
                        PendingIntent pi = PendingIntent.getBroadcast(getActivity(),alarmCounts++,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        //存储闹钟时间
                        SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
                        editor.putInt("HOUR",hourOfDay);
                        editor.putInt("MINUTE",minute);

                        //设置闹钟唤醒
                        if(aSwitch.isChecked()) {
                            checked = 1;
                            editor.putInt("CHECK",checked);

                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                            } else {
                                alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
                            }

                        }
                        editor.apply();
                        if (minute < 10) {
                            minuteStr = "0" + minute;
                        } else {
                            minuteStr = String.valueOf(minute);
                        }
                        if (hourOfDay < 10) {
                            hourStr = "0" + hourOfDay;
                        } else {
                            hourStr = String.valueOf(hourOfDay);
                        }
                        SpannableString ss = new SpannableString(hourStr + ":" + minuteStr);
                        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        textClock.setFormat12Hour(ss);

                        //显示闹钟设置成功
                        Toast.makeText(getActivity(),"闹钟设置成功",Toast.LENGTH_SHORT).show();
                    }
                },currentTime.get(Calendar.HOUR_OF_DAY),currentTime.get(Calendar.MINUTE),false).show();
            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                switch (checkedId){
                    case R.id.radio_black:
                        editor.putInt("Color",BLACK);
                        editor.apply();
                        callBackListener.changeColor(BLACK);
                        break;
                    case R.id.radio_blue:
                        editor.putInt("Color",BLUE);
                        editor.apply();
                        callBackListener.changeColor(BLUE);
                        break;
                    case R.id.radio_yellow:
                        editor.putInt("Color",YELLOW);
                        editor.apply();
                       callBackListener.changeColor(YELLOW);
                        break;
                    case R.id.radio_green:
                        editor.putInt("Color",GREEN);
                        editor.apply();
                        callBackListener.changeColor(GREEN);
                        break;
                    case R.id.radio_red:
                        editor.putInt("Color",RED);
                        editor.apply();
                        callBackListener.changeColor(RED);
                        break;
                    default:
                        break;

                }
            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public interface CallBackListener {
        void changeColor(int color);
    }


}
