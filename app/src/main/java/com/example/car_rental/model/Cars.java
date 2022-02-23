package com.example.car_rental.model;

public class Cars {

    private Integer Id;
    private String Manufacturer;
    private String Model;
    private String CarTypes;
    private Integer Price;
    private String Equipment;
    private Boolean available;

    public Cars(Integer id, String manufacturer, String model, String carTypes, Integer price, String equipment, Boolean available) {
        Id = id;
        Manufacturer = manufacturer;
        Model = model;
        CarTypes = carTypes;
        Price = price;
        Equipment = equipment;
        this.available = available;
    }

    public Cars() {
    }

    public Cars(String manufacturer, String model, String equipment, Integer price) {
        Manufacturer = manufacturer;
        Model = model;
        Price = price;
        Equipment = equipment;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "Id=" + Id +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", Model='" + Model + '\'' +
                ", Price=" + Price +
                ", Equipment='" + Equipment + '\'' +
                ", available=" + available +
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
}
