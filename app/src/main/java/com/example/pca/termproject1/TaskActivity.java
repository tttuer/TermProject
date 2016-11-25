package com.example.pca.termproject1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TaskActivity extends Activity {
    //시작 버튼이 얼마나 눌렸는지 확인할 값
    private int btnCount = 0;
    private TextView btnCountText = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        textView = (TextView) findViewById(R.id.TextView);
        startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnCount == 0) {
                    btnCount++;
                    //sendEmptyMessage 메소드로 빈 메세지를 보냄으로서 호출된다.
                    handler.sendEmptyMessage(0);
                }
            }
        }
        );

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCount=0;
                handler.removeMessages(0);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCount = 0;
                value = 0;
                textView.setText("Value = " + value);
            }
        });
    }

    //매 1초 마다 증가할 정수값
    private int value = 0;
    private TextView textView = null;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    //타이머를 처리하기 위해 핸들러 객체 생성
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            textView.setText("Value = " + value);
            value++;
            handler.sendEmptyMessageDelayed(0, 1000);
            //1초간의 지연 시간을 두어 1초후에 자기자신이 호출 되도록 한다.
        }
    };
}