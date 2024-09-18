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
    private JButton showCPUUsageButton;

    protected ProgramGUI() {

        // Initiate UI properties
        setTitle("Operating System Class work 4");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        imageProcessor = new ImageProcessor(availableProcessors);

        initComponents();
        addComponentsToFrame();
        addActionListeners();

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        openButton = new JButton("Open Image");
        convertButton = new JButton("Convert");


        imageLabel = new JLabel();
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    private void addComponentsToFrame() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(convertButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(imageLabel), BorderLayout.CENTER);
    }

    private void addActionListeners() {
        openButton.addActionListener(e -> openImage());
        convertButton.addActionListener(e -> convertImage());
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(selectedFile);
                Image newImage = originalImage.getScaledInstance(700, 500, Image.SCALE_DEFAULT);
                imageLabel.setIcon(new ImageIcon(newImage));
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

}
