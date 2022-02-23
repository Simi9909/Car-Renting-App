package com.example.car_rental.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.car_rental.R;
import com.example.car_rental.model.Cars;
import com.example.car_rental.utils.CarListAdapter;
import com.example.car_rental.utils.DBHelper;

import java.util.List;


public class SelectionFragment extends Fragment {

    ListView listView;
    String selectionvalue;
    ArrayAdapter carsArrayAdapter;
    List<Cars> carList;

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

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            selectionvalue = bundle.getString("bundleKey2");
            Log.d("Success ", selectionvalue);
        }

        if (selectionvalue.equals("All")) {
            carList = dbHelper.getAllCar();

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (selectionvalue.equals("Small car")) {

            carList = dbHelper.getSelectedCar(selectionvalue);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (selectionvalue.equals("4/5 door car")) {

            carList = dbHelper.getSelectedCar(selectionvalue);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (selectionvalue.equals("SUV")) {

            carList = dbHelper.getSelectedCar(selectionvalue);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (selectionvalue.equals("Minivan")) {

            carList = dbHelper.getSelectedCar(selectionvalue);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (selectionvalue.equals("Bus")) {

            carList = dbHelper.getSelectedCar(selectionvalue);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (selectionvalue.equals("Big bus")) {

            carList = dbHelper.getSelectedCar(selectionvalue);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        }
    }

}