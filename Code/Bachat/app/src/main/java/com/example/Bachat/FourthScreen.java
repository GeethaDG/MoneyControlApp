package com.example.Bachat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FourthScreen extends AppCompatActivity {
    private static final String TAG = "FourthScreen";
    public String fromThird,category,mop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_screen);

        String[] values = getIntent().getStringExtra("fromThird").split(","); //splitting the intent from previous screen
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.insertListContents(values[0],values[1]);
        databaseAccess.close();

        //The following code is for the text spinner to enable the selection of the mode of payment
        Spinner modeSpinner = (Spinner) findViewById(R.id.spnModes);
        ArrayAdapter<CharSequence> modeAdapter = ArrayAdapter.createFromResource(this,R.array.Modeofpayment,android.R.layout.simple_spinner_item);
        //ModeofPayment is the list specified in strings.xml
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(modeAdapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mode = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),mode+" chosen",Toast.LENGTH_LONG).show();
                mop = mode;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button fourthButton = (Button) findViewById(R.id.btnGoToFifth);
        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthScreen.this, FifthScreen.class);
                TextView theTextView = (TextView) findViewById(R.id.textView3);
                // theTextView.setText("Learing Navigation");
                // String stringFromTextView = theTextView.getText().toString();
                // System.out.println(stringFromTextView);
                Log.d(TAG, "onClick: Value of mode of payment is "+mop);
                fromThird = getIntent().getStringExtra("fromThird");
                EditText amount = (EditText) findViewById(R.id.editText2);
                String toadd = fromThird + "," + mop + "," + amount.getText().toString();
                Log.d(TAG, "onClick: Value passing onto fifth "+toadd);
                intent.putExtra("fromFourth",toadd);
                startActivity(intent);
            }
        });
    }
}