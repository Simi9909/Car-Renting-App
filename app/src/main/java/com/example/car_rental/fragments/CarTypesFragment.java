package com.example.car_rental.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.car_rental.R;


public class CarTypesFragment extends Fragment {


    String id;
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


        Bundle user_id_bundle = this.getArguments();

        if (user_id_bundle != null){
            id = user_id_bundle.getString("user_id");
            Log.d("user_d", ""+id);
        }
    }


    Bundle bundle = new Bundle();

    private void bigBus() {
        bundle.putString("bundleKey", "Big bus");
        goToCarOptions();
    }

    private void bus() {
        bundle.putString("bundleKey", "Bus");
        goToCarOptions();
    }

    private void minivan() {
        bundle.putString("bundleKey", "Minivan");
        goToCarOptions();
    }

    private void suv() {
        bundle.putString("bundleKey", "SUV");
        goToCarOptions();
    }

    private void fourDoorCar() {
        bundle.putString("bundleKey", "4/5 door car");
        goToCarOptions();
    }

    private void smallCar() {
        bundle.putString("bundleKey", "Small car");
        goToCarOptions();
    }

    private void goToCarOptions() {

        bundle.putString("user_id",id);

        CarOptionsFragment carOptionsFragment = new CarOptionsFragment();
        carOptionsFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, carOptionsFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }

}