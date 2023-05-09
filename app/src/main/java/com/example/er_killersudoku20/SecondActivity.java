package com.example.er_killersudoku20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private ImageButton pencil, eraser;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.btn1).setOnClickListener(onClickListener);
        findViewById(R.id.btn2).setOnClickListener(onClickListener);
        findViewById(R.id.btn3).setOnClickListener(onClickListener);
        findViewById(R.id.btn4).setOnClickListener(onClickListener);
        findViewById(R.id.btn5).setOnClickListener(onClickListener);
        findViewById(R.id.btn6).setOnClickListener(onClickListener);
        findViewById(R.id.btn7).setOnClickListener(onClickListener);
        findViewById(R.id.btn8).setOnClickListener(onClickListener);
        findViewById(R.id.btn9).setOnClickListener(onClickListener);

        findViewById(R.id.pencilbtn).setOnClickListener(onClickListener);
        findViewById(R.id.eraserbtn).setOnClickListener(onClickListener);

        gameView = (GameView) findViewById(R.id.gameView);


    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn1:
                    if (gameView.brouillon) {
                        gameView.setNote(1);
                    }
                    else {
                        gameView.setValeurtest(1);
                    }
                    break;

                case R.id.btn2:
                    if (gameView.brouillon) {
                        gameView.setNote(2);
                    } else {
                        gameView.setValeurtest(2);
                    }
                    break;

                case R.id.btn3:
                    if (gameView.brouillon) {
                        gameView.setNote(3);
                    } else {
                        gameView.setValeurtest(3);
                    }
                    break;

                case R.id.btn4:
                    if (gameView.brouillon) {
                        gameView.setNote(4);
                    } else{
                        gameView.setValeurtest(4);
                    }
                    break;

                case R.id.btn5:
                    if (gameView.brouillon) {
                        gameView.setNote(5);
                    } else {
                        gameView.setValeurtest(5);
                    }
                    break;

                case R.id.btn6:
                    if (gameView.brouillon) {
                        gameView.setNote(6);
                    } else {
                        gameView.setValeurtest(6);
                    }
                    break;

                case R.id.btn7:
                    if (gameView.brouillon) {
                        gameView.setNote(7);
                    } else {
                        gameView.setValeurtest(7);
                    }
                    break;

                case R.id.btn8:
                    if (gameView.brouillon) {
                        gameView.setNote(8);
                    } else {
                        gameView.setValeurtest(8);
                    }
                    break;

                case R.id.btn9:
                    if (gameView.brouillon) {
                        gameView.setNote(9);
                    }
                    else {
                        gameView.setValeurtest(9);
                    }
                    break;

                case R.id.pencilbtn:
                    if (gameView.brouillon == false) {
                        gameView.brouillon = true;
                    }
                    else if (gameView.brouillon == true) {
                        gameView.brouillon = false;
                    }
                    break;

                case R.id.eraserbtn:
                    gameView.eraseValue();
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };
}