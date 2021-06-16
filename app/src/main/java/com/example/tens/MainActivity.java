package com.example.tens;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Switch bt;
    BluetoothAdapter BAdapter;
    SeekBar current, P_rate, P_width;
    TextView textC, textPR, textPW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.bt);
        BAdapter = BluetoothAdapter.getDefaultAdapter();
        current = findViewById(R.id.current);
        P_rate = findViewById(R.id.P_rate);
        P_width = findViewById(R.id.P_width);
        textC = findViewById(R.id.textC);
        textPR = findViewById(R.id.textPR);
        textPW = findViewById(R.id.textPW);



        if(BAdapter.isEnabled()){
            bt.setChecked(true);
        }

        bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    BAdapter.enable();
                }
                else{
                    BAdapter.disable();
                }

            }
        });

        current.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textC.setText("Current: " + progress+" mA");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        P_rate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                int sum = 0;
                if(i<=9){
                    sum = i+1;
                }
                else if(i>= 17){
                    sum = (i-17)*10+50;
                }
                else{
                    sum = (i-9)*5+10;
                }
                textPR.setText("Pulse Rate: " + sum +" Hz");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        P_width.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                int sum = (i+1)*25;

                textPW.setText("Pulse Width: " + sum +" μs");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}