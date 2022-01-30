package org.bukkit.craftbukkit.command;

import net.minecraft.server.ICommandListener;
import net.minecraft.server.CommandBlockListenerAbstract;
import net.minecraft.server.IChatBaseComponent;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.craftbukkit.util.CraftChatMessage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents input from a command block
 */
public class CraftBlockCommandSender extends ServerCommandSender implements BlockCommandSender {
    private final CommandBlockListenerAbstract commandBlock;

    public CraftBlockCommandSender(CommandBlockListenerAbstract commandBlockListenerAbstract) {
        super();
        this.commandBlock = commandBlockListenerAbstract;
    }

    public Block getBlock() {
        return commandBlock.getWorld().getWorld().getBlockAt(commandBlock.getChunkCoordinates().getX(), commandBlock.getChunkCoordinates().getY(), commandBlock.getChunkCoordinates().getZ());
    }

    public void sendMessage(String message) {
        for (IChatBaseComponent component : CraftChatMessage.fromString(message)) {
            commandBlock.sendMessage(component);
        }
    }

    public void sendMessage(String[] messages) {
        for (String message : messages) {
            sendMessage(message);
        }
    }

    // FeatherMC
    @Override
    public void sendMessageColor(String message) {
        for (IChatBaseComponent component : CraftChatMessage.fromString(message)) {
            commandBlock.sendMessage(component);
        }
    }

    public String getName() {
        return commandBlock.getName();
    }

    public boolean isOp() {
        return true;
    }

    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Cannot change operator status of a block");
    }

    public ICommandListener getTileEntity() {
        return commandBlock;
    }
}
