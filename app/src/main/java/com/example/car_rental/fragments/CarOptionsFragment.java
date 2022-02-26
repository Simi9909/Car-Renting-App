package com.example.car_rental.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.fragment.app.Fragment;

import com.example.car_rental.R;
import com.example.car_rental.model.Cars;
import com.example.car_rental.utils.DBHelper;

import java.util.List;


public class CarOptionsFragment extends Fragment {

    ListView listView;
    String cartype;
    List<Cars> carList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_options, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {

        listView = view.findViewById(R.id.lv_car_list);

        DBHelper dbHelper = new DBHelper(getActivity());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            cartype = bundle.getString("bundleKey");
            Log.d("bundle", cartype);
        }

        switch (cartype) {
            case "Small car":

                populateListView(dbHelper);
                break;
            case "4/5 door car":

                populateListView(dbHelper);
                break;
            case "SUV":

                populateListView(dbHelper);
                break;
            case "Minivan":

                populateListView(dbHelper);
                break;
            case "Bus":

                populateListView(dbHelper);
                break;
            case "Big bus":

                populateListView(dbHelper);
                break;
        }

    }

    private void populateListView(DBHelper dbHelper) {
        carList = dbHelper.getSelectedCar(cartype);
        SimpleCursorAdapter simpleCursorAdapter = dbHelper.populateListViewFromDB(cartype);
        listView.setAdapter(simpleCursorAdapter);
    }

}