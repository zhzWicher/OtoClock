package com.android.otoclock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;


import com.android.otoclock.db.Music;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MusicActivity extends AppCompatActivity {

    private List<Music> musicList =  new ArrayList<>();

    private MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle("音乐");
        Glide.with(this).load(R.drawable.otobeijing).into(imageView);
        
        //设置滑动界面
        initMusic();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MusicAdapter(musicList);
        adapter.setOnItemClickListener(new MusicAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MusicActivity.this,LyricActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MusicName",musicList.get(position).getMusicName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initMusic() {
        Music music1 = new Music("初音未来的消失","乙女音");
        musicList.add(music1);
        Music music2 = new Music("北国之春","乙女音");
        musicList.add(music2);
        Music music3 = new Music("红莲华","乙女音");
        musicList.add(music3);
        Music music4 = new Music("北国之春（复古）","乙女音");
        musicList.add(music4);
        Music music5 = new Music("Mojito","乙女音");
        musicList.add(music5);
        Music music6 = new Music("七里香","乙女音");
        musicList.add(music6);
        Music music7 = new Music("奥特曼之歌","乙女音");
        musicList.add(music7);
    }
}
