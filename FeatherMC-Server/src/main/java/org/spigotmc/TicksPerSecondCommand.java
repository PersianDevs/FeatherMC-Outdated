package org.spigotmc;

import com.sun.management.OperatingSystemMXBean;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public class TicksPerSecondCommand extends Command {

    public TicksPerSecondCommand(String name) {
        super(name);
        this.description = "Gets the current ticks per second for the server";
        this.usageMessage = "/tps";
        this.setPermission("bukkit.command.tps");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) {
            return true;
        }

        // PaperSpigot start - Further improve tick handling
        double[] tps = org.bukkit.Bukkit.spigot().getTPS();
        String[] tpsAvg = new String[tps.length];

        for (int i = 0; i < tps.length; i++) {
            tpsAvg[i] = format(tps[i]);
        }

        // FeatherMC - Improved TPS System
        sender.sendMessageColor("&eTPS: &b" + tpsAvg[0] + "&7, &b" + tpsAvg[1] + "&7, &b" + tpsAvg[2] + "&8 \n" +
                "&eMemory: " + getMemoryUsage() + "\n" +
                "&eCPU: &b" + getCPUUsage() + "%&7, &b" + getProcessUsage() + "% &7(System, Process)");
        return true;
    }

    private static String format(double tps) { // PaperSpigot - made static & FeatherMC - Removed useless .toString() method
        return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED)
                + ((tps > 20.0) ? "*" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
    }

    final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    final OperatingSystemMXBean platformMXBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    private String getMemoryUsage() {
        return "&b" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) /
                (1024 * 1024) + "&7/&b" + (Runtime.getRuntime().totalMemory() /
                (1024 * 1024)) + " &7(Free: " + (Runtime.getRuntime().freeMemory() /
                (1024 * 1024)) + " MB)\n";
    }
    private String getCPUUsage() {
        final float cpuUsage = (float) (platformMXBean.getSystemCpuLoad() * 100);
        final String formattedString = decimalFormat.format(cpuUsage);
        if (cpuUsage >= 0 && cpuUsage <= 40) {
            return "&a" + formattedString;
        } else if (cpuUsage >= 40 && cpuUsage <= 75) {
            return "&e" + formattedString;
        } else if (cpuUsage >= 75 && cpuUsage <= 95) {
            return "&c" + formattedString;
        } else if (cpuUsage >= 95 && cpuUsage <= 100) {
            return "&4" + formattedString;
        }
        return decimalFormat.format(cpuUsage);
    }
    private String getProcessUsage() {
        float processCpuUsage = (float) (platformMXBean.getProcessCpuLoad() * 100);
        final String formattedString = decimalFormat.format(processCpuUsage);
        if (processCpuUsage >= 0 && processCpuUsage <= 40) {
            return "&a" + formattedString;
        } else if (processCpuUsage >= 40 && processCpuUsage <= 75) {
            return "&e" + formattedString;
        } else if (processCpuUsage >= 75 && processCpuUsage <= 95) {
            return "&c" + formattedString;
        } else if (processCpuUsage >= 95 && processCpuUsage <= 100) {
            return "&4" + formattedString;
        }
        return decimalFormat.format(processCpuUsage);
    }
}
