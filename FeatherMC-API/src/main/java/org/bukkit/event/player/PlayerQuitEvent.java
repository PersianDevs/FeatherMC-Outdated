package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Vaqty In Event Call Mishe Ke Playeri Az Server Kharej She
 */
public class PlayerQuitEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String quitMessage;

    public PlayerQuitEvent(final Player who, final String quitMessage) {
        super(who);
        this.quitMessage = quitMessage;
    }

    /**
     * Gets the quit message to send to all online players
     *
     * @return string quit message
     */
    public String getQuitMessage() {
        return quitMessage;
    }

    /**
     * Sets the quit message to send to all online players
     *
     * @param quitMessage quit message
     */
    public void setQuitMessage(String quitMessage) {
        this.quitMessage = quitMessage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
