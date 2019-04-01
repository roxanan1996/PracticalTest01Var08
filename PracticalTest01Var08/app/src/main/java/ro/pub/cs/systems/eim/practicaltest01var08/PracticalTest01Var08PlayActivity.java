package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var08PlayActivity extends AppCompatActivity {

    private TextView riddle = null;
    private EditText answer = null;
    private Button check = null;
    private Button back = null;

    private IntentFilter intentFilter = new IntentFilter();

    String answer_text;
    String riddle_text;
    Boolean is_oke = false;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.back:
                    if (is_oke == true)
                        setResult(RESULT_OK, null);
                    else
                        setResult(RESULT_CANCELED, null);
                    finish();
                    break;
                case R.id.check:
                    if (riddle_text.equals(answer.getText().toString())) {
                        is_oke = true;
                        Toast.makeText(getApplicationContext(), "Check " + is_oke, Toast.LENGTH_LONG).show();
                    }
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_play);


        riddle = (TextView) findViewById(R.id.Riddle);
        answer = (EditText) findViewById(R.id.Answer);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("riddle")) {
            riddle_text = intent.getStringExtra("riddle");
            riddle.setText(riddle_text);
        }

        if (intent != null && intent.getExtras().containsKey("answer")) {

            answer_text = intent.getStringExtra("answer");
        }

        check = (Button)findViewById(R.id.check);
        back = (Button)findViewById(R.id.back);

        check.setOnClickListener(buttonClickListener);
        back.setOnClickListener(buttonClickListener);

        Intent broad_intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
        broad_intent.putExtra("answer", answer_text);

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }

        getApplicationContext().startService(broad_intent);

    }


    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }

    @Override
    public void onResume() {
        registerReceiver(messageBroadcastReceiver, intentFilter);
        super.onResume();
        Log.d(Constants.TAG, "onResume() method was invoked");

    }

    @Override
    public void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
        Log.d(Constants.TAG, "onPause() method was invoked");

    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
        Log.d(Constants.TAG, "onDestroy() method was invoked");
    }

}
