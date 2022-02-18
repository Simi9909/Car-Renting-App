package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.car_rental.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginFragment;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            loadFragment();
        }
    }

    private void loadFragment() {
        if (loginFragment == null) {

            loginFragment = new LoginFragment();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentFrame, loginFragment, MainActivity.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}