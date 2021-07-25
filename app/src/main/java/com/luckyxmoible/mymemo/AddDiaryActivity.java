package com.luckyxmoible.mymemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddDiaryActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary_activity);
        Button button = (Button)findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //将数据写入数据库
                new AsyncTask<Void, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        EditText et = (EditText)findViewById(R.id.edit_text_blank);
                        String data = et.getText().toString();
                        Log.e("hello",data);
                        DiaryDatabaseAccessor
                                .getInstance(getApplication()).diaryDAO().insertDiary(new Diary("今天天气不错"));
                        return true;
                    }
                }.execute();
            }
        });
    }


}
