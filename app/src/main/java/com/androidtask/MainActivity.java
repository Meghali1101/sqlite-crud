package com.androidtask;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.androidtask.fragments.FirstFragment;

public class MainActivity extends AppCompatActivity {

    MyHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, new FirstFragment(), null).commit();

        mydb = new MyHelper(this);
    }
}