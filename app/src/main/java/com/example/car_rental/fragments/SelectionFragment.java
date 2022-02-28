package com.example.car_rental.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.car_rental.R;
import com.example.car_rental.utils.DBHelper;


public class SelectionFragment extends Fragment {

    String selectionvalue;
    static DBHelper dbHelper;

    SimpleCursorAdapter simpleCursorAdapter = null;
    SimpleCursorAdapter simpleCursorAdapterForSelectedCarCategory = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {

        ListView listView = view.findViewById(R.id.lv_car);

        dbHelper = new DBHelper(getActivity());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            selectionvalue = bundle.getString("bundleKey2");
            Log.d("Success ", selectionvalue);

            simpleCursorAdapter = dbHelper.populateListViewFromDB();
            simpleCursorAdapterForSelectedCarCategory = dbHelper.populateListViewFromDBAdmin(selectionvalue);
        }

        switch (selectionvalue) {
            case "All":
                listView.setAdapter(simpleCursorAdapter);
                listView.setOnItemClickListener((parent, view1, pos, id) -> {
                    Cursor cursor = (Cursor) simpleCursorAdapter.getItem(pos);
                    setIntentValues(id, cursor);
                });
                break;
            case "Small car":
                setContentForSelectedCarCategory(listView);
            case "4/5 door car":
                setContentForSelectedCarCategory(listView);
            case "SUV":
                setContentForSelectedCarCategory(listView);
            case "Minivan":
                setContentForSelectedCarCategory(listView);
            case "Bus":
                setContentForSelectedCarCategory(listView);
            case "Big bus":
                setContentForSelectedCarCategory(listView);
                break;
        }
    }

    private void setContentForSelectedCarCategory(ListView listView) {
        listView.setAdapter(simpleCursorAdapterForSelectedCarCategory);
        listView.setOnItemClickListener((parent, view1, pos, id) -> {
            Cursor cursor = (Cursor) simpleCursorAdapterForSelectedCarCategory.getItem(pos);
            setIntentValues(id, cursor);
        });
    }

    private void setIntentValues(long id, Cursor cursor) {
        String manufacturer = cursor.getString(2);
        String model = cursor.getString(3);
        String price = cursor.getString(5);
        String equipment = cursor.getString(6);
        String available = cursor.getString(7);

        Intent intent = new Intent(getActivity(), EditOrDelete.class);
        intent.putExtra("intent_id", id);
        intent.putExtra("manufacturer", manufacturer);
        intent.putExtra("model", model);
        intent.putExtra("price", price);
        intent.putExtra("equipment", equipment);
        intent.putExtra("available", available);

        startActivity(intent);
    }
}