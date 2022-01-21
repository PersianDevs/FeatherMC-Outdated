package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Vaqty In Event Call Mishe Ke Yek Player
 * Blocki Ro Bezare
 * <p>
 * Age Block Place Cancel Shode Bashe, Player Nemitoone Block Bezare
 */
public class BlockPlaceEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    protected boolean cancel;
    protected boolean canBuild;
    protected Block placedAgainst;
    protected BlockState replacedBlockState;
    protected ItemStack itemInHand;
    protected Player player;

    public BlockPlaceEvent(final Block placedBlock, final BlockState replacedBlockState, final Block placedAgainst, final ItemStack itemInHand, final Player thePlayer, final boolean canBuild) {
        super(placedBlock);
        this.placedAgainst = placedAgainst;
        this.itemInHand = itemInHand;
        this.player = thePlayer;
        this.replacedBlockState = replacedBlockState;
        this.canBuild = canBuild;
        cancel = false;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * In Method Objecte Playeri Ro Migire Ke Block Ro Place Karde
     *
     * @return Playeri Ro Migire Ke Toye Event Call Shode
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Blockio Migire Ke Place Shode
     *
     * @return Blockio Migire Ke Place Shode
     */
    public Block getBlockPlaced() {
        return getBlock();
    }

    /**
     * BlockState Ro Migire Vaqty Ke Yek Block Jabeja (Replace) Mishe. Masalan
     * Block Be Air Tabdil Mishe
     *
     * @return BlockState Blocki Ro Migire Ke Replace Shode
     */
    public BlockState getBlockReplacedState() {
        return this.replacedBlockState;
    }

    /**
     * Gets the block that this block was placed against
     *
     * @return Block the block that the new block was placed against
     */
    public Block getBlockAgainst() {
        return placedAgainst;
    }

    /**
     * Gets the item in the player's hand when they placed the block.
     *
     * @return The ItemStack for the item in the player's hand when they
     *     placed the block
     */
    public ItemStack getItemInHand() {
        return itemInHand;
    }

    /**
     * Gets the value whether the player would be allowed to build here.
     * Defaults to spawn if the server was going to stop them (such as, the
     * player is in Spawn). Note that this is an entirely different check
     * than BLOCK_CANBUILD, as this refers to a player, not universe-physics
     * rule like cactus on dirt.
     *
     * @return boolean whether the server would allow a player to build here
     */
    public boolean canBuild() {
        return this.canBuild;
    }

    /**
     * Sets the canBuild state of this event. Set to true if you want the
     * player to be able to build.
     *
     * @param canBuild true if you want the player to be able to build
     */
    public void setBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
