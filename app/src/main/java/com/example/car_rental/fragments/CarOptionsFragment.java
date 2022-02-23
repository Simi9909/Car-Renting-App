package com.example.car_rental.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.car_rental.R;
import com.example.car_rental.model.Cars;
import com.example.car_rental.utils.CarListAdapter;
import com.example.car_rental.utils.DBHelper;

import java.util.List;


public class CarOptionsFragment extends Fragment {

    ListView listView;
    String cartype;
    ArrayAdapter carsArrayAdapter;
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

        if (cartype.equals("Small car")) {

            carList = dbHelper.getSelectedCar(cartype);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (cartype.equals("4/5 door car")) {

            carList = dbHelper.getSelectedCar(cartype);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (cartype.equals("SUV")) {

            carList = dbHelper.getSelectedCar(cartype);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (cartype.equals("Minivan")) {

            carList = dbHelper.getSelectedCar(cartype);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (cartype.equals("Bus")) {

            carList = dbHelper.getSelectedCar(cartype);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        } else if (cartype.equals("Big bus")) {

            carList = dbHelper.getSelectedCar(cartype);

            CarListAdapter carListAdapter = new CarListAdapter(getActivity(), R.layout.car_adapter_view_layout, carList);
            listView.setAdapter(carListAdapter);
        }

    }

}