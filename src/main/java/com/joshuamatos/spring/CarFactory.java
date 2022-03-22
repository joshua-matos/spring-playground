package com.joshuamatos.spring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createTitledBorder;


public class CarFactory extends JFrame {


    //Create GUI fields, labels, and buttons
    final private ButtonGroup buttonGroup = new ButtonGroup();
    private final JLabel labelMakeModel = new JLabel("Make and Model");
    private final JTextField textFieldMakeModel = new JTextField(12);
    private final JLabel labelSalesPrice = new JLabel("Sales Price");
    private final JTextField textFieldSalesPrice = new JTextField(12);
    private final JRadioButton radioButtonHybrid = new JRadioButton("Hybrid");
    private final JRadioButton radioButtonElectric = new JRadioButton("Electric");
    private final JRadioButton radioButtonOther = new JRadioButton("Other");
    private final JLabel labelMPG = new JLabel("Miles per Gallon");
    private final JTextField textFieldMPG = new JTextField(10);
    private final JLabel labelWeightsLBS = new JLabel("Weights in LBS");
    private final JTextField textFieldWeightLBS = new JTextField(10);
    private final JButton bSales = new JButton("Compute Sales Tax");
    private final JButton bClearFields = new JButton("Clear Fields");
    private final JButton bDisplayReport = new JButton("Display Report");
    private final JLabel panelPriceStatus = new JLabel("           ");
    private final JPanel wrapper1 = new JPanel();
    private final JPanel wrapper2 = new JPanel();
    private final JPanel wrapper3 = new JPanel();

    //Create an array list of type Automobile
    private final List<Automobile> automobileArray = new ArrayList<Automobile>(5);


    public CarFactory() {
        //Create the main frame for the program
        JFrame frame = new JFrame("Automobile Sales Tax Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        //Panel to store fields
        JPanel panelTop = new JPanel(new GridLayout(2, 2, 12, 12));
        panelTop.add(labelMakeModel);
        panelTop.add(textFieldMakeModel);
        panelTop.add(labelSalesPrice);
        panelTop.add(textFieldSalesPrice);

        //Group radio buttons
        buttonGroup.add(radioButtonHybrid);
        buttonGroup.add(radioButtonElectric);
        buttonGroup.add(radioButtonOther);

        //set Action commands for group buttons, used for switch/case later in program
        radioButtonElectric.setActionCommand("Electric");
        radioButtonHybrid.setActionCommand("Hybrid");
        radioButtonOther.setActionCommand("Other");

        //create middle panel
        JPanel panelMiddle = new JPanel(new GridLayout(3, 3, 15, 10));
        panelMiddle.setBorder(createTitledBorder("Automobile Type"));
        panelMiddle.add(radioButtonHybrid);
        panelMiddle.add(labelMPG);
        panelMiddle.add(textFieldMPG);
        panelMiddle.add(radioButtonElectric);
        panelMiddle.add(labelWeightsLBS);
        panelMiddle.add(textFieldWeightLBS);
        panelMiddle.add(radioButtonOther);

        //create bottom panel
        JPanel panelBottom = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBottom.add(bSales);
        panelBottom.add(panelPriceStatus);
        panelBottom.add(bClearFields);
        panelBottom.add(bDisplayReport);

        //create on click actions for buttons
        panelPriceStatus.setBorder(createTitledBorder(""));
        bSales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                computeSalesTax();
            }
        });
        bClearFields.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        bDisplayReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayReport();
            }
        });


        //create a panel to hold other panels
        JPanel panelContainer = new JPanel(new BorderLayout());
        panelContainer.setVisible(true);
        panelContainer.setBorder(createEmptyBorder(15, 15, 15, 15));

        //Wrap the panels within another panel - this prevents the inner panels from spreading across the entire JFrame
        wrapper1.add(panelTop);
        wrapper2.add(panelMiddle);
        wrapper3.add(panelBottom);


        panelContainer.add(wrapper1, BorderLayout.NORTH);
        panelContainer.add(wrapper2, BorderLayout.CENTER);
        panelContainer.add(wrapper3, BorderLayout.SOUTH);

        //add panels to frame and pack into one location on the in the frame
        frame.getContentPane().add(panelContainer);
        frame.pack();

    }

    //Main program

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CarFactory();
            }
        });
    }

    //when invalid input is not entered, this pane is called.
    public void notValidNumber() {
        JOptionPane.showMessageDialog(this,
                "A value that is not a number has been entered\n" +
                        "Please enter a number for Sales Price, Miles Per Gallon \n" +
                        "and weight in pounds. Please ensure all fields are filled\n" +
                        "out correctly. Thank you.",
                "Please Enter Valid Input", JOptionPane.WARNING_MESSAGE);
    }

    //Compute the sales tax for program
    public void computeSalesTax() {
        int weightConvert, mpgConvert;
        double priceConvert;
        try {
            //Set values, based on input - error handled
            weightConvert = Integer.parseInt(textFieldWeightLBS.getText());
            priceConvert = Double.parseDouble(textFieldSalesPrice.getText());
            mpgConvert = Integer.parseInt(textFieldMPG.getText());
            String makeModelAuto = textFieldMakeModel.getText();

            //Create specific Object instance based on radio button selection
            switch (buttonGroup.getSelection().getActionCommand()) {
                case "Hybrid":
                    Hybrid mobileHybrid = new Hybrid(makeModelAuto, priceConvert, mpgConvert);
                    panelPriceStatus.setText(" " + mobileHybrid.salesTax());
                    addAutomobile(mobileHybrid);
                    break;
                case "Electric":
                    Electric mobileElectric = new Electric(makeModelAuto, priceConvert, weightConvert);
                    panelPriceStatus.setText(" " + mobileElectric.salesTax());
                    addAutomobile(mobileElectric);
                    break;
                case "Other":
                    Automobile mobileAuto = new Automobile(makeModelAuto, priceConvert);
                    panelPriceStatus.setText(" " + mobileAuto.salesTax());
                    addAutomobile(mobileAuto);
                    break;
            }
        } catch (Exception e) {
            //Call this method if the input values are wrong
            notValidNumber();
        }

    }

    //Store automobile in arraylist
    public void addAutomobile(Automobile automobile) {
        if (automobileArray.size() >= 5) {
            automobileArray.remove(0);
        }
        automobileArray.add(automobile);
    }

    //Method to clear fields
    public void clearFields() {
        textFieldMakeModel.setText("");
        textFieldSalesPrice.setText("");
        textFieldMPG.setText("");
        textFieldWeightLBS.setText("");
        panelPriceStatus.setText("");

    }

    //Print report of arraylist contents
    public void displayReport() {
        for (int i = 0; i < automobileArray.size(); i++) {
            System.out.println(automobileArray.get(i) + "\n");
        }
    }
}