package com.sing.wheel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alliances.yutils.view.loading.LoadingButton;
import com.alliances.yutils.view.address.model.City;
import com.alliances.yutils.view.address.model.County;
import com.alliances.yutils.view.address.OnAddressSelectedListener;
import com.alliances.yutils.view.address.model.Province;
import com.alliances.yutils.view.address.model.Street;
import com.alliances.yutils.view.dialog.BottomDialog;

import java.util.Timer;
import java.util.TimerTask;

public class TestActivity extends AppCompatActivity {
    LoadingButton loadingButton;
    private int i = 0;
    Timer timer = new Timer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test_activity);

        loadingButton = findViewById(R.id.loading_button);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final BottomDialog dialog = new BottomDialog(TestActivity.this);
                dialog.setOnAddressSelectedListener(new OnAddressSelectedListener() {
                    @Override
                    public void onAddressSelected(Province province, City city, County county, Street street) {
                        Log.e("1" + 1, province.name + city.name + county.name);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                final float scale = getResources().getDisplayMetrics().density;
                int height = (int) (500 * scale + 0.5f);
//                CommonBottomSheetDialog bottomSheetDialog = new CommonBottomSheetDialog(TestActivity.this, height, height);
//                bottomSheetDialog.setContentView(view);
//                bottomSheetDialog.show();
            }
        });

    }

    /**
     *
     */
    public void getApkUpdate() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final View updateView = View.inflate(this, R.layout.activity_update_layout, null);
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
