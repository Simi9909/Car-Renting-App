package com.example.car_rental.fragments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.car_rental.R;

public class EditOrDeleteDriver extends AppCompatActivity {

    EditText etdrivername, etidcardnumber, etphonenumber, etdrivinglicencenumber, etaddress;
    Switch swdriveravailable;
    long id;
    Button btn_driver_save, btn_driver_delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_or_delete_driver);
        id = getIntent().getExtras().getLong("inten_id");
        String name = getIntent().getExtras().getString("name");
        String idcard = getIntent().getExtras().getString("idcard");
        String phonenumber = getIntent().getExtras().getString("phone");
        String drivinglicence = getIntent().getExtras().getString("drivinglicence");
        String address = getIntent().getExtras().getString("address");
        boolean available = Boolean.parseBoolean(getIntent().getExtras().getString("available"));

        etdrivername = findViewById(R.id.ed_driver_name);
        etidcardnumber = findViewById(R.id.ed_driver_id_card_number);
        etphonenumber = findViewById(R.id.ed_phone_number);
        etdrivinglicencenumber = findViewById(R.id.ed_driver_licence_number);
        etaddress = findViewById(R.id.ed_driver_address);
        swdriveravailable = findViewById(R.id.switch_driver_availability);

        etdrivername.setText(name);
        etidcardnumber.setText(idcard);
        etphonenumber.setText(phonenumber);
        etdrivinglicencenumber.setText(drivinglicence);
        etaddress.setText(address);
        swdriveravailable.setChecked(available);

        btn_driver_save = findViewById(R.id.btn_save_driver_update);
        btn_driver_save.setOnClickListener(view -> updateDriver());

        btn_driver_delete = findViewById(R.id.btn_delete_driver);
        btn_driver_delete.setOnClickListener(view -> deleteDriverFromDatabase());
    }

    private void deleteDriverFromDatabase() {

        DriversListFragment.dbHelper.deleteDriverData(id);

        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new AdminFragment()).commit();
        finish();
    }

    private void updateDriver() {

        String name = etdrivername.getText().toString();
        String idcard = etidcardnumber.getText().toString();
        String phone = etphonenumber.getText().toString();
        String drivinglicence = etdrivername.getText().toString();
        String address = etaddress.getText().toString();
        Boolean available = swdriveravailable.isChecked();
        DriversListFragment.dbHelper.updateDriverToNew(id, name, idcard, phone, drivinglicence, address, available);

        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new AdminFragment()).commit();

        finish();
    }

}
