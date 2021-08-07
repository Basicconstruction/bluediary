package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
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
import java.util.Vector;

public class ShowDiaryDetails extends AppCompatActivity implements AddLockDialog.MyDialogInterface{
    private Diary diary;
    ImageViewModel mImageViewModel2;
    ImageListFragment mImageListFragment2;
    private static final String TAG_IMAGE_LIST_FRAGMENT2 = "TAG_IMAGE_LIST_FRAGMENT2";
    boolean open;
    public ShowDiaryDetails(Diary diary){
        this.diary = diary;
        ImageSizeInterface.width = 300;
        ImageSizeInterface.height = 300;
        ImageSizeInterface.col = 1;
        this.open = !diary.isLocked;
    }
    public ShowDiaryDetails(){
        this.diary = RecyclerItemListener.diary;
        ImageSizeInterface.width = 300;
        ImageSizeInterface.height = 300;
        ImageSizeInterface.col = 1;
        this.open = false;
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
        TextView content = (TextView)findViewById(R.id.content_det);
        TextView place = (TextView)findViewById(R.id.loc_det);
        TextView time = (TextView)findViewById(R.id.time_det);
        title.setText(diary.title);
        time.setText(diary.getTimeInfo());
        if(diary.isLocked){
            content.setText("locked");
            place.setText("locked");
            assert mImageListFragment2 != null;
            mImageListFragment2.mImageStorages.get(0).uris = new Vector<>(0);
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
            showNoticeDialog();
        }else{
            presentNoLock(content,place);
        }
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
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        new AsyncTask<Void, String, Boolean>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Boolean doInBackground(Void... voids) {
                ImageUriDatabaseAccessor.getInstance(getApplication()).imageUriDao().deleteImageStorage(mImageListFragment2.mImageStorages.get(0));
                return true;
            }
        }.execute();
    }
    @SuppressLint("SetTextI18n")
    public void presentNoLock(TextView content,TextView place){
        content.setText(diary.textContent);
        place.setText(diary.getLocation()+diary.getUriLength());
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
    public void showNoticeDialog() {
        DialogFragment dialog = new AddLockDialog();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDialogPositiveClick(View layouts) {
        EditText pass_et = layouts.findViewById(R.id.password);
        if(AddLockDialog.InterfaceUtils.passwordText.equals(diary.password)){
            open = true;
            Log.d("TAG", "onDialogPositiveClick: open is true");
            TextView content = (TextView)findViewById(R.id.content_det);
            TextView place = (TextView)findViewById(R.id.loc_det);
            content.setText(diary.textContent);
            place.setText(diary.getLocation()+diary.getUriLength());
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
        }else{
            showNoticeDialog();
        }
    }

    @Override
    public void onDialogNegativeClick(View layouts) {

    }
}
