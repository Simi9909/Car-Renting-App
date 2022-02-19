package com.example.car_rental.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.car_rental.R;

public class LoginFragment extends Fragment {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View v) {

        etEmail = (EditText) v.findViewById(R.id.et_email);
        etPassword = (EditText) v.findViewById(R.id.et_password);
        btnLogin = (Button) v.findViewById(R.id.btn_login);
        tvRegister = (TextView) v.findViewById(R.id.tv_register);

        btnLogin.setOnClickListener(view -> login());
        tvRegister.setOnClickListener(view -> goToRegister());
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/


    private void login() {
        if (etEmail.getText().equals("admin")) {
            goToAdminPage();
        } else {
            goToCarTypesPage();
        }
    }

    private void goToCarTypesPage() {
        CarTypesFragment carTypesFragment = new CarTypesFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, carTypesFragment, RegisterFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    private void goToAdminPage() {
        AdminFragment adminFragment = new AdminFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, adminFragment, RegisterFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    private void goToRegister(){
        RegisterFragment registerFragment = new RegisterFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, registerFragment, RegisterFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }


}