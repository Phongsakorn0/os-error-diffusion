package org.phongsakorn;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ProgramGUI extends JFrame {
    private JButton openButton, saveButton, convertButton;  // 3 Buttons in Program
    private JLabel imageLabel;  // Canvas for Image
    private BufferedImage originalImage, convertedImage;    // Two image instance
    private final ImageProcessor imageProcessor;        // Image Processor Instance Jaa
    private final long[] cpuTimes;   // List to hold CPU usage times
    private JButton showCPUUsageButton;

<<<<<<<< HEAD:src/main/java/org/phongsakorn/ConverterGUI.java
    public ConverterGUI() {
        setTitle("Operating System Class work 4");
========
    protected ProgramGUI() {

        // Initiate UI properties
        setTitle("Operating System Class work 4 POND HELP MEEE");
>>>>>>>> origin/main:src/main/java/org/phongsakorn/ProgramGUI.java
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        imageProcessor = new ImageProcessor(availableProcessors);
        cpuTimes = imageProcessor.getCpuTimes(); // Initialize list with available CPUs

        initComponents();
        addComponentsToFrame();
        addActionListeners();

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        openButton = new JButton("Open Image");
        saveButton = new JButton("Save Image");
        convertButton = new JButton("Convert");
        showCPUUsageButton = new JButton("Show CPU Usage");  // Button to show CPU usage chart

        imageLabel = new JLabel();
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    private void addComponentsToFrame() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(convertButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(showCPUUsageButton);  // Add CPU usage button

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(imageLabel), BorderLayout.CENTER);
    }

    private void addActionListeners() {
        openButton.addActionListener(e -> openImage());
        convertButton.addActionListener(e -> convertImage());
        saveButton.addActionListener(e -> saveImage());
        showCPUUsageButton.addActionListener(e -> CoreGraph.showCPUUsageChart(cpuTimes));  // Show CPU usage chart on click
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(selectedFile);
                imageLabel.setIcon(new ImageIcon(originalImage));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening image: " + ex.getMessage());
            }
        }
    }

    private void convertImage() {
        if (originalImage == null) {
            JOptionPane.showMessageDialog(this, "Please open an image first.");
            return;
        }

        convertedImage = imageProcessor.convertToBlackAndWhite(originalImage);
        imageLabel.setIcon(new ImageIcon(convertedImage));
    }

    private void saveImage() {
        if (convertedImage == null) {
            JOptionPane.showMessageDialog(this, "Please convert an image first.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ImageIO.write(convertedImage, "png", selectedFile);
                JOptionPane.showMessageDialog(this, "Image saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving image: " + ex.getMessage());
            }
        }
    }
}
