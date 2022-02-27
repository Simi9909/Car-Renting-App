package com.example.car_rental.fragments;

import static com.example.car_rental.utils.Validation.validateEmail;
import static com.example.car_rental.utils.Validation.validateFields;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.car_rental.R;
import com.example.car_rental.utils.DBHelper;

public class LoginFragment extends Fragment {

    private EditText etEmail;
    private EditText etPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View v) {

        etEmail = v.findViewById(R.id.et_email);
        etPassword = v.findViewById(R.id.et_password);
        Button btnLogin = v.findViewById(R.id.btn_login);
        TextView tvRegister = v.findViewById(R.id.tv_register);

        btnLogin.setOnClickListener(view -> login());
        tvRegister.setOnClickListener(view -> goToRegister());
    }


    Bundle bundle = new Bundle();

    private void login() {

        setError();
        boolean ok = true;
        DBHelper dbHelper = new DBHelper(getActivity());

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (etEmail.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin")) {
            goToAdminPage();
        }

        String user_d = dbHelper.checkIfPasswordMatchesForEmail(email.trim(), password.trim());
        if (user_d.equals("null")) {
            ok = false;
            etEmail.setError("Wrong email or password");
        } else {

            Log.d("User ID", user_d);
            bundle.putString("user_id", user_d);
        }

        if (!validateEmail(email)) {
            ok = false;
            etEmail.setError("Email field can not be empty or email pattern invalid");
        }

        if (!validateFields(password)) {
            ok = false;
            etPassword.setError("Password field can not be empty");
        }

        if (ok) goToCarTypesPage();

    }

    private void goToCarTypesPage() {

        CarTypesFragment carTypesFragment = new CarTypesFragment();
        carTypesFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, carTypesFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    private void goToAdminPage() {
        AdminFragment adminFragment = new AdminFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, adminFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    private void goToRegister() {
        RegisterFragment registerFragment = new RegisterFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, registerFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    private void setError() {

        etEmail.setError(null);
        etPassword.setError(null);
    }
}