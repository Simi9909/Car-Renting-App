package com.example.car_rental.model;

public class Driver {

    private Integer Id;
    private String Name;
    private String IdCardNumber;
    private Integer PhoneNumber;
    private String DrivingLicenceNumber;
    private String Address;
    private Boolean available;

    public Driver(Integer id, String name, String idCardNumber, Integer phoneNumber, String drivingLicenceNumber, String address, Boolean available) {
        Id = id;
        Name = name;
        IdCardNumber = idCardNumber;
        PhoneNumber = phoneNumber;
        DrivingLicenceNumber = drivingLicenceNumber;
        Address = address;
        this.available = available;
    }

    public Driver() {
    }

    public Integer getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", IdCardNumber='" + IdCardNumber + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", DrivingLicenceNumber='" + DrivingLicenceNumber + '\'' +
                ", Address='" + Address + '\'' +
                ", available=" + available +
                '}';
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdCardNumber() {
        return IdCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        IdCardNumber = idCardNumber;
    }

    public Integer getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDrivingLicenceNumber() {
        return DrivingLicenceNumber;
    }

    public void setDrivingLicenceNumber(String drivingLicenceNumber) {
        DrivingLicenceNumber = drivingLicenceNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
