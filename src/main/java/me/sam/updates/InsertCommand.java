package me.sam.updates;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class InsertCommand implements CommandExecutor {
    Updates main;

    public InsertCommand(Updates main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("insertupdate"))
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (!p.hasPermission("SamUpdates.insertupdate")) {
                    p.sendMessage(Utils.chat(Locale.instance.get("nopermission")));
                    return false;
                }
                if (args.length < 3) {
                    p.sendMessage(Utils.chat(Locale.instance.get("insertcommandargs")));
                    return false;
                }
                if (args[0].equalsIgnoreCase("last")) {
                    if (args[1].equalsIgnoreCase("update")) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 2; i < args.length; i++)
                            sb.append(args[i] + " ");
                        String update = sb.toString().trim();
                        UpdateText updateText = new UpdateText(UpdateType.UPDATE, update);
                        this.main.updateList.addLast(updateText);
                        p.sendMessage(Utils.chat(Locale.instance.getNoPrefix("updatesuccess").replace("%update%", update)));
                        this.main.incrementMissedUpdatesToAll();
                    } else if (args[1].equalsIgnoreCase("bugfix")) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 2; i < args.length; i++)
                            sb.append(args[i] + " ");
                        String update = sb.toString().trim();
                        UpdateText updateText = new UpdateText(UpdateType.BUGFIX, update);
                        this.main.updateList.addLast(updateText);
                        p.sendMessage(Utils.chat(Locale.instance.getNoPrefix("bugfixsuccess").replace("%update%", update)));
                        this.main.incrementMissedUpdatesToAll();
                    }
                    return false;
                }
                if (!Utils.parseInt(args[0]).isPresent()) {
                    p.sendMessage(Utils.chat(Locale.instance.get("insertcommandargs")));
                    return false;
                }
                int index = Integer.parseInt(args[0]);
                if (index >= this.main.updateList.size()) {
                    p.sendMessage(Utils.chat(Locale.instance.get("indexnotfound1")));
                    p.sendMessage(Utils.chat(Locale.instance.get("indexnotfound2").replace("%maxindex%", this.main.updateList.size() + "")));
                    return false;
                }
                if (args[1].equalsIgnoreCase("update")) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++)
                        sb.append(args[i] + " ");
                    String update = sb.toString().trim();
                    UpdateText updateText = new UpdateText(UpdateType.UPDATE, update);
                    this.main.updateList.add(index, updateText);
                    p.sendMessage(Utils.chat(Locale.instance.getNoPrefix("updatesuccess").replace("%update%", update)));
                    this.main.incrementMissedUpdatesToAll();
                } else if (args[1].equalsIgnoreCase("bugfix")) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++)
                        sb.append(args[i] + " ");
                    String update = sb.toString().trim();
                    UpdateText updateText = new UpdateText(UpdateType.BUGFIX, update);
                    this.main.updateList.add(index, updateText);
                    p.sendMessage(Utils.chat(Locale.instance.getNoPrefix("bugfixsuccess").replace("%update%", update)));
                    this.main.incrementMissedUpdatesToAll();
                }
            } else if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender console = (ConsoleCommandSender)sender;
                if (args.length < 3) {
                    console.sendMessage(Utils.chat(Locale.instance.get("insertcommandargs")));
                    return false;
                }
                if (args[0].equalsIgnoreCase("last")) {
                    if (args[1].equalsIgnoreCase("update")) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 2; i < args.length; i++)
                            sb.append(args[i] + " ");
                        String update = sb.toString().trim();
                        UpdateText updateText = new UpdateText(UpdateType.UPDATE, update);
                        this.main.updateList.addLast(updateText);
                        console.sendMessage(Utils.chat(Locale.instance.getNoPrefix("updatesuccess").replace("%update%", update)));
                        this.main.incrementMissedUpdatesToAll();
                    } else if (args[1].equalsIgnoreCase("bugfix")) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 2; i < args.length; i++)
                            sb.append(args[i] + " ");
                        String update = sb.toString().trim();
                        UpdateText updateText = new UpdateText(UpdateType.BUGFIX, update);
                        this.main.updateList.addLast(updateText);
                        console.sendMessage(Utils.chat(Locale.instance.getNoPrefix("bugfixsuccess").replace("%update%", update)));
                        this.main.incrementMissedUpdatesToAll();
                    }
                    return false;
                }
                if (!Utils.parseInt(args[0]).isPresent()) {
                    console.sendMessage(Utils.chat(Locale.instance.get("insertcommandargs")));
                    return false;
                }
                int index = Integer.parseInt(args[0]);
                if (index >= this.main.updateList.size()) {
                    console.sendMessage(Utils.chat(Locale.instance.get("indexnotfound1")));
                    console.sendMessage(Utils.chat(Locale.instance.get("indexnotfound2").replace("%maxindex%", this.main.updateList.size() + "")));
                    return false;
                }
                if (args[1].equalsIgnoreCase("update")) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++)
                        sb.append(args[i] + " ");
                    String update = sb.toString().trim();
                    UpdateText updateText = new UpdateText(UpdateType.UPDATE, update);
                    this.main.updateList.add(index, updateText);
                    console.sendMessage(Utils.chat(Locale.instance.getNoPrefix("updatesuccess").replace("%update%", update)));
                    this.main.incrementMissedUpdatesToAll();
                } else if (args[1].equalsIgnoreCase("bugfix")) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++)
                        sb.append(args[i] + " ");
                    String update = sb.toString().trim();
                    UpdateText updateText = new UpdateText(UpdateType.BUGFIX, update);
                    this.main.updateList.add(index, updateText);
                    console.sendMessage(Utils.chat(Locale.instance.getNoPrefix("bugfixsuccess").replace("%update%", update)));
                    this.main.incrementMissedUpdatesToAll();
                }
            }
        return false;
    }
}
