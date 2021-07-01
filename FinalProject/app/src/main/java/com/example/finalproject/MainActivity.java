package com.example.finalproject;


import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int TEST_DATA_SIZE = 365;
    public static ArrayList<HashMap<String,String>> TEST_DATA;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    DBHelper users_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bind the Main Activity Layout to a variable
        users_data = new DBHelper(getApplicationContext());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_finances) //, R.id.nav_slideshow, R.id.nav_contacts
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //setupNavigationHeader(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setupNavigationHeader(Bundle savedInstanceState){
        //TextView nav_username = findViewById(R.layout.nav_header_main);
        //TextView nav_email = findViewById(R.id.nav_email_textview);

//        nav_username.setText("testUser");//savedInstanceState.getString("username"));
  //      nav_email.setText("testUser@gmail.com");//savedInstanceState.getString("email"));
    }


    public static void initializeTestData(){
        TEST_DATA = new ArrayList<>();

        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE,TEST_DATA_SIZE * -1);

        for(int i = 0; i < TEST_DATA_SIZE; i++) {
            HashMap<String,String> DataMap = new HashMap<>();
            DataMap.put("id", "1");
            DataMap.put("financial_operation", "income");
            DataMap.put("amount", Integer.toString(new Random().nextInt(100)*i));
            DataMap.put("date", calender.getTime().toString());

            TEST_DATA.add(DataMap);
            calender.add(Calendar.DATE,1);


        }
    }
}