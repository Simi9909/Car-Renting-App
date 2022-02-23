package com.example.car_rental.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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

    public boolean addCarToRentTable(Cars cars, Driver driver, User user, RentedCars rentedCars){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CAR_ID, cars.getId());
        contentValues.put(COLUMN_DRIVER_ID, driver.getId());
        contentValues.put(COLUMN_USER_ID, user.getId());
        contentValues.put(COLUMN_RENT_START_TIME, rentedCars.getRentStartDate());
        contentValues.put(COLUMN_RENT_FINISH_TIME, rentedCars.getRentFinnishDate());

        long insert = database.insert(CARS_IN_RENT_TABLE, null, contentValues);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Cars> getAllCar() {

        List<Cars> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + CARS_TABLE;

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
                boolean carAvailability = cursor.getInt(6) == 1 ? true: false;

                Cars car = new Cars(carID, carManufacturer, carModel, carType, carPrice, carEquipment, carAvailability);
                returnList.add(car);
            } while (cursor.moveToNext());
        } else {
            Log.d("", "No car in database!");
        }

        cursor.close();
        database.close();
        return returnList;

    }

    public List<Cars> getSelectedCar(String selectionvalue) {
        List<Cars> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + CARS_TABLE + " WHERE " + COLUMN_CAR_TYPE + " = '" + selectionvalue+"'";

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
                boolean carAvailability = cursor.getInt(6) == 1 ? true: false;

                Cars car = new Cars(carID, carManufacturer, carModel, carType, carPrice, carEquipment, carAvailability);
                returnList.add(car);
            } while (cursor.moveToNext());
        } else {
            Log.d("", "No car in database!");
        }

        cursor.close();
        database.close();
        return returnList;
    }

    public List<Driver> getAllDriver() {

        List<Driver> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + DRIVERS_TABLE;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int driverid = cursor.getInt(0);
                String name = cursor.getString(1);
                String idcardnumber = cursor.getString(2);
                int phonenumber = cursor.getInt(3);
                String drivinglicence = cursor.getString(4);
                String address = cursor.getString(5);
                boolean driveravailability = cursor.getInt(6) == 1 ? true: false;

                Driver driver = new Driver(driverid, name, idcardnumber, phonenumber, drivinglicence, address, driveravailability);
                returnList.add(driver);
            } while (cursor.moveToNext());
        } else {
            Log.d("", "No driver in database!");
        }

        cursor.close();
        database.close();
        return returnList;

    }
}
