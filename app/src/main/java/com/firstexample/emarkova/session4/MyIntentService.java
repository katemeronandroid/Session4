package com.firstexample.emarkova.session4;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myLog", "Service created");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if((intent.getStringExtra("command").equals("yes"))) {
            Log.d("myLog", "Command yes");
            StateManager.changeState();
            Intent broadcastIntent = new Intent("emarkova.CHANGE_STATE");
            Log.d("myLog", StateManager.getState().toString());
            broadcastIntent.putExtra("state", StateManager.getState().toString());
            sendBroadcast(broadcastIntent);
        }
    }
    public static  final  Intent newIntent(Context context) {
        return new Intent(context, MyIntentService.class);
    }

    public static final Intent getIntentForSend(Context context, String message) {
        Intent intent = newIntent(context);
        intent.putExtra("command", message);
        return  intent;
    }
}
