package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowDiaryDetails extends AppCompatActivity {
    private Diary diary;
    public ShowDiaryDetails(Diary diary){
        this.diary = diary;
    }
    public ShowDiaryDetails(){
        this.diary = RecyclerItemListener.diary;
        //这是一种绑定方法，但是这种绑定方法会有额外的一点开销，事实上还可以传递diaries在View holder中的索引，
        // 在这里的onCreate方法中直接引入diaries[position].属性
        //但是实际作用并不大，
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_details_activity);
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
                startActivity(new Intent(view.getContext(),MainActivity.class));
            }
        });
        LinearLayout image_det_lin = (LinearLayout)findViewById(R.id.image_det);
        for(Uri uri : diary.uris){
            image_det_lin.removeAllViews();
            if(uri!=null){
                ImageView imageView = new ImageView(this);
                //imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageView.setImageURI(uri);
                image_det_lin.addView(imageView);
            }
        }

    }
}
