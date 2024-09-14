package org.phongsakorn;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ImageProcessor {
    private final int nThreads;
<<<<<<< HEAD:src/main/java/org/phongsakorn/ImageProcessor.java
    private final long[] cpuTimes;

    private CoreGraph coreGraph;
=======
    private final long[] cpuTimes;  // To store CPU time for each thread
>>>>>>> origin/main:src/main/java/org/bunyawat/ImageProcessor.java

    protected ImageProcessor(int nThreads) {
        this.nThreads = nThreads;
        cpuTimes = new long[nThreads];
    }

    protected BufferedImage convertToBlackAndWhite(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage convertedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        for (int i = 0; i < nThreads; i++) {
            int finalI = i;
            executor.submit(() -> {
                long startTime = System.nanoTime();  // Start time for CPU usage
                processRows(originalImage, convertedImage, width, height, finalI);
                long endTime = System.nanoTime();    // End time for CPU usage
                cpuTimes[finalI] = endTime - startTime;  // Calculate CPU time used by this thread
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        CoreGraph.CoreGraphcpu(cpuTimes);

        return convertedImage;
    }

    private void processRows(BufferedImage originalImage, BufferedImage convertedImage, int width, int height, int threadIndex) {
        for (int y = threadIndex; y < height; y += nThreads) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);
                int gray = (rgb >> 16) & 0xFF;
                int newPixel = (gray < 128) ? 0 : 255;
                int error = gray - newPixel;
                convertedImage.setRGB(x, y, (newPixel << 16) | (newPixel << 8) | newPixel);
                distributeError(originalImage, x, y, error, width, height);
            }
        }
    }

    private void distributeError(BufferedImage image, int x, int y, int error, int width, int height) {
        distributeErrorToPixel(image, x + 1, y, error * 7 / 16, width, height);
        distributeErrorToPixel(image, x - 1, y + 1, error * 3 / 16, width, height);
        distributeErrorToPixel(image, x, y + 1, error * 5 / 16, width, height);
        distributeErrorToPixel(image, x + 1, y + 1, error / 16, width, height);
    }

    private void distributeErrorToPixel(BufferedImage image, int x, int y, int error, int width, int height) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            int rgb = image.getRGB(x, y);
            int gray = (rgb >> 16) & 0xFF;
            int newGray = Math.max(0, Math.min(255, gray + error));
            image.setRGB(x, y, (newGray << 16) | (newGray << 8) | newGray);
        }
    }

    public long[] getCpuTimes() {
        return cpuTimes;  // Return CPU times to be used in the chart
    }
}
