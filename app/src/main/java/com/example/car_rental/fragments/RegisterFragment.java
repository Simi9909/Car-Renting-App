package com.example.car_rental.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.car_rental.R;
import com.example.car_rental.model.User;
import com.example.car_rental.utils.DBHelper;

import static com.example.car_rental.utils.Validation.validateEmail;
import static com.example.car_rental.utils.Validation.validateFields;


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
        etEmail = v.findViewById(R.id.et_email);
        etIdCardNumber = v.findViewById(R.id.et_id_card_number);
        etDrivingLicenceNumber = v.findViewById(R.id.et_driving_licence_number);
        etPhoneNumber = v.findViewById(R.id.et_phone_number);
        etAddress = v.findViewById(R.id.et_address);
        etPassword = v.findViewById(R.id.et_password);
        etPassword2 = v.findViewById(R.id.et_password2);

        Button btnRegister = v.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(view -> register());
    }

    private void register() {

        User user = new User();
        DBHelper dbHelper = new DBHelper(getActivity());

        setError();
        boolean ok = true;

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String idcard = etIdCardNumber.getText().toString();
        String drivinglicence = etDrivingLicenceNumber.getText().toString();
        Integer phone = Integer.valueOf(etPhoneNumber.getText().toString());
        String address = etAddress.getText().toString();
        String password = etPassword.getText().toString();
        String password2 = etPassword2.getText().toString();

        /*if (dbHelper.checkIfUserIsRegistered(email) == etEmail.getText().toString()) {
            ok = false;
            etEmail.setError("This email is already in use");
        }*/

        if (!validateFields(name)) {
            ok = false;
            etName.setError("Name field can not be empty");
        }

        if (dbHelper.checkIfUserIsRegistered(email.trim())) {
            ok = false;
            etEmail.setError("This email is already in use");
        }

        if (!validateEmail(email)) {
            ok = false;
            etEmail.setError("Email field can not be empty or email pattern invalid");

        }

        if (!validateFields(idcard)) {
            ok = false;
            etIdCardNumber.setError("Id card field can not be empty");
        }

        if (!validateFields(String.valueOf(phone))) {
            ok = false;
            etPhoneNumber.setError("Name field can not be empty");
        }

        if (!validateFields(address)) {
            ok = false;
            etAddress.setError("Address field can not be empty");
        }

        if (!validateFields(password)) {
            ok = false;
            etPassword.setError("Password field can not be empty");
        }

        if (!validateFields(password2)) {
            ok = false;
            etPassword2.setError("Driving licence field can not be empty");
        }

        if (!password.equals(password2)) {
            ok = false;
            etPassword.setError("Passwords need to match");
            etPassword2.setError("Passwords need to match");
        }

        if (ok) {
            try {
                user = new User(0, etName.getText().toString(),
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

    private void setError() {

        etName.setError(null);
        etEmail.setError(null);
        etIdCardNumber.setError(null);
        etDrivingLicenceNumber.setError(null);
        etAddress.setError(null);
        etPassword.setError(null);
        etPassword2.setError(null);

    }
}