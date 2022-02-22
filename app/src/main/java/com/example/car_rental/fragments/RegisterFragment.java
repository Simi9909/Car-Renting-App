package com.example.car_rental.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.car_rental.MainActivity;
import com.example.car_rental.R;
import com.example.car_rental.model.User;
import com.example.car_rental.utils.DBHelper;


public class RegisterFragment extends Fragment {

    EditText etName;
    EditText etEmail;
    EditText etIdCardNumber;
    EditText etDrivingLicenceNumber;
    EditText etPhoneNumber;
    EditText etAddress;
    EditText etPassword;
    EditText etPassword2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View v) {

        etName = v.findViewById(R.id.et_name);
        etEmail = (EditText) v.findViewById(R.id.et_email);
        etIdCardNumber = (EditText) v.findViewById(R.id.et_id_card_number);
        etDrivingLicenceNumber = (EditText) v.findViewById(R.id.et_driving_licence_number);
        etPhoneNumber = (EditText) v.findViewById(R.id.et_phone_number);
        etAddress = (EditText) v.findViewById(R.id.et_address);
        etPassword = (EditText) v.findViewById(R.id.et_password);
        etPassword2 = (EditText) v.findViewById(R.id.et_password2);

        Button btnRegister = (Button) v.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(view -> register());
    }

    private void register() {

        User user = new User();

        try {
            user = new User(-1, etName.getText().toString(),
                    etEmail.getText().toString(),
                    etIdCardNumber.getText().toString(),
                    etDrivingLicenceNumber.getText().toString(),
                    Integer.parseInt(etPhoneNumber.getText().toString()),
                    etAddress.getText().toString(),
                    etPassword.getText().toString());
            Log.d("Added", user.toString());
        } catch (Exception e) {
            Log.d(String.valueOf(e), "Error in register");
        }

        DBHelper dbHelper = new DBHelper(getActivity());

        boolean succes = dbHelper.registerNewUser(user);
        Log.d("succes= ", String.valueOf(succes));

        LoginFragment loginFragment = new LoginFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, loginFragment, LoginFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}