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

    private String[] carModels;
    private String[] fuelConsumption;
    private String[] tankVolume;

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

        tankVolume = new String[7];
        tankVolume[0] = "30";
        tankVolume[1] = "40";
        tankVolume[2] = "50";
        tankVolume[3] = "60";
        tankVolume[4] = "70";
        tankVolume[5] = "80";
        tankVolume[6] = "90";

        comboBoxId.setModel(new DefaultComboBoxModel<>(carModels));
        comboBoxFuelConsumption.setModel(new DefaultComboBoxModel<>(fuelConsumption));
        comboBoxTankVolume.setModel(new DefaultComboBoxModel<>(tankVolume));
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

    public class AddCarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Car car = new Car(comboBoxId.getItemAt(comboBoxId.getSelectedIndex()),
                    Double.parseDouble(comboBoxFuelConsumption.getItemAt(comboBoxFuelConsumption.getSelectedIndex())),
                    Double.parseDouble(comboBoxTankVolume.getItemAt(comboBoxTankVolume.getSelectedIndex())));
            CarWindow carWindow = new CarWindow(controller, car);
        }
    }

}
