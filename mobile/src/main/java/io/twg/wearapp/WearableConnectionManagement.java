package io.twg.wearapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

public class WearableConnectionManagement implements
        OnLifeCycleChangeListener,
        GoogleApiClient.ConnectionCallbacks,
        CapabilityApi.CapabilityListener {

    private static final String TAG = WearableConnectionManagement.class.getSimpleName();
    public final static String DATA_PATH_TO_WEARABLE = "/vehicle-sensors";
    public final static String DATA_PATH_TO_HANDHELD = "/wearable-sensors";
    public static final String CAPABILITY_1 = "capability_node_connected";

    private Context mContext;
    private GoogleApiClient mGoogleApiClient;
    private List<String> nodeList = new ArrayList<>();

    public WearableConnectionManagement(@NonNull Context context) {
        mContext = context;
        initDataLayer();
    }

    private void initDataLayer() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addApi(Wearable.API) // Request access only to the Wearable API
                .build();
    }

    //region OnLifeCycleChangeListener callbacks
    @Override
    public void onControllerPaused() {
        if ((mGoogleApiClient != null) && (mGoogleApiClient.isConnected())) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onControllerResumed() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        } else {
            initDataLayer();
        }
    }
    //endregion

    //region ConnectionCallbacks
    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onCapabilityChanged(CapabilityInfo capabilityInfo) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    //endregion

    public void sendMessageToWearable(String sensorName, String sensorValue) {
        PutDataMapRequest dataMap = PutDataMapRequest.create(DATA_PATH_TO_WEARABLE);
        dataMap.getDataMap().putString(sensorName, sensorValue); //Has to be a different value to call DataChanged
        PutDataRequest request = dataMap.asPutDataRequest();
        request.setUrgent();
        Wearable.DataApi.putDataItem(mGoogleApiClient, request)
                .setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                    @Override
                    public void onResult(DataApi.DataItemResult dataItemResult) {
                        if (dataItemResult.getStatus().isSuccess()) {
                            Log.v(TAG, "Data sent successfully");
                        }else{
                            Log.v(TAG, "Failed");
                        }
                    }
                });
    }

}
