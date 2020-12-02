package org.techtown.narcissism_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String categoryName = "청소";     //나중에 서버에서 값을 받아서 바꾸는 거로 교체

        RecyclerView recyclerView;

        TextView categoryId = findViewById(R.id.categoryId);

        categoryId.setText(categoryName);   //서버에서 값을 받아와 바꾼다.
    }

}