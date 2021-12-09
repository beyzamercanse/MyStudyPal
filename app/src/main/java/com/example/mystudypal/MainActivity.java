package com.example.mystudypal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

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

        binding.startBtn.setOnClickListener(v -> timer(25));
        binding.shortbrkBtn.setOnClickListener(v -> goToShortBreak());
        binding.longbrkBtn.setOnClickListener(v -> goToLongBreak());

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

    private void updateTimeDisplay() {
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

    private void goToShortBreak() {
        Intent intent = new Intent(this, ShortBreak.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToLongBreak() {
        Intent intent = new Intent(this, LongBreak.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

//    @SuppressLint("ResourceType")
//    public void startShortBreak() {
//        View view = this.getWindow().getDecorView();
//        view.setBackgroundColor(getResources().getColor(R.color.background_green));
//        binding.startBtn.setTextColor(getResources().getColor(R.color.dark_green));
//        binding.reportBtn.setBackgroundColor(getResources().getColor(R.color.light_green));
//        binding.settingsBtn.setBackgroundColor(getResources().getColor(R.color.light_green));
//        binding.frameLayout.setBackgroundColor(getResources().getColor(R.color.light_green));
//        binding.pomodoroBtn.setBackgroundColor(getResources().getColor(R.color.light_green));
//        binding.timer.setText("05:00");
//        binding.shortbrkBtn.setBackgroundColor(getResources().getColor(R.color.dark_green));
//        binding.longbrkBtn.setBackgroundColor(getResources().getColor(R.color.light_green));
//
//        timer(5);
//
//    }

}