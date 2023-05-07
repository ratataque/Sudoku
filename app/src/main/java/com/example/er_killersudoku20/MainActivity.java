package com.example.er_killersudoku20;

import static com.example.er_killersudoku20.R.layout.activity_main;
import static com.example.er_killersudoku20.R.layout.activity_second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button easybtn, mediumbtn, hardbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        easybtn = (Button) findViewById(R.id.easybtn);
        mediumbtn = (Button) findViewById(R.id.mediumbtn);
        hardbtn = (Button) findViewById(R.id.hardbtn);

        easybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commencerLeJeu();
                GameView.gamelevel = GameLevel.EASY;
            }
        });

        mediumbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commencerLeJeu();
                GameView.gamelevel = GameLevel.MEDIUM;
            }
        });

        hardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commencerLeJeu();
                GameView.gamelevel = GameLevel.HARD;
            }
        });
    }

    private void commencerLeJeu() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}