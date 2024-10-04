package org.example;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class CPUMonitor {
    private final CentralProcessor processor;

    public CPUMonitor() {
        SystemInfo systemInfo = new SystemInfo();
        this.processor = systemInfo.getHardware().getProcessor();
    }

    public double getCpuUsage() throws InterruptedException {
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Thread.sleep(1000);
        long[] currentTicks = processor.getSystemCpuLoadTicks();
        return processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
    }
}
