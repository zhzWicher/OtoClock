package com.android.otoclock;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MyLrcBuilder implements ILrcBuilder{

    static final String TAG = "LrcBuilder";
    static final String TAG1 = "LrcBuilder1";

    @Override
    public List<LrcRow> getLrcRows(String lrcString) {
        Log.d(TAG,"get lrcRows");

        if (lrcString == null || lrcString.length() == 0) {
            Log.d(TAG,"null lrcString");
            return null;
        }

        StringReader stringReader = new StringReader(lrcString);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        String line = null;
        List<LrcRow> rows = new ArrayList<LrcRow>();
        try {
            //循环
            do {
                line = bufferedReader.readLine();
                Log.d(TAG,"lrcString :" + line);
                if (line != null && line.length() > 0) {
                    LrcRow lrcRow = LrcRow.addRow(line);
                    if (lrcRow != null) {
                        Log.d(TAG1,"lrcRow" + lrcRow);
                        rows.add(lrcRow);
                    }
                }
            } while (line != null);
        } catch (Exception e){
            Log.d(TAG,"Exception");
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringReader.close();
        }
        return rows;
    }
}
