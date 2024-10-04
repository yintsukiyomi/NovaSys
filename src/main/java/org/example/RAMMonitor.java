package org.example;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

public class RAMMonitor {
    private final GlobalMemory memory;

    public RAMMonitor() {
        SystemInfo systemInfo = new SystemInfo();
        this.memory = systemInfo.getHardware().getMemory();
    }

    public String getMemoryUsage() {
        long totalMemory = memory.getTotal();
        long usedMemory = totalMemory - memory.getAvailable();

        return String.format("Total: %.2f GB, Used: %.2f GB, Available: %.2f GB",
                totalMemory / 1e9, usedMemory / 1e9, memory.getAvailable() / 1e9);
    }

    // Kullanılan bellek miktarını döndüren metod
    public long getUsedMemory() {
        return memory.getTotal() - memory.getAvailable(); // Kullanılan bellek miktarını hesapla
    }
}
