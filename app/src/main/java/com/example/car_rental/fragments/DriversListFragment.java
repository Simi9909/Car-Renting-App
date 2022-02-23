package com.example.car_rental.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.car_rental.R;
import com.example.car_rental.model.Cars;
import com.example.car_rental.model.Driver;
import com.example.car_rental.utils.CarListAdapter;
import com.example.car_rental.utils.DBHelper;
import com.example.car_rental.utils.DriverListAdapter;

import java.util.List;

public class DriversListFragment extends Fragment {

    ListView listView;
    List<Driver> driverList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {

        listView = view.findViewById(R.id.lv_car);

        DBHelper dbHelper = new DBHelper(getActivity());

        driverList = dbHelper.getAllDriver();

        DriverListAdapter driverListAdapter = new DriverListAdapter(getActivity(), R.layout.driver_adapter_view_layout, driverList);
        listView.setAdapter(driverListAdapter);
    }
}