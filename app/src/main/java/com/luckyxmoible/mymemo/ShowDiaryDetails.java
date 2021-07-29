package com.luckyxmoible.mymemo;

import android.os.Bundle;
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
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_details_activity);
        TextView title = (TextView)findViewById(R.id.title_det);
        title.setText(diary.title);
        TextView content = (TextView)findViewById(R.id.content_det);
        content.setText(diary.textContent);
        TextView place = (TextView)findViewById(R.id.loc_det);
        place.setText(diary.getLocation());
        TextView time = (TextView)findViewById(R.id.time_det);
        time.setText(diary.getTimeInfo());
    }
}
