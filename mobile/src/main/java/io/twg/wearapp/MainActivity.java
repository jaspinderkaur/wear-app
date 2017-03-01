package io.twg.wearapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private TextView mTextView;

    private WearableConnectionManagement mWearableConnectionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to wearable
        mWearableConnectionManagement = new WearableConnectionManagement(this);

        mTextView=(TextView)findViewById(R.id.text);
        mRadioGroup=(RadioGroup)findViewById(R.id.radio_group);
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

    public void setColor(View view) {
        int selectedId=mRadioGroup.getCheckedRadioButtonId();
        RadioButton radioButton =(RadioButton)findViewById(selectedId);

        String selectedColor = radioButton.getTag().toString();
        mWearableConnectionManagement.sendMessageToWearable(selectedColor);

        mTextView.setTextColor(Color.parseColor(selectedColor));
    }
}
