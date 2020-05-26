package com.example.Bachat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener//,HomeFragment.onFragmentBtnSelected
         {
    private static final String TAG = "MainActivity";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: MainActivity Started" );

        //Declaring all the components of Navigation Menu
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        //For the toggle animation as the user open navigation menu
        actionBarDrawerToggle  = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        //loading default fragment
        if(savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction= fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new HomeFragment());
            fragmentTransaction.commit();
            //navigationView.setCheckedItem(R.id.nav_message);
        }


    }

    //Navigation Menu Items on click fragment loading
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.menuHome:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new HomeFragment());
                fragmentTransaction.commit();
                break;
            case R.id.menuExpense:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new ShowAllExpenseFragment());
                fragmentTransaction.commit();
                break;
            case R.id.menuIncome:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new ShowAllIncomeFragment());
                fragmentTransaction.commit();
                break;
            case R.id.menuThreshold:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new SetThresholdFragment());
                fragmentTransaction.commit();
                break;
            case R.id.menuImport:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                        new ImportFragment()).commit();
                break;
            case R.id.menuExport:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                        new ExportFragment()).commit();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /*@Override
    public void onButtonSelected() {
        Toast.makeText(this, "Fragment Button is working", Toast.LENGTH_SHORT).show();
    }

    /*
   // DatabaseHelper myDb = null;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Test Commit
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: MainActivity Started" );
      //  myDb= new DatabaseHelper(this);

        TextView showallearning = (TextView) findViewById(R.id.textViewShowAllEarning);
        showallearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EarningShowAllScreen.class);
                startActivity(intent);
            }
        });
        TextView showallexpense = (TextView) findViewById(R.id.textViewShowAllExpense);
        showallexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowAllScreen.class);
                startActivity(intent);
            }
        });

        Button threshold = (Button) findViewById(R.id.btnGoToSetThreshold);
        threshold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThresholdViewScreen.class);
                startActivity(intent);
            }
        });

       /* Button viewthreshold = (Button) findViewById(R.id.btnGoToViewThreshold);
        viewthreshold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThresholdCategoryScreen.class);
                startActivity(intent);
            }
        });*/
//addding here again because one was already existing
        /*ImageView plusImage = (ImageView) findViewById(R.id.plusImage);
        int imageResource = getResources().getIdentifier("@drawable/plus", null, this.getPackageName());
        plusImage.setImageResource(imageResource);
        plusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: you clicked on the plus image");
                //Toast.makeText(MainActivity.this, "image clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EarningSecondScreen.class);
                startActivity(intent);
            }
        });
        ImageView minusImage = (ImageView) findViewById(R.id.minusImage);
        int imageResourcea = getResources().getIdentifier("@drawable/minus", null, this.getPackageName());
        minusImage.setImageResource(imageResourcea);
        minusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: you clicked on the minus image");
                //Toast.makeText(MainActivity.this, "image clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondScreen.class);
                startActivity(intent);
            }
        });
        ImageView convertImage = (ImageView) findViewById(R.id.convertImage);
        int imageResourceb = getResources().getIdentifier("@drawable/convert", null, this.getPackageName());
        convertImage.setImageResource(imageResourceb);
        convertImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: you clicked on the convert image");
                //Toast.makeText(MainActivity.this, "image clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ImportToScreen.class);
                startActivity(intent);
            }
        });
    }*/

}
