package com.example.car_rental.fragments.adminside;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.car_rental.R;
import com.example.car_rental.utils.DBHelper;


public class DriversListFragment extends Fragment {

    static DBHelper dbHelper;
    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drivers_list, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {

        ListView listView = view.findViewById(R.id.lv_driver);

        dbHelper = new DBHelper(getActivity());
        simpleCursorAdapter = dbHelper.populateDriverListViewFromDB();
        listView.setAdapter(simpleCursorAdapter);

        listView.setOnItemClickListener((parent, view1, pos, id) -> {
            Cursor cursor = (Cursor) simpleCursorAdapter.getItem(pos);
            setIntentValues(id, cursor);
        });
    }

    private void setIntentValues(long id, Cursor cursor) {

        String name = cursor.getString(2);
        String idcard = cursor.getString(3);
        String phonenumber = cursor.getString(4);
        String drivinglicence = cursor.getString(5);
        String address = cursor.getString(6);
        String available = cursor.getString(7);

        Intent intent = new Intent(getActivity(), EditOrDeleteDriver.class);
        intent.putExtra("inten_id", id);
        intent.putExtra("name", name);
        intent.putExtra("idcard", idcard);
        intent.putExtra("phone", phonenumber);
        intent.putExtra("drivinglicence", drivinglicence);
        intent.putExtra("address", address);
        intent.putExtra("available", available);

        startActivity(intent);
    }
}