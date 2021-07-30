package com.luckyxmoible.mymemo;

import androidx.fragment.app.Fragment;

public class ImageListFragment extends Fragment {
    /*
    *
    设计模式：利用数组存储图片们的Uri，在添加的图片的Uri上添加删除listener，点击后在数组中删除这个图片的Uri。
    如何处理这个Uri数组，其一根据一定规律将数组Uri存储为字符串，并放入到数据库中。

    *
    <fragment android:name="com.luckyxmoible.mymemo.ImageListFragment"
        android:id="@+id/recyclerView"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@id/delete_button"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:paddingTop="5dp"
        android:clipToPadding="false"
        />

    *
    * */
}
