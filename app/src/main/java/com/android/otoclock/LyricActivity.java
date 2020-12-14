package com.android.otoclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LyricActivity extends AppCompatActivity {

    private List<LrcRow> lrcRows= new ArrayList<LrcRow>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric);

        String strOfLrc = getLrcFile("北国之春.lrc");
        MyLrcBuilder myLrcBuilder = new MyLrcBuilder();
        lrcRows = myLrcBuilder.getLrcRows(strOfLrc);
    }

    public String getLrcFile(String fileName) {
        try {
            InputStreamReader inputStream = new InputStreamReader(getResources().getAssets().open(fileName),"GBK");
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null){
                if (line.trim().equals("")) {
                    continue;
                }
                Log.d("Line",line);
                result += line + "\r\n";
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
