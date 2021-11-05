package com.snayab.ahooraelevator.receiver.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

public class SmsBroadCastReciver extends BroadcastReceiver {

    public SmsBroadcastInterface smsBroadcastInterface;


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() == SmsRetriever.SMS_RETRIEVED_ACTION) {
            Bundle extera = intent.getExtras();
            Status smsRetriever = (Status) extera.get(SmsRetriever.EXTRA_STATUS);
            switch (smsRetriever.getStatusCode()) {

                case CommonStatusCodes.SUCCESS:



                    String message = extera.getString(SmsRetriever.EXTRA_SMS_MESSAGE);
                    smsBroadcastInterface.onsucess(message);
                    break;

                case CommonStatusCodes.TIMEOUT:
                    smsBroadcastInterface.error();
                    break;
            }
        }
    }

    public interface SmsBroadcastInterface {
        void onsucess(String message);

        void error();
    }

}
