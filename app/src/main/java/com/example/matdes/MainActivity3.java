package com.example.matdes;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    CardView card1, card2;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        floatingActionButton = findViewById(R.id.fab_id);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "HELLO", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });


        recyclerView=findViewById(R.id.recyclerView);
        List<GameDesc> gameDescs=new ArrayList<>();

       gameDescs.add(new GameDesc("Angry Birds",R.drawable.angrybirds));
        gameDescs.add(new GameDesc("Asphalt 8",R.drawable.asphalt8));
        gameDescs.add(new GameDesc("Cut The Rope",R.drawable.cuttherope));
        gameDescs.add(new GameDesc("Fruit Ninja",R.drawable.fruitninja));
        gameDescs.add(new GameDesc("Pou",R.drawable.pou));

        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(this,gameDescs);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
      //  GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
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
}