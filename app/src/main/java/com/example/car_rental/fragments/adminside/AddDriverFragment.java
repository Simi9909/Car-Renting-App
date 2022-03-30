package com.example.car_rental.fragments.adminside;

import static com.example.car_rental.utils.Validation.validateFields;

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
import com.example.car_rental.fragments.RegisterFragment;
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

        drivername = view.findViewById(R.id.et_driver_name);
        idCardNumber = view.findViewById(R.id.et_driver_id_card_number);
        drivingLicenceNumber = view.findViewById(R.id.et_drivers_driving_licence_number);
        phoneNumber = view.findViewById(R.id.et_driver_phone_number);
        address = view.findViewById(R.id.et_drivers_address);
        availabilitySwitch = view.findViewById(R.id.sw_availability_driver);

        Button saveButton = view.findViewById(R.id.btn_save_driver);
        saveButton.setOnClickListener(view1 -> save());

    }

    private void save() {

        setError();

        Driver driver = null;
        boolean ok = true;

        String drivern = drivername.getText().toString();
        String idcard = idCardNumber.getText().toString();
        Integer phonenumber = Integer.valueOf(phoneNumber.getText().toString());
        String drivinglicence = drivingLicenceNumber.getText().toString();
        String driveraddress = address.getText().toString();

        if (!validateFields(drivern)) {
            ok = false;
            drivername.setError("Name field can not be empty");
        }

        if (!validateFields(idcard)) {
            ok = false;
            idCardNumber.setError("Id card number field can not be empty");
        }

        if (!validateFields(String.valueOf(phonenumber))) {
            ok = false;
            phoneNumber.setError("Phone number field can not be empty");
        }

        if (!validateFields(drivinglicence)) {
            ok = false;
            address.setError("Address field can not be empty");
        }

        if (!validateFields(driveraddress)) {
            ok = false;
            drivingLicenceNumber.setError("Driving licence number field can not be empty");
        }

        if (ok) {

            try {
                driver = new Driver(-1, drivername.getText().toString(),
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

    private void setError() {

        drivername.setError(null);
        idCardNumber.setError(null);
        phoneNumber.setError(null);
        drivingLicenceNumber.setError(null);
        address.setError(null);

    }
}
