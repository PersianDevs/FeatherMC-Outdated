package dev.cobblesword.nachospigot.commands;

import dev.cobblesword.nachospigot.commons.ClickableBuilder;
import dev.cobblesword.nachospigot.knockback.CraftKnockbackProfile;
import dev.cobblesword.nachospigot.knockback.KnockbackConfig;
import dev.cobblesword.nachospigot.knockback.KnockbackProfile;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class KnockbackCommand extends Command {

    private final String separator = "§8§m-=-------------------------=-";

    public KnockbackCommand(String name) {
        super(name);
        this.description = "Avaz Kardane Tanzimate Knockback";
        this.usageMessage = "/kb [action] [arguments]";
        this.setAliases(Collections.singletonList("knockback"));
        this.setPermission("feathermc.command.knockback");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;
        if (!(sender instanceof Player)) {
            sender.sendMessage("Shoma Bayad Player Bashid Ke In Kar Ro Anjam Bedid");
            return false;
        }
        Player player = (Player) sender;

        switch (args.length) {
            case 2: {
                switch (args[0].toLowerCase()) {
                    case "create": {
                        if (!isProfileName(args[1])) {
                            CraftKnockbackProfile profile = new CraftKnockbackProfile(args[1]);
                            KnockbackConfig.getKbProfiles().add(profile);
                            profile.save();
                            knockbackCommandMain(player);
                            player.sendMessage("§aProfile e Jadide §e" + args[1] + " §aSakhte Shod");
                            return true;
                        } else {
                            player.sendMessage("§cIn Profile Ba In Esm Ghablan Vojood Dare");
                        }
                        break;
                    }
                    case "delete": {
                        if (KnockbackConfig.getCurrentKb().getName().equalsIgnoreCase(args[1])) {
                            knockbackCommandMain(player);
                            player.sendMessage("§cShoma Nemitoonid Profile ee Ke Dare Estefade Mishe Ro Hazf Konid");
                            return false;
                        }
                        if (KnockbackConfig.getKbProfiles().removeIf(profile -> profile.getName().equalsIgnoreCase(args[1]))) {
                            KnockbackConfig.set("knockback.profiles." + args[1], null);
                            knockbackCommandMain(player);
                            player.sendMessage("§aProfile e §e" + args[1] + " §aRemove Shod");
                            return true;
                        } else {
                            player.sendMessage("§cIn Profile Vojood Nadare");
                        }
                        break;
                    }
                    case "load": {
                        KnockbackProfile profile = KnockbackConfig.getKbProfileByName(args[1]);
                        if (profile != null) {
                            if (KnockbackConfig.getCurrentKb().getName().equalsIgnoreCase(args[1])) {
                                player.sendMessage("§cProfile Load Shod");
                                return false;
                            }
                            KnockbackConfig.setCurrentKb(profile);
                            KnockbackConfig.set("knockback.current", profile.getName());
                            KnockbackConfig.save();
                            knockbackCommandMain(player);
                            player.sendMessage("§aProfile e §e" + args[1] + " §aLoad Shod");
                            return true;
                        } else {
                            player.sendMessage("§cIn Profile Vojood Nadare");
                        }
                        break;
                    }
                    case "view": {
                        KnockbackProfile profile = KnockbackConfig.getKbProfileByName(args[1]);
                        if (profile != null) {
                            knockbackCommandView(player, profile);
                            return true;
                        }
                        player.sendMessage("§cIn Profile Vojood Nadare");
                        break;
                    }
                    case "projectile": {
                        KnockbackProfile profile = KnockbackConfig.getKbProfileByName(args[1]);
                        if (profile != null) {
                            knockbackCommandViewProjectiles(player, profile);
                            return true;
                        }
                        player.sendMessage("§cIn Profile Vojood Nadare");
                        break;
                    }
                    default: {
                        knockbackCommandMain(player);
                    }
                }
                break;
            }
            case 3: {
                switch (args[0].toLowerCase()) {
                    case "set": {
                        KnockbackProfile profile = KnockbackConfig.getKbProfileByName(args[1]);
                        if (profile == null) {
                            sender.sendMessage("§cIn Profile Ba In Name Vojood Nadare");
                            return false;
                        }
                        Player target = Bukkit.getPlayer(args[2]);
                        if (target == null) {
                            sender.sendMessage("§cIn Player Online Nist");
                            return false;
                        }
                        target.setKnockbackProfile(profile);
                        break;
                    }
                }
                break;
            }
            case 4: {
                if (args[0].equalsIgnoreCase("edit")) {
                    KnockbackProfile profile = KnockbackConfig.getKbProfileByName(args[1].toLowerCase());
                    if (profile == null) {
                        player.sendMessage("§cIn Profile Vojood Nadare");
                        return false;
                    }
                    switch (args[2].toLowerCase()) {
                        case "friction-horizontal": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setFrictionHorizontal(value);
                            profile.save();
                            knockbackCommandView(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "friction-vertical": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setFrictionVertical(value);
                            profile.save();
                            knockbackCommandView(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "horizontal": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setHorizontal(value);
                            profile.save();
                            knockbackCommandView(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "vertical": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setVertical(value);
                            profile.save();
                            knockbackCommandView(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "extra-horizontal": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setExtraHorizontal(value);
                            profile.save();
                            knockbackCommandView(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "extra-vertical": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setExtraVertical(value);
                            profile.save();
                            knockbackCommandView(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "vertical-max": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setVerticalMax(value);
                            profile.save();
                            knockbackCommandView(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "vertical-min": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setVerticalMin(value);
                            profile.save();
                            knockbackCommandView(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "stop-sprint": {
                            if ((args[3]).equalsIgnoreCase("true") || (args[3]).equalsIgnoreCase("false")) {
                                profile.setStopSprint(Boolean.parseBoolean(args[3]));
                                profile.save();
                                knockbackCommandView(player, profile);
                                player.sendMessage("§aIn Adad Save Shod");
                                return true;
                            } else {
                                player.sendMessage("§4" + args[3] + " §cis not a boolean.");
                            }
                            break;
                        }
                        case "rod-horizontal": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setRodHorizontal(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "rod-vertical": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setRodVertical(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "arrow-horizontal": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setArrowHorizontal(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "arrow-vertical": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setArrowVertical(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "pearl-horizontal": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setPearlHorizontal(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "pearl-vertical": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setPearlVertical(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "snowball-horizontal": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setSnowballHorizontal(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "snowball-vertical": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setSnowballVertical(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "egg-horizontal": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setEggHorizontal(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                        case "egg-vertical": {
                            if (!NumberUtils.isNumber(args[3])) {
                                player.sendMessage("§4" + args[3] + " §c Adad Nist");
                                return false;
                            }
                            double value = Double.parseDouble(args[3]);
                            profile.setEggVertical(value);
                            profile.save(true);
                            knockbackCommandViewProjectiles(player, profile);
                            player.sendMessage("§aIn Adad Save Shod");
                            break;
                        }
                    }
                }
                break;
            }
            default: {
                knockbackCommandMain(player);
            }
        }
        return false;
    }

    private void knockbackCommandMain(Player player) {
        player.sendMessage(separator + "\n" + "§a§lKnockback profile list:\n");

        for (KnockbackProfile profile : KnockbackConfig.getKbProfiles()) {
            boolean current = KnockbackConfig.getCurrentKb().getName().equals(profile.getName());

            TextComponent line = new ClickableBuilder("§8§l(§e§l➜§8§l) ")
                    .setHover("§c[Inja Click Kon Ta In Profile Ro Be Player E'mal Bokoni] ")
                    .setClick("/kb set " + profile.getName() + " ", ClickEvent.Action.SUGGEST_COMMAND)
                    .build();
            TextComponent load = new ClickableBuilder("§8§l(" + (current ? "§a" : "§7") + "✔§8§l) ")
                    .setHover(current ? "§c[In Profile Load Shode] " : "§a[Inja Click Kon Ta Profile Load She]")
                    .setClick("/kb load " + profile.getName(), ClickEvent.Action.RUN_COMMAND)
                    .build();
            TextComponent delete = new ClickableBuilder("§8§l(§c§l✖§8§l) ")
                    .setHover("§c[Inja Click Kon Ta In Profile Delete She] ")
                    .setClick("/kb delete " + profile.getName(), ClickEvent.Action.RUN_COMMAND)
                    .build();
            TextComponent edit = new ClickableBuilder("§7 " + profile.getName() + " §8§l[§6§l✎§8§l]")
                    .setHover("§e[Inja Click Kon Ta Profile Ro Edit Koni]")
                    .setClick("/kb view " + profile.getName(), ClickEvent.Action.RUN_COMMAND)
                    .build();
            player.spigot().sendMessage(line, load, delete, edit);
        }

        player.spigot().sendMessage(
                new ClickableBuilder("\n§8§l[§a§lSakhtane Profile Jadid§8]")
                        .setHover("§c[Inja Click Kon Ta Yek Profile e Jadid Besazi]")
                        .setClick("/kb create ", ClickEvent.Action.SUGGEST_COMMAND)
                        .build()
        );
        player.sendMessage(separator);
    }

    private void knockbackCommandView(Player player, KnockbackProfile profile) {
        player.sendMessage(separator + "\n" + "§a§lKnockback values:\n");
        for (String values : profile.getKnockbackValues()) {
            TextComponent value = new TextComponent("§6» §e" + values);
            TextComponent edit = new ClickableBuilder(" §8§l[§e§l✎§8§l]")
                    .setHover("§e[Click Kon Ta Value e " + values + " Ro Edit Koni]")
                    .setClick("/kb edit " + profile.getName() + " " + values.replace("§7: ", " "), ClickEvent.Action.SUGGEST_COMMAND)
                    .build();
            player.spigot().sendMessage(value, edit);
        }
        TextComponent page = new ClickableBuilder("\n§8§l[§c§l⬑§8§l] ")
                .setHover("§e[Bargasht]")
                .setClick("/kb", ClickEvent.Action.RUN_COMMAND)
                .build();
        TextComponent projectiles = new ClickableBuilder(" §8§l[§a§lEdit projectiles§8§l]")
                .setClick("/kb projectile " + profile.getName(), ClickEvent.Action.RUN_COMMAND)
                .setHover("§e[Click Kon Ta Projectile Haro Edit Koni]")
                .build();
        player.spigot().sendMessage(page, projectiles);
        player.sendMessage(separator);
    }

    private void knockbackCommandViewProjectiles(Player player, KnockbackProfile profile) {
        player.sendMessage(separator + "\n§a§lProjectiles values: \n");
        for (String values : profile.getProjectilesValues()) {
            TextComponent value = new TextComponent("§6» §e" + values);
            TextComponent edit = new ClickableBuilder(" §8§l[§e§l✎§8§l]")
                    .setHover("§e[Click Kon Ta Value e " + values + " Ro Edit Koni]")
                    .setClick("/kb edit " + profile.getName() + " " + values.replace("§7: ", " "), ClickEvent.Action.SUGGEST_COMMAND)
                    .build();
            player.sendMessage(value, edit);
        }
        TextComponent page = new ClickableBuilder("\n§8§l[§c§l⬑§8§l] ")
                .setHover("§e[Bargasht]")
                .setClick("/kb", ClickEvent.Action.RUN_COMMAND)
                .build();
        TextComponent knockback = new ClickableBuilder("§8§l[§a§lEdit knockback§8§l]")
                .setHover("§e[Click Kon Ta In Knockback Ro Edit Koni]")
                .setClick("/kb view " + profile.getName(), ClickEvent.Action.RUN_COMMAND)
                .build();
        player.spigot().sendMessage(page, knockback);
        player.sendMessage(separator);

    }

    private boolean isProfileName(String name) {
        for (KnockbackProfile profile : KnockbackConfig.getKbProfiles()) {
            if (profile.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
