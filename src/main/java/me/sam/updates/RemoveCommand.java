package me.sam.updates;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RemoveCommand implements CommandExecutor {
    Updates main;

    public RemoveCommand(Updates main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("removeUpdate"))
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (!p.hasPermission("SamUpdates.removeupdate")) {
                    p.sendMessage(Utils.chat(Locale.instance.get("nopermission")));
                    return false;
                }
                if (args.length < 1) {
                    p.sendMessage(Utils.chat(Locale.instance.get("removecommandargs")));
                    return false;
                }
                if (!Utils.parseInt(args[0]).isPresent()) {
                    p.sendMessage(Utils.chat(Locale.instance.get("removecommandargs")));
                    return false;
                }
                int index = Integer.parseInt(args[0]);
                if (index >= this.main.updateList.size()) {
                    p.sendMessage(Utils.chat(Locale.instance.get("indexnotfound1")));
                    p.sendMessage(Utils.chat(Locale.instance.get("indexnotfound2").replace("%maxindex%", this.main.updateList.size() + "")));
                    return false;
                }
                UpdateText updateText = this.main.updateList.get(index);
                p.sendMessage(Utils.chat(Locale.instance.get("removesuccess").replace("%index%", index + "").replace("%update%", updateText.getText())));
                this.main.updateList.remove(index);
            } else if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender console = (ConsoleCommandSender)sender;
                if (args.length < 1) {
                    console.sendMessage(Utils.chat(Locale.instance.get("removecommandargs")));
                    return false;
                }
                if (!Utils.parseInt(args[0]).isPresent()) {
                    console.sendMessage(Utils.chat(Locale.instance.get("removecommandargs")));
                    return false;
                }
                int index = Integer.parseInt(args[0]);
                if (index >= this.main.updateList.size()) {
                    console.sendMessage(Utils.chat(Locale.instance.get("indexnotfound1")));
                    console.sendMessage(Utils.chat(Locale.instance.get("indexnotfound2").replace("%maxindex%", this.main.updateList.size() + "")));
                    return false;
                }
                UpdateText updateText = this.main.updateList.get(index);
                console.sendMessage(Utils.chat(Locale.instance.get("removesuccess").replace("%index%", index + "").replace("%update%", updateText.getText())));
                this.main.updateList.remove(index);
            }
        return false;
    }
}

