package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Vaqty In Event Call Mishe Ke Yek Player Blocki Ro Kharab Kone
 * <p>
 * Age Mikhay EXP Block Ro Begiri, Bayad Value e EXP Ro
 * Balaye 0 Bezari. Betore Pishfarz, Vaqty EXP To Event Set Mishe Ke:
 * <ol>
 * <li>Player GameModesh Creative Ya Adventure Nabashe
 * <li>Player Betoone Block Ro Begire (ie: Na Inke Kharabesh Kone,
 *     Hich Nagire)
 * <li>Player Silk Touch Nadashte Bashe
 * <li>Blocke Toye Vanilla e Asly Game EXP Drop Kone
 * </ol>
 * <p>
 */
public class BlockBreakEvent extends BlockExpEvent implements Cancellable {
    private final Player player;
    private boolean cancel;

    public BlockBreakEvent(final Block theBlock, final Player player) {
        super(theBlock, 0);

        this.player = player;
    }

    /**
     * In Method Objecte Playeri Ro Migire Ke Block Ro Kande
     *
     * @return Playeri Ro Migire Ke Toye Event Call Shode
     */
    public Player getPlayer() {
        return player;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
