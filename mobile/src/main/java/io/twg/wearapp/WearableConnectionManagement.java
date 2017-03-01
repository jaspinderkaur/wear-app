package io.twg.wearapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

public class WearableConnectionManagement implements
        OnLifeCycleChangeListener,
        GoogleApiClient.ConnectionCallbacks,
        CapabilityApi.CapabilityListener {

    private static final String TAG = WearableConnectionManagement.class.getSimpleName();

    private Context mContext;
    private GoogleApiClient mGoogleApiClient;

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

    public void sendMessageToWearable(String value) {
        PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(mContext.getString(R.string.path_wearable_data));
        putDataMapRequest.getDataMap().putString(mContext.getString(R.string.data), value);
        PutDataRequest request = putDataMapRequest.asPutDataRequest();

        PendingResult<DataApi.DataItemResult> pendingResult =
                Wearable.DataApi.putDataItem(mGoogleApiClient, request);

        pendingResult.setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
            @Override
            public void onResult(DataApi.DataItemResult dataItemResult) {
                if (dataItemResult.getStatus().isSuccess()) {
                    Log.v(TAG, "Data sent successfully");
                } else {
                    Log.v(TAG, "Failed");
                }
            }
        });
    }

}
