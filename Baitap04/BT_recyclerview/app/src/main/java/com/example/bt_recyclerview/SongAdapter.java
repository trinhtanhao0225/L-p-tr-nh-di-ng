package com.example.bt_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private static List<SongModel> mSongs;
    private static Context mContext;
    private LayoutInflater mLayoutInflater;

    public SongAdapter(List<SongModel> mSongs, Context mContext) {
        this.mSongs = mSongs;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    //tao doi tuong viewholder bang inflfate file xml thanh doi tuong view
    //chay khi recyclerview can tao mot item moi
    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_item_song, parent, false);
        return new SongViewHolder(view);
    }

    //gan du lieu cho cac view trong viewholer
    //khi mot item hien thi, recyclerview se goi phuong thuc nay
    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        SongModel song = mSongs.get(position);
        if(song == null)
            return;
        //gan du lieu
        holder.tvCode.setText(song.getmCode());
        holder.tvTitle.setText(song.getmTitle());
        holder.tvArtist.setText(song.getmArtist());
        holder.tvLyric.setText(song.getmLyric());
    }

    @Override
    public int getItemCount() {
        if (mSongs != null)
            return mSongs.size();
        return 0;
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCode;
        private TextView tvTitle;
        private TextView tvLyric;
        private TextView tvArtist;
        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLyric = itemView.findViewById(R.id.tv_lyric);
            tvArtist = itemView.findViewById(R.id.tv_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SongModel song = mSongs.get(getAdapterPosition());
                    Toast.makeText(mContext, song.getmTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
