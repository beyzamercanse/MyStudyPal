package com.example.mystudypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.mystudypal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private long timeLeft;
    private CountDownTimer pomoTimer;
    private boolean timerOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pomodoroBtn.setOnClickListener(v -> timer(25));


    }

    private void timer(int timerMins) {
        timeLeft = timerMins * 60000; // have to use milliseconds so multiply time by 60000
        pomoTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimeDisplay();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void updateTimeDisplay() {
        int displayMins = (int) (timeLeft / 60000);
        int displaySeconds = (int) ((timeLeft % 60000)/ 1000);

        String displayTime;

        displayTime = "" + displayMins;
        displayTime += ":";
        if (displaySeconds < 10) { displayTime += "0"; }
        displayTime += displaySeconds;

        binding.timer.setText(displayTime);
    }
}