package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.luckyxmoible.mymemo.recyclerImageView.ImageListFragment;
import com.luckyxmoible.mymemo.recyclerImageView.ImageSizeInterface;
import com.luckyxmoible.mymemo.recyclerImageView.ImageStorage;
import com.luckyxmoible.mymemo.recyclerImageView.ImageUriDatabaseAccessor;
import com.luckyxmoible.mymemo.recyclerImageView.ImageViewModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class AddDiaryActivity extends AppCompatActivity {
    private static final String TAG = "HELLO";
    private static final String TAG_IMAGE_LIST_FRAGMENT = "TAG_IMAGE_LIST_FRAGMENT";
    ImageListFragment mImageListFragment;
    Uri imageUri;
    ImageViewModel mImageViewModel;
    TextView time_tv;
    Toolbar toolbar;
    ImageButton push_add;
    ImageButton back_add;
    ImageButton select_image;
    private static final int PICK_IMAGE = 100;
    public  AddDiaryActivity(){
        ImageSizeInterface.width = 300;
        ImageSizeInterface.height = 300;
        ImageSizeInterface.col = 3;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary_activity);
        ImageSizeInterface.width = 300;
        ImageSizeInterface.height = 300;
        ImageSizeInterface.col = 3;
        FragmentManager fm2 = getSupportFragmentManager();
        if(savedInstanceState==null){
            FragmentTransaction ft2 = fm2.beginTransaction();
            mImageListFragment = new ImageListFragment();
            mImageListFragment.mImageStorages.add(new ImageStorage());
            ft2.commitNow();
        }else{
            mImageListFragment = (ImageListFragment)fm2.findFragmentByTag(TAG_IMAGE_LIST_FRAGMENT);
        }
        mImageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        //String title = "写日记";
        //this.getSupportActionBar().setTitle(title);
        toolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        time_tv = (TextView)findViewById(R.id.time);
        time_tv.setText(Diary.getDateTime());
        push_add = (ImageButton)findViewById(R.id.push_add);
        push_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //将数据写入数据库
                new AsyncTask<Void, String, Boolean>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        EditText et_textContent = (EditText)findViewById(R.id.edit_text_content);
                        String textContent = et_textContent.getText().toString();
                        EditText et_title = (EditText)findViewById(R.id.edit_text_title);
                        String title = et_title.getText().toString();
                        Vector<Uri> uris = new Vector<Uri>();
                        for(int i = 0; i < mImageListFragment.mImageStorages.get(0).uris.size();i++){
                            if(mImageListFragment.mImageStorages.get(0).uris.get(i)!=null){
                                uris.add(mImageListFragment.mImageStorages.get(0).uris.get(i));
                            }
                        }
                        //uris.add(imageUri);
                        Log.d("Length off uris",uris.size()+"");
                        if(textContent.equals("")&&title.equals("")&&uris.size()==0){
                        }else{
                            DiaryDatabaseAccessor
                                    .getInstance(getApplication()).diaryDAO().insertDiary(new Diary(title,textContent,uris));
                        }
                        if(imageUri==null){
                            Log.d(TAG, "OK");
                        }else{
                            Log.d(TAG, "doInBackground: "+imageUri.getPath());
                        }
                        ImageUriDatabaseAccessor.getInstance(getApplication()).imageUriDao().deleteImageStorage(mImageListFragment.mImageStorages.get(0));
                        //mImageListFragment.mImageStorages.get(0).uris.clear();
                        return true;
                    }
                }.execute();

                startActivity(new Intent(AddDiaryActivity.this,MainActivity.class));
            }
        });
        back_add = (ImageButton)findViewById(R.id.back_add);
        back_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(view.getContext(),MainActivity.class));
            }
        });
        select_image = (ImageButton)findViewById(R.id.image_select);
        select_image.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v){
                openGalley();
            }
        });
        ImageButton lock = (ImageButton)findViewById(R.id.lock);
        lock.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DialogFragment dialog = new AddLockDialog();
                dialog.show(getSupportFragmentManager(),"input the password");
            }
        });


    }
    public boolean empty_content(){
        EditText et_textContent = (EditText)findViewById(R.id.edit_text_content);
        et_textContent.setText("");
        EditText et_title = (EditText)findViewById(R.id.edit_text_title);
        et_title.setText("");
        return true;
    }
    public boolean empty_diary_call(MenuItem item){
        return empty_content();
    }
    @SuppressLint("IntentReset")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void openGalley(){
        Intent galley = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        galley.setType("image/*");
        galley.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        galley.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(galley,PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK&&requestCode==PICK_IMAGE){
            imageUri = data.getData();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getContext().getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            //show_image.setImageURI(imageUri);
            mImageListFragment.mImageStorages.get(0).uris.add(imageUri);
            new AsyncTask<Void, String, Boolean>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                protected Boolean doInBackground(Void... voids) {
                    List<ImageStorage> cc = new ArrayList<>(0);
                    cc.add(mImageListFragment.mImageStorages.get(0));
                    ImageUriDatabaseAccessor.getInstance(getApplication()).imageUriDao().insertImageStorages(cc);
                    return true;
                }
            }.execute();
        }

    }
    /*
    @Override
    public void onRestart() {
        super.onRestart();
        if(mImageListFragment.mImageStorages!=null&&mImageListFragment.mImageStorages.size()>=1){
            new AsyncTask<Void, String, Boolean>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                protected Boolean doInBackground(Void... voids) {
                    ImageUriDatabaseAccessor.getInstance(getApplication()).imageUriDao().deleteImageStorage(mImageListFragment.mImageStorages.get(0));
                    return true;
                }
            }.execute();
        }
    }*/
    @Override
    public void onDestroy() {

        super.onDestroy();
        if(mImageListFragment.mImageStorages!=null&&mImageListFragment.mImageStorages.size()>=1){
            new AsyncTask<Void, String, Boolean>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                protected Boolean doInBackground(Void... voids) {
                    ImageUriDatabaseAccessor.getInstance(getApplication()).imageUriDao().deleteImageStorage(mImageListFragment.mImageStorages.get(0));
                    return true;
                }
            }.execute();
        }

    }
    private Context getContext() {
        return this;
    }
}
