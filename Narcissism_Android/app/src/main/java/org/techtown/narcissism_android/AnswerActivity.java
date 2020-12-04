package org.techtown.narcissism_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        layout = findViewById(R.id.detailLayout);

        Intent intent = getIntent();
        id = intent.getIntExtra("questionId", 1);

        text = findViewById(R.id.textView3);

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

    }
}
