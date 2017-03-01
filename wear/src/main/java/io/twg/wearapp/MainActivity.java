package io.twg.wearapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.widget.TextView;

public class MainActivity extends WearableActivity implements
        DataListenerService.MessageListener {

    private WearableApplication mApplication;

    private BoxInsetLayout mContainerView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mApplication = (WearableApplication) getApplicationContext();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.text);

        //Get data when activity launches
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String message = extras.getString(getString(R.string.data));
            configureUI(message);
        }

        //Set listener to receive updates
        DataListenerService.setListener(this);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    @Override
    public void receiveMessage(final String message) {
        configureUI(message);
    }

    private void configureUI(final String message) {
        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setTextColor(Color.parseColor(message));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mApplication.setMainViewLaunched(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        mApplication.setMainViewLaunched(false);
    }

}
