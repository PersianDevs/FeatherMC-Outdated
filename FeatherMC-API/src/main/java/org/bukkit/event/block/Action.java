package org.bukkit.event.block;

public enum Action {

    /**
     * Left Click Kardane Block
     */
    LEFT_CLICK_BLOCK,
    /**
     * Right Click Kardane Block
     */
    RIGHT_CLICK_BLOCK,
    /**
     * Left Click Kardane Air
     */
    LEFT_CLICK_AIR,
    /**
     * Right Click Kardane Air
     */
    RIGHT_CLICK_AIR,
    /**
     * Stepping onto or into a block (Ass-pressure)
     *
     * Examples:
     * <ul>
     * <li>Jumping on soil
     * <li>Standing on pressure plate
     * <li>Triggering redstone ore
     * <li>Triggering tripwire
     * </ul>
     */
    PHYSICAL,
}
