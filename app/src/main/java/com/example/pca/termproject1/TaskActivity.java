package com.example.pca.termproject1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TaskActivity extends Fragment {
    //시작 버튼이 얼마나 눌렸는지 확인할 값
    private int btnCount = 0;
    private View rootView;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private Button createTaskBtn;
    private EditText taskEditText;
    String s = "";

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_task,container,false);

        hour_textView = (TextView) rootView.findViewById(R.id.hourText);
        minute_textView = (TextView) rootView.findViewById(R.id.minuteText);
        second_textView = (TextView) rootView.findViewById(R.id.secondText);
        startBtn = (TextView) rootView.findViewById(R.id.startBtn);
        stopBtn = (TextView) rootView.findViewById(R.id.stopBtn);
        resetBtn = (TextView) rootView.findViewById(R.id.resetBtn);
        createTaskBtn = (Button) rootView.findViewById(R.id.createTask);


        //리스트뷰 정의
        arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1);
        listView = (ListView) rootView.findViewById(R.id.taskListView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });



        createTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == createTaskBtn){
                    Context context = getContext().getApplicationContext();
                    LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View layout = inflater.inflate(R.layout.activity_pop_up,(ViewGroup)rootView.findViewById(R.id.popup));
                    AlertDialog.Builder aDialog = new AlertDialog.Builder(getActivity());

                    aDialog.setTitle("할 일 추가");
                    aDialog.setView(layout);

                    //taskEditText값을 layout에서의 finviewbyid로해서 찾기!!! 중요
                    taskEditText = (EditText) layout.findViewById(R.id.taskName);

                    aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            arrayAdapter.add(taskEditText.getText().toString());
                        }
                    });
                    aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });


                    AlertDialog ad = aDialog.create();
                    ad.show();
                }
            }
        });


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
        textOnTouchEvent(startBtn);

        //정지 작업 수행
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCount=0;
                handler.removeMessages(0);
            }
        });
        textOnTouchEvent(stopBtn);

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
        textOnTouchEvent(resetBtn);

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

    //버튼이 눌렀을때 텍스트 색깔이 바뀌는 작업 수행
    public void textOnTouchEvent(final TextView textView){
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    if(textView.getClass()==view.getClass()){
                        textView.setTextColor(Color.RED);
                    }
                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(textView.getClass()==view.getClass()){
                        textView.setTextColor(Color.BLACK);
                    }
                }
                return false;
            }
        });
    }
}