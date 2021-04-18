package Threads;

import Controller.Controller;
import Entities.Car;
import Enums.CarStatus;

public class CarThread implements Runnable{
    private Controller controller;
    private Car car;

    public CarThread(Controller controller, Car car) {
        this.controller = controller;
        this.car = car;
    }

    @Override
    public void run() {
        switch(car.getCarStatus()) {
            case NOT_STARTED:
                System.out.println("NOT_STARTED");
                controller.updateLog(car);
                break;

            case RUNNING:
                System.out.println("RUNNING");
                car.setDistanceTraveled(car.getDistanceTraveled() + (double) (car.getSpeed() / 2));
                double distanceInMil = (double) car.getSpeed() / 10;
                double fuelConsumed = distanceInMil * car.getFuelConsumption();
                car.setCurrentTankVolume(car.getCurrentTankVolume() - fuelConsumed);
                if(car.getCurrentTankVolume() <= 0) {
                    car.setCurrentTankVolume(0);
                    car.setSpeed(0);
                    car.setCarStatus(CarStatus.OUT_OF_GAS);
                }
                controller.updateLog(car);
                break;

            case PAUSED:
                System.out.println("PAUSED");
                car.setCarStatus(CarStatus.PAUSED);
                break;

            case OUT_OF_GAS:
                System.out.println("OUT_OF_GAS");
                controller.updateLog(car);
                break;

            case LEAVING:
                System.out.println("LEAVING");
                controller.updateLog(car);
                controller.removeCarFromCarPool(car);
                break;

            case RESTARTED:
                System.out.println("RESTARTED");
                car.setDistanceTraveled(0);
                car.setCurrentTankVolume(car.getFuelTankMAX());
                car.setCarStatus(CarStatus.RESTARTED);
                controller.updateLog(car);
                car.setCarStatus(CarStatus.RUNNING);
                break;
        }
    }
}
