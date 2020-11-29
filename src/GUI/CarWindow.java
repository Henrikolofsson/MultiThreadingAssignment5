package GUI;

import Controller.Controller;
import Entities.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarWindow extends JFrame {
    private Controller controller;
    private Car car;
    private CarPanel carPanel;



    public CarWindow(Controller controller, Car car) {
        this.controller = controller;
        this.car = car;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        setTitle("Car: " + car.getID());
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setSize(new Dimension(500,250));
        setMinimumSize(new Dimension(500, 250));
        setVisible(true);
        setBackground(Color.GRAY);
        setLayout(new GridBagLayout());
        carPanel = new CarPanel(controller, car);
    }

    private void initializeGUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 0,0);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        add(carPanel, c);

       // pack();
    }


    private class CarPanel extends JPanel {
        private Controller controller;
        private Car car;
        private JLabel lblSpeed;
        private JSlider slider;
        private JPanel panelSouth;
        private JButton btnStart;
        private JButton btnPause;
        private JButton btnExitRace;

        public CarPanel(Controller controller, Car car) {
            this.controller = controller;
            this.car = car;
            initializeComponents();
            initializeGUI();
            registerListeners();
        }

        private void initializeComponents() {
            setLayout(new BorderLayout(1, 1));
            setBackground(Color.GRAY);
            setSize(new Dimension(500,250));
            setMinimumSize(new Dimension(500, 250));
            lblSpeed = new JLabel("Speed:");
            slider = new JSlider(0, 100);
            slider.setOrientation(JSlider.HORIZONTAL);
            slider.setMajorTickSpacing(10);
            slider.setMinorTickSpacing(5);
            slider.setPaintTicks(true);
            slider.setBackground(Color.LIGHT_GRAY);
            slider.createStandardLabels(10);
            slider.setPaintLabels(true);
            slider.setSnapToTicks(true);
            slider.setPreferredSize(new Dimension(450, 120));
            slider.setSize(new Dimension(500, 120));
            slider.setMinimumSize(new Dimension(500, 120));

            panelSouth = new JPanel(new GridBagLayout());
            btnStart = new JButton("Start");
            btnPause = new JButton("Pause");
            btnExitRace = new JButton("Exit Race");
        }

        private void initializeGUI() {
            add(lblSpeed, BorderLayout.NORTH);
            add(slider, BorderLayout.CENTER);

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(10, 10, 10, 10);
            panelSouth.add(btnStart, c);

            c.gridx = 1;
            panelSouth.add(btnPause, c);

            c.gridx = 2;
            panelSouth.add(btnExitRace, c);

            add(panelSouth, BorderLayout.SOUTH);

        }

        private void registerListeners() {
            btnStart.addActionListener(new StartListener());
        }

        private class StartListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(car);
                System.out.println("Speed: " + slider.getValue());
                //controller.startCar(car, slider.getValue());
                car.setSpeed(slider.getValue());
                controller.addCarToQueue(car);
                dispose();
            }
        }

    }

}
