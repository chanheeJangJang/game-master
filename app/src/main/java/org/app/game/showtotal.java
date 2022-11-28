package org.app.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class showtotal extends AppCompatActivity { //점수에 따라 이미지 변화 + 텍스트 뷰

    TextView text1;
    Button btn1, btn2;

    int version = 1;
    static log2.DatabaseOpenHelper helper;
    static SQLiteDatabase database;
    String sql;
    Cursor cursor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtotal);

        text1 = (TextView) findViewById(R.id.textView12);
        btn1 = (Button) findViewById(R.id.button6);
        btn2 = (Button) findViewById(R.id.button7);

        String t1 = log2.getg1() +" ";
        String t2 = log2.getg2() +" ";
        String t3 = log2.getg3() +" ";
        String t4 = log2.getg4() +" ";
        String t5 = log2.getg5() +" ";
        String t6 = log2.gettotal() +" ";

        String t = t1+t2+t3+t4+t5+t6;

        text1.setText(t);




        btn1.setOnClickListener(new View.OnClickListener() {  //단계 선택창으로
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(showtotal.this, smallStep.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(showtotal.this, story2.class);
                startActivity(intent);
            }
        });

    }
}