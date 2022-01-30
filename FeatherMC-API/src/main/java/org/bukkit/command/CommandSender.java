package org.bukkit.command;

import org.bukkit.Server;
import org.bukkit.permissions.Permissible;

public interface CommandSender extends Permissible {

    /**
     * Be Kasi Ke Commando Zade Payam Mifreste
     *
     * @param message Payami Ke Gharare Ersal She
     */
    public void sendMessage(String message);

    /**
     * Be Kasi Ke Commando Zade Chanta Payam Mifreste
     *
     * @param messages Array ii Az Payam Ke Gharare Ersal She
     */
    public void sendMessage(String[] messages);

    // FeatherMC
    /** Be Kasi Ke Commando Zade Ba Supporte Chat Color Payam Mifreste
     *
     * @param message Payami Ke Gharare Ersal She
     */
    public void sendMessageColor(String message);

    /**
     * Serveri Ro Return Mikone Ke Command Ro Toosh Zadi
     *
     * @return Serveri Ke Command Ro Toosh Zadi
     */
    public Server getServer();

    /**
     * Name e Kasi Ro Migire Ke Commando Zade
     *
     * @return Name e Sender
     */
    public String getName();

    // Paper start
    /**
     * Sends the component to the sender // todo: Farsi Kardan sendMessaage e CommandSender
     *
     * <p>If this sender does not support sending full components then
     * the component will be sent as legacy text.</p>
     *
     * @param component the component to send
     */
    default void sendMessage(net.md_5.bungee.api.chat.BaseComponent component) {
        this.sendMessage(component.toLegacyText());
    }

    /**
     * Sends an array of components as a single message to the sender
     *
     * <p>If this sender does not support sending full components then
     * the components will be sent as legacy text.</p>
     *
     * @param components the components to send
     */
    default void sendMessage(net.md_5.bungee.api.chat.BaseComponent... components) {
        this.sendMessage(new net.md_5.bungee.api.chat.TextComponent(components).toLegacyText());
    }
    // Paper end
}
