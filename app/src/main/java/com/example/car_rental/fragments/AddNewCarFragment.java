package com.example.car_rental.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.car_rental.R;
import com.example.car_rental.model.Cars;
import com.example.car_rental.utils.DBHelper;


import static com.example.car_rental.utils.Validation.validateFields;

public class AddNewCarFragment extends Fragment {

    private ImageView selectImage;
    private EditText carManufacturer;
    private EditText carModel;
    private String carType;
    private EditText carPrice;
    private EditText carEquipment;
    private Switch availabilitySwitch2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_car, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {
        carManufacturer = view.findViewById(R.id.carmanufacturer);
        carModel = view.findViewById(R.id.carmodel);
        carPrice = view.findViewById(R.id.carprice);
        carEquipment = view.findViewById(R.id.carequipment);

        Spinner spinner = view.findViewById(R.id.spinner2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                carType = parent.getItemAtPosition(pos).toString();
                Log.d("value from spinner", carType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("herevego", "");
            }
        });
        String[] cartypes2 = getResources().getStringArray(R.array.cartypes2);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.dropdownmenu, cartypes2);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        Button saveButton = view.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(view1 -> save());

        availabilitySwitch2 = view.findViewById(R.id.sw_availability);

        selectImage = view.findViewById(R.id.selectimage);
        selectImage.setOnClickListener(view12 -> openYourActivity());
    }

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Uri image = data.getData();
                    selectImage.setImageURI(image);
                }
            });

    public void openYourActivity() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        launchSomeActivity.launch(intent);
    }

    private void save() {

        setError();
        boolean ok = true;

        Cars cars = null;

        String carmanufacturer = carManufacturer.getText().toString();
        String carmodel = carModel.getText().toString();
        Integer carprice = Integer.valueOf(carPrice.getText().toString());
        String carequipment = carEquipment.getText().toString();


        if (!validateFields(carmanufacturer)) {
            ok = false;
            carManufacturer.setError("Manufacturer field can not be empty");
        }

        if (!validateFields(carmodel)) {
            ok = false;
            carModel.setError("Model field can not be empty");
        }

        if (!validateFields(String.valueOf(carprice))) {
            ok = false;
            carPrice.setError("Price field can not be empty");
        }

        if (!validateFields(carequipment)) {
            ok = false;
            carEquipment.setError("Equipment field can not be empty");
        }

        if (ok) {

            try {
                cars = new Cars(-1, carManufacturer.getText().toString(),
                        carModel.getText().toString(),
                        carType,
                        Integer.parseInt(carPrice.getText().toString()),
                        carEquipment.getText().toString(),
                        availabilitySwitch2.isChecked());

                Log.d("added", cars.toString());
            } catch (Exception e) {
                Log.d(String.valueOf(e), "Error in adding new car");
            }

            DBHelper dbHelper = new DBHelper(getActivity());

            boolean succes = dbHelper.addNewCar(cars);
            Log.d("succes= ", String.valueOf(succes));

            //adding to database TO DO
            AdminFragment adminFragment = new AdminFragment();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentFrame, adminFragment, RegisterFragment.class.getSimpleName())
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void setError() {
        carManufacturer.setError(null);
        carModel.setError(null);
        carPrice.setError(null);
        carEquipment.setError(null);
    }
}