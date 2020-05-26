package com.example.Bachat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ThresholdScreen extends AppCompatActivity {
    //String fromThresholdCategory;
    String toeditfromthresholdviewscreen;
    int flag = 0;
    EditText Threshold;
    String value;
    String[] splitstring;
    public Button removeButton;
    DatabaseHelper myDb;
    private static final String TAG = "ThresholdScreen";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        myDb = new DatabaseHelper(this);
        //fromThresholdCategory = getIntent().getStringExtra("fromThresholdCategory");
        toeditfromthresholdviewscreen = getIntent().getStringExtra("toeditfromthresholdviewscreen");;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threshold_screen);
        TextView showcat = (TextView)findViewById(R.id.textViewThresholdCategory);
        Threshold = (EditText) findViewById(R.id.editTextThreshold);

        splitstring= toeditfromthresholdviewscreen.split(",");
        showcat.setText(splitstring[0]);
        //showcat.setText(fromThresholdCategory);
        //Threshold.setText("0");
        if(splitstring[1].equals("Not Set")) {
            Threshold.setText("0");
        }
        else{
            Threshold.setText(splitstring[1]);
        }


        final Button setThreshold = (Button) findViewById(R.id.btnSetThreshold);
        setThreshold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setThreshold();
            }
        });

        removeButton =(Button)findViewById(R.id.btnRemoveThreshold);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeThreshold();
            }
        });

        Button home = (Button) findViewById(R.id.btnGoHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThresholdScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void setThreshold(){
        value=Threshold.getText().toString();
        if(value.length()<1) {
            Log.d(TAG, "value.length < 1");
            String threshold = "Not Set";
            //boolean isUpdate = myDb.updateThreshold(fromThresholdCategory,threshold);
            boolean isUpdate = myDb.updateThreshold(splitstring[0],threshold);

            Log.d(TAG, "update amount is : "+threshold);
            if(isUpdate == true)
                flag=1;
                //Toast.makeText(ThresholdScreen.this,"Threshold Set",Toast.LENGTH_LONG).show();
            //else
                //Toast.makeText(ThresholdScreen.this,"Threshold not Set",Toast.LENGTH_LONG).show();

        }
        else{
            //boolean isUpdate = myDb.updateThreshold(fromThresholdCategory,value);
            boolean isUpdate = myDb.updateThreshold(splitstring[0],value);

            Log.d(TAG, "update amount is : "+value);
            if(isUpdate == true)
                Log.d(TAG, "setThreshold: update ok");
                flag=1;
                //Toast.makeText(ThresholdScreen.this,"Threshold Set",Toast.LENGTH_LONG).show();
            //else
                //Toast.makeText(ThresholdScreen.this,"Threshold not Set",Toast.LENGTH_LONG).show();
        }
       if(flag == 1){
           Log.d(TAG, "flag set to 1");

           Intent intent = new Intent(ThresholdScreen.this, ThresholdViewScreen.class);
           startActivity(intent);
       }
    }

    void removeThreshold(){
        Log.d(TAG, "removeThreshold: ");
            String threshold = "Not Set";

            //boolean isUpdate = myDb.updateThreshold(fromThresholdCategory,threshold);
            boolean isUpdate = myDb.updateThreshold(splitstring[0],threshold);

            Log.d(TAG, "removed amount is : "+threshold);
            if(isUpdate == true)
                flag=1;
        if(flag == 1){
            Log.d(TAG, "flag set to 1");

            Intent intent = new Intent(ThresholdScreen.this, ThresholdViewScreen.class);
            startActivity(intent);
        }
    }

}
