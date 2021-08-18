package com.luckyxmoible.mymemo.recyclerImageView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luckyxmoible.mymemo.Diary;
import com.luckyxmoible.mymemo.DiaryRecyclerViewAdapter;
import com.luckyxmoible.mymemo.DiaryViewModel;
import com.luckyxmoible.mymemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageListFragment extends Fragment {
    public ImageStorage mImageStorages = new ImageStorage();
    private RecyclerView mRecyclerView2;
    private final ImageRecyclerViewAdapter mImageAdapter = new ImageRecyclerViewAdapter(mImageStorages);
    public ImageListFragment(){};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_image_uri_list,container,false);
        mRecyclerView2 = (RecyclerView)view.findViewById(R.id.image_list);
        return view;
    }
    public void setUris(ImageStorage imageStorages){
        mImageStorages.uris.clear();
        mImageAdapter.notifyDataSetChanged();
        for(Uri uri:imageStorages.uris){
            if(!mImageStorages.uris.contains(uri)){
                mImageStorages.uris.add(uri);
                mImageAdapter.notifyItemInserted(mImageStorages.uris.indexOf(uri));
            }
        }

    }
    public void insertUries(Uri uri){
        if(!mImageStorages.uris.contains(uri)){
            mImageStorages.uris.add(uri);
//            mImageAdapter.notifyItemInserted(mImageStorages.indexOf(imageStorage));
        }
        mImageAdapter.notifyDataSetChanged();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Context context = view.getContext();
        mRecyclerView2.setLayoutManager(new GridLayoutManager(context,ImageSizeInterface.col));
        mRecyclerView2.setAdapter(mImageAdapter);
    }
}
