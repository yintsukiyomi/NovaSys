package org.example;

import oshi.SystemInfo;
import oshi.hardware.Sensors;

public class CpuTemperatureMonitor {
    private final Sensors sensors;

    public CpuTemperatureMonitor() {
        SystemInfo systemInfo = new SystemInfo();
        this.sensors = systemInfo.getHardware().getSensors();
    }

    public double getCpuTemperature() {
        return sensors.getCpuTemperature();
    }
}
