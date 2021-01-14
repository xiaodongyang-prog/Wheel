package com.sing.wheel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alliances.yutils.view.LoadingButton;

import java.util.Timer;
import java.util.TimerTask;

public class TestActivity extends Activity {
    LoadingButton loadingButton;
    private int i = 0;
    Timer timer = new Timer();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        loadingButton = findViewById(R.id.loading_button);

        final Handler handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (i >= 100) {
                    timer.cancel();
                    return;
                }
                i = i + 1;
                loadingButton.setProgress(i);

            }
        };
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(1);
            }
        };

        timer.schedule(timerTask, 100, 50);
    }
}
