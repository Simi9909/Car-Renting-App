package com.example.car_rental.fragments.adminside;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.car_rental.R;
import com.example.car_rental.utils.DBHelper;


public class AdminFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {

        Spinner spinner = view.findViewById(R.id.spinner);

        DBHelper dbHelper = new DBHelper(getActivity());
        dbHelper.populateListViewFromDB();
        dbHelper.populateDriverListViewFromDB();

        spinner.setOnItemSelectedListener(this);
        String[] cartypes = getResources().getStringArray(R.array.cartypes);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.dropdownmenu, cartypes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        Button buttonAddNewCAar = view.findViewById(R.id.btn_add_car);
        Button buttonShowSelected = view.findViewById(R.id.btn_show);
        Button buttonAddNewDriver = view.findViewById(R.id.btn_add_driver);
        Button buttonShowDrivers = view.findViewById(R.id.btn_show_drivers);

        buttonAddNewCAar.setOnClickListener(view1 -> goToAddNewVehicle());
        buttonShowSelected.setOnClickListener(view1 -> goToShowSelectedCategory());
        buttonAddNewDriver.setOnClickListener(view1 -> goToAddNewDriver());
        buttonShowDrivers.setOnClickListener(view1 -> goToShowDrivers());

    }

    private void goToShowDrivers() {
        DriversListFragment driversListFragment = new DriversListFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, driversListFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    private void goToAddNewDriver() {
        AddDriverFragment addDriverFragment = new AddDriverFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, addDriverFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    private void goToAddNewVehicle() {
        AddNewCarFragment addNewCarFragment = new AddNewCarFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, addNewCarFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    Bundle bundle = new Bundle();
    String valueFormSpinner;

    private void goToShowSelectedCategory() {

        CarsListFragment carsListFragment = new CarsListFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, carsListFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();

        carsListFragment.setArguments(bundle);
        bundle.putString("bundleKey2", valueFormSpinner);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        valueFormSpinner = parent.getItemAtPosition(pos).toString();
        Log.d("value from spinner", valueFormSpinner);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}