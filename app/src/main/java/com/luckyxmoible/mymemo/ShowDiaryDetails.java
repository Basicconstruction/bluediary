package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.luckyxmoible.mymemo.recyclerImageView.ImageListFragment;
import com.luckyxmoible.mymemo.recyclerImageView.ImageSizeInterface;
import com.luckyxmoible.mymemo.recyclerImageView.ImageStorage;
import com.luckyxmoible.mymemo.recyclerImageView.ImageUriDatabaseAccessor;
import com.luckyxmoible.mymemo.recyclerImageView.ImageViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowDiaryDetails extends AppCompatActivity {
    private Diary diary;
    ImageViewModel mImageViewModel2;
    ImageListFragment mImageListFragment2;
    private static final String TAG_IMAGE_LIST_FRAGMENT2 = "TAG_IMAGE_LIST_FRAGMENT2";
    public ShowDiaryDetails(Diary diary){
        this.diary = diary;
        ImageSizeInterface.width = 300;
        ImageSizeInterface.height = 300;
        ImageSizeInterface.col = 1;
    }
    public ShowDiaryDetails(){
        this.diary = RecyclerItemListener.diary;
        ImageSizeInterface.width = 300;
        ImageSizeInterface.height = 300;
        ImageSizeInterface.col = 1;
        //这是一种绑定方法，但是这种绑定方法会有额外的一点开销，事实上还可以传递diaries在View holder中的索引，
        // 在这里的onCreate方法中直接引入diaries[position].属性
        //但是实际作用并不大，
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_details_activity);
        FragmentManager fm3 = getSupportFragmentManager();
        if(savedInstanceState==null){
            FragmentTransaction ft2 = fm3.beginTransaction();
            mImageListFragment2 = new ImageListFragment();
            mImageListFragment2.mImageStorages.add(new ImageStorage());
            ft2.commitNow();
        }else{
            mImageListFragment2 = (ImageListFragment)fm3.findFragmentByTag(TAG_IMAGE_LIST_FRAGMENT2);
        }
        mImageViewModel2 = ViewModelProviders.of(this).get(ImageViewModel.class);

        TextView title = (TextView)findViewById(R.id.title_det);
        title.setText(diary.title);
        TextView content = (TextView)findViewById(R.id.content_det);
        content.setText(diary.textContent);
        TextView place = (TextView)findViewById(R.id.loc_det);
        //place.setText(diary.getLocation());
        place.setText(diary.getLocation()+diary.getUriLength());
        TextView time = (TextView)findViewById(R.id.time_det);
        time.setText(diary.getTimeInfo());
        ImageButton bt_back = (ImageButton)findViewById(R.id.back_button_det);
        bt_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //实际测试时，使用xml定义onClick属性运行时并不稳定。
                new AsyncTask<Void, String, Boolean>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        ImageUriDatabaseAccessor.getInstance(getApplication()).imageUriDao().deleteImageStorage(mImageListFragment2.mImageStorages.get(0));
                        return true;
                    }
                }.execute();
                startActivity(new Intent(view.getContext(),MainActivity.class));
            }
        });

        assert mImageListFragment2 != null;
        mImageListFragment2.mImageStorages.get(0).uris = diary.uris;
        new AsyncTask<Void, String, Boolean>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Boolean doInBackground(Void... voids) {
                List<ImageStorage> cc = new ArrayList<>(0);
                cc.add(mImageListFragment2.mImageStorages.get(0));
                ImageUriDatabaseAccessor.getInstance(getApplication()).imageUriDao().insertImageStorages(cc);
                return true;
            }
        }.execute();

    }
}
