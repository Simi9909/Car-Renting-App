package com.example.car_rental.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.car_rental.R;
import com.example.car_rental.model.Driver;

import java.util.List;

public class DriverListAdapter extends ArrayAdapter<Driver> {

    private Context mContext;
    private int mResource;

    public DriverListAdapter(@NonNull Context context, int resource, List<Driver> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView textViewName = convertView.findViewById(R.id.text_name);
        TextView textViewIdCard = convertView.findViewById(R.id.text_id_card_number);
        TextView textViewPhoneNumber = convertView.findViewById(R.id.text_phone_number);
        TextView textViewDrivingLicence = convertView.findViewById(R.id.text_driving_licence);
        TextView textViewAddress = convertView.findViewById(R.id.text_address);
        TextView textViewAvailable = convertView.findViewById(R.id.text_available);

        String name = getItem(position).getName();
        String idCard = getItem(position).getIdCardNumber();
        Integer phoneNumber = getItem(position).getPhoneNumber();
        String drivingLicence = getItem(position).getDrivingLicenceNumber();
        String address = getItem(position).getAddress();
        Boolean available = getItem(position).getAvailable();

        textViewName.setText(name);
        textViewIdCard.setText(idCard);
        textViewPhoneNumber.setText(String.valueOf(phoneNumber));
        textViewDrivingLicence.setText(drivingLicence);
        textViewAddress.setText(address);
        textViewAvailable.setText(available.toString());

        return convertView;
    }
}
