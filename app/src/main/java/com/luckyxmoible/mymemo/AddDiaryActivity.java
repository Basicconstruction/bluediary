package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddDiaryActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary_activity);
        TextView time_tv = (TextView)findViewById(R.id.time);
        time_tv.setText(Diary.getDateTime());
        Button button = (Button)findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //将数据写入数据库
                new AsyncTask<Void, Void, Boolean>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        EditText et_textContent = (EditText)findViewById(R.id.edit_text_content);
                        String textContent = et_textContent.getText().toString();
                        EditText et_title = (EditText)findViewById(R.id.edit_text_title);
                        String title = et_title.getText().toString();
                        if(textContent.equals("")&&title.equals("")){
                        }else{
                            DiaryDatabaseAccessor
                                    .getInstance(getApplication()).diaryDAO().insertDiary(new Diary(title,textContent));
                        }
                        return true;
                    }
                }.execute();
                startActivity(new Intent(AddDiaryActivity.this,MainActivity.class));
            }
        });
    }


}
