package com.example.car_rental.fragments.userside;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.car_rental.R;
import com.example.car_rental.utils.DBHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CarRent extends AppCompatActivity {

    TextView tvmanufacturer, tvmodel, tvprice, tvequipment;
    CheckBox checkBox;
    DatePicker dpstart, dpfinish;
    Button btnrentcar;
    long id;
    DateTimeFormatter dateTimeFormatter;
    LocalDate selected_start_date, selected_finish_date;
    String userid, carid;
    DBHelper dbHelper = new DBHelper(this);

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
        //selected_start_date = LocalDate.of(dpstart.getYear(), dpstart.getMonth(), dpstart.getDayOfMonth());
        //selected_finish_date = LocalDate.of(dpfinish.getYear(), dpfinish.getMonth(), dpfinish.getDayOfMonth());
        //String startDate = dateTimeFormatter.format(selected_start_date);
        //String finishDate = dateTimeFormatter.format(selected_finish_date);

        //Log.d("start", startDate);
        //Log.d("finish", finishDate);
        Log.d("user_id", userid);
        Log.d("carid", carid);

        btnrentcar.setOnClickListener(view -> saveRentData());

    }

    private void saveRentData() {


        boolean ok = true;
        String driverid = null;

        selected_start_date = LocalDate.of(dpstart.getYear(), dpstart.getMonth(), dpstart.getDayOfMonth());
        selected_finish_date = LocalDate.of(dpfinish.getYear(), dpfinish.getMonth(), dpfinish.getDayOfMonth());
        String startDate = dateTimeFormatter.format(selected_start_date);
        String finishDate = dateTimeFormatter.format(selected_finish_date);
        Log.d("car date start", startDate);


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
            verifyStoragePermissions();
            generatePDF(Integer.parseInt(userid), Integer.parseInt(carid));
            //Toast.makeText(this, "Car successfully rented!", Toast.LENGTH_LONG).show();
            dbHelper.addCarToRentTable(carid, driverid, userid, startDate, finishDate);
            Log.d("rent", String.valueOf(dbHelper.addCarToRentTable(carid, userid, driverid, startDate, finishDate)));

            getSupportFragmentManager().beginTransaction().add(android.R.id.content, new CarTypesFragment()).commit();
            finish();
        }

    }

    private void generatePDF(int id, int carid) {

        PdfDocument pdfDocument = new PdfDocument();

        Paint paint = new Paint();

        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(720, 1080, 1).create();

        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        Canvas canvas = myPage.getCanvas();
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        paint.setTextSize(15);
        canvas.drawText("Rental confirmation!", 300, 100, paint);
        String username = dbHelper.getUserNameByID(id);
        canvas.drawText("Dear " + username, 100, 125, paint);
        String car = dbHelper.getCarDataByID(carid);
        canvas.drawText("You successfully rented our " + car + " from " + selected_start_date
                + " till " + selected_finish_date, 100, 150, paint);
        canvas.drawText("Car Rental", 600, 940, paint);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        canvas.drawText(dateTimeFormatter.format(now), 550, 980, paint);

        pdfDocument.finishPage(myPage);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "CarRentConfirmation.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));

            Toast.makeText(this, "Car successfully rented!\nConfirmation PDF created!", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            e.printStackTrace();
        }

        pdfDocument.close();
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public boolean verifyStoragePermissions() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return true;
        } return false;
    }


}
