package com.example.matdes;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static Snackbar snackbar;
    FloatingActionButton floatingActionButton;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    ViewPager viewPager,viewPager2;
    SlideShowAdapter adapter;
    CircleIndicator indicator;
    Handler handler;
    Runnable runnable;
    Timer timer;
    TabLayout tabLayout;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case (android.R.id.home): {
                Toast.makeText(getApplicationContext(), "HOMIE", Toast.LENGTH_LONG).show();
                finish();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager=findViewById(R.id.viewpager_id);
        adapter=new SlideShowAdapter(this);
        viewPager2=findViewById(R.id.viewpager2_id);
        viewPager.setAdapter(adapter);
        indicator=findViewById(R.id.circleindicator_id);
        indicator.setViewPager(viewPager);
        handler=new Handler();
        timer=new Timer();
        runnable=new Runnable() {
            @Override
            public void run() {
                int i=viewPager.getCurrentItem();
                i++;
                if (i==adapter.images.length)
                {
                    i=0;
                }
                viewPager.setCurrentItem(i,true);
            }
        };
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },4000,2000);

bottomNavigationView=findViewById(R.id.bottomnav_id);
bottomNavigationView.setOnNavigationItemSelectedListener(this);
        floatingActionButton=findViewById(R.id.float2_id);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MakeSnackBar("HELLO SNACKBAR");
            }
        });

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        viewPagerAdapter.AddFragment(new one_frag(),"FRAGMENT 1");
        viewPagerAdapter.AddFragment(new two_frag(),"FRAGMENT 2");
        viewPagerAdapter.AddFragment(new three_frag(),"FRAGMENT 3");
        viewPager2.setAdapter(viewPagerAdapter);
        tabLayout=findViewById(R.id.tablayout_id);
        tabLayout.setupWithViewPager(viewPager2);
       
getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        String name=null;
        switch (id)
        {
            case R.id.triangle_id:
            {name="TRIANGLE";break;}
            case R.id.heart_id:
            {name="HEART";break;}
            case R.id.star_id:
            {name="STAR";break;}
        }

        MakeSnackBar("THIS IS "+name);
        return true;
    }

    public void MakeSnackBar(String text)
    {
        snackbar=Snackbar.make(findViewById(R.id.coord_layout),text,Snackbar.LENGTH_INDEFINITE);
        //   snackbar.setAnchorView(findViewById(R.id.bottomnav_id));
        snackbar.setDuration(2000);
        View view=snackbar.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorSnackBarBG));
        TextView txt=(TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        txt.setTextColor(getResources().getColor(R.color.colorSnackBarText));
        TextView txtAction=(TextView) view.findViewById(com.google.android.material.R.id.snackbar_action);
        txtAction.setTextColor(getResources().getColor(R.color.colorSnackBarAction));
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

}