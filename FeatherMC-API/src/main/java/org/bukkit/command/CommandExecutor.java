package org.bukkit.command;

public interface CommandExecutor {

    /**
     * Execute Kardane Command
     *
     * @param sender Objecte Kasi Ke In Commando Zade
     * @param command Commandi Ke Estefade Shode
     * @param label Alias Commandi Ke Estefade Shode
     * @param args Argumenti Ke Toye Commande
     * @return true Age Command Motabar Bashe, Magane false
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
