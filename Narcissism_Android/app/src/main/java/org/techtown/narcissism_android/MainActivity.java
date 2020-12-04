package org.techtown.narcissism_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonClean = (Button) findViewById(R.id.buttonClean);
        Button buttonRecycle = (Button) findViewById(R.id.buttonRecycle);
        Button buttonClothes = (Button) findViewById(R.id.buttonClothes);
        Button buttonInterior = (Button) findViewById(R.id.buttonInterior);
        Button buttonTip = (Button) findViewById(R.id.buttonTip);
        ImageView imageSearch = findViewById(R.id.imageSearch);
        ImageView imageOption = findViewById(R.id.imageOption);

        buttonClean.setOnClickListener(this);
        buttonRecycle.setOnClickListener(this);
        buttonClothes.setOnClickListener(this);
        buttonInterior.setOnClickListener(this);
        buttonTip.setOnClickListener(this);
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchingActivity.class);
                startActivity(intent);
            }
        });

        imageOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OptionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = 0;
        switch (view.getId()){
            case R.id.buttonClean: id = 1; break;
            case R.id.buttonRecycle: id = 2; break;
            case R.id.buttonClothes: id = 3; break;
            case R.id.buttonInterior: id = 4; break;
            case R.id.buttonTip: id = 5; break;
        }
        if(id > 0){
            Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
            intent.putExtra("categoryId", id);
            startActivity(intent);
        }
    }
}