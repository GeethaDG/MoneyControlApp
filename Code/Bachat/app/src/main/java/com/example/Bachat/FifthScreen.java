package com.example.Bachat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FifthScreen extends AppCompatActivity {
    private static final String TAG = "FifthScreen";
    public String fromFourth;
    DatePicker picker;
    DatabaseHelper myDb;
    public String category;
    public String subcategory;
    public String modeofpayment;
    public String amount;
    public String date;
    public String note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: created fifth screen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth_screen);
        picker = (DatePicker) findViewById(R.id.editText3);

        myDb = new DatabaseHelper(this);

        //fromFifth = getIntent().getStringExtra("fromFifth");
        //Log.d(TAG, "onCreate: from fifth"+ fromFifth);
        //TextView finaltextView = (TextView) findViewById(R.id.textView5) ;
        //finaltextView.setText(fromFifth);
        final String result[];// = fromFifth.split(",");


        Button fifthButton = (Button) findViewById(R.id.btnGoToSixth);
        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(FifthScreen.this, SixthScreen.class);
                TextView theTextView = (TextView) findViewById(R.id.textView4);
                Toast.makeText(FifthScreen.this, "Added", Toast.LENGTH_SHORT).show();
                fromFourth = getIntent().getStringExtra("fromFourth");
                //EditText date = (EditText) findViewById(R.id.editText3)
                EditText editnote = (EditText) findViewById(R.id.editTextNote);
                ;
                String notecheck = editnote.getText().toString();
                String note;
                if (notecheck.length() == 0) {
                    note = "Not Set";
                } else
                    note = notecheck;

                String date = picker.getYear() + "/" + (picker.getMonth() + 1) + "/" + picker.getDayOfMonth();
                String toadd = fromFourth + "," + date + "," + note;
                final String result[] = toadd.split(",");
                category = result[0];
                subcategory = result[1];
                modeofpayment = result[2];
                amount = result[3];
                date = result[4];
                note = result[5];

                //intent.putExtra("fromFifth",toadd);
                //startActivity(intent);
            }
        });
        Button addButton = (Button) findViewById(R.id.btnAddDataToDb);
        AddData(addButton);

        Button sixthButton = (Button) findViewById(R.id.btnGoToShowAll);
        sixthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifthScreen.this, ShowAllScreen.class);
                startActivity(intent);
            }
        });
    }
    public void AddData (Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(category, subcategory, modeofpayment, amount, date, note);
                if (isInserted = true)
                    Toast.makeText(FifthScreen.this, "data inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(FifthScreen.this, "not inserted", Toast.LENGTH_SHORT).show();

            }
        });
    }
}