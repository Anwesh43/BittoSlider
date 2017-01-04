package com.anwesome.ui.bittysliderdemo;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.anwesome.ui.bittoslider.BittoSliderView;
import com.anwesome.ui.bittoslider.SliderOnChangeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final TextView textView = (TextView)findViewById(R.id.slider_value);
        BittoSliderView sliderView = (BittoSliderView)findViewById(R.id.slider);
        sliderView.setOnChangeListener(new SliderOnChangeListener() {
            @Override
            public void onChange(float value) {
                textView.setText(""+(int)Math.floor(value));
            }
        });
    }

}
