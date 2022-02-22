package com.example.car_rental.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.car_rental.R;


public class SelectionFragment extends Fragment {

    //private TextView text;

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_selection, container, false);


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = getArguments();
        Log.d("bundle", String.valueOf(bundle));
        if (bundle!=null) {
            String selectedcategory = bundle.getString("bundleKey2");
            Log.d("kapott srtnig", selectedcategory);
            Log.d("Eddig jut", selectedcategory);
            String value = String.valueOf(bundle);
            text.setText(value+"");
        }
    }*/


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);


        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View v) {

        TextView text = v.findViewById(R.id.text);
        Bundle bundle = getArguments();
        if (bundle!=null) {
            String selectedcategory = bundle.getString("bundleKey2");
            Log.d("kapott srtnig", selectedcategory);
            Log.d("Eddig jut", selectedcategory);
            text.setText("asdfdsa");
        }

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View view) {

        TextView text = view.findViewById(R.id.text);

        Bundle bundle = this.getArguments();
        if (bundle!=null) {
            String selectionvalue = bundle.getString("bundleKey2");
            text.setText(selectionvalue);
        }
    }

}