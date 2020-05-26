package com.example.Bachat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EarningEditScreen extends AppCompatActivity {
    DatabaseHelper myDb;
    private static final String TAG = "EarningEditScreen";
    public String fromShowAll;
    public String id;
    String[] splitstring;
    public Button updateButton;
    public Button deleteButton;
    public Button repeatButton;
    public String updatedea;
    public String updateded;
    public String updatedn;
    DatePickerDialog picker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        Log.d(TAG, "onCreate: EditScreen created");
        setContentView(R.layout.earning_edit_screen);
        // TextView editid =(TextView) findViewById(R.id.textViewEditId);
        TextView editcategory =(TextView) findViewById(R.id.textViewEditCategory);
        final EditText editamount = (EditText) findViewById(R.id.editTextEditAmount);
        final EditText editdate = (EditText) findViewById(R.id.editTextEditDate);
        final EditText editnote = (EditText) findViewById(R.id.editTextEditNote);
        editdate.setInputType(InputType.TYPE_NULL);
        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(EarningEditScreen.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editdate.setText(  year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        fromShowAll =getIntent().getStringExtra("toeditfromearningshowall");
        splitstring= fromShowAll.split(",");
        id= splitstring[0];
        editcategory.setText(splitstring[1]);
        editamount.setText(splitstring[2]);
        editdate.setText(splitstring[3]);
        Cursor res = myDb.getNoteEarning(splitstring[0]);
        StringBuffer note= new StringBuffer();
        while (res.moveToNext())
        {
            note.append(res.getString(0));
        }
        editnote.setText(note.toString());


        updateButton = (Button) findViewById(R.id.btnUpdate);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedea = editamount.getText().toString();
                updateded = editdate.getText().toString();
                updatedn = editnote.getText().toString();
                UpdateData();
                Intent intent =new Intent(EarningEditScreen.this, EarningShowAllScreen.class);
                startActivity(intent);
            }
        });

        deleteButton = (Button) findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
                Intent intent =new Intent(EarningEditScreen.this, EarningShowAllScreen.class);
                startActivity(intent);
            }
        });
        repeatButton = (Button) findViewById(R.id.btnRepeat);
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedea = editamount.getText().toString();
                updateded = editdate.getText().toString();
                updatedn = editnote.getText().toString();
                RepeatData();
                Intent intent =new Intent(EarningEditScreen.this, EarningShowAllScreen.class);
                startActivity(intent);
            }
        });

        Button homeButton =(Button) findViewById(R.id.btnGoToHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(EarningEditScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void RepeatData() {
        //deleteButton.setOnClickListener(
        //  new View.OnClickListener() {
        //  @Override
        // public void onClick(View v) {
        boolean insertRow = myDb.insertDataEarning(splitstring[1],updatedea,updateded,updatedn);
        Log.d(TAG, "repeat string is : "+splitstring[1]+" "+updatedea+" "+updateded);
        if(insertRow)
            Toast.makeText(EarningEditScreen.this,"Data Repeated",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(EarningEditScreen.this,"Data not repeated",Toast.LENGTH_LONG).show();
        //  }
        // }
        // );
    }
    public void DeleteData() {
        //deleteButton.setOnClickListener(
        //  new View.OnClickListener() {
        //  @Override
        // public void onClick(View v) {
        Integer deletedRows = myDb.deleteDataEarning(splitstring[0]);
        Log.d(TAG, "delete id is : "+id);
        if(deletedRows > 0)
            Toast.makeText(EarningEditScreen.this,"Data Deleted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(EarningEditScreen.this,"Data not Deleted",Toast.LENGTH_LONG).show();
        //  }
        // }
        // );
    }
    public void UpdateData() {
        // updateButton.setOnClickListener(
        //   new View.OnClickListener() {
        // @Override
        //public void onClick(View v) {
        boolean isUpdate = myDb.updateDataEarning(id,splitstring[1],updatedea,updateded,updatedn);
        Log.d(TAG, "update string is : "+updatedea+" "+updateded);
        if(isUpdate == true)
            Toast.makeText(EarningEditScreen.this,"Data Update",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(EarningEditScreen.this,"Data not Updated",Toast.LENGTH_LONG).show();
        //}
        //  }
        //);
    }
}
