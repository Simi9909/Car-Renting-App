package com.example.car_rental.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.car_rental.R;
import com.example.car_rental.model.Cars;

import java.util.List;

public class CarListAdapter extends ArrayAdapter<Cars> {

    private Context mContext;
    private int mResource;

    public CarListAdapter(@NonNull Context context, int resource, List<Cars> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView textViewManufacturer = convertView.findViewById(R.id.text_manufacturer);
        TextView textViewModel = convertView.findViewById(R.id.text_model);
        TextView textViewEquipment = convertView.findViewById(R.id.text_equipment);
        TextView textViewPrice = convertView.findViewById(R.id.text_price);
        /*TextView textViewId = convertView.findViewById(R.id.text_id);
        TextView textViewCarType = convertView.findViewById(R.id.text_type);
        TextView textViewAvailable = convertView.findViewById(R.id.text_ava);*/

        String manufacturer = getItem(position).getManufacturer();
        String model = getItem(position).getModel();
        String equipment = getItem(position).getEquipment();
        Integer price = getItem(position).getPrice();
        /*Integer id = getItem(position).getId();
        String cartype = getItem(position).getCarType();
        Boolean available = getItem(position).getAvailable();*/

        textViewManufacturer.setText(manufacturer);
        textViewModel.setText(model);
        textViewEquipment.setText(equipment);
        textViewPrice.setText(String.valueOf(price));
        /*textViewId.setText(id);
        textViewCarType.setText(cartype);
        textViewAvailable.setText(available.toString());*/

        return convertView;
    }
}
