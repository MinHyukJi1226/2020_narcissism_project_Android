package org.techtown.narcissism_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.techtown.narcissism_android.data.CategoryResponse;
import org.techtown.narcissism_android.data.QuestionResponse;
import org.techtown.narcissism_android.net.Server;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity implements ClickListener{

    private RecyclerAdapter adapter = new RecyclerAdapter();
    private Call<List<CategoryResponse>> request;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        TextView category = findViewById(R.id.categoryId);

        Intent intent = getIntent();
        id = intent.getIntExtra("categoryId", 1);

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
        getCategories();
    }

    private void getCategories(){
        request = Server.getInstance().getApi().getCategory(id);
        request.enqueue((new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                if(response.code() == 200){
                    List<CategoryResponse> result = response.body();
                    getData(result);
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
            }
        }));
    }


    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void getData(List<CategoryResponse> categoryList){

        adapter.setListener(this);

        for(int i = 0; i < categoryList.size(); i++){
            Data data = new Data();
            data.setTitle(categoryList.get(i).question);
            data.setQuestionId(categoryList.get(i).questionId);
//            data.setContent(listContent.get(i));
//            data.setImage(listImage.get(i));
            adapter.addItem(data);
        }
        adapter.notifyDataSetChanged();
    }

    public void onItemClick(View view){
        int categoryId = view.getId();

    }

    @Override
    public void onClick(int questionId) {
        Intent intent = new Intent(getApplicationContext(), AnswerActivity.class);
        intent.putExtra("questionId",questionId);
        startActivity(intent);
    }
}