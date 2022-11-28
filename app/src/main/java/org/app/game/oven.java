package org.app.game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class oven extends AppCompatActivity {
    Button buttonStart; //불 버튼
    TextView textView, textscore, textmission, texttime;
    ImageView oven; //오븐상태

    private ProgressBar bar; //온도 바
    public Integer success_count = 2;//평가점수 최댓값 2에서 감점하여 최종 값을 구함.
    public Integer timer_Count = 20; //미션 시간
    private CountDownTimer countDownTimer;

    int score =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oven);

        setTitle("오븐 굽기 게임");


        AlertDialog.Builder gamestart_dlg = new AlertDialog.Builder(oven.this);
        gamestart_dlg.setTitle("게임 시작");
        gamestart_dlg.setMessage("130도에서 170도 사이에서 구워주세요. 시작을 누르면 게임을 시작합니다.");
        gamestart_dlg.setPositiveButton("시작", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startCountDownTimer();
                timer_Start();
            }
        });
        gamestart_dlg.show();

        textView = (TextView) findViewById(R.id.textView);
        textscore = (TextView) findViewById(R.id.textscore);
        textmission = (TextView) findViewById(R.id.textmission);
        texttime = (TextView) findViewById(R.id.texttime);
        bar = findViewById(R.id.progressBar);
        buttonStart = findViewById(R.id.buttonStart);
        oven = (ImageView) findViewById(R.id.oven);

        buttonStart = (Button) findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar = (ProgressBar) findViewById(R.id.progressBar);
                textView = (TextView) findViewById(R.id.textView);

                int nowValue = bar.getProgress();
                int maxValue = bar.getMax();

                CharSequence str = "";

                if (maxValue == nowValue) { //온도 바 최대값 = 현재값
                    nowValue = 0;
                } else {

                    bar.setVisibility(View.VISIBLE);
                    nowValue += 5;
                    if (nowValue < 30) { //온도가 너무 낮으면 -1점
                        success_count--;
                        Toast.makeText(oven.this, "반죽이 충분히 익지 않고 있어요!", Toast.LENGTH_SHORT).show();
                        oven.setImageResource(R.drawable.startbread);
                    }
                    if (nowValue > 31) { //적당하면 감점 없음
                        oven.setImageResource(R.drawable.ingbread);
                    }
                    if (nowValue > 70) { //너무 높으면 -1점
                        success_count--;
                        Toast.makeText(oven.this, "반죽이 탈 것 같아요!", Toast.LENGTH_SHORT).show();
                        oven.setImageResource(R.drawable.tadabread);
                    }
                }
                str = "버튼을 터치하며 오븐 온도를 조절하세요." + "[" + 1+ nowValue + "]";


                bar.setProgress(nowValue);
                textView.setText(str);

            }
        });

    }

    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {//30초 카운트다운
            @Override
            public void onTick(long millisUntilFinished) {
                bar.setProgress(25+(int) (millisUntilFinished / 2000));
            }

            @Override
            public void onFinish() {
                textView.setText("시간이 다 되었어요!"); //만약 0이 되면 나올 말
            }
        }.start();
        countDownTimer.start();
    }

    public void timer_Start() {
        timer_Count=20;

        final Timer timer=new Timer();

        final TimerTask tt=new TimerTask() {
            @Override
            public void run() {
                timer_Count--;

                oven.this.runOnUiThread(new Runnable() {//타이머에서 ui를 수정해야 할경우 runOnUiThread를 사용해야함
                    @Override
                    public void run() {
                        if(timer_Count<0) {//타이머가 0 미만이 될 경우
                            timer.cancel();

                            AlertDialog.Builder gameover_dlg=new AlertDialog.Builder(oven.this);
                            gameover_dlg.setTitle("게임 종료");
                            if(success_count<=0){
                                gameover_dlg.setMessage("Bad");
                                score =1;
                                log2.updateUsers5(0,0,1,0,0);
                            }
                            else if(success_count==1){
                                gameover_dlg.setMessage("Good");
                                score =2;
                                log2.updateUsers5(0,0,2,0,0);
                            }
                            else if(success_count==2){
                                gameover_dlg.setMessage("Perfect!");
                                score =3;
                                log2.updateUsers5(0,0,3,0,0);
                            }
                            gameover_dlg.setPositiveButton("다음 단계로", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(oven.this, stacking.class);
                                    startActivity(intent);
                                }
                            });
                            gameover_dlg.show();
                        }
                        else
                        {
                            texttime.setText("남은 시간 : " + timer_Count.toString());
                        }
                    }
                });

            }
        };

        timer.schedule(tt,0,1000);
    }}
