package Controller;

import Entities.Car;
import GUI.ApplicationWindow;

public class Controller implements LogCallback {
    private ApplicationWindow applicationWindow;
    private ThreadManager threadManager;

    public Controller(ApplicationWindow applicationWindow) {
        this.applicationWindow = applicationWindow;
        threadManager = new ThreadManager(this);
        threadManager.start();
    }


    public void startCar(Car car, int speed) {
        threadManager.startCar(car);
    }

    public void stopCar(Car car) {
        threadManager.pauseCar(car);
    }

    public void exitRace(Car car) {
        threadManager.removeCar(car);
    }

    public void addCarToQueue(Car car) {
        threadManager.addCarToQueue(car);
    }

    @Override
    public synchronized void updateLog(Car car) {
        applicationWindow.logInfo(car);
    }
}
