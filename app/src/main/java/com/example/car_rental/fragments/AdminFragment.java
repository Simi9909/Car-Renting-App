package com.example.car_rental.fragments;

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

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);
        String[] cartypes = getResources().getStringArray(R.array.cartypes);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.dropdownmenu, cartypes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        Button buttonAdd = (Button) view.findViewById(R.id.btn_add);
        Button buttonShowSelected = (Button) view.findViewById(R.id.btn_show);

        buttonAdd.setOnClickListener(view1 -> goToAddNewVehicle());
        buttonShowSelected.setOnClickListener(view1 -> goToShowSelectedCategory());

    }

    Bundle bundle = new Bundle();

    private void goToShowSelectedCategory() {
        SelectionFragment selectionFragment = new SelectionFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, selectionFragment, RegisterFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    private void goToAddNewVehicle() {
        AddNewCarFragment addNewCarFragment = new AddNewCarFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, addNewCarFragment, RegisterFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent.getId() == R.id.spinner) {
            String valueFormSpinner = parent.getItemAtPosition(pos).toString();
            Log.d("value from spinner", valueFormSpinner);
            bundle.putString("bundleKey", valueFormSpinner);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}