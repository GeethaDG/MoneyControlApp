package com.example.Bachat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EarningFourthScreen extends AppCompatActivity {
    private static final String TAG = "FifthScreen";
    public String fromEarningThird;
    DatePicker picker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: created fifth screen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth_screen);
        picker = (DatePicker) findViewById(R.id.editText3);
        final EditText editnote = (EditText)findViewById(R.id.editTextNote);


        Button fifthButton = (Button) findViewById(R.id.btnGoToSixth);
        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EarningFourthScreen.this, EarningFifthScreen.class);
                TextView theTextView = (TextView) findViewById(R.id.textView4);
                // theTextView.setText("Learing Navigation");
                // String stringFromTextView = theTextView.getText().toString();
                // System.out.println(stringFromTextView);

                String notecheck = editnote.getText().toString();
                String note;
                if(notecheck.length() == 0){
                    note = "Not Set";
                }
                else
                    note= notecheck;


                fromEarningThird = getIntent().getStringExtra("fromEarningThird");
               // EditText date = (EditText) findViewById(R.id.editText3);
                String date = picker.getYear()+"/"+ (picker.getMonth() + 1)+"/"+picker.getDayOfMonth();

                String toadd = fromEarningThird + "," + date + "," + note;
                intent.putExtra("fromEarningFourth",toadd);
                startActivity(intent);
            }
        });
    }
}