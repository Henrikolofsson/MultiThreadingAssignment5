package Entities;

import Enums.CarStatus;

public class Car {
    private String ID;
    private double fuelConsumption;
    private double fuelTankMAX;
    private Enums.CarStatus CarStatus;
    private int speed;
    private double distanceTraveled;
    private double currentTankVolume;

    public Car(String ID, double fuelConsumption, double fuelTankVolume) {
        this.ID = ID;
        this.fuelConsumption = fuelConsumption;
        this.fuelTankMAX = fuelTankVolume;
        CarStatus = Enums.CarStatus.NOT_STARTED;
        currentTankVolume = fuelTankMAX;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelTankMAX() {
        return fuelTankMAX;
    }

    public void setFuelTankMAX(double fuelTankMAX) {
        this.fuelTankMAX = fuelTankMAX;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        System.out.println("Distance traveled:" + distanceTraveled);
        this.distanceTraveled = distanceTraveled;
    }

    public double getCurrentTankVolume() {
        return currentTankVolume;
    }

    public void setCurrentTankVolume(double currentTankVolume) {
        this.currentTankVolume = currentTankVolume;
    }

    public Enums.CarStatus getCarStatus() {
        return CarStatus;
    }

    public void setCarStatus(Enums.CarStatus carStatus) {
        CarStatus = carStatus;
    }

    @Override
    public String toString() {
        return "Car{" +
                "ID='" + ID + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", fuelTankMAX=" + fuelTankMAX +
                ", CarStatus=" + CarStatus +
                ", speed=" + speed +
                ", distanceTraveled=" + distanceTraveled +
                ", currentTankVolume=" + currentTankVolume +
                '}';
    }
}
