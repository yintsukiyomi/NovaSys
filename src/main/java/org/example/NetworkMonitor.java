package org.example;

import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

import java.util.List;

public class NetworkMonitor {
    private final List<NetworkIF> networks;
    private long previousBytesSent = 0;
    private long previousBytesRecv = 0;

    public NetworkMonitor() {
        SystemInfo systemInfo = new SystemInfo();
        this.networks = systemInfo.getHardware().getNetworkIFs();
        updatePreviousBytes();
    }

    private void updatePreviousBytes() {
        for (NetworkIF net : networks) {
            net.updateAttributes();
            previousBytesSent = net.getBytesSent();
            previousBytesRecv = net.getBytesRecv();
        }
    }

    public String getNetworkUsage() {
        StringBuilder networkUsage = new StringBuilder();

        for (NetworkIF net : networks) {
            net.updateAttributes();
            long currentBytesSent = net.getBytesSent();
            long currentBytesRecv = net.getBytesRecv();

            long sentDifference = currentBytesSent - previousBytesSent;
            long recvDifference = currentBytesRecv - previousBytesRecv;

            double sentMB = sentDifference / 1048576.0;
            double recvMB = recvDifference / 1048576.0;

            networkUsage.append(String.format("Interface: %s%n", net.getName()));
            networkUsage.append(String.format("Bytes sent: %.2f MB, Bytes received: %.2f MB%n", sentMB, recvMB));

            previousBytesSent = currentBytesSent;
            previousBytesRecv = currentBytesRecv;
        }

        return networkUsage.toString();
    }
}
