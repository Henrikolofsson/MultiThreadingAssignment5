package GUI;

import Controller.Controller;
import Entities.Car;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {
    private Controller controller;
    private CarDetailsPanel carDetailsPanel;
    private CarLoggerPanel carLoggerPanel;

    public ApplicationWindow() {
        initializeGUI();
        initializeComponents();

    }

    private void initializeGUI() {
        setTitle("Car race");
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setSize(new Dimension(800,800));
        setMinimumSize(new Dimension(800, 800));
        setVisible(true);
        setBackground(Color.GRAY);
        setLayout(new GridBagLayout());
    }

    private void initializeComponents() {
        controller = new Controller(this);
        carDetailsPanel = new CarDetailsPanel(controller);
        carLoggerPanel = new CarLoggerPanel(controller);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(0, 0, 0,10);
        c.gridx = 0;
        c.gridy = 0;
        add(carDetailsPanel, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0,0);
        add(carLoggerPanel, c);

        pack();
    }

    public synchronized void logInfo(Car car) {
        carLoggerPanel.logInfo(car);
    }

}
