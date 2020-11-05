package com.android.otoclock;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextClock;


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

    private TextClock textClock;

    private CallBackListener callBackListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_setting,container,false);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        aSwitch = (Switch) view.findViewById(R.id.switch_button);
        radioButton1 = (RadioButton) view.findViewById(R.id.radio_black);
        radioButton2 = (RadioButton) view.findViewById(R.id.radio_blue);
        radioButton3 = (RadioButton) view.findViewById(R.id.radio_yellow);
        radioButton4 = (RadioButton) view.findViewById(R.id.radio_green);
        radioButton5 = (RadioButton) view.findViewById(R.id.radio_red);
        callBackListener = (CallBackListener) getActivity();




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                switch (checkedId){
                    case R.id.radio_black:
                        callBackListener.changeColor(BLACK);
                        break;
                    case R.id.radio_blue:
                        callBackListener.changeColor(BLUE);
                        break;
                    case R.id.radio_yellow:
                       callBackListener.changeColor(YELLOW);
                        break;
                    case R.id.radio_green:
                        callBackListener.changeColor(GREEN);
                        break;
                    case R.id.radio_red:
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
