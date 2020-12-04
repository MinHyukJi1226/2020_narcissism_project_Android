package org.techtown.narcissism_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.techtown.narcissism_android.data.QuestionResponse;
import org.techtown.narcissism_android.net.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswerActivity extends AppCompatActivity {

    private LinearLayout layout;
    private Call<List<QuestionResponse>> request;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        layout = findViewById(R.id.detailLayout);

        Intent intent = getIntent();
        id = intent.getIntExtra("questionId", 1);

        getQuestions();
    }
    private void getQuestions(){
        request = Server.getInstance().getApi().getAnswer(id);
        request.enqueue((new Callback<List<QuestionResponse>>() {
            @Override
            public void onResponse(Call<List<QuestionResponse>> call, Response<List<QuestionResponse>> response) {

                if(response.code() == 200){
                    List<QuestionResponse> result = response.body();

                    updateWidget(result);
                }
            }

            @Override
            public void onFailure(Call<List<QuestionResponse>> call, Throwable t) {

            }
        }));
    }

    private void updateWidget(List<QuestionResponse> result){
        for(int i=0; i < result.size(); i++) {
            QuestionResponse item = result.get(i);

            if (item.type == 1) {
                TextView newText = new TextView(this);
                newText.setText(item.content);
                layout.addView(newText);
            }
            else if(item.type == 2){
                ImageView img = new ImageView(this);
                Glide.with(this).load(item.content).into(img);
                layout.addView(img);
            }
            else{
                TextView newText = new TextView(this);
                newText.setText("데이터가 없습니다.");
                layout.addView(newText);
            }
        }
    }
}
