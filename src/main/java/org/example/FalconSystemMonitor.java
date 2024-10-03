package org.example;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.NetworkIF;
import oshi.hardware.Sensors;

import java.util.List;

public class FalconSystemMonitor {
    public static void main(String[] args) throws InterruptedException {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        Sensors sensors = systemInfo.getHardware().getSensors();

        // CPU Kullanımı
        double[] load = processor.getSystemCpuLoadTicks();
        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(load) * 100;
        System.out.printf("Current CPU usage: %.2f%%\n", cpuLoad);

        // RAM Kullanımı
        long totalMemory = memory.getTotal();
        long availableMemory = memory.getAvailable();
        long usedMemory = totalMemory - availableMemory;
        System.out.printf("Total RAM: %.2f GB\n", totalMemory / 1e9);
        System.out.printf("Used RAM: %.2f GB\n", usedMemory / 1e9);
        System.out.printf("Available RAM: %.2f GB\n", availableMemory / 1e9);

        // Ağ Trafiği
        List<NetworkIF> networks = systemInfo.getHardware().getNetworkIFs();
        for (NetworkIF net : networks) {
            net.updateAttributes();
            System.out.printf("Interface: %s\n", net.getName());
            System.out.printf("Bytes sent: %d\n", net.getBytesSent());
            System.out.printf("Bytes received: %d\n", net.getBytesRecv());
        }

        // CPU Sıcaklığı
        double cpuTemperature = sensors.getCpuTemperature();
        System.out.printf("CPU Temperature: %.1f°C\n", cpuTemperature);
    }
}
