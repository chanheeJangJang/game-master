package org.app.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class smallStep extends AppCompatActivity {

    ImageView imgback, imglock;
    ImageButton imgbtn1, imgbtn2;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_step);
        setTitle("BAKING PAPA");

        imgback = (ImageView) findViewById(R.id.imageView2);
        imglock = (ImageView) findViewById(R.id.imageView3);
        imgbtn1 = (ImageButton) findViewById(R.id.imageButton8);
        imgbtn2 = (ImageButton) findViewById(R.id.imageButton9);


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), story.class);
                startActivity(intent);
            }
        });
        


        if (log2.gettotal().equals("0")) {
            //그냥 토탈이 0이 아닐 경우를 위해서 걍 있는 구문임
        }
        else {//////////////일단 sub창으로 가게 했는데 다음 스토리 보드로 가게해야함
            imglock.setVisibility(View.INVISIBLE);
            imgbtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Intent intent = new Intent(getApplicationContext(), story2.class);
                    //startActivity(intent);
                }
            });
        }


    }
}