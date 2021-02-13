package GUI;

import Controller.Controller;
import Entities.Car;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarDetailsPanel extends JPanel {
    private Controller controller;
    private JLabel lblCarId;
    private JLabel lblFuelConsumption;
    private JLabel lblTankVolume;
    private JComboBox<String> comboBoxId;
    private JComboBox<String> comboBoxFuelConsumption;
    private JComboBox<String> comboBoxTankVolume;
    private JButton btnAddCar;


    public CarDetailsPanel(Controller controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblCarId = new JLabel("Car ID:");
        lblFuelConsumption = new JLabel("Fuel Consumption (l/m)");
        lblTankVolume = new JLabel("Tank Volume (l)");
        comboBoxId = new JComboBox<>();
        comboBoxFuelConsumption = new JComboBox<>();
        comboBoxTankVolume = new JComboBox<>();
        btnAddCar = new JButton("Add Car");

        comboBoxId.setModel(new DefaultComboBoxModel<>(controller.getCarModels()));
        comboBoxFuelConsumption.setModel(new DefaultComboBoxModel<>(controller.getFuelConsumption()));
        comboBoxTankVolume.setModel(new DefaultComboBoxModel<>(controller.getTankVolume()));
    }

    private void initializeGUI() {
        Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE);
        Border titledBorder = BorderFactory.createTitledBorder(whiteBorder, "New Car");
        setBorder(titledBorder);
        setSize(new Dimension(250, 380));
        setMinimumSize(new Dimension(250, 380));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 20, 10, 20);
        add(lblCarId, c);

        c.gridx = 0;
        c.gridy = 1;
        add(comboBoxId, c);

        c.gridx = 0;
        c.gridy = 2;
        add(lblFuelConsumption, c);

        c.gridx = 0;
        c.gridy = 3;
        add(comboBoxFuelConsumption, c);

        c.gridx = 0;
        c.gridy = 4;
        add(lblTankVolume, c);

        c.gridx = 0;
        c.gridy = 5;
        add(comboBoxTankVolume, c);

        c.gridx = 0;
        c.gridy = 6;
        add(btnAddCar, c);

    }

    private void registerListeners() {
        btnAddCar.addActionListener(new AddCarListener());
    }

    public Car getCar() {
        return new Car(comboBoxId.getItemAt(comboBoxId.getSelectedIndex()),
                Double.parseDouble(comboBoxFuelConsumption.getItemAt(comboBoxFuelConsumption.getSelectedIndex())),
                Double.parseDouble(comboBoxTankVolume.getItemAt(comboBoxTankVolume.getSelectedIndex())));
    }

    public void setCars(String[] carModels) {
        comboBoxId.setModel(new DefaultComboBoxModel<>(carModels));
    }

    public class AddCarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            controller.btnPressed("AddCar");
        }
    }

}
