package com.example.esacpedoctor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView hero, doctor, ingredient1, ingredient2;
    private TextView scoreText;
    private int heroX, heroY;
    private int doctorX, doctorY;
    private int ingredient1X, ingredient1Y;
    private int ingredient2X, ingredient2Y;
    private int score = 0;
    private boolean ingredient1Found = false;
    private boolean ingredient2Found = false;
    private final Handler handler = new Handler();
    private Runnable runnable;
    private final int DELAY = 16; // 60 FPS

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hero = findViewById(R.id.hero);
        doctor = findViewById(R.id.doctor);
        ingredient1 = findViewById(R.id.ingredient1);
        ingredient2 = findViewById(R.id.ingredient2);
        scoreText = findViewById(R.id.scoreText);

        heroX = (int) hero.getX();
        heroY = (int) hero.getY();
        doctorX = (int) doctor.getX();
        doctorY = (int) doctor.getY();
        ingredient1X = (int) ingredient1.getX();
        ingredient1Y = (int) ingredient1.getY();
        ingredient2X = (int) ingredient2.getX();
        ingredient2Y = (int) ingredient2.getY();

        findViewById(R.id.gameLayout).setOnTouchListener((v, event) -> {
            heroX = (int) event.getX() - hero.getWidth() / 2;
            heroY = (int) event.getY() - hero.getHeight() / 2;
            hero.setX(heroX);
            hero.setY(heroY);
            checkCollision();
            return true;
        });

        runnable = new Runnable() {
            @Override
            public void run() {
                moveDoctor();
                handler.postDelayed(this, DELAY);
            }
        };
        handler.postDelayed(runnable, DELAY);
    }

    private void moveDoctor() {
        if (doctorX < heroX) doctorX += 5;
        else if (doctorX > heroX) doctorX -= 5;
        if (doctorY < heroY) doctorY += 5;
        else if (doctorY > heroY) doctorY -= 5;

        doctor.setX(doctorX);
        doctor.setY(doctorY);

        if (Math.abs(doctorX - heroX) < 50 && Math.abs(doctorY - heroY) < 50) {
            gameOver();
        }
    }

    @SuppressLint("SetTextI18n")
    private void checkCollision() {
        if (!ingredient1Found && Math.abs(heroX - ingredient1X) < 50 && Math.abs(heroY - ingredient1Y) < 50) {
            ingredient1.setVisibility(View.INVISIBLE);
            ingredient1Found = true;
            score++;
            scoreText.setText("Ingredients Found: " + score);
        }
        if (!ingredient2Found && Math.abs(heroX - ingredient2X) < 50 && Math.abs(heroY - ingredient2Y) < 50) {
            ingredient2.setVisibility(View.INVISIBLE);
            ingredient2Found = true;
            score++;
            scoreText.setText("Ingredients Found: " + score);
        }

        if (score == 2) {
            gameWon();
        }
    }

    @SuppressLint("SetTextI18n")
    private void gameOver() {
        handler.removeCallbacks(runnable);
        scoreText.setText("Game Over! Doctor caught you.");
    }

    @SuppressLint("SetTextI18n")
    private void gameWon() {
        handler.removeCallbacks(runnable);
        scoreText.setText("You Win! Found all ingredients.");
    }
}