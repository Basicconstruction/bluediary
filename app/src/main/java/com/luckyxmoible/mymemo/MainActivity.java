package com.luckyxmoible.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_PREFERENCES = Menu.FIRST+1;
    private static final int SHOW_PREFERENCES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        final Observer<List<Diary>> diaryObserver = new Observer<List<Diary>>(){
            @Override
            public void onChanged(@Nullable final List<Diary> updatedDiary){

                需要扩展以根据数据库内容更新ui

            }
        };

        LiveData diaryLiveData =
                DiaryDatabaseAccessor.getInstance(getApplicationContext()).diaryDAO().monitorAllDiaries();
        diaryLiveData.observe(this,diaryObserver);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,MENU_PREFERENCES,Menu.NONE,R.string.menu_settings);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case MENU_PREFERENCES:
                Intent intent = new Intent(this,PreferencesActivity.class);
                startActivityForResult(intent,SHOW_PREFERENCES);
                return true;
        }
        return false;
    }


}