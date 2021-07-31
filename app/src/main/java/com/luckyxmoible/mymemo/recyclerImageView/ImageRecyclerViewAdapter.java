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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.luckyxmoible.mymemo.R;

import java.util.List;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class ImageRecyclerViewAdapter extends
        RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {

    private final List<Uri> mUris;
    private Context context;
    public ImageRecyclerViewAdapter(List<Uri> uris ) {
        mUris = uris;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        /*ListItemDiaryBinding binding = ListItemDiaryBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Uri uri = mUris.get(position);
        if(uri!=null){
            holder.img_v.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return mUris.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View parentView;
        public final ImageView img_v;

        public ViewHolder(View view) {
            super(view);
            parentView = view;
            img_v = (ImageView)view.findViewById(R.id.single_image);
        }
    }
}