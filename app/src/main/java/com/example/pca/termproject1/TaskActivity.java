package com.example.pca.termproject1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TaskActivity extends Fragment {
    //시작 버튼이 얼마나 눌렸는지 확인할 값
    private int btnCount = 0;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_task,container,false);

        hour_textView = (TextView) rootView.findViewById(R.id.hourText);
        minute_textView = (TextView) rootView.findViewById(R.id.minuteText);
        second_textView = (TextView) rootView.findViewById(R.id.secondText);
        startBtn = (TextView) rootView.findViewById(R.id.startBtn);
        stopBtn = (TextView) rootView.findViewById(R.id.stopBtn);
        resetBtn = (TextView) rootView.findViewById(R.id.resetBtn);

        //시작 작업 수행
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnCount == 0) {
                    btnCount++;
                    //sendEmptyMessage 메소드로 빈 메세지를 보냄으로서 호출된다.
                    handler.sendEmptyMessage(0);
                }
            }
        });

        //정지 작업 수행
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCount=0;
                handler.removeMessages(0);
            }
        });

        //초기화 작업 수행
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCount = 0;
                second_value = 0;
                minute_value = 0;
                hour_value = 0;
                second_textView.setText("00");
                minute_textView.setText("00");
                hour_textView.setText("00");
            }
        });

        return rootView;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //매 1초 마다 증가할 정수값
    private int hour_value = 0;
    private int minute_value = 0;
    private int second_value = 0;
    private TextView hour_textView = null;
    private TextView minute_textView = null;
    private TextView second_textView = null;
    private TextView startBtn;
    private TextView stopBtn;
    private TextView resetBtn;

    //타이머를 처리하기 위해 핸들러 객체 생성
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(second_value >= 60){
                second_value = 0;
                minute_value++;
            }
            if(minute_value >= 60){
                minute_value = 0;
                hour_value++;
            }

            //초에 대한 textView값 처리
            if(second_value < 10) {
                second_textView.setText("0" + second_value);
            } else {
                second_textView.setText("" + second_value);
            }
            if(minute_value < 10) {
                minute_textView.setText("0" + minute_value);
            } else {
                minute_textView.setText("" + minute_value);
            }
            if(hour_value < 10) {
                hour_textView.setText("0" + hour_value);
            } else {
                hour_textView.setText("" + hour_value);
            }

            second_value++;

            handler.sendEmptyMessageDelayed(0, 1000);
            //1초간의 지연 시간을 두어 1초후에 자기자신이 호출 되도록 한다.
        }
    };
}