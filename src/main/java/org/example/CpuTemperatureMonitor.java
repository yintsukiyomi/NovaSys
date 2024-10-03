package org.example;

import oshi.SystemInfo;
import oshi.hardware.Sensors;

public class TemperatureMonitor {
    private SystemInfo systemInfo;
    private Sensors sensors;

    public TemperatureMonitor() {
        this.systemInfo = new SystemInfo();
        this.sensors = systemInfo.getHardware().getSensors();
    }

    public void getCpuTemperature() {
        double cpuTemperature = sensors.getCpuTemperature();
        System.out.printf("CPU Temperature: %.1f°C\n", cpuTemperature);
    }
}
