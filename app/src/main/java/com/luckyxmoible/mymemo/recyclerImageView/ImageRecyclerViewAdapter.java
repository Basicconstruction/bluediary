package com.luckyxmoible.mymemo.recyclerImageView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.luckyxmoible.mymemo.R;

import java.util.List;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class ImageRecyclerViewAdapter extends
        RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {

    private final List<ImageStorage> mImageStorages;
    private Context context;
    public ImageRecyclerViewAdapter(List<ImageStorage> imageStorages) {
        mImageStorages = imageStorages;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if(mImageStorages!=null){
            //return;
            if(mImageStorages.size()>=1){
                if(mImageStorages.get(0).uris.get(position)!=null){
                    Log.d(TAG, "GET POSITION :"+position);
                    holder.img_v.setImageURI(mImageStorages.get(0).uris.get(position));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if(mImageStorages==null){
            return 0;
        }else if(mImageStorages.size()<1){
            return 0;
        }else{
            return mImageStorages.get(0).uris.size();
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View parentView;
        public final ImageView img_v;
        public final LinearLayout linearImageControl;

        public ViewHolder(View view) {
            super(view);
            parentView = view;
            img_v = (ImageView)view.findViewById(R.id.single_image);
            img_v.setMinimumWidth(ImageSizeInterface.width);
            img_v.setMinimumHeight(ImageSizeInterface.height);
            linearImageControl = (LinearLayout)view.findViewById(R.id.linearImageControl);
            linearImageControl.setMinimumWidth(ImageSizeInterface.width);
            linearImageControl.setMinimumHeight(ImageSizeInterface.height);
        }
    }
}