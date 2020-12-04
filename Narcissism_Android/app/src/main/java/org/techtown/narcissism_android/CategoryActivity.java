package org.techtown.narcissism_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerAdapter adapter = new RecyclerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        TextView category = findViewById(R.id.categoryId);

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra("categoryId", -1);
        switch (categoryId){
            case 1:{
                category.setText("청소");
                break;
            }
            case 2:{
                category.setText("분리수거");
                break;
            }
            case 3:{
                category.setText("의류");
                break;
            }
            case 4:{
                category.setText("인테리어");
                break;
            }
            case 5:{
                category.setText("팁");
                break;
            }
        }
        init();
        getData();
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }

    private void getData(){

        List<String> listTitle = Arrays.asList("d","d","d","d","d","d","d");
        List<String> listContent = Arrays.asList("d","d","d","d","d","d","d");
        List<Integer> listImage = Arrays.asList();

        for(int i = 0; i < listTitle.size(); i++){
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
//            data.setImage(listImage.get(i));

            adapter.addItem(data);
        }
        adapter.notifyDataSetChanged();
    }
}