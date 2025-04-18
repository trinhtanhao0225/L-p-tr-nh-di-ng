package com.example.mutipleviewtype_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Object> mObjects;

    public static final int TEXT = 0;
    public static final int IMAGE = 1;
    public static final int USER = 2;

    public CustomAdapter(Context context, List<Object> objects) {
        mContext = context;
        mObjects = objects;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        switch (viewType){
            case TEXT:
                View view = layoutInflater.inflate(R.layout.row_text, parent, false);
                return new TextViewHolder(view);
            case IMAGE:
                View view1 = layoutInflater.inflate(R.layout.row_image, parent, false);
                return new ImageViewHolder(view1);
            case USER:
                View view2 = layoutInflater.inflate(R.layout.row_user, parent, false);
                return new UserViewHolder(view2);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TEXT:
                TextViewHolder textViewHolder  = (TextViewHolder) holder;
                textViewHolder.tvText.setText(mObjects.get(position).toString());
                break;
            case IMAGE:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                imageViewHolder.imvImage.setImageResource((int) mObjects.get(position));
                break;

            case USER:
                UserModel userModel = (UserModel) mObjects.get(position);
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                userViewHolder.tvName.setText(userModel.getName());
                userViewHolder.tvAdress.setText(userModel.getAddress());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    @Override
    public int getItemViewType(int position){
        if (mObjects.get(position) instanceof String){
            return TEXT;
        }
        else if (mObjects.get(position) instanceof Integer)
            return IMAGE;
        else if(mObjects.get(position) instanceof UserModel)
            return USER;
        return -1;
    }

    public class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView tvText;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mObjects.get(getAdapterPosition()).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView imvImage;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imvImage = itemView.findViewById(R.id.imv_image);


        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvAdress;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAdress = itemView.findViewById(R.id.tv_address);
            //su kien click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mObjects.get(getAdapterPosition()).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
