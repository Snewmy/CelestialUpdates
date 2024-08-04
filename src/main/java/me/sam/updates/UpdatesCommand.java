package me.sam.updates;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class UpdatesCommand implements CommandExecutor {
    Updates main;

    public UpdatesCommand(Updates main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("updates") &&
                sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length < 1) {
                if (!Updates.updatesTrackers.containsKey(p.getUniqueId())) {
                    UpdatesTracker updatesTracker1 = new UpdatesTracker();
                    Updates.updatesTrackers.put(p.getUniqueId(), updatesTracker1);
                }
                UpdatesTracker updatesTracker = Updates.updatesTrackers.get(p.getUniqueId());
                this.main.sendPlayerUpdatesJoinMessage(p, updatesTracker);
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (!p.hasPermission("CloudUpdates.Admin")) {
                    p.sendMessage(Utils.chat(Locale.instance.get("nopermission")));
                    return false;
                }
                long currentTime = System.currentTimeMillis();
                Updates.instance.messages = YamlConfiguration.loadConfiguration(Updates.instance.messagesFile);
                p.sendMessage(Utils.chat(Locale.instance.get("reloadmessage").replace("%time%", System.currentTimeMillis() - currentTime + "")));
            }
        }
        return false;
    }
}
