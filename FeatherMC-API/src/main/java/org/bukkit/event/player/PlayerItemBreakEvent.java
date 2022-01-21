package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Vaqty In Event Call Mishe Ke Yek Item Kharab She (Masalan Yek Sword Ya Flint and Steel).
 * <p>
 * The item that's breaking will exist in the inventory with a stack size of
 * 0. After the event, the item's durability will be reset to 0.
 */
public class PlayerItemBreakEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack brokenItem;

    public PlayerItemBreakEvent(final Player player, final ItemStack brokenItem) {
        super(player);
        this.brokenItem = brokenItem;
    }

    /**
     * Gets the item that broke
     *
     * @return The broken item
     */
    public ItemStack getBrokenItem() {
        return brokenItem;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
