package org.app.game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class quiz extends AppCompatActivity {

    CheckBox chk1,chk2,chk3,chk4;
    Button trybtn, startbtn;
    ImageView imageView;
    TextView timertxt;
    public Integer timercnt=20, trynum=1, anscnt=0;
    int score =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        timertxt=(TextView)findViewById(R.id.timertxt) ;
        imageView=(ImageView)findViewById(R.id.imageView);
        trybtn=(Button)findViewById(R.id.trybtn);
        startbtn=(Button)findViewById(R.id.startbtn);
        chk1=(CheckBox)findViewById(R.id.chk1);
        chk2=(CheckBox)findViewById(R.id.chk2);
        chk3=(CheckBox)findViewById(R.id.chk3);
        chk4=(CheckBox)findViewById(R.id.chk4);

        imageView.setVisibility(View.INVISIBLE);
        chk1.setVisibility(View.INVISIBLE);
        chk2.setVisibility(View.INVISIBLE);
        chk3.setVisibility(View.INVISIBLE);
        chk4.setVisibility(View.INVISIBLE);
        trybtn.setVisibility(View.INVISIBLE);

        startbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timer_Start();
                imageView.setVisibility(View.VISIBLE);
                chk1.setVisibility(View.VISIBLE);
                chk2.setVisibility(View.VISIBLE);
                chk3.setVisibility(View.VISIBLE);
                chk4.setVisibility(View.VISIBLE);
                trybtn.setVisibility(View.VISIBLE);
                startbtn.setVisibility(View.INVISIBLE);
            }
        });

        trybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(trynum == 1){
                    if(chk1.isChecked()){
                        anscnt++;
                        imageView.setImageResource(R.drawable.sugar);
                        trynum++;
                        chk1.setChecked(false);
                    }
                    else{
                        chk2.setChecked(false);
                        chk3.setChecked(false);
                        chk4.setChecked(false);
                    }
                }
                if(trynum == 2){
                    if(chk3.isChecked()){
                        anscnt++;
                        imageView.setImageResource(R.drawable.cream);
                        trynum++;
                        chk3.setChecked(false);
                    }
                    else{
                        chk1.setChecked(false);
                        chk2.setChecked(false);
                        chk4.setChecked(false);
                    }
                }
                if(trynum == 3){
                    if(chk2.isChecked()){
                        anscnt++;
                        imageView.setImageResource(R.drawable.flour);
                        trynum++;
                        chk2.setChecked(false);
                    }
                    else{
                        chk1.setChecked(false);
                        chk3.setChecked(false);
                        chk4.setChecked(false);
                    }
                }
                if(trynum == 4){
                    if(chk1.isChecked()){
                        anscnt++;
                        imageView.setImageResource(R.drawable.salt);
                        trynum++;
                        chk1.setChecked(false);
                    }
                    else{
                        chk2.setChecked(false);
                        chk3.setChecked(false);
                        chk4.setChecked(false);
                    }
                }
                if(trynum == 5){
                    if(chk1.isChecked()){
                        anscnt++;
                        imageView.setImageResource(R.drawable.yeast);
                        trynum++;
                        chk1.setChecked(false);
                    }
                    else{
                        chk2.setChecked(false);
                        chk3.setChecked(false);
                        chk4.setChecked(false);
                    }
                }
                if(trynum == 6){
                    if(chk4.isChecked()){
                        anscnt++;
                        imageView.setImageResource(R.drawable.chocolate);
                        trynum++;
                        chk4.setChecked(false);
                    }
                    else{
                        chk1.setChecked(false);
                        chk2.setChecked(false);
                        chk3.setChecked(false);
                    }
                }
                if(trynum == 7){
                    if(chk4.isChecked()){
                        anscnt++;
                        imageView.setImageResource(R.drawable.egg1);
                        trynum++;
                        chk4.setChecked(false);
                    }
                    else{
                        chk1.setChecked(false);
                        chk2.setChecked(false);
                        chk3.setChecked(false);
                    }
                }
                if(trynum == 8){
                    if(chk1.isChecked()){
                        anscnt++;
                        imageView.setImageResource(R.drawable.finish);
                        trynum++;
                        chk1.setChecked(false);
                    }
                    else{
                        chk2.setChecked(false);
                        chk3.setChecked(false);
                        chk4.setChecked(false);
                    }
                }
                if(trynum == 9){
                    timercnt=0;
                }
            }
        });

    }
    public  void timer_Start()
    {
        timercnt=20;
        final Timer timer=new Timer();
        final TimerTask tt=new TimerTask() {

            public void run() {
                timercnt--;

                quiz.this.runOnUiThread(new Runnable() {

                    public void run() {
                        if(timercnt<0) {
                            timer.cancel();

                            AlertDialog.Builder gameover_dlg=new AlertDialog.Builder(quiz.this);
                            gameover_dlg.setTitle("게임 종료");
                            if(anscnt<=2){
                                score =1;///////////////////////////점수 부여
                                log2.updateUsers5(0,1,0,0,0);
                                gameover_dlg.setMessage("Bad");
                            }
                            else if(anscnt<=7){
                                score =2;
                                log2.updateUsers5(0,2,0,0,0);
                                gameover_dlg.setMessage("Good");
                            }
                            else if(anscnt==8){
                                score =3;
                                log2.updateUsers5(0,3,0,0,0);
                                gameover_dlg.setMessage("Perfect!");
                            }
                            gameover_dlg.setPositiveButton("다음 단계로", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(quiz.this, oven.class); ///////다음 게임으로
                                    startActivity(intent);
                                }
                            });
                            gameover_dlg.show();
                        }
                        else
                        {
                            timertxt.setText("남은 시간 : " + timercnt.toString());
                        }
                    }
                });

            }
        };

        timer.schedule(tt,0,1000);
    }
}
