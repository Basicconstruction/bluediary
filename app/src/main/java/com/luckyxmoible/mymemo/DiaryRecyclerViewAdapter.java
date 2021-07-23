package com.luckyxmoible.mymemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiaryRecyclerViewAdapter extends
        RecyclerView.Adapter<DiaryRecyclerViewAdapter.ViewHolder> {

    private final List<Diary> mDiarys;

    public DiaryRecyclerViewAdapter(List<Diary> Diarys ) {
        mDiarys = Diarys;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_diary,
                        parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.Diary = mDiarys.get(position);
        holder.detailsView.setText(mDiarys.get(position).textContent);
    }

    @Override
    public int getItemCount() {
        return mDiarys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View parentView;
        public final TextView detailsView;
        public Diary Diary;

        public ViewHolder(View view) {
            super(view);
            parentView = view;
            detailsView = (TextView)view.findViewById(R.id.list_item_diary_details);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + detailsView.getText() + "'";
        }
    }
}
