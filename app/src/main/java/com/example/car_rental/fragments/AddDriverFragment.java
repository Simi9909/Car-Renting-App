package com.example.car_rental.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.car_rental.R;
import com.example.car_rental.model.Driver;
import com.example.car_rental.utils.DBHelper;

public class AddDriverFragment extends Fragment {

    EditText drivername;
    EditText idCardNumber;
    EditText drivingLicenceNumber;
    EditText phoneNumber;
    EditText address;
    Switch availabilitySwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_driver, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {

        drivername = (EditText) view.findViewById(R.id.et_driver_name);
        idCardNumber = (EditText) view.findViewById(R.id.et_driver_id_card_number);
        drivingLicenceNumber = (EditText) view.findViewById(R.id.et_drivers_driving_licence_number);
        phoneNumber = (EditText) view.findViewById(R.id.et_driver_phone_number);
        address = (EditText) view.findViewById(R.id.et_drivers_address);

        Button saveButton = (Button) view.findViewById(R.id.btn_save_driver);
        saveButton.setOnClickListener(view1 -> save());

        availabilitySwitch = (Switch) view.findViewById(R.id.sw_availability_driver);

    }

    private void save() {

        Driver driver = null;

        try {
            driver = new Driver(0, drivername.getText().toString(),
                    idCardNumber.getText().toString(),
                    Integer.parseInt(phoneNumber.getText().toString()),
                    drivingLicenceNumber.getText().toString(),
                    address.getText().toString(),
                    availabilitySwitch.isChecked());

            Log.d("added", driver.toString());
        } catch (Exception e) {
            Log.d(String.valueOf(e), "Error in adding new driver");
        }

        DBHelper dbHelper = new DBHelper(getActivity());

        boolean succes = dbHelper.addNewDriver(driver);
        Log.d("Succes: ", String.valueOf(succes));

        AdminFragment adminFragment = new AdminFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, adminFragment, RegisterFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}
