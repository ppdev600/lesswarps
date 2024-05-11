package plugin.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private Map<String, Location> warps;

    @Override
    public void onEnable() {
        warps = new HashMap<>();
        getLogger().info("LessWarps plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LessWarps plugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setwarp")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    String warpName = args[0].toLowerCase();
                    Location playerLocation = player.getLocation();
                    warps.put(warpName, playerLocation);
                    player.sendMessage(ChatColor.GREEN + "Warp '" + warpName + "' has been set.");
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /setwarp <name>");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("warp")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    String warpName = args[0].toLowerCase();
                    Location warpLocation = warps.get(warpName);
                    if (warpLocation != null) {
                        player.teleport(warpLocation);
                        player.sendMessage(ChatColor.GREEN + "Teleported to warp '" + warpName + "'.");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Warp '" + warpName + "' does not exist.");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /warp <name>");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("del")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    String warpName = args[0].toLowerCase();
                    if (warps.containsKey(warpName)) {
                        warps.remove(warpName);
                        player.sendMessage(ChatColor.GREEN + "Warp '" + warpName + "' has been deleted.");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Warp '" + warpName + "' does not exist.");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /del <warp>");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("author")) {
            sender.sendMessage(ChatColor.YELLOW + "This plugin was created by PP.322666.");
            return true;
        }
        return false;
    }
}
