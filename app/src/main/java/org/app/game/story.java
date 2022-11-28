package org.app.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class story extends AppCompatActivity {
    ImageView img1, img2, img3, img4, img5, img6, img7;
    TextView text1;
    Button btn1;

    int count =0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        setTitle("BAKING PAPA");

        img1 = (ImageView) findViewById(R.id.imageView5);
        img2 = (ImageView) findViewById(R.id.imageView6);
        img3 = (ImageView) findViewById(R.id.imageView7);
        img4 = (ImageView) findViewById(R.id.imageView8);
        img5 = (ImageView) findViewById(R.id.imageView9);
        img6 = (ImageView) findViewById(R.id.imageView10);
        img7 = (ImageView) findViewById(R.id.imageView11);
        text1 = (TextView) findViewById(R.id.textView11);
        btn1 = (Button) findViewById(R.id.button5);


        img2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        img4.setVisibility(View.INVISIBLE);
        img5.setVisibility(View.INVISIBLE);
        img6.setVisibility(View.INVISIBLE);
        img7.setVisibility(View.INVISIBLE);

//첫번째에 tv자막 보여주는 이미지

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count ==0){
                    text1.setText("아빠 :\n바쁜 스케출 다니며 방송활동 요리활동 하느라 늘 고맙고 많이 못 쉬는 것 같아서 미안하네 .... 허허허");
                    img2.setVisibility(View.VISIBLE); //두번째에 아버지 사진 등장
                    img1.setVisibility(View.INVISIBLE);
                    text1.setBackgroundColor(Color.GRAY);
                    count+=1;
                }
                else if(count ==1){
                    img3.setVisibility(View.VISIBLE);  //티비광고
                    img2.setVisibility(View.INVISIBLE);
                    text1.setBackgroundColor(Color.WHITE);
                    text1.setText(" ");
                    count+=1;
                }
                else if(count ==2){
                    img4.setVisibility(View.VISIBLE); //아버지의 회상
                    img3.setVisibility(View.INVISIBLE);
                    text1.setBackgroundColor(Color.GRAY);
                    text1.setText("아빠 :\n헉!!! 베이킹 서바이벌?! 맞아, 난 어렸을때 잠시 파티시에를 꿈 꾸었지.... 이 아빠는 베이킹을 좋아해!!!");
                    count+=1;
                }
                else if(count ==3){
                    img5.setVisibility(View.VISIBLE);  //아빠의 결심 아들 호출
                    img4.setVisibility(View.INVISIBLE);
                    text1.setText("아빠 :\n늦었지만 저 프로에 나가서 내 꿈을 도전해야겠어!! 우선 연습을 해야겠다. 아들! 뭐 먹고 싶어?");
                    count+=1;
                }
                else if(count ==4){
                    img6.setVisibility(View.VISIBLE);  //아들 등장
                    img5.setVisibility(View.INVISIBLE);
                    text1.setText("아들 :\n음..... 딸기케이크요!!!");
                    count+=1;
                }
                else if(count ==5){
                    img7.setVisibility(View.VISIBLE);  //아빠 등장
                    img6.setVisibility(View.INVISIBLE);
                    text1.setText("아빠 :\n좋아! 곧 생일인 아들에게 직접 케이크를 만들어주면서 맛 평가도 부탁해볼까?");
                    count+=1;
                    btn1.setText("게임 시작!");
                }
                else if(count ==6){
                    Intent intent = new Intent(getApplicationContext(), sub.class);
                    startActivity(intent);

                }


            }
        });



    }
}