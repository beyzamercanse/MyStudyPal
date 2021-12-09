package com.example.mystudypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.mystudypal.databinding.ActivityShortBreakBinding;

public class ShortBreak extends AppCompatActivity {

    private ActivityShortBreakBinding binding;
    private long timeLeft;
    private CountDownTimer pomoTimer;
    private boolean timerOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShortBreakBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startBtn.setOnClickListener(v -> timer(5));
        binding.pomodoroBtn.setOnClickListener(v -> finish());
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

        displayTime = "";
        if (displayMins < 10) { displayTime += "0"; }
        displayTime += displayMins;
        displayTime += ":";
        if (displaySeconds < 10) { displayTime += "0"; }
        displayTime += displaySeconds;

        binding.timer.setText(displayTime);
    }
}