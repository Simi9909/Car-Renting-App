package com.example.car_rental.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.car_rental.R;


public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View v) {

        EditText etName = v.findViewById(R.id.et_name);
        EditText etEmail = (EditText) v.findViewById(R.id.et_email);
        EditText etIdCardNumber = (EditText) v.findViewById(R.id.et_id_card_number);
        EditText etDrivingLicenceNumber = (EditText) v.findViewById(R.id.et_driving_licence_number);
        EditText etPhoneNumber = (EditText) v.findViewById(R.id.et_phone_number);
        EditText etAddress = (EditText) v.findViewById(R.id.et_address);
        EditText etPassword = (EditText) v.findViewById(R.id.et_password);
        EditText etPassword2 = (EditText) v.findViewById(R.id.et_password2);

        Button btnRegister = (Button) v.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(view -> register());
    }

    private void register() {
        LoginFragment loginFragment = new LoginFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, loginFragment, LoginFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}