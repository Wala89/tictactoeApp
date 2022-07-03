package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    // 0: gelb - 1: rot - 2: leer
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappCounter] == 2 && gameRunning) {
            gameState[tappCounter] = activePlayer;

            counter.setTranslationY(-1000);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000).rotation(3600).setDuration(500);
            checkWinner(view);
        }
    }

    public void checkWinner(View view) {
        for (int[] wP: winningPositions) {
            if (gameState[wP[0]] == gameState[wP[1]] && gameState[wP[1]] == gameState[wP[2]] && gameState[wP[1]] != 2) {
                String winner = "";

                if (activePlayer == 1) {
                    winner = "Gelb";
                } else {
                    winner = "Rot";
                }

                Toast.makeText(this, "Winner: " + winner, Toast.LENGTH_LONG).show();
                Log.i("WINNER", "WINNER");

                gameRunning = false;

                Button playAgain = findViewById(R.id.button);
                TextView winnerText = findViewById(R.id.textView2);

                winnerText.setText("Winner = " + winner);
                winnerText.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);

                androidx.gridlayout.widget.GridLayout myGrid = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);

            }
        }
    }

    public void resett(View view) {
        Button playAgain = findViewById(R.id.button);
        TextView winnerText = findViewById(R.id.textView2);

        winnerText.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout myGrid = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < myGrid.getChildCount(); i++) {
            ImageView iV = (ImageView) myGrid.getChildAt(i);
            iV.setImageDrawable(null);
            gameState[i] = 2;
        }

        gameRunning = true;
        activePlayer = 0;
    }
}