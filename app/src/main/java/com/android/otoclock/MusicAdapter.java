package com.android.otoclock;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.otoclock.db.Music;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{
    public List<Music> musicList;

    private OnItemClickListener mOnItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView musicName;
        TextView singer;
        Button menuButton;

        public ViewHolder(View view){
            super(view);
            musicName = (TextView) view.findViewById(R.id.music_name);
            singer = (TextView) view.findViewById(R.id.singer_name);
            menuButton = (Button) view.findViewById(R.id.menu_button);
        }
    }

    public MusicAdapter(List<Music> list){
        musicList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.musiclist_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Music music = musicList.get(position);
        holder.musicName.setText(music.getMusicName());
        holder.singer.setText(music.getSingerName());
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public interface OnItemClickListener{
        void onClick( int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
}
