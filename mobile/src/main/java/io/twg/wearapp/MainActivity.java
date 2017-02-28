package io.twg.wearapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mSendMessage;

    private WearableConnectionManagement mWearableConnectionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to wearable
        mWearableConnectionManagement = new WearableConnectionManagement(this);

        mSendMessage = (TextView) findViewById(R.id.tv_send_message);
        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWearableConnectionManagement.sendMessageToWearable("Message", "Its time to smile");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mWearableConnectionManagement.onControllerPaused();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWearableConnectionManagement.onControllerResumed();
    }
}
