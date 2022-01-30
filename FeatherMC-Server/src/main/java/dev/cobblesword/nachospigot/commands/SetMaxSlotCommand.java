package dev.cobblesword.nachospigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;

public class SetMaxSlotCommand extends Command {

    public SetMaxSlotCommand(String name) {
        super(name);
        this.description = "Set Kardan Max Slote Server";
        this.usageMessage = "/sms [amount]";
        this.setAliases(Collections.singletonList("setslots"));
        setPermission("feathermc.command.setslots");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;
        if(args.length != 1) {
            sender.sendMessage(ChatColor.GRAY + "Slote Max e In Server Alan " + ChatColor.WHITE + Bukkit.getMaxPlayers() + ChatColor.GRAY + " Hast!");
            sender.sendMessage(ChatColor.RED + "Lotfan Az '/sms [Tedad]' Estefade Koni Ta Max Slot Ro Avaz Konid");
            return false;
        }
        int amount;
        try {
            amount = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            sender.sendMessage(ChatColor.RED + "Lotfan Yek Adad Vared Konid Bejaye Neveshtane '" + args[0] + "'.");
            return false;
        }
        Bukkit.getServer().setMaxPlayers(amount);
        sender.sendMessage(ChatColor.GREEN + "Slote Player Ha Be " + amount + " Set Shod");
        return false;
    }
}
