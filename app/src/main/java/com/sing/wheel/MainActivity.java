package com.sing.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alliances.yutils.wheelview.LoopView;
import com.alliances.yutils.wheelview.WheelView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoopView loopView = findViewById(R.id.wheelView_loopview);
        loopView.setItems(Arrays.asList(new String[]{"One","Two"}));


        WheelView wheelView = (WheelView) findViewById(R.id.wheelView_);
        wheelView.setOffset(1);
        wheelView.setItems(Arrays.asList(new String[]{"One","Two"}));
    }
}
