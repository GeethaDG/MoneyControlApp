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

public class EditScreen extends AppCompatActivity {
    DatabaseHelper myDb;
    private static final String TAG = "EditScreen";
    public String fromShowAll;
    public String id;
    String[] splitstring;
    public Button updateButton;
    public Button deleteButton;
    public Button repeatButton;
    public String updatedsc;
    public String updatedmop; //for mode of payment
    public String updateda;
    public String updatedd;
    public String updatedn;
    DatePickerDialog picker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        Log.d(TAG, "onCreate: EditScreen created");
        setContentView(R.layout.edit_screen);
       // TextView editid =(TextView) findViewById(R.id.textViewEditId);
        TextView editcategory =(TextView) findViewById(R.id.textViewEditCategory);
        final EditText editsubcategory = (EditText) findViewById(R.id.editTextEditSubcategory);
        final EditText editmodeofpayment = (EditText) findViewById(R.id.editTextEditModeofPayment);
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
                picker = new DatePickerDialog(EditScreen.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editdate.setText(  year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


        fromShowAll =getIntent().getStringExtra("toeditfromshowall");
        splitstring= fromShowAll.split(",");
        Log.d(TAG, "onCreate: "+splitstring.length + fromShowAll);
        id= splitstring[0];
        editcategory.setText(splitstring[1]);
        editsubcategory.setText(splitstring[2]);
        editmodeofpayment.setText(splitstring[3]); //setting the split value from string
        editamount.setText(splitstring[4]);
        editdate.setText(splitstring[5]);
        Cursor res = myDb.getNote(splitstring[0]);
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
                updatedsc = editsubcategory.getText().toString();
                updateda = editamount.getText().toString();
                updatedd = editdate.getText().toString();
                updatedmop = editmodeofpayment.getText().toString();
                updatedn = editnote.getText().toString();
                UpdateData();
                Intent intent =new Intent(EditScreen.this, ShowAllScreen.class);
                startActivity(intent);
            }
        });

       deleteButton = (Button) findViewById(R.id.btnDelete);
       deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
                Intent intent =new Intent(EditScreen.this, ShowAllScreen.class);
                startActivity(intent);
            }
        });
        repeatButton = (Button) findViewById(R.id.btnRepeat);
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedsc = editsubcategory.getText().toString();
                updateda = editamount.getText().toString();
                updatedd = editdate.getText().toString();
                updatedmop = editdate.getText().toString();
                updatedn = editnote.getText().toString();
                RepeatData();
                Intent intent =new Intent(EditScreen.this, ShowAllScreen.class);
                startActivity(intent);
            }
        });

        Button homeButton =(Button) findViewById(R.id.btnGoToHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(EditScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void RepeatData() {
        //deleteButton.setOnClickListener(
        //  new View.OnClickListener() {
        //  @Override
        // public void onClick(View v) {
        boolean insertRow = myDb.insertData(splitstring[1],updatedsc,updatedmop,updateda,updatedd,updatedn);
        Log.d(TAG, "repeat string is : "+splitstring[1]+" "+updatedsc+" "+updateda+" "+updatedd+" "+ updatedn);
        if(insertRow)
            Toast.makeText(EditScreen.this,"Data Repeated",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(EditScreen.this,"Data not repeated",Toast.LENGTH_LONG).show();
        //  }
        // }
        // );
    }
    public void DeleteData() {
        //deleteButton.setOnClickListener(
              //  new View.OnClickListener() {
                  //  @Override
                   // public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(splitstring[0]);
                        Log.d(TAG, "delete id is : "+id);
                        if(deletedRows > 0)
                            Toast.makeText(EditScreen.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditScreen.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                  //  }
               // }
       // );
    }
    public void UpdateData() {
       // updateButton.setOnClickListener(
             //   new View.OnClickListener() {
                   // @Override
                    //public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(id,splitstring[1],updatedsc,updatedmop,updateda,updatedd,updatedn);
                        Log.d(TAG, "update string is : "+updatedsc+" "+updateda+" "+updatedd+ " "+updatedn);
                        if(isUpdate == true)
                            Toast.makeText(EditScreen.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditScreen.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    //}
              //  }
        //);
    }
}

