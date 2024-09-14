<<<<<<< HEAD:src/main/java/org/phongsakorn/CoreGraph.java
package org.phongsakorn;
=======
package org.bunyawat;
>>>>>>> origin/main:src/main/java/org/bunyawat/CoreGraph.java

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
<<<<<<< HEAD:src/main/java/org/phongsakorn/CoreGraph.java
import javax.swing.*;


public class CoreGraph extends JFrame {
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private DefaultCategoryDataset dataset ;
    public static void CoreGraphcpu(long[] cpuTimes) {
=======

import javax.swing.*;

public class CoreGraph {
    protected static void showCPUUsageChart(long[] cpuTimes) {
>>>>>>> origin/main:src/main/java/org/bunyawat/CoreGraph.java
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add CPU usage data to dataset
        for (int i = 0; i < cpuTimes.length; i++) {
            dataset.addValue(cpuTimes[i], "CPU Time", "CPU" + i);
        }

        // Create bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "CPU Usage during Error Diffusion",
                "CPU Cores",
                "CPU Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Show chart in a new window
        ChartPanel chartPanel = new ChartPanel(barChart);
        JFrame chartFrame = new JFrame("CPU Usage Chart");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setSize(800, 600);
        chartFrame.add(chartPanel);
        chartFrame.setLocationRelativeTo(null);
        chartFrame.setVisible(true);
    }
<<<<<<< HEAD:src/main/java/org/phongsakorn/CoreGraph.java
=======

>>>>>>> origin/main:src/main/java/org/bunyawat/CoreGraph.java
}
