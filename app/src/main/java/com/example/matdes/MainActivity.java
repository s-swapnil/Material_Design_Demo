package com.example.matdes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    AppCompatEditText user, pass;
    ConstraintLayout constraintLayout;
    TextInputLayout userlayout, passlayout;
    AppCompatButton raisedBtn, flatBtn;
    FloatingActionButton floatBtn;
    SwitchCompat switchCompat;
    AppCompatCheckBox appCompatCheckBox;
    AppCompatRadioButton radioButton;
    RadioGroup radioGroup;
    ProgressBar progressBar, progressBar2;
    Handler handler;
    Runnable runnable;
    Timer timer;
    AlertDialog alertDialog, alertDialog2;
    AlertDialog.Builder builder, builder2;
    ProgressDialog progressDialog, progressDialog2;
    SwipeRefreshLayout swipeRefreshLayout;
    String[] items = {"Easy", "Medium", "Hard", "Very Hard"};

    public final static String TAG = "tag";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.item1_id): {
                Toast.makeText(getApplicationContext(), "Item number1 is selected", Toast.LENGTH_LONG).show();
                break;
            }
            case (R.id.item2_id): {
                Toast.makeText(getApplicationContext(), "Item number2 is selected", Toast.LENGTH_LONG).show();
                break;
            }
            case (R.id.magnify_id): {
                Toast.makeText(getApplicationContext(), "Item magnify", Toast.LENGTH_LONG).show();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
            }
            case (android.R.id.home): {
                Toast.makeText(getApplicationContext(), "HOMIE", Toast.LENGTH_LONG).show();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
            }
            case R.id.print_id:
            {
               // startActivity(new Intent(MainActivity.this,MainActivity4.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        constraintLayout = findViewById(R.id.consLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        userlayout = findViewById(R.id.username_txtInputlayout);
        passlayout = findViewById(R.id.password_txtInputlayout);
        raisedBtn = findViewById(R.id.btn);
        flatBtn = findViewById(R.id.flat_btn);
        floatBtn = findViewById(R.id.floatingBtn);
        switchCompat = findViewById(R.id.switch_id);
        appCompatCheckBox = findViewById(R.id.checkbox_id);
        radioButton = findViewById(R.id.radiobtn_id);
        radioGroup = findViewById(R.id.radiogrp_id);
        progressBar = findViewById(R.id.progressbar_id);
        swipeRefreshLayout=findViewById(R.id.swipelayout);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorLoadRefresh));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setColorSchemeResources(R.color.colorFinishRefresh);
                final Runnable runnable=new Runnable() {
                    @Override
                    public void run() {

                        Snackbar.make(findViewById(R.id.coordinator_layout3),"Screen Refreshed", BaseTransientBottomBar.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorLoadRefresh));
                    }
                };
                final Handler handler=new Handler();
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(runnable);

                    }
                },3000);
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                //  progressBar.setVisibility(View.GONE);
                if (progressBar2.getProgress() < 100) {
                    progressBar2.incrementProgressBy(5);
                    progressBar2.incrementSecondaryProgressBy(10);
                } else {
                    timer.cancel();
                }
                //   Toast.makeText(getApplicationContext(),"HELLO",Toast.LENGTH_SHORT).show();
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 8000, 1000);
        progressBar2 = findViewById(R.id.progressbarHori_id);
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Discard Draft?");
        builder.setPositiveButton("ACTIVITY 2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "ACTIVITY 2", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
        builder.setNegativeButton("ACTIVITY 4", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "ACTIVITY 4", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,MainActivity4.class));
            }
        });
        builder.setNeutralButton("ACTIVITY 3", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "ACTIVITY 3", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,MainActivity3.class));
            }
        });
        builder.setTitle("SLOW DOWN");
        alertDialog = builder.create();

        builder2 = new AlertDialog.Builder(MainActivity.this, R.style.ConfirmationDialogTheme);
        builder2.setTitle("Select the difficulty level");
        builder2.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "SELECTED " + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder2.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog2 = builder2.create();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Progress Dialog");
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        progressDialog2 = new ProgressDialog(MainActivity.this);
        progressDialog2.setTitle("Progress Dialog");
        progressDialog2.setMessage("Please wait");
        progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog2.setIndeterminate(true);


        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((getSupportActionBar().getDisplayOptions() & ActionBar.DISPLAY_HOME_AS_UP) == 0) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });

        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: s=" + s + "  start=" + start + "  count=" + count + "  after=" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "OnTextChanged: s=" + s + "  start=" + start + "  count=" + count + "  before=" + before);

                if (!user.getText().toString().isEmpty()) {
                    userlayout.setErrorEnabled(false);

                } else {
                    userlayout.setErrorEnabled(true);
                    userlayout.setError("Please enter your username");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "AfterTextChanged: string=" + s);
            }
        });

        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d(TAG, "onFocusChange: " + hasFocus);

                if (user.getText().toString().isEmpty()) {
                    userlayout.setErrorEnabled(true);
                    userlayout.setError("Please enter your username");
                }

                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        constraintLayout.setOnClickListener(null);
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        passlayout.setCounterEnabled(true);
        passlayout.setCounterMaxLength(8);
        raisedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "RAISED BTN", Toast.LENGTH_SHORT).show();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAlertDialog));
            }
        });
        flatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "FLAT BTN", Toast.LENGTH_SHORT).show();
                alertDialog2.show();
            }
        });
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "FLOAT BTN", Toast.LENGTH_SHORT).show();
                progressDialog2.show();
            }
        });
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "SWITCH=" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        appCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "CheckBox=" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radiobtn_id: {
                        Toast.makeText(getApplicationContext(), "RADIOBTN 1", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.radiobtn2_id: {
                        Toast.makeText(getApplicationContext(), "RADIOBTN 2", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(View.VISIBLE);
        progressBar2.setProgress(0);
        progressBar2.setSecondaryProgress(0);
        progressBar2.setMax(100);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        String name = null;
        switch (id) {
            case (R.id.trophy_id):
                name = "TROPHY";
                break;
            case (R.id.cake_id):
                name = "CAKE";
                break;
            case (R.id.umbrella_id):
                name = "UMBRELLA";
                break;
            case (R.id.train_id):
                name = "TRAIN";
                break;
            case (R.id.traffic_id):
                name = "TRAFFIC";
                break;

        }
        Log.d("HERE", "onNavigationItemSelected: ");
        Toast.makeText(getApplicationContext(), name + " WAS SELECTED", Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if ((getSupportActionBar().getDisplayOptions() & ActionBar.DISPLAY_HOME_AS_UP) != 0) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            super.onBackPressed();
        }
    }
}
