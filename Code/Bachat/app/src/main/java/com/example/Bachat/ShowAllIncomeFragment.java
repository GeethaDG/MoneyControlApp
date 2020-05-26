package com.example.Bachat;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Calendar;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.fragment.app.Fragment;

public class ShowAllIncomeFragment extends Fragment {
    DatabaseHelper myDb;
    TextView modeChange;
    Button getDetails;
    Button getSelectedDetails;
    Spinner monthSelected;
    Spinner yearSelected;
    Spinner daySelected;
    ArrayAdapter adapter;
    ListView filterList;
    Spinner filterOptionsSelected;
    Spinner categorySelected;
    int month,year;

    private static final String TAG = "EarningShowAll";
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myDb = new DatabaseHelper(getActivity());
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.show_all_fragment, container, false);
        //Log.d(TAG, "onCreate: created Earning Show All screen");
        modeChange = view.findViewById(R.id.textView7);
        modeChange.setText("Choose Income mode");

        //Initialising and linking resources for filtering functionality
        daySelected = view.findViewById(R.id.spnDate);
        monthSelected = view.findViewById(R.id.spnMonth);
        yearSelected = view.findViewById(R.id.spnYear);
        filterOptionsSelected = view.findViewById(R.id.spnOptions);
        categorySelected = view.findViewById(R.id.spnCategory);
        getDetails = view.findViewById(R.id.btnFilter);

        //Spinner for options
        ArrayAdapter<String> optionsAdapter;
        String[] filterOptions = {"Show all", "Mode Only", "Date Only", "Date and Mode"};
        Spinner optionsSpinner = view.findViewById(R.id.spnOptions);
        optionsAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, filterOptions);
        optionsSpinner.setAdapter(optionsAdapter);

        optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String options = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), options + " chosen", Toast.LENGTH_SHORT).show();
                if (options == "Mode Only" || options == "Date and Mode"){
                    //Spinner for mode
                    Cursor resMode = myDb.getAllDataEarning();
                    if (resMode.getCount() == 0) {
                        //Log.d("myTag", "No data found");
                    }

                    ArrayList<String> ModeExpenseList = new ArrayList<String>();
                    ModeExpenseList.add("All");
                    while (resMode.moveToNext()) {
                        ModeExpenseList.add(resMode.getString(0));
                    }

                    ArrayAdapter<String> modeAdapter;
                    Spinner modeSpinner = getActivity().findViewById(R.id.spnCategory);
                    modeAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, ModeExpenseList);
                    modeSpinner.setAdapter(modeAdapter);

                    modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String category = parent.getItemAtPosition(position).toString();
                            //Toast.makeText(parent.getContext(), category + " chosen", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if (options == "Date Only" || options == "Date and Category"){
                    ArrayAdapter<String> yearAdapter;
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    String[] years = new String[24];
                    for (int i = 0; i < 24; i++) {
                        years[i] = Integer.toString(currentYear);
                        currentYear--;
                    }

                    Spinner yearSpinner = getActivity().findViewById(R.id.spnYear);
                    yearAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, years);
                    yearSpinner.setAdapter(yearAdapter);

                    yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(parent.getContext(), "Choose month and date to refine your search", Toast.LENGTH_SHORT).show();
                            int yearItem = Integer.parseInt(parent.getItemAtPosition(position).toString());
                            if (monthSelected.getSelectedItem().toString() == "All") {
                                dateSetting(yearItem, 0,view);
                            } else {
                                month = Integer.parseInt(monthSelected.getSelectedItem().toString());
                                dateSetting(yearItem, month,view);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    //Spinner for month
                    ArrayAdapter<String> monthAdapter;
                    String[] months = new String[13];
                    months[0] = "All";
                    for (int i = 1; i <= 12; i++) {
                        months[i] = Integer.toString(i);
                    }

                    Spinner monthSpinner = getActivity().findViewById(R.id.spnMonth);
                    monthAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, months);
                    monthSpinner.setAdapter(monthAdapter);

                    monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            year = Integer.parseInt(yearSelected.getSelectedItem().toString());
                            if (monthSelected.getSelectedItem().toString() == "All") {
                                dateSetting(year, 0,view);
                            } else {
                                int monthItem = Integer.parseInt(parent.getItemAtPosition(position).toString());
                                dateSetting(year, monthItem,view);
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ListView listshowall= view.findViewById(R.id.listFilter);

        viewData();
        //Log.d(TAG, "onCreate: values are"+filterOptionsSelected + "" + categorySelected + "" + yearSelected + "" + monthSelected);

        //Home Button
        Button lastbutton = view.findViewById(R.id.btnLast);
        lastbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    public void dateSetting(int yearChosen, int monthChosen,View v){

        ArrayAdapter<String> dayAdapter;
        String[] days1 = new String[30];
        String[] days2 = new String[31];
        String[] days3 = new String[29];
        String[] days4 = new String[32];
        days1[0] = days2[0] = days3[0] = days4[0] = "All";

        Spinner daySpinner = v.findViewById(R.id.spnDate);
        if (yearChosen%4 == 0 && monthChosen == 2){
            for (int i = 1; i <= 29; i++) {
                days1[i] = Integer.toString(i);
                dayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, days1);
                daySpinner.setAdapter(dayAdapter);
            }
        }else if(monthChosen == 4 || monthChosen == 6 || monthChosen == 9 || monthChosen == 11){
            for (int i = 1; i <= 30; i++) {
                days2[i] = Integer.toString(i);
                dayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, days2);
                daySpinner.setAdapter(dayAdapter);
            }
        }else if(monthChosen == 2){
            for (int i = 1; i <= 28; i++) {
                days3[i] = Integer.toString(i);
                dayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, days3);
                daySpinner.setAdapter(dayAdapter);
            }
        }else{
            for (int i = 1; i <= 31; i++) {
                days4[i] = Integer.toString(i);
                dayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, days4);
                daySpinner.setAdapter(dayAdapter);
            }
        }
    }

    public void viewData() {

        getDetails.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView listshowall= getActivity().findViewById(R.id.listFilter);
                Cursor res = checkFilterOption();

                ArrayList<String> listItem = new ArrayList<>();

                if (res.getCount() == 0) {
                    //message
                    //showMessage("Oops", "no earnings found");
                    return;}

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append(res.getString(0) + "," + res.getString(1) + "," + res.getString(2) + "," + res.getString(3) + "," + res.getString(4)+ "\n");
                }


                String[] bufferdata  = buffer.toString().split("\n");
                final ArrayList<EarningListItem> EarningListItems = new ArrayList<>();
                for(int i=0; i< bufferdata.length; i++){
                    String[] listindex  = bufferdata[i].split(",");
                    EarningListItem item = new EarningListItem(listindex[0],listindex[1],listindex[2],listindex[3],listindex[4]);
                    EarningListItems.add(item);
                }

                EarningViewAllAdapter adapter = new EarningViewAllAdapter(getActivity(), R.layout.earning_view_all_layout,EarningListItems);
                listshowall.setAdapter(adapter);

                listshowall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Log.d(TAG, "onItemClick:you clicked on a list item  " + peopleList.get(position).getName());
                        //toast.makeText(SecondScreen.this, "you clicked on " + peopleList.get(position).getName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), EarningEditScreen.class);
                        String toeditid =  EarningListItems.get(position).getId();
                        String toeditmode =  EarningListItems.get(position).getMode();
                        String toeditamount =  EarningListItems.get(position).getAmount();
                        String toeditdate =  EarningListItems.get(position).getDate();
                        String toedit = toeditid+","+toeditmode+","+toeditamount+","+toeditdate;


                        intent.putExtra("toeditfromearningshowall", toedit);
                        startActivity(intent);
                    }
                });

            }
        });

        Button lastbutton = getActivity().findViewById(R.id.btnLast);
        lastbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public Cursor checkFilterOption() {
        String selected = filterOptionsSelected.getSelectedItem().toString();

        switch (selected) {
            case "Show all": {
                Cursor res = myDb.getAllDataEarning();
                return res;

            }
            case "Date Only": {
                String day = daySelected.getSelectedItem().toString();
                if (monthSelected.getSelectedItem().toString() == "All"){
                    if (day == "All"){
                        Cursor res = myDb.getEarningAllDate(year, 0);
                        return res;
                    }else{
                        int date = Integer.parseInt(daySelected.getSelectedItem().toString());
                        Cursor res = myDb.getEarningAllDateWithDay(year, 0, date);
                        return res;
                    }
                }else {
                    int month = Integer.parseInt(monthSelected.getSelectedItem().toString());
                    int year = Integer.parseInt(yearSelected.getSelectedItem().toString());

                    //Log.d(TAG, "checkFilterOption: value of day at Date Only: " + day);
                    if (day == "All") {
                        //Log.d(TAG, "checkFilterOption: month and year are: " + month + " " + year);
                        Cursor res = myDb.getEarningAllDate(year, month);
                        return res;
                    } else {
                        int date = Integer.parseInt(daySelected.getSelectedItem().toString());
                        Cursor res = myDb.getEarningAllDateWithDay(year, month, date);
                        return res;
                    }
                }
            }
            case "Mode Only": {
                String categoryS = categorySelected.getSelectedItem().toString();
                Cursor res = myDb.getEarningMode(categoryS);
                return res;

            }
            case "Date and Mode": {
                String day = daySelected.getSelectedItem().toString();
                String categoryS = categorySelected.getSelectedItem().toString();
                if (monthSelected.getSelectedItem().toString() == "All") {
                    if (day == "All") {
                        Cursor res = myDb.getEarningModeAndDate(year, 0, categoryS);
                        return res;
                    } else {
                        int date = Integer.parseInt(daySelected.getSelectedItem().toString());
                        Cursor res = myDb.getEarningModeAndDateWithDay(year, 0, date, categoryS);
                        return res;
                    }
                }else {
                    int month = Integer.parseInt(monthSelected.getSelectedItem().toString());
                    int year = Integer.parseInt(yearSelected.getSelectedItem().toString());

                    if (day == "All") {
                        Cursor res = myDb.getEarningModeAndDate(year, month, categoryS);
                        return res;
                    } else {
                        int date = Integer.parseInt(daySelected.getSelectedItem().toString());
                        Cursor res = myDb.getEarningModeAndDateWithDay(year, month, date, categoryS);
                        return res;
                    }
                }
            }
        }
        Cursor res = myDb.getAllDataEarning();
        return res;
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}

