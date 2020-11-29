package GUI;

import Controller.Controller;
import Entities.Car;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class CarLoggerPanel extends JPanel {
    private Controller controller;
    private JLabel lblTitle;
    private JScrollPane scrollPane;
    private JTable table;
    private String[] columnNames;
    private String[][] data;
    private DefaultTableModel model;
    private int numberOfLines;
    private int rowSize;
    private int columnSize;

    public CarLoggerPanel(Controller controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        rowSize = 10;
        columnSize = 3;
        numberOfLines = 0;
        lblTitle = new JLabel("Car Logger");
        scrollPane = new JScrollPane();
        columnNames = new String[3];
        columnNames[0] = "Car ID";
        columnNames[1] = "Distance (Km)";
        columnNames[2] = "Status";
        data = new String[rowSize][columnSize];
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setBounds(0, 200, 225, 350);
        scrollPane.setViewportView(table);
    }

    private void initializeGUI() {
        Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE);
        Border titledBorder = BorderFactory.createTitledBorder(whiteBorder, "Car Logger");
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
        add(scrollPane, c);
    }

    public synchronized void logInfo(Car car) {
        System.out.println("CarLoggerPanel:LogInfo");
        if(numberOfLines >= rowSize) {
            expandArray();
        }
        data[numberOfLines][0] = car.getID();
        data[numberOfLines][1] = String.valueOf(car.getSpeed());
        data[numberOfLines][2] = String.valueOf(car.getCarStatus());
        numberOfLines++;
        model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

    private void expandArray() {
        String[][] tempData = new String[rowSize][columnSize];
        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < columnSize; j++) {
                tempData[i][j] = data[i][j];
            }
        }
        rowSize = rowSize * 2;
        data = new String[rowSize][columnSize];
        for(int i = 0; i < (rowSize/2); i++) {
            for(int j = 0; j < columnSize; j++) {
                data[i][j] = tempData[i][j];
            }
        }
        tempData = null;
    }
}
