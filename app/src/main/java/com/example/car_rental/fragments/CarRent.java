package com.example.car_rental.fragments;

import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.car_rental.R;
import com.example.car_rental.utils.DBHelper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CarRent extends AppCompatActivity {

    TextView tvmanufacturer, tvmodel, tvprice, tvequipment;
    CheckBox checkBox;
    DatePicker dpstart, dpfinish;
    Button btnrentcar;
    long id;
    DateTimeFormatter dateTimeFormatter;
    LocalDate selected_start_date, selected_finish_date;
    String userid, carid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_car_view_layout);

        tvmanufacturer = findViewById(R.id.tv_car_manufacturer);
        tvmodel = findViewById(R.id.tv_car_model);
        tvprice = findViewById(R.id.tv_car_price);
        tvequipment = findViewById(R.id.tv_car_equipment);
        checkBox = findViewById(R.id.driver_checkBox);
        dpstart = findViewById(R.id.date_picker_start);
        dpfinish = findViewById(R.id.date_picker_finish);
        btnrentcar = findViewById(R.id.button_rent);

        id = getIntent().getExtras().getLong("rent_intent_id");
        carid = getIntent().getExtras().getString("car_id");
        String manufacturer = getIntent().getExtras().getString("manufacturer");
        String model = getIntent().getExtras().getString("model");
        String price = getIntent().getExtras().getString("price");
        String equipment = getIntent().getExtras().getString("equipment");
        userid = getIntent().getExtras().getString("user_id");

        tvmanufacturer.setText(manufacturer);
        tvmodel.setText(model);
        tvprice.setText(price);
        tvequipment.setText(equipment);
        dpstart.setMinDate(System.currentTimeMillis());
        dpfinish.setMinDate(System.currentTimeMillis());

        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        selected_start_date = LocalDate.of(dpstart.getYear(), dpstart.getMonth(), dpstart.getDayOfMonth());
        selected_finish_date = LocalDate.of(dpfinish.getYear(), dpfinish.getMonth(), dpfinish.getDayOfMonth());
        String startDate = dateTimeFormatter.format(selected_start_date);
        String finishDate = dateTimeFormatter.format(selected_finish_date);

        Log.d("start", startDate);
        Log.d("finish", finishDate);
        Log.d("user_id", userid);
        Log.d("carid", carid);

        btnrentcar.setOnClickListener(view -> saveRentData());

    }

    private void saveRentData() {

        DBHelper dbHelper = new DBHelper(this);
        boolean ok = true;
        String driverid = null;

        selected_start_date = LocalDate.of(dpstart.getYear(), dpstart.getMonth(), dpstart.getDayOfMonth());
        selected_finish_date = LocalDate.of(dpfinish.getYear(), dpfinish.getMonth(), dpfinish.getDayOfMonth());
        String startDate = dateTimeFormatter.format(selected_start_date);
        String finishDate = dateTimeFormatter.format(selected_finish_date);


        if (selected_finish_date.isBefore(selected_start_date)) {
            ok = false;
            Toast.makeText(this, "Start date cant be after finish date!", Toast.LENGTH_SHORT).show();
        }

        if (checkBox.isChecked()) {

            //Toast.makeText(this, "Assigning driver", Toast.LENGTH_SHORT).show();

            driverid = dbHelper.getAvailableDriver();
            if (driverid.equals("not found")) {
                ok = false;
                Toast.makeText(this, "No available driver", Toast.LENGTH_SHORT).show();
            }

        } else if (!dbHelper.checkIfUserHasDrivingLicenceAdded(userid)) {
            ok = false;
            Toast.makeText(this, "You need a driver to rent cars", Toast.LENGTH_SHORT).show();
        }

        if (dbHelper.checkIfCarIsAvailable(carid, startDate, finishDate)) {
            ok = false;
            Toast.makeText(this, "Car can not be rented for the set dates", Toast.LENGTH_SHORT).show();
        }

        if (ok) {
            Log.d("ok", String.valueOf(true));
            dbHelper.addCarToRentTable(carid, userid, driverid, startDate, finishDate);
            Log.d("rent", String.valueOf(dbHelper.addCarToRentTable(carid, userid, driverid, startDate, finishDate)));

            getSupportFragmentManager().beginTransaction().add(android.R.id.content, new CarTypesFragment()).commit();
            finish();
        }

    }


}
