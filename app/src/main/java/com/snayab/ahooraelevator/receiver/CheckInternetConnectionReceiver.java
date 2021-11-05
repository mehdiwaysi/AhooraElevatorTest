package com.snayab.ahooraelevator.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetConnectionReceiver extends BroadcastReceiver {

    private OnConnectedListner onConnectedListner;
    private OnDisconnectListener onDisconnectListener;

    public CheckInternetConnectionReceiver(OnConnectedListner onConnectedListner, OnDisconnectListener onDisconnectListener) {
        this.onConnectedListner = onConnectedListner;
        this.onDisconnectListener = onDisconnectListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            onConnectedListner.onConnect();
        } else {
            onDisconnectListener.onDisconnect();
        }
    }

    public interface OnDisconnectListener {
        void onDisconnect();
    }

    public void setOnDisconnectListener(OnDisconnectListener onDisconnectListener) {
        this.onDisconnectListener = onDisconnectListener;
    }

    public interface OnConnectedListner {
        void onConnect();
    }

    public void setOnConnectedListner(OnConnectedListner onConnectedListner) {
        this.onConnectedListner = onConnectedListner;
    }

}
