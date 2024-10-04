package org.example;

import javax.swing.*;

public class AlertManager {
    private static final double CPU_THRESHOLD = 90.0; // CPU için eşik

    public static void checkCpuUsage(double cpuUsage) {
        if (cpuUsage > CPU_THRESHOLD) {
            JOptionPane.showMessageDialog(null,
                    "Warning: CPU usage has exceeded " + CPU_THRESHOLD + "%!", "CPU Usage Alert",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // 1.1 RAM uyarı fonksiyonu kaldırıldı çünkü daima sınıra ulaştın hatası veriyor
}
