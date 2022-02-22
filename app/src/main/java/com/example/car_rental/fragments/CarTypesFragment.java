package com.example.car_rental.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.car_rental.R;


public class CarTypesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_types, container, false);
        initViews(view);
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    private void initViews(View v) {

        ImageView smallCarImage = v.findViewById(R.id.smallcarimage);
        TextView smallCar = v.findViewById(R.id.small_car);
        ImageView fourDoorCarImage = v.findViewById(R.id.sedanimage);
        TextView fourdoorCar = v.findViewById(R.id.sedan);
        ImageView suvCarImage = v.findViewById(R.id.suvimage);
        TextView suvCar = v.findViewById(R.id.suv);
        ImageView minivanImage = v.findViewById(R.id.minivanimage);
        TextView minivan = v.findViewById(R.id.minivan);
        ImageView busImage = v.findViewById(R.id.busimage);
        TextView bus = v.findViewById(R.id.bus);
        ImageView bigBusImage = v.findViewById(R.id.bigbusimage);
        TextView bigBus = v.findViewById(R.id.bigbus);

        smallCar.setOnClickListener(view -> smallCar());
        smallCarImage.setOnClickListener(view -> smallCar());
        fourdoorCar.setOnClickListener(view -> fourDoorCar());
        fourDoorCarImage.setOnClickListener(view -> fourDoorCar());
        suvCar.setOnClickListener(view -> suv());
        suvCarImage.setOnClickListener(view -> suv());
        minivan.setOnClickListener(view -> minivan());
        minivanImage.setOnClickListener(view -> minivan());
        bus.setOnClickListener(view -> bus());
        busImage.setOnClickListener(view -> bus());
        bigBus.setOnClickListener(view -> bigBus());
        bigBusImage.setOnClickListener(view -> bigBus());
    }


    Bundle bundle = new Bundle();

    private void bigBus() {
        bundle.putString("bundleKey", "bigbus");
        goToCarOptions();
    }

    private void bus() {
        bundle.putString("bundleKey", "bus");
        goToCarOptions();
    }

    private void minivan() {
        bundle.putString("bundleKey", "minivan");
        goToCarOptions();
    }

    private void suv() {
        bundle.putString("bundleKey", "suv");
        goToCarOptions();
    }

    private void fourDoorCar() {
        bundle.putString("bundleKey", "sedan");
        goToCarOptions();
    }

    private void smallCar() {
        bundle.putString("bundleKey", "small");
        goToCarOptions();
    }

    private void goToCarOptions() {

        CarOptionsFragment carOptionsFragment = new CarOptionsFragment();
        carOptionsFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, carOptionsFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

}