package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddDiaryActivity extends AppCompatActivity {
    private final int MENU_EMPTY = 3;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary_activity);
        //String title = "写日记";
        //this.getSupportActionBar().setTitle(title);
        Toolbar toolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        TextView time_tv = (TextView)findViewById(R.id.time);
        time_tv.setText(Diary.getDateTime());
        Button button = (Button)findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener(){
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
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(1,MENU_EMPTY,0,R.string.empty_diary);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case MENU_EMPTY:
                empty_content();
                break;
            default:
        }
        return true;

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

}
