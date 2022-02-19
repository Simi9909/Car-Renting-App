package com.example.car_rental.model;

public class Cars {

    private Integer Id;
    private String Manufacturer;
    private String Model;
    private Integer Price;
    private String Equipment;
    private Boolean available;

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
