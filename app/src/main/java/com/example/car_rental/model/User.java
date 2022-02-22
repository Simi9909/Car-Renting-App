package com.example.car_rental.model;

public class User {

    private Integer Id;
    private String Name;
    private String Email;
    private String IdCardNumber;
    private String DrivingLicenceNumber;
    private Integer PhoneNumber;
    private String Address;
    private String Password;

    public User(Integer id, String name, String email, String idCardNumber, String drivingLicenceNumber, Integer phoneNumber, String address, String password) {
        Id = id;
        Name = name;
        Email = email;
        IdCardNumber = idCardNumber;
        DrivingLicenceNumber = drivingLicenceNumber;
        PhoneNumber = phoneNumber;
        Address = address;
        Password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", IdCardNumber='" + IdCardNumber + '\'' +
                ", DrivingLicenceNumber='" + DrivingLicenceNumber + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", Address='" + Address + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public Integer getId() {
        return Id;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIdCardNumber() {
        return IdCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        IdCardNumber = idCardNumber;
    }

    public String getDrivingLicenceNumber() {
        return DrivingLicenceNumber;
    }

    public void setDrivingLicenceNumber(String drivingLicenceNumber) {
        DrivingLicenceNumber = drivingLicenceNumber;
    }

    public Integer getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
