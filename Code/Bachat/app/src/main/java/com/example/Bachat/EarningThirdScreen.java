package com.example.Bachat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EarningThirdScreen extends AppCompatActivity {
    private static final String TAG = "EarningThirdScreen";
    public String fromEarningSecond;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earning_third_screen);



        Button fourthButton = (Button) findViewById(R.id.btnGoToFifth);
        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarningThirdScreen.this, EarningFourthScreen.class);
                TextView theTextView = (TextView) findViewById(R.id.textView3);
                // theTextView.setText("Learing Navigation");
                // String stringFromTextView = theTextView.getText().toString();
                // System.out.println(stringFromTextView);
                fromEarningSecond = getIntent().getStringExtra("fromEarningSecond");
                EditText amount = (EditText) findViewById(R.id.editText2);
                String toadd = fromEarningSecond + "," + amount.getText().toString();
                intent.putExtra("fromEarningThird",toadd);
                startActivity(intent);
            }
        });
    }
}