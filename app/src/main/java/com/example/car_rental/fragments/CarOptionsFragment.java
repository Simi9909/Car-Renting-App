package com.example.car_rental.fragments;

import android.content.Intent;
import android.database.Cursor;
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
    String cartype, userid;
    List<Cars> carList;
    SimpleCursorAdapter simpleCursorAdapter;

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
        Log.d("bundle", String.valueOf(bundle));

        if(bundle != null) {
            cartype = bundle.getString("bundleKey");
            userid = bundle.getString("user_id");
            Log.d("cartype", cartype);
            Log.d("userid", userid);
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
        listView.setOnItemClickListener((parent, view1, pos, id) -> {
            Cursor cursor = (Cursor) simpleCursorAdapter.getItem(pos);
            setIntentValues(id, cursor);
        });
    }

    private void populateListView(DBHelper dbHelper) {

        carList = dbHelper.getSelectedCar(cartype);
        simpleCursorAdapter = dbHelper.populateListViewFromDB(cartype);
        listView.setAdapter(simpleCursorAdapter);

    }

    private void setIntentValues(long id, Cursor cursor) {

        String car_id = cursor.getString(1);
        Log.d("cardid car options",car_id);
        String manufacturer = cursor.getString(2);
        String model = cursor.getString(3);
        String price = cursor.getString(5);
        String equipment = cursor.getString(6);
        String available = cursor.getString(7);

        Intent intent = new Intent(getActivity(), CarRent.class);
        intent.putExtra("rent_intent_id", id);
        intent.putExtra("car_id", car_id);
        intent.putExtra("manufacturer", manufacturer);
        intent.putExtra("model", model);
        intent.putExtra("price", price);
        intent.putExtra("equipment", equipment);
        intent.putExtra("available", available);
        intent.putExtra("user_id", userid);
        startActivity(intent);
    }

}