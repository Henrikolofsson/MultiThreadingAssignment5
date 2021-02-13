package GUI;

import Controller.Controller;
import Entities.Car;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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


    public void closeWindow() {
        dispose();
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
        private JButton btnCloseWindow;

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
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            lblSpeed = new JLabel("Speed: 50km/h");
            slider = new JSlider(0, 100);
            slider.setOrientation(JSlider.HORIZONTAL);
            slider.setMajorTickSpacing(10);
            slider.setMinorTickSpacing(1);
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
            btnCloseWindow = new JButton("Exit");
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

            c.gridx = 3;
            panelSouth.add(btnCloseWindow, c);

            add(panelSouth, BorderLayout.SOUTH);

        }

        public Car getCar() {
            return car;
        }


        private void registerListeners() {
            btnStart.addActionListener(new StartListener());
            btnCloseWindow.addActionListener(new ExitListener());
            slider.addChangeListener(new SliderListener());
        }

        private class SliderListener implements ChangeListener {

            @Override
            public void stateChanged(ChangeEvent e) {
                lblSpeed.setText("Speed: " + slider.getValue() + "km/h");
                car.setSpeed(slider.getValue());
            }
        }

        private class StartListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.btnPressed("StartCar");
            }
        }

        private class ExitListener implements  ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }
    }


}
