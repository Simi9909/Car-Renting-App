package com.example.car_rental.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.car_rental.R;

import java.util.Objects;


public class SelectionFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selection, container, false);


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TextView text = this.findViewById(R.id.text);

        EditText text = (EditText) Objects.requireNonNull(getActivity()).findViewById(R.id.text);
        Bundle bundle = getArguments();
        if (bundle!=null) {
            String selectedcategory = bundle.getString("bundleKey2");
            Log.d("kapott srtnig", selectedcategory);
            Log.d("Eddig jut", selectedcategory);
            //text.setText(selectedcategory);
        }
    }


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {

        TextView text = view.findViewById(R.id.text);

        Bundle bundle = getArguments();
        if (bundle!=null) {
            String selectedcategory = bundle.getString("bundleKey2");
            Log.d("kapott srtnig", selectedcategory);
            Log.d("Eddig jut", selectedcategory);
            //text.setText(selectedcategory);
        }

    }*/

}