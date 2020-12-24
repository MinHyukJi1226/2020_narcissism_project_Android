package org.techtown.narcissism_android;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private String question;
    private TextView title;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        layout = findViewById(R.id.detailLayout);
        title = findViewById(R.id.titleTextView);
        back = findViewById(R.id.backBnt);

        Intent intent = getIntent();
        id = intent.getIntExtra("questionId", 1);
        question = intent.getStringExtra("question");
        Log.i("question", question);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        title.setTextColor(Color.parseColor("#ffffff"));
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
        if(question!=null){
            title.setText(question);
        }
        for(int i=0; i < result.size(); i++) {
            QuestionResponse item = result.get(i);

            if (item.type == 1) {
                TextView newText = new TextView(this);
                newText.setText(item.content);
                newText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                title.setLineSpacing(TypedValue.COMPLEX_UNIT_DIP, 1);
                layout.addView(newText);
            }
            else if(item.type == 2){
                ImageView img = new ImageView (this);
                img.setAdjustViewBounds(true);
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
