package com.example.car_rental.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.car_rental.R;


public class CarOptionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_options, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View v) {

        TextView text = v.findViewById(R.id.text);

        Bundle bundle = this.getArguments();
        if (bundle!=null) {
            String cartype = bundle.getString("bundleKey");
            text.setText(cartype);
        }
    }
}