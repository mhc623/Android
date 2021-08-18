package com.example.game2048;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView tv_score;

    //private com.example.game2048.view.GameView mGameView;

    private static GameActivity gameActivity = null;

    private int score = 0;

    public GameActivity() {
        gameActivity = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tv_score = (TextView)findViewById(R.id.tv_score);
    }

    public static GameActivity getGameActivity() {
        return gameActivity;
    }

    public static void setGameActivity(GameActivity gameActivity) {

        GameActivity.gameActivity = gameActivity;
    }

    public void clearScore() {
        score = 0;
        showScore();
    }

    public void showScore() {
        tv_score.setText("分数：" + score);
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }
}
