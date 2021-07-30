package com.luckyxmoible.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MENU_PREFERENCES = Menu.FIRST+1;
    private static final int SHOW_PREFERENCES = 1;
    public static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";
    DiaryListFragment mDiaryListFragment;
    DiaryViewModel diaryViewModel;
    public Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState==null){
            FragmentTransaction ft = fm.beginTransaction();

            mDiaryListFragment = new DiaryListFragment();
            //ft.add(R.id.main_activity_frame,mDiaryListFragment, TAG_LIST_FRAGMENT); //重复产生于这里
            //这条语句的作用是向main_activity_frame添加一个mDiaryListFragment，本来在recyclerView就包括了一个？
            ft.commitNow();
        }else{
            mDiaryListFragment = (DiaryListFragment)fm.findFragmentByTag(TAG_LIST_FRAGMENT);
        }
        diaryViewModel = ViewModelProviders.of(this).get(DiaryViewModel.class);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNewDiary(v);
            }

        });


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
    public void addNewDiary(View view){
        Intent intent = new Intent(this,AddDiaryActivity.class);
        startActivity(intent);

    }
    @Override
    public void onStart(){
        super.onStart();
    }
    //purple_500用于应用栏
    //purple_700用于手机状态栏

}