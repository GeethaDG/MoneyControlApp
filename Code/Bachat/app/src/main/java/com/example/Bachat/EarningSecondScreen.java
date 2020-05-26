package com.example.Bachat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EarningSecondScreen extends AppCompatActivity {

    private static final String TAG = "SecondScreen";
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);
        Log.d(TAG, "onCreate:Started Earning SecondScreen");


        //ListView mListView = (ListView) findViewById(R.id.theGrid);
        GridView mListView = (GridView) findViewById(R.id.theGrid);
        //Create the Person objects
        /*
        Person john = new Person("John","12-20-1998","Male",
                getResources().getIdentifier("@drawable/cartman_cop", null,this.getPackageName()));
                */

        Category earningcategory1 = new Category("Salary", "drawable://" + R.drawable.loan);
        Category earningcategory2 = new Category("Business", "drawable://" + R.drawable.loan);
        Category earningcategory3 = new Category("Gifts", "drawable://" + R.drawable.loan);
        Category earningcategory4 = new Category("Bonus", "drawable://" + R.drawable.loan);
        Category earningcategory5 = new Category("Interest", "drawable://" + R.drawable.loan);
        Category earningcategory6 = new Category("Cash Back", "drawable://" + R.drawable.loan);;
        Category earningcategory7= new Category("Refund", "drawable://" + R.drawable.loan);
        Category earningcategory8 = new Category("Donation", "drawable://" + R.drawable.loan);
        Category earningcategory9 = new Category("Insurance Payout", "drawable://" + R.drawable.loan);
        Category earningcategory10 = new Category("Credit points", "drawable://" + R.drawable.loan);
        Category earningcategory11 = new Category("Extra Income", "drawable://" + R.drawable.loan);
        Category earningcategory12 = new Category("Others", "drawable://" + R.drawable.loan);



        //Add the Person objects to an ArrayList
        final ArrayList<Category> earningcategoryList = new ArrayList<>();
        earningcategoryList.add(earningcategory1);
        earningcategoryList.add(earningcategory2);
        earningcategoryList.add(earningcategory3);
        earningcategoryList.add(earningcategory4);
        earningcategoryList.add(earningcategory5);
        earningcategoryList.add(earningcategory6);
        earningcategoryList.add(earningcategory7);
        earningcategoryList.add(earningcategory8);
        earningcategoryList.add(earningcategory9);
        earningcategoryList.add(earningcategory10);
        earningcategoryList.add(earningcategory11);
        earningcategoryList.add(earningcategory12);


        CategoryListAdapter adapter = new CategoryListAdapter(this, R.layout.adapter_view_layout, earningcategoryList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d(TAG, "onItemClick:you clicked on a list item  " + peopleList.get(position).getName());
                //toast.makeText(SecondScreen.this, "you clicked on " + peopleList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EarningSecondScreen.this, EarningThirdScreen.class);
                String toadd =  earningcategoryList.get(position).getName();
                intent.putExtra("fromEarningSecond",toadd);
                startActivity(intent);
            }
        });
    }
}





