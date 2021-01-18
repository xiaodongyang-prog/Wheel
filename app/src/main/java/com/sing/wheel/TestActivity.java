package com.sing.wheel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

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

        getApkUpdate();

        loadingButton = findViewById(R.id.loading_button);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }

    /**
     */
    public void getApkUpdate() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final View updateView = View.inflate(this,R.layout.activity_update_layout , null);
        Button finish = updateView.findViewById(R.id.apk_update_finish);
        final LoadingButton loadingButton = updateView.findViewById(R.id.loading_button);
        alertDialog
                .setView(updateView)
                .create();
        final AlertDialog show = alertDialog.show();
        show.setCanceledOnTouchOutside(false);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
            }

        });
        updateView.findViewById(R.id.apk_update_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView.findViewById(R.id.apk_update_sure).setVisibility(View.GONE);
                loadingButton.setVisibility(View.VISIBLE);

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
                timer.schedule(timerTask, 100, 100);
            }
        });
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
