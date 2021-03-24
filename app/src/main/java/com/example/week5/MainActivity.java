package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_FirstFragment, btn_SecondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_FirstFragment=findViewById(R.id.FirstFragment);
        btn_SecondFragment=findViewById(R.id.SecondFragment);

        btn_FirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.main, new FirstFragment()).addToBackStack(null).commit();
            }
        });

       /* btn_SecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.main, new SecondFragment()).addToBackStack(null).commit();
            }
        });*/

    }
}