package com.example.localnotifyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText hour;
    EditText min;
    EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hour = (EditText) findViewById(R.id.txt_hour);
        min = (EditText) findViewById(R.id.txt_min);
        msg = (EditText) findViewById(R.id.txt_msg);

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clear();
            }
        });


        findViewById(R.id.btn_schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Schedule();
            }
        });
    }

    void Clear() {
        LocalNotificationUtils.clearLocalNotification(getApplicationContext());
    }

    void Schedule() {
        Clear();

        String textMsg = msg.getText().toString();
        int hourData = (int)Float.parseFloat(hour.getText().toString());
        int minData = (int)Float.parseFloat(min.getText().toString());
        LocalNotificationUtils.MORNING_NOTIFICATION_TIME_HOUR = hourData;
        LocalNotificationUtils.MORNING_NOTIFICATION_TIME_MINUTE = minData;

        LocalNotificationUtils.scheduleLocalNotification(getApplicationContext(), "", textMsg);
    }
}
