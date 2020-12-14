package com.android.otoclock;

import java.util.ArrayList;
import java.util.List;

public class LrcRow {

    /** 该行歌词的内容 */
    public String lrcStr;

    /** 该行歌词要开始播放的时间，格式如下：[02:34.14] */
    public String strTime;

    /** 该行歌词要开始播放的时间，由[02:34.14]格式转换为long型，
     * 即将2分34秒14毫秒都转为毫秒后 得到的long型值：time=02*60*1000+34*1000+14
     */
    public long time;

    public LrcRow(){}

    public LrcRow(String lrcStr,String strTime,Long time) {
        this.lrcStr = lrcStr;
        this.strTime = strTime;
        this.time = time;
    }

    @Override
    public String toString() {
        return strTime + lrcStr;
    }

    //提取一行歌词
    public static LrcRow addRow(String strLine) {

        //
        try {
            if (strLine.indexOf("[") != 0 || strLine.indexOf("]") != 9) {
                return null;
            }
            int rightBracket = strLine.indexOf("]");
            //歌词内容
            String lrcStr = strLine.substring(rightBracket + 1,strLine.length());
            //歌词时间
            String strTime = strLine.substring(0,rightBracket + 1);
            LrcRow lrcRow = new LrcRow(lrcStr,strTime,TimeChanger(strTime));

            return lrcRow;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static long TimeChanger(String strTime) {
        String time0 = strTime.substring(1,9).replace(".",":");
        String[] times = time0.split(":");
        //mm:ss:SS
        return Integer.valueOf(times[0]) * 60 * 1000 +
                Integer.valueOf(times[1]) * 1000 +
                Integer.valueOf(times[2]);

    }

}
