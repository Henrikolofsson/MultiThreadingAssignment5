package Threads;

import Controller.Controller;
import Entities.Car;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestThreadManager {
    private Controller controller;
    private ScheduledExecutorService scheduledExecutor;

    public TestThreadManager(Controller controller) {
        this.controller = controller;
        scheduledExecutor =  Executors.newScheduledThreadPool(10);
    }

    public void addTask(Car car) {
        CarThread carThread = new CarThread(controller,car);
        scheduledExecutor.scheduleAtFixedRate(carThread, 0, 5, TimeUnit.SECONDS);
    }
}
