package com.example.animationtrongrecyclerview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAnimationAdapter extends RecyclerView.Adapter<CustomAnimationAdapter.ViewHolder> {
    private List<String> mDatas;

    public CustomAnimationAdapter(List<String> mDatas) {
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.row_animation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = mDatas.get(position);
        holder.tvItem.setText(item);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addItem(String item){
        mDatas.add(item);
        notifyItemInserted(mDatas.size()-1);
    }

    public void addItem(int position, String item){
        mDatas.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        mDatas.remove(position);
        notifyItemInserted(position);
    }

    public void removeItem( String item){
        int index = mDatas.indexOf(item);
        if(index < 0){
            return;
        }
        mDatas.remove(index);
        notifyItemInserted(index);
    }

    public void replaceItem(int positon, String item){
        mDatas.remove(positon);
        mDatas.add(positon, item);
        notifyItemInserted(positon);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(), "Removed Animation", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceItem(getAdapterPosition(), "item changed");
                    Toast.makeText(itemView.getContext(), "Changed Animation", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

