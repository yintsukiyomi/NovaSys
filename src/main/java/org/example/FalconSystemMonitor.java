package org.example;

import javax.swing.*;
import java.awt.*;

public class FalconSystemMonitor {

    // Swing bileşenleri
    private static JLabel cpuLabel, ramLabel, networkLabel, tempLabel;

    public static void main(String[] args) {
        System.out.println("Starting Falcon System Monitor...");

        // Arayüz kurulum
        setupUI();

        // Monitor sınıfları
        CPUMonitor cpuMonitor = new CPUMonitor();
        RAMMonitor ramMonitor = new RAMMonitor();
        NetworkMonitor networkMonitor = new NetworkMonitor();
        CpuTemperatureMonitor temperatureMonitor = new CpuTemperatureMonitor();

        // veri toplama ve arayüzü güncelleme
        while (true) {
            try {
                updateMetrics(cpuMonitor, ramMonitor, networkMonitor, temperatureMonitor);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Kesinti durumunda güncel durumu koru
                System.out.println("Thread was interrupted, exiting...");
                break; // Dışarı çıkmak istersen döngüyü sonlandır
            }
        }
    }

    // Arayüzü güncelleme metodu
    private static void updateMetrics(CPUMonitor cpuMonitor, RAMMonitor ramMonitor,
                                      NetworkMonitor networkMonitor, CpuTemperatureMonitor temperatureMonitor) throws InterruptedException {
        System.out.println("Fetching system metrics...");

        // CPU Kullanımı
        double cpuUsage = cpuMonitor.getCpuUsage();
        cpuLabel.setText(String.format("CPU Usage: %.2f%%", cpuUsage));
        AlertManager.checkCpuUsage(cpuUsage); // CPU kullanımı için kontrol et
        SystemLogger.log("CPU Usage: " + cpuUsage + "%"); // Logla

        // RAM Kullanımı
        String ramUsage = ramMonitor.getMemoryUsage();
        ramLabel.setText("RAM Usage: " + ramUsage);
        SystemLogger.log("RAM Usage: " + ramUsage); // Logla

        // Ağ Kullanımı
        String networkUsage = networkMonitor.getNetworkUsage();
        networkLabel.setText("<html>" + networkUsage.replaceAll("\n", "<br>") + "</html>");

        // CPU Sıcaklığı
        double cpuTemperature = temperatureMonitor.getCpuTemperature();
        tempLabel.setText(String.format("CPU Temperature: %.1f°C", cpuTemperature));
        SystemLogger.log("CPU Temperature: " + cpuTemperature + "°C"); // Logla
    }

    // Swing arayüzü kurma
    private static void setupUI() {
        // Ana pencere
        JFrame frame = new JFrame("Falcon System Monitor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Arayüz paneli
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(4, 1));

        // Etiketler
        cpuLabel = new JLabel("CPU Usage: ");
        ramLabel = new JLabel("RAM Usage: ");
        networkLabel = new JLabel("Network Usage: ");
        tempLabel = new JLabel("CPU Temperature: ");

        // Etiketlerin yazı rengi
        Color white = Color.WHITE;
        cpuLabel.setForeground(white);
        ramLabel.setForeground(white);
        networkLabel.setForeground(white);
        tempLabel.setForeground(white);

        // Panel'e etiketleri
        panel.add(cpuLabel);
        panel.add(ramLabel);
        panel.add(networkLabel);
        panel.add(tempLabel);

        // Pencereye panelİ EKLEME
        frame.add(panel);
        frame.setVisible(true);
    }
}
