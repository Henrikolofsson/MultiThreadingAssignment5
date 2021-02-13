package Controller;

import Entities.Car;
import Enums.CarStatus;
import java.util.LinkedList;
import java.util.concurrent.*;

public class ThreadManager extends Thread {
    private Controller controller;
    private ScheduledThreadPoolExecutor executor;
    private Thread thread;
    private LinkedList<Car> carQueue;


    private ScheduledExecutorService scheduledExecutor;

    public ThreadManager(Controller controller) {
        this.controller = controller;
        scheduledExecutor =  Executors.newScheduledThreadPool(10);
        carQueue = new LinkedList<>();

    }

    public void addCarToQueue(Car car) {
        carQueue.add(car);
    }

    @Override
    public void run() {
        super.run();
        //Run the thread
        while(true) {

            //While a car is added to active car list, do a task for each car
           while(controller.getCars().size() > 0) {
               for(int i = 0; i < controller.getCars().size(); i++) {
                   System.out.println(controller.getCars().get(i));
                   CarTask task = new CarTask(controller.getCars().get(i));
                   ScheduledFuture<Integer> future = scheduledExecutor.schedule(task, 5, TimeUnit.SECONDS);


                   try {
                       System.out.println(future.get());
                   } catch (InterruptedException | ExecutionException e) {
                       e.printStackTrace();
                   }
               }
       }

            try {
                sleep(500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    public void startCar(Car car) {
        CarTask carTask = new CarTask(car);
        Future<Integer> test = executor.submit(carTask);
        try {
            Integer result = test.get();
            System.out.println("Result: " + result);
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void pauseCar(Car car) {

    }

    public void removeCar(Car car) {

    }

    private class CarTask implements Callable<Integer> {
        private Car car;

        public CarTask(Car car) {
            this.car = car;
        }


        @Override
        public Integer call() throws Exception {
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
                        car.setCarStatus(CarStatus.OUT_OF_GAS);
                    }

                    break;

                case PAUSED:
                    System.out.println("PAUSED");
                    break;

                case OUT_OF_GAS:
                    System.out.println("OUT_OF_GAS");
                    break;

                case LEAVING:
                    System.out.println("LEAVING");
                    break;

                case RESTARTED:
                    System.out.println("RESTARTED");
                    break;
            }

            try {
                System.out.println(car);
                TimeUnit.SECONDS.sleep(5);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }
    }

}
