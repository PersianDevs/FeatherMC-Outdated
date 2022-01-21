package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;

/**
 * Vaqty Call Mishe Ke Bebinim Mitoonim Inja Build Konim Ya Na
 * <p>
 * Note:
 * <ul>
 * <li>Ba Estefade Az getBlock() Mitoonim Blocki Ro Begeirim Ke Mikhaym
 *     Mikhaym Rooye Ye Chizi Bezarim, Na Inke Mikhaym Hamintori Block Ro Bezarim
 * <li>Age Mikhay Bedoone Che Blocki Place Shode Az {@link
 *     #getMaterial()} Ya {@link #getMaterialId()} Estefade Kon
 * </ul>
 */
public class BlockCanBuildEvent extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();
    protected boolean buildable;

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    protected int material;

    /**
     *
     * @deprecated Magic value
     * @param block Blocki Ke Toye In Block Hast
     * @param id Id e Blocki Ke Toye In Event Hast
     * @param canBuild boolean ee Ke return Mishe Baraye Place Kardane Block
     */
    @Deprecated
    public BlockCanBuildEvent(final Block block, final int id, final boolean canBuild) {
        super(block);
        buildable = canBuild;
        material = id;
    }

    /** // todo: BlockCanBuildEvent
     * Gets whether or not the block can be built here.
     * <p>
     * By default, returns Minecraft's answer on whether the block can be
     * built here or not.
     *
     * @return boolean whether or not the block can be built
     */
    public boolean isBuildable() {
        return buildable;
    }

    /**
     * Sets whether the block can be built here or not.
     *
     * @param cancel true if you want to allow the block to be built here
     *     despite Minecraft's default behaviour
     */
    public void setBuildable(boolean cancel) {
        this.buildable = cancel;
    }

    /**
     * Gets the Material that we are trying to place.
     *
     * @return The Material that we are trying to place
     */
    public Material getMaterial() {
        return Material.getMaterial(material);
    }

    /**
     * Gets the Material ID for the Material that we are trying to place.
     *
     * @return The Material ID for the Material that we are trying to place
     * @deprecated Magic value
     */
    @Deprecated
    public int getMaterialId() {
        return material;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
