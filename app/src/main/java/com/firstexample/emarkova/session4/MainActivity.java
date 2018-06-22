package com.firstexample.emarkova.session4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CustomBroadcastReciever mReciever;
    private IntentFilter mFilter;
    private TextView textView;
    static StateManager manager = new StateManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        init();
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(mReciever, mFilter);
    }

    private void init() {
        mReciever = new CustomBroadcastReciever();
        mFilter = new IntentFilter("emarkova.CHANGE_STATE");
    }

    public void onClickChange(View view) {
        startService(MyIntentService.getIntentForSend(MainActivity.this, "yes"));
        Log.d("myLog", "Send yes");
    }

    public void onClickNotChange(View view) {
        startService(MyIntentService.getIntentForSend(MainActivity.this, "no"));
        Log.d("myLog", "Send no");
    }

    public class CustomBroadcastReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("myLog", "receiver");
            textView = (TextView)findViewById(R.id.textView);
            textView.setText(intent.getStringExtra("state"));
        }
    }
}
