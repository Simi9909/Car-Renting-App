package com.example.car_rental.fragments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.car_rental.R;

public class EditOrDelete extends AppCompatActivity {

    EditText etmanufacturer, etmodel, etprice, etequipment;
    Switch swavailable;
    long id;
    Button btn_save, btn_delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_or_delete);
        id = getIntent().getExtras().getLong("intent_id");
        String manufacturer = getIntent().getExtras().getString("manufacturer");
        String model = getIntent().getExtras().getString("model");
        String price = getIntent().getExtras().getString("price");
        String equipment = getIntent().getExtras().getString("equipment");
        String available = getIntent().getExtras().getString("available");

        etmanufacturer = findViewById(R.id.ed_car_manufacture);
        etmodel = findViewById(R.id.ed_car_model);
        etprice = findViewById(R.id.ed_car_price);
        etequipment = findViewById(R.id.ed_car_equipment);
        swavailable = findViewById(R.id.switch_car_available);

        etmanufacturer.setText(manufacturer);
        etmodel.setText(model);
        etprice.setText(price);
        etequipment.setText(equipment);
        if (available.equals("1")) {
            swavailable.setChecked(true);
        } else swavailable.setChecked(false);

        btn_save = findViewById(R.id.btn_save_car_update);
        btn_save.setOnClickListener(view -> updateCarData());

        btn_delete = findViewById(R.id.btn_delete_car);
        btn_delete.setOnClickListener(view -> deleteCardFromDatabase());
    }

    private void deleteCardFromDatabase() {

        SelectionFragment.dbHelper.deleteCarData(id);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new AdminFragment()).commit();
        finish();
    }

    public void updateCarData(){

        String manufacturer = etmanufacturer.getText().toString();
        String model = etmodel.getText().toString();
        String price = etprice.getText().toString();
        String equipment = etequipment.getText().toString();
        Boolean available = swavailable.isChecked();
        SelectionFragment.dbHelper.updateCarDataToNew(id, manufacturer, model, price, equipment, available);


        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new AdminFragment()).commit();

        finish();

    }
}
