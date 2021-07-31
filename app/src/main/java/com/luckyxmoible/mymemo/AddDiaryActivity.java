package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class AddDiaryActivity extends AppCompatActivity {
    private static final String TAG = "HELLO";
    private final int MENU_EMPTY = 3;
    Uri imageUri;
    TextView time_tv;
    Toolbar toolbar;
    ImageButton push_add;
    ImageButton back_add;
    ImageButton select_image;
    ImageView show_image;
    private static final int PICK_IMAGE = 100;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary_activity);
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

                        ImageView img_v = (ImageView)findViewById(R.id.image_show);
                        //Uri[] uri = new Uri[]{imageUri};
                        //Uri[] uri = new Uri[0];
                        Vector<Uri> uris = new Vector<Uri>();
                        uris.add(imageUri);
                        Log.d("Length off uris",uris.size()+"");
                        if(textContent.equals("")&&title.equals("")){
                        }else{
                            DiaryDatabaseAccessor
                                    .getInstance(getApplication()).diaryDAO().insertDiary(new Diary(title,textContent,uris));
                        }
                        if(imageUri==null){
                            Log.d(TAG, "OK");
                        }else{
                            Log.d(TAG, "doInBackground: "+imageUri.getPath());
                        }
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
        show_image = (ImageView)findViewById(R.id.image_show);
        select_image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGalley();
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
    public void openGalley(){
        Intent galley = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galley,PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK&&requestCode==PICK_IMAGE){
            imageUri = data.getData();
            show_image.setImageURI(imageUri);
        }
    }
}
