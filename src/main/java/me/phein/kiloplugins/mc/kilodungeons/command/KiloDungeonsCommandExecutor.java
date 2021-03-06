package me.phein.kiloplugins.mc.kilodungeons.command;

import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public class KiloDungeonsCommandExecutor implements CommandExecutor {

    private String version;

    public KiloDungeonsCommandExecutor(JavaPlugin plugin) {
        this.version = plugin.getDescription().getVersion();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Entity) {
            sender.sendMessage("" + ChatColor.DARK_BLUE + ChatColor.BOLD + "Kilo" + ChatColor.DARK_PURPLE + "Dungeons"
                    + ChatColor.GOLD + " v" + version
                    + ChatColor.GRAY + " by IPatGamer and WolfHybrid23");
            sender.sendMessage("" + ChatColor.DARK_GRAY + ChatColor.ITALIC + "Note: This is a pre-release version. More dungeons will be added in the future.");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Need help? Want to suggest features? Join our "
                    + ChatColor.BLUE + ChatColor.BOLD + "Discord"
                    + ChatColor.LIGHT_PURPLE + " community by clicking on the happy face:");
            ComponentBuilder builder = new ComponentBuilder("\u25D5\u203F\u25D5");
            builder.color(net.md_5.bungee.api.ChatColor.GREEN);
            builder.event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/YUN262D"));
            builder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] { new TextComponent("discord.gg/YUN262D") }));
            sender.spigot().sendMessage(builder.create());
        } else {
            sender.sendMessage("KiloDungeons v" + version + " by IPatGamer and WolfHybrid23");
            sender.sendMessage("Note: This is a pre-release version. More dungeons will be added in the future.");
            sender.sendMessage("");
            sender.sendMessage("Need help? Want to suggest features? Join our Discord community by using this link:");
            sender.sendMessage("https://discord.gg/YUN262D");
        }
        return true;
    }
}
