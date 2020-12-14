package com.android.otoclock;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class AlarmActivity extends Activity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载音乐
        mediaPlayer = MediaPlayer.create(this,R.raw.chuyin);
        mediaPlayer.setLooping(true);
        //播放音乐
        mediaPlayer.start();
        //创建对话框
        new AlertDialog.Builder(AlarmActivity.this)
                .setTitle("闹钟")
                .setMessage("该打钱了！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        //结束闹钟
                        AlarmActivity.this.finish();
                    }
                }).show();
    }
}
