package com.example.car_rental.model;

public class RentedCars {

    private Integer carId;
    private Integer driverId;
    private Integer userId;
    private String rentStartDate;
    private String rentFinnishDate;

    public RentedCars(Integer carId, Integer driverId, Integer userId, String rentStartDate, String rentFinnishDate) {
        this.carId = carId;
        this.driverId = driverId;
        this.userId = userId;
        this.rentStartDate = rentStartDate;
        this.rentFinnishDate = rentFinnishDate;
    }

    public RentedCars() {
    }

    @Override
    public String toString() {
        return "RentedCars{" +
                "carId=" + carId +
                ", driverId=" + driverId +
                ", userId=" + userId +
                ", rentStartDate='" + rentStartDate + '\'' +
                ", rentFinnishDate='" + rentFinnishDate + '\'' +
                '}';
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getRentFinnishDate() {
        return rentFinnishDate;
    }

    public void setRentFinnishDate(String rentFinnishDate) {
        this.rentFinnishDate = rentFinnishDate;
    }
}
