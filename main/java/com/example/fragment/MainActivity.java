package com.example.fragment;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentOne.OnFileSelectedListener {

    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (!isLandscape) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new FragmentOne())
                    .commit();
        }
    }

    @Override
    public void onFileSelected(String fileName) {
        FragmentTwo fragment2 = FragmentTwo.newInstance(fileName);

        if (isLandscape) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment2_container, fragment2)
                    .commit();
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment2);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
