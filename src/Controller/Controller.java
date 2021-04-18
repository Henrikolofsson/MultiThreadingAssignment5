package Controller;

import Entities.Car;
import Enums.CarStatus;
import GUI.ApplicationWindow;
import GUI.CarWindow;
import Threads.TestThreadManager;
import Threads.ThreadManager;

import java.util.LinkedList;

public class Controller implements LogCallback {
    private ApplicationWindow applicationWindow;
    private ThreadManager threadManager;
    private CarWindow carWindow;
    private String[] carModels;
    private String[] fuelConsumption;
    private String[] tankVolume;
    private LinkedList<Car> cars;

    private TestThreadManager testThreadManager;

    public Controller(ApplicationWindow applicationWindow) {
        initializeCars();
        initializeFuel();
        initializeTankVolume();
        this.applicationWindow = applicationWindow;
        cars = new LinkedList<>();
        //threadManager = new ThreadManager(this);
        //threadManager.start();

        testThreadManager = new TestThreadManager(this);

    }

    private void initializeCars() {
        carModels = new String[10];
        carModels[0] = "Fiat 300";
        carModels[1] = "Saab 9000";
        carModels[2] = "Volvo S60";
        carModels[3] = "Lamborghini Aventador";
        carModels[4] = "Ferrari California";
        carModels[5] = "Hyundai i30";
        carModels[6] = "BMW M3 e46";
        carModels[7] = "Audi R8";
        carModels[8] = "Koenigsegg GEMERA";
        carModels[9] = "Porsche 911";
    }

    private void initializeFuel() {
        fuelConsumption = new String[15];
        fuelConsumption[0] = "0.4";
        fuelConsumption[1] = "0.5";
        fuelConsumption[2] = "0.6";
        fuelConsumption[3] = "0.7";
        fuelConsumption[4] = "0.8";
        fuelConsumption[5] = "0.9";
        fuelConsumption[6] = "1.0";
        fuelConsumption[7] = "1.1";
        fuelConsumption[8] = "1.2";
        fuelConsumption[9] = "1.3";
        fuelConsumption[10] = "1.4";
        fuelConsumption[11] = "1.5";
        fuelConsumption[12] = "1.6";
        fuelConsumption[13] = "1.7";
        fuelConsumption[14] = "1.8";
    }

    private void initializeTankVolume() {
        tankVolume = new String[7];
        tankVolume[0] = "30";
        tankVolume[1] = "40";
        tankVolume[2] = "50";
        tankVolume[3] = "60";
        tankVolume[4] = "70";
        tankVolume[5] = "80";
        tankVolume[6] = "90";
    }

    public String[] getCarModels() {
        return carModels;
    }

    public String[] getFuelConsumption() {
        return fuelConsumption;
    }

    public String[] getTankVolume() {
        return tankVolume;
    }

    public void btnPressed(String button) {
        Car car;

        switch(button) {
            case "AddCar":  car = applicationWindow.getCar();
                            cars.add(car);
                            removeCar(applicationWindow.getCar().getID());
                            applicationWindow.logInfo(applicationWindow.getCar());
                            applicationWindow.updateCars(carModels);
                            testThreadManager.addTask(car);
                break;

            case "OpenCarWindow":    carWindow = new CarWindow(this, getCar(applicationWindow.getChosenCar()));
                break;

            case "StartCar":    car = getCar(applicationWindow.getChosenCar());
                                assert car != null;
                                car.setCarStatus(CarStatus.RUNNING);
                                car.setSpeed(carWindow.getSpeed());
                break;

            case "ExitRace":    Car car2 = getCar(applicationWindow.getChosenCar());
                                assert car2 != null;
                                car2.setCarStatus(CarStatus.LEAVING);
                break;
        }
    }

    public LinkedList<Car> getCars() {
        return cars;
    }

    private Car getCar(String carID) {
        for(int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getID().equals(carID)) {
                return cars.get(i);
            }
        }
        return null;
    }

    @Override
    public synchronized void updateLog(Car car) {
        applicationWindow.logInfo(car);
    }

    public void removeCarFromCarPool(Car car) {
        cars.remove(car);
    }

    private void removeCar(String carName) {
        String[] tempCars = new String[carModels.length-1];
        int offset = 0;

        for(int i = 0; i < tempCars.length; i++) {
            if(carModels[i].equals(carName)) {
                offset = +1;
            }
            tempCars[i] = carModels[i+offset];
        }


        carModels = new String[tempCars.length];
        for(int i = 0; i < tempCars.length; i++) {
            carModels[i] = tempCars[i];
        }

        tempCars = null;
    }
}
