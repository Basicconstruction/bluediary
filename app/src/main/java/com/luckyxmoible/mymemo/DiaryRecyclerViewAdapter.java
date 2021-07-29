package com.luckyxmoible.mymemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luckyxmoible.mymemo.databinding.ListItemDiaryBinding;

import java.util.List;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class DiaryRecyclerViewAdapter extends
        RecyclerView.Adapter<DiaryRecyclerViewAdapter.ViewHolder> {

    private final List<Diary> mDiaries;
    private RecyclerItemListener recyclerItemListener;

    public DiaryRecyclerViewAdapter(List<Diary> Diaries ) {
        mDiaries = Diaries;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListItemDiaryBinding binding = ListItemDiaryBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Diary diary = mDiaries.get(position);
        holder.binding.setDiary(diary);
        //Bitmap img = BitmapFactory.decodeFile(String.valueOf(diary.url)); keep
        //holder.binding.ImageView.setImageBitmap(img); keep
        holder.binding.executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                //holder.binding.setDiary(new Diary("hehe","lala"));
                if(recyclerItemListener!=null){
                    recyclerItemListener.onItemClick(diary);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDiaries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ListItemDiaryBinding binding;

        public ViewHolder(ListItemDiaryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}