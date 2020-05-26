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

public class ThresholdCategoryScreen extends AppCompatActivity {

    private static final String TAG = "ThresholdCategoryScreen";
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);
        Log.d(TAG, "onCreate:Started Threshold Category Screen");


        //ListView mListView = (ListView) findViewById(R.id.theGrid);
        GridView mListView = (GridView) findViewById(R.id.theGrid);

        //Create the Person objects
        /*
        Person john = new Person("John","12-20-1998","Male",
                getResources().getIdentifier("@drawable/cartman_cop", null,this.getPackageName()));
                */

        Category category1 = new Category("Health", "drawable://" + R.drawable.health);
        Category category2 = new Category("Donations", "drawable://" + R.drawable.donate);
        Category category3 = new Category("Bills", "drawable://" + R.drawable.bills);
        Category category4 = new Category("Shopping", "drawable://" + R.drawable.shopping);
        Category category5 = new Category("Dining Out", "drawable://" + R.drawable.dinner);
        Category category6 = new Category("Entertainment", "drawable://" + R.drawable.fun);;
        Category category7= new Category("Groceries", "drawable://" + R.drawable.groceries);
        Category category8 = new Category("Pet", "drawable://" + R.drawable.pet);
        Category category9 = new Category("Transport", "drawable://" + R.drawable.transport);
        Category category10 = new Category("Loans", "drawable://" + R.drawable.loan);
        Category category11 = new Category("Personal Care", "drawable://" + R.drawable.personalcare);
        Category category12= new Category("Miscellaneous", "drawable://" + R.drawable.miscellaneous);



        //Add the Person objects to an ArrayList
        final ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category3);
        categoryList.add(category2);
        categoryList.add(category4);
        categoryList.add(category5);
        categoryList.add(category6);
        categoryList.add(category7);
        categoryList.add(category8);
        categoryList.add(category9);
        categoryList.add(category10);
        categoryList.add(category11);
        categoryList.add(category12);
        categoryList.add(category12);
        categoryList.add(category12);


        CategoryListAdapter adapter = new CategoryListAdapter(this, R.layout.adapter_view_layout, categoryList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d(TAG, "onItemClick:you clicked on a list item  " + peopleList.get(position).getName());
                //toast.makeText(SecondScreen.this, "you clicked on " + peopleList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ThresholdCategoryScreen.this, ThresholdScreen.class);
                String toadd =  categoryList.get(position).getName();
                intent.putExtra("fromThresholdCategory",toadd);
                startActivity(intent);
            }
        });
    }
}





