package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
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

    public final List<Diary> mDiaries;
    private Context context;
    private final RecyclerItemListener recyclerItemListener = new RecyclerItemListener() {
        @Override
        public void onItemClick(Diary diary,View v) {
            RecyclerItemListener.diary = diary;
            Intent intent = new Intent(v.getContext(),ShowDiaryDetails.class);
            v.getContext().startActivity(intent);
            Log.d(TAG, "onItemClick: 6666666"+v.getContext().toString());

        }
    };
    public DiaryRecyclerViewAdapter(List<Diary> Diaries ) {
        mDiaries = Diaries;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_diary,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        holder.img_v1.setImageResource(0);
        Diary diary = mDiaries.get(position);
        if(diary.isLocked){
            holder.textContent_v.setText("locked");
            holder.local_v.setText("locked");
        }else{
            if(diary.uris!=null){
                if(diary.uris.size()>=1){
                    if(diary.uris.get(0)!=null){
                        holder.img_v1.setImageURI(diary.uris.get(0));
                        Log.d(TAG, "onBindViewHolder: "+diary.uris.get(0).toString());
                    }
                }
            }
            holder.textContent_v.setText(diary.getContentSummary());
            holder.local_v.setText(diary.getLocation());
        }

        holder.title_v.setText(diary.title);
        holder.time_v.setText(diary.getTimeInfo());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                //holder.binding.setDiary(new Diary("hehe","lala"));
                recyclerItemListener.onItemClick(diary,v);

            }
        });

    }

    @Override
    public int getItemCount() {
        if(mDiaries!=null){
            Log.d(TAG, "getItemCount: xxx"+mDiaries.size());
        }
        assert mDiaries != null;
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