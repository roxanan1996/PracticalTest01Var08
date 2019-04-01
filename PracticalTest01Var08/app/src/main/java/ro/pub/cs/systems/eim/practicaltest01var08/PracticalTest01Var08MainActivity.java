package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    private EditText riddle = null;
    private EditText answer = null;
    private Button play = null;

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08PlayActivity.class);

            if (riddle.getText().equals("") == false && answer.getText().equals("") == false)
                intent.putExtra("riddle", riddle.getText().toString());
                intent.putExtra("answer", answer.getText().toString());
                startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        riddle = (EditText) findViewById(R.id.Riddle);
        answer = (EditText) findViewById(R.id.Answer);

        riddle.setText("");
        answer.setText("");

        play = (Button)findViewById(R.id.play);
        play.setOnClickListener(buttonClickListener);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        riddle = (EditText)findViewById(R.id.Riddle);
        answer = (EditText)findViewById(R.id.Answer);
        savedInstanceState.putString("riddle", riddle.getText().toString());
        savedInstanceState.putString("answer", answer.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {


        super.onRestoreInstanceState(savedInstanceState);


        if (savedInstanceState.containsKey("riddle")) {
            riddle = (EditText) findViewById(R.id.Riddle);
            riddle.setText(savedInstanceState.getString("riddle"));
        } else {
            riddle.setText("");
        }
        if (savedInstanceState.containsKey("answer")) {
            answer = (EditText) findViewById(R.id.Answer);
            answer.setText(savedInstanceState.getString("answer"));
        } else {
            answer.setText("");
        }
    }

}
