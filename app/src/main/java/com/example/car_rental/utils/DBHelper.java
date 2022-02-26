package com.example.car_rental.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import com.example.car_rental.R;
import com.example.car_rental.model.Cars;
import com.example.car_rental.model.Driver;
import com.example.car_rental.model.RentedCars;
import com.example.car_rental.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String COLUMN_DRIVER_ID = "DRIVER_ID";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_ID_CARD_NUMBER = "USER_ID_CARD_NUMBER";
    public static final String COLUMN_USER_DRIVING_LICENCE_NUMBER = "USER_DRIVING_LICENCE_NUMBER";
    public static final String COLUMN_USER_PHONE_NUMBER = "USER_PHONE_NUMBER";
    public static final String COLUMN_USER_ADDRESS = "USER_ADDRESS";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String DRIVERS_TABLE = "DRIVERS_TABLE";
    public static final String COLUMN_DRIVER_NAME = "DRIVER_NAME";
    public static final String COLUMN_DRIVER_ID_CARD_NUMBER = "DRIVER_ID_CARD_NUMBER";
    public static final String COLUMN_DRIVER_PHONE_NUMBER = "DRIVER_PHONE_NUMBER";
    public static final String COLUMN_DRIVER_DRIVING_LICENCE_NUMBER = "DRIVER_DRIVING_LICENCE_NUMBER";
    public static final String COLUMN_DRIVER_ADDRESS = "DRIVER_ADDRESS";
    public static final String COLUMN_DRIVER_AVAILABILITY = "DRIVER_AVAILABILITY";
    public static final String CARS_TABLE = "CARS_TABLE";
    public static final String COLUMN_CAR_MANUFACTURER = "CAR_MANUFACTURER";
    public static final String COLUMN_CAR_MODEL = "CAR_MODEL";
    public static final String COLUMN_CAR_TYPE = "CAR_TYPE";
    public static final String COLUMN_CAR_PRICE = "CAR_PRICE";
    public static final String COLUMN_CAR_EQUIPMENT = "CAR_EQUIPMENT";
    public static final String COLUMN_CAR_AVAILABILITY = "CAR_AVAILABILITY";
    public static final String COLUMN_CAR_ID = "CAR_ID";
    public static final String CARS_IN_RENT_TABLE = "CARS_IN_RENT_TABLE";
    public static final String COLUMN_RENT_START_TIME = "RENT_START_TIME";
    public static final String COLUMN_RENT_FINISH_TIME = "RENT_FINISH_TIME";

    SQLiteDatabase databaseWrite;
    ContentValues contentValues;
    Context context;

    public DBHelper(@Nullable Context context) {
        super(context, "car_rental.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableUsers = "CREATE TABLE " + USERS_TABLE + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAME + " TEXT, "
                + COLUMN_USER_EMAIL + " TEXT, "
                + COLUMN_USER_ID_CARD_NUMBER + " TEXT, "
                + COLUMN_USER_DRIVING_LICENCE_NUMBER + " TEXT, "
                + COLUMN_USER_PHONE_NUMBER + " INTEGER, "
                + COLUMN_USER_ADDRESS + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT)";

        sqLiteDatabase.execSQL(createTableUsers);


        String createTableDriver = "CREATE TABLE " + DRIVERS_TABLE + " ("
                + COLUMN_DRIVER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DRIVER_NAME + " TEXT, "
                + COLUMN_DRIVER_ID_CARD_NUMBER + " TEXT, "
                + COLUMN_DRIVER_PHONE_NUMBER + " INTEGER, "
                + COLUMN_DRIVER_DRIVING_LICENCE_NUMBER + " TEXT, "
                + COLUMN_DRIVER_ADDRESS + " TEXT, "
                + COLUMN_DRIVER_AVAILABILITY + " BOOL)";

        sqLiteDatabase.execSQL(createTableDriver);


        String createTableCars = "CREATE TABLE " + CARS_TABLE + " ("
                + COLUMN_CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CAR_MANUFACTURER + " TEXT, "
                + COLUMN_CAR_MODEL + " TEXT, "
                + COLUMN_CAR_TYPE + " TEXT, "
                + COLUMN_CAR_PRICE + " INTEGER, "
                + COLUMN_CAR_EQUIPMENT + " TEXT, "
                + COLUMN_CAR_AVAILABILITY + " BOOL)";

        sqLiteDatabase.execSQL(createTableCars);


        String createTableCarsInRent = "CREATE TABLE " + CARS_IN_RENT_TABLE + " ("
                + COLUMN_CAR_ID + " INTEGER, "
                + COLUMN_DRIVER_ID + " INTEGER, "
                + COLUMN_USER_ID + " INTEGER, "
                + COLUMN_RENT_START_TIME + " TEXT, "
                + COLUMN_RENT_FINISH_TIME + " TEXT, "
                + " FOREIGN KEY (CAR_ID) REFERENCES CARS_TABLE (CAR_ID),"
                + " FOREIGN KEY (DRIVER_ID) REFERENCES DRIVERS_TABLE (DRIVER_ID), "
                + " FOREIGN KEY (USER_ID) REFERENCES USERS_TABLE (USER_ID))";


        sqLiteDatabase.execSQL(createTableCarsInRent);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean registerNewUser(User user) {

        databaseWrite = this.getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(COLUMN_USER_NAME, user.getName());
        contentValues.put(COLUMN_USER_EMAIL, user.getEmail());
        contentValues.put(COLUMN_USER_ID_CARD_NUMBER, user.getIdCardNumber());
        contentValues.put(COLUMN_USER_DRIVING_LICENCE_NUMBER, user.getDrivingLicenceNumber());
        contentValues.put(COLUMN_USER_PHONE_NUMBER, user.getPhoneNumber());
        contentValues.put(COLUMN_USER_ADDRESS, user.getAddress());
        contentValues.put(COLUMN_USER_PASSWORD, user.getPassword());

        long insert = databaseWrite.insert(USERS_TABLE, null, contentValues);

        return insert != -1;

    }

    public boolean addNewDriver(Driver driver) {

        databaseWrite = this.getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(COLUMN_DRIVER_NAME, driver.getName());
        contentValues.put(COLUMN_DRIVER_ID_CARD_NUMBER, driver.getIdCardNumber());
        contentValues.put(COLUMN_DRIVER_PHONE_NUMBER, driver.getPhoneNumber());
        contentValues.put(COLUMN_DRIVER_DRIVING_LICENCE_NUMBER, driver.getDrivingLicenceNumber());
        contentValues.put(COLUMN_DRIVER_ADDRESS, driver.getAddress());
        contentValues.put(COLUMN_DRIVER_AVAILABILITY, driver.getAvailable());

        long insert = databaseWrite.insert(DRIVERS_TABLE, null, contentValues);

        return insert != -1;

    }

    public boolean addNewCar(Cars cars) {

        databaseWrite = this.getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(COLUMN_CAR_MANUFACTURER, cars.getManufacturer());
        contentValues.put(COLUMN_CAR_MODEL, cars.getModel());
        contentValues.put(COLUMN_CAR_TYPE, cars.getCarType());
        contentValues.put(COLUMN_CAR_PRICE, cars.getPrice());
        contentValues.put(COLUMN_CAR_EQUIPMENT, cars.getEquipment());
        contentValues.put(COLUMN_CAR_AVAILABILITY, cars.getAvailable());

        long insert = databaseWrite.insert(CARS_TABLE, null, contentValues);

        return insert != -1;
    }

    public boolean addCarToRentTable(Cars cars, Driver driver, User user, RentedCars rentedCars) {

        databaseWrite = this.getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(COLUMN_CAR_ID, cars.getId());
        contentValues.put(COLUMN_DRIVER_ID, driver.getId());
        contentValues.put(COLUMN_USER_ID, user.getId());
        contentValues.put(COLUMN_RENT_START_TIME, rentedCars.getRentStartDate());
        contentValues.put(COLUMN_RENT_FINISH_TIME, rentedCars.getRentFinnishDate());

        long insert = databaseWrite.insert(CARS_IN_RENT_TABLE, null, contentValues);

        return insert != -1;
    }

    public List<Cars> getSelectedCar(String selectionvalue) {
        List<Cars> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + CARS_TABLE + " WHERE " + COLUMN_CAR_TYPE + " = '" + selectionvalue + "'";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int carID = cursor.getInt(0);
                String carManufacturer = cursor.getString(1);
                String carModel = cursor.getString(2);
                String carType = cursor.getString(3);
                int carPrice = cursor.getInt(4);
                String carEquipment = cursor.getString(5);
                boolean carAvailability = cursor.getInt(6) == 1;

                Cars car = new Cars(carID, carManufacturer, carModel, carType, carPrice, carEquipment, carAvailability);
                returnList.add(car);
            } while (cursor.moveToNext());
        } else {
            Log.d("", "No car in database!");
        }

        cursor.close();
        return returnList;
    }

    public SimpleCursorAdapter populateListViewFromDB() {

        String query = "SELECT rowid _id,* FROM " + CARS_TABLE;

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        String[] fromFieldNames = new String[]{
                COLUMN_CAR_ID, COLUMN_CAR_MANUFACTURER, COLUMN_CAR_MODEL, COLUMN_CAR_PRICE, COLUMN_CAR_EQUIPMENT, COLUMN_CAR_AVAILABILITY
        };

        int[] toViewIds = new int[]{R.id.text_id, R.id.text_manufacturer, R.id.text_model, R.id.text_price, R.id.text_equipment, R.id.switch_car};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                context,
                R.layout.car_adapter_view_layout,
                cursor,
                fromFieldNames,
                toViewIds
        );
        return adapter;
    }

    public SimpleCursorAdapter populateListViewFromDB(String type) {

        String query = "SELECT rowid _id,* FROM " + CARS_TABLE + " WHERE " + COLUMN_CAR_TYPE + " = '" + type + "'";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        String[] fromFieldNames = new String[]{
                COLUMN_CAR_ID, COLUMN_CAR_MANUFACTURER, COLUMN_CAR_MODEL, COLUMN_CAR_PRICE, COLUMN_CAR_EQUIPMENT, COLUMN_CAR_AVAILABILITY
        };

        int[] toViewIds = new int[]{R.id.text_id, R.id.text_manufacturer, R.id.text_model, R.id.text_price, R.id.text_equipment, R.id.switch_car};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                context,
                R.layout.car_adapter_view_layout,
                cursor,
                fromFieldNames,
                toViewIds
        );
        return adapter;
    }

    public void updateCarDataToNew(long id, String manufacturer, String model, String price, String equipment, Boolean available) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CAR_MANUFACTURER, manufacturer);
        contentValues.put(COLUMN_CAR_MODEL, model);
        contentValues.put(COLUMN_CAR_PRICE, price);
        contentValues.put(COLUMN_CAR_EQUIPMENT, equipment);
        contentValues.put(COLUMN_CAR_AVAILABILITY, available);
        String[] args = {"" + id};
        database.update(CARS_TABLE, contentValues, COLUMN_CAR_ID + "=?", args);

    }

    public void deleteCarData(long id) {

        SQLiteDatabase database = this.getWritableDatabase();

        String[] args = {"" + id};

        database.delete(CARS_TABLE, COLUMN_CAR_ID + "=?", args);
    }

    public SimpleCursorAdapter populateDriverListViewFromDB() {

        String query = "SELECT rowid _id,* FROM " + DRIVERS_TABLE;

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        String[] fromFieldNames = new String[]{
                COLUMN_DRIVER_ID, COLUMN_DRIVER_NAME, COLUMN_DRIVER_ID_CARD_NUMBER, COLUMN_DRIVER_PHONE_NUMBER, COLUMN_DRIVER_DRIVING_LICENCE_NUMBER, COLUMN_DRIVER_ADDRESS, COLUMN_DRIVER_AVAILABILITY
        };

        int[] toViewIds = new int[]{R.id.text_driver_id, R.id.text_name, R.id.text_id_card_number, R.id.text_phone_number, R.id.text_driving_licence, R.id.text_address, R.id.text_available};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                context,
                R.layout.driver_adapter_view_layout,
                cursor,
                fromFieldNames,
                toViewIds
        );
        Log.d("adapter", String.valueOf(adapter));
        return adapter;
    }

    public void deleteDriverData(long id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String[] args = {"" + id};

        database.delete(DRIVERS_TABLE, COLUMN_DRIVER_ID + "=?", args);

    }

    public void updateDriverToNew(long id, String name, String idcard, String phone, String drivinglicence, String address, Boolean available) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DRIVER_NAME, name);
        contentValues.put(COLUMN_DRIVER_ID_CARD_NUMBER, idcard);
        contentValues.put(COLUMN_DRIVER_PHONE_NUMBER, phone);
        contentValues.put(COLUMN_DRIVER_DRIVING_LICENCE_NUMBER, drivinglicence);
        contentValues.put(COLUMN_DRIVER_ADDRESS, address);
        contentValues.put(COLUMN_DRIVER_AVAILABILITY, available);
        String[] args = {"" + id};
        database.update(DRIVERS_TABLE, contentValues, COLUMN_DRIVER_ID + "=?", args);
    }

    public Boolean checkIfUserIsRegistered(String email) {

        String query = "SELECT rowid _id,* FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_EMAIL + " = '" + email + "'";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);
        String emailsInDataBase;
        if (cursor.moveToNext()) {
            do {
                emailsInDataBase = cursor.getString(3);
                if (emailsInDataBase.equals(email)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }

    public Boolean checkIfPasswordMatchesForEmail(String email, String password) {
        String query = "SELECT rowid _id,* FROM " + USERS_TABLE + " WHERE "
                + COLUMN_USER_EMAIL + " = '" + email + "'";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);
        String emailsInDataBase, passwordInDataBase;
        if (cursor.moveToNext()) {
            do {
                emailsInDataBase = cursor.getString(3);
                passwordInDataBase = cursor.getString(8);

                Log.d("eamil",emailsInDataBase + " password " +passwordInDataBase);

                if ((emailsInDataBase.equals(email) && passwordInDataBase.equals(password))==true) {
                    return true;
                }

            } while (cursor.moveToNext());
        }
        return false;
    }
}
