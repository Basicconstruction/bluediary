package com.luckyxmoible.mymemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


import java.util.List;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class DiaryRecyclerViewAdapter extends
        RecyclerView.Adapter<DiaryRecyclerViewAdapter.ViewHolder> {

    private final List<Diary> mDiaries;
    private Context context;
    private final RecyclerItemListener recyclerItemListener = new RecyclerItemListener() {
        @Override
        public void onItemClick(Diary diary,View v) {
            RecyclerItemListener.diary = diary;
            Intent intent = new Intent(v.getContext(),ShowDiaryDetails.class);
            //intent.putExtra("Diary symbol", (Parcelable) diary);
            v.getContext().startActivity(intent);

        }
    };

    public DiaryRecyclerViewAdapter(List<Diary> Diaries ) {
        mDiaries = Diaries;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        /*ListItemDiaryBinding binding = ListItemDiaryBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_diary,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Diary diary = mDiaries.get(position);
        if(diary.uris!=null){
            if(diary.uris.size()>=1){
                if(diary.uris.get(0)!=null){
                    holder.img_v1.setImageURI(diary.uris.get(0));
                }
            }
        }
        holder.title_v.setText(diary.title);
        holder.textContent_v.setText(diary.textContent);
        holder.time_v.setText(diary.getTimeInfo());
        holder.local_v.setText(diary.getLocation());
        /*
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
                    recyclerItemListener.onItemClick(diary,v);

                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mDiaries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View parentView;
        public final TextView title_v;
        public final TextView local_v;
        public final TextView time_v;
        public final TextView textContent_v;
        public final ImageView img_v1;

        public ViewHolder(View view) {
            super(view);
            parentView = view;
            title_v = (TextView)view.findViewById(R.id.title);
            time_v = (TextView)view.findViewById(R.id.date);
            local_v = (TextView)view.findViewById(R.id.location);
            textContent_v = (TextView)view.findViewById(R.id.text_content);
            img_v1 = (ImageView)view.findViewById(R.id.image_view);
        }
    }
}