package com.example.car_rental.model;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

public class Cars {

    private Integer Id;
    private String Manufacturer;
    private String Model;
    private String CarTypes;
    private Integer Price;
    private String Equipment;
    private Boolean available;
    private byte[] carImage;
    private ImageView carImageView;

    public Cars(Integer id, String manufacturer, String model, String carTypes, Integer price, String equipment, Boolean available,  byte[] carImage) {
        Id = id;
        Manufacturer = manufacturer;
        Model = model;
        CarTypes = carTypes;
        Price = price;
        Equipment = equipment;
        this.available = available;
        this.carImage = carImage;
    }

    public Cars() {
    }

    public Cars(String manufacturer, String model, String equipment, Integer price) {
        Manufacturer = manufacturer;
        Model = model;
        Price = price;
        Equipment = equipment;
    }

    public Cars(Integer id, String manufacturer, String model, String carTypes, Integer price, String equipment, Boolean available, ImageView carImage) {

        Id = id;
        Manufacturer = manufacturer;
        Model = model;
        CarTypes = carTypes;
        Price = price;
        Equipment = equipment;
        this.available = available;
        carImageView = carImage;

    }

    @Override
    public String toString() {
        return "Cars{" +
                "Id=" + Id +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", Model='" + Model + '\'' +
                ", CarTypes='" + CarTypes + '\'' +
                ", Price=" + Price +
                ", Equipment='" + Equipment + '\'' +
                ", available=" + available +
                ", carImage=" + carImage +
                '}';
    }

    public String getCarType() {
        return CarTypes;
    }

    public void setCarType(String carType) {
        CarTypes = carType;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getEquipment() {
        return Equipment;
    }

    public void setEquipment(String equipment) {
        Equipment = equipment;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public byte[] getCarImage() {
        return carImage;
    }

    public String getCarTypes() {
        return CarTypes;
    }

    public void setCarTypes(String carTypes) {
        CarTypes = carTypes;
    }

    public void setCarImage(byte[] carImage) {
        this.carImage = carImage;
    }

    public ImageView getCarImageView() {
        return carImageView;
    }

    public void setCarImageView(ImageView carImageView) {
        this.carImageView = carImageView;
    }
}
