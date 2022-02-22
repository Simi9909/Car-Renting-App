package com.example.car_rental.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.car_rental.model.Cars;
import com.example.car_rental.model.Driver;
import com.example.car_rental.model.User;

public class DBHelper extends SQLiteOpenHelper {
    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String COLUMN_DRIVER_ID = "ID";
    public static final String COLUMN_USER_ID = COLUMN_DRIVER_ID;
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_ID_CARD_NUMBER = "USER_" + COLUMN_DRIVER_ID + "_CARD_NUMBER";
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

    public DBHelper(@Nullable Context context) {
        super(context, "car_rental.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableUsers = "CREATE TABLE " + USERS_TABLE + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAME + " TEXT, "
                + COLUMN_USER_EMAIL + " TEXT, "
                + COLUMN_USER_ID_CARD_NUMBER + " TEXT, "
                + COLUMN_USER_DRIVING_LICENCE_NUMBER + " TEXT, "
                + COLUMN_USER_PHONE_NUMBER + " INT, "
                + COLUMN_USER_ADDRESS + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT)";

        sqLiteDatabase.execSQL(createTableUsers);


        String createTableDriver = "CREATE TABLE " + DRIVERS_TABLE + " ("
                + COLUMN_DRIVER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DRIVER_NAME + " TEXT, "
                + COLUMN_DRIVER_ID_CARD_NUMBER + " TEXT, "
                + COLUMN_DRIVER_PHONE_NUMBER + " INT, "
                + COLUMN_DRIVER_DRIVING_LICENCE_NUMBER + " TEXT, "
                + COLUMN_DRIVER_ADDRESS + " TEXT, "
                + COLUMN_DRIVER_AVAILABILITY + " BOOL)";

        sqLiteDatabase.execSQL(createTableDriver);


        String createTableCars = "CREATE TABLE " + CARS_TABLE + " ("
                + COLUMN_CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CAR_MANUFACTURER + " TEXT, "
                + COLUMN_CAR_MODEL + " TEXT, "
                + COLUMN_CAR_TYPE + " TEXT, "
                + COLUMN_CAR_PRICE + " INT, "
                + COLUMN_CAR_EQUIPMENT + " TEXT, "
                + COLUMN_CAR_AVAILABILITY + " BOOL)";

        //String createTableCarsInRent = "";

        sqLiteDatabase.execSQL(createTableCars);
        //sqLiteDatabase.execSQL(createTableCarsInRent);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean registerNewUser(User user) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USER_NAME, user.getName());
        contentValues.put(COLUMN_USER_EMAIL, user.getEmail());
        contentValues.put(COLUMN_USER_ID_CARD_NUMBER, user.getIdCardNumber());
        contentValues.put(COLUMN_USER_DRIVING_LICENCE_NUMBER, user.getDrivingLicenceNumber());
        contentValues.put(COLUMN_USER_PHONE_NUMBER, user.getPhoneNumber());
        contentValues.put(COLUMN_USER_ADDRESS,user.getAddress());
        contentValues.put(COLUMN_USER_PASSWORD, user.getPassword());

        long insert = database.insert(USERS_TABLE, null, contentValues);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean addNewDriver(Driver driver) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_DRIVER_NAME, driver.getName());
        contentValues.put(COLUMN_DRIVER_ID_CARD_NUMBER, driver.getIdCardNumber());
        contentValues.put(COLUMN_DRIVER_PHONE_NUMBER, driver.getPhoneNumber());
        contentValues.put(COLUMN_DRIVER_DRIVING_LICENCE_NUMBER, driver.getDrivingLicenceNumber());
        contentValues.put(COLUMN_DRIVER_ADDRESS, driver.getAddress());
        contentValues.put(COLUMN_DRIVER_AVAILABILITY, driver.getAvailable());

        long insert = database.insert(DRIVERS_TABLE, null, contentValues);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean addNewCar(Cars cars) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CAR_MANUFACTURER, cars.getManufacturer());
        contentValues.put(COLUMN_CAR_MODEL, cars.getModel());
        contentValues.put(COLUMN_CAR_TYPE, cars.getCarType());
        contentValues.put(COLUMN_CAR_PRICE, cars.getPrice());
        contentValues.put(COLUMN_CAR_EQUIPMENT, cars.getEquipment());
        contentValues.put(COLUMN_CAR_AVAILABILITY, cars.getAvailable());

        long insert = database.insert(CARS_TABLE, null, contentValues);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
}
