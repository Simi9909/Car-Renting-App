package com.example.car_rental.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.car_rental.R;


public class AddNewCarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_car, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {
        EditText carManufacturer = (EditText) view.findViewById(R.id.carmanufacturer);
        EditText carModel = (EditText) view.findViewById(R.id.carmodel);
        EditText carPrice = (EditText) view.findViewById(R.id.carprice);
        EditText carEquipment = (EditText) view.findViewById(R.id.carequipment);

        Button saveButton = (Button) view.findViewById(R.id.btn_save);

        saveButton.setOnClickListener(view1 -> save());
    }

    private void save() {
        //adding to database TO DO
        AdminFragment adminFragment = new AdminFragment();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, adminFragment, RegisterFragment.class.getSimpleName())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}