package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Vaqty In Event Call Mishe Ke Yek Blocki Ba Fire Kharab She Va Besooze
 * <p>
 * Age In Event Cancel She, Dige Block Ba Atish Gereftan
 * Nemisooze Va Kharab Nemishe
 */
public class BlockBurnEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    public BlockBurnEvent(final Block block) {
        super(block);
        this.cancelled = false;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
