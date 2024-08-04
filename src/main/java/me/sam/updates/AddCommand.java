package me.sam.updates;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddCommand implements CommandExecutor {
    private final Updates main;
    private final boolean silent;

    public AddCommand(Updates main, boolean silent) {
        this.main = main;
        this.silent = silent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("SamUpdates.addupdate")) {
            sender.sendMessage(Utils.chat(Locale.instance.get("nopermission")));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(Utils.chat(Locale.instance.get("addcommandargs")));
            return false;
        }

        String updateType = args[0].toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        String update = sb.toString().trim();
        UpdateType type = updateType.equals("bugfix") ? UpdateType.BUGFIX : UpdateType.UPDATE;
        String messageTemplate = type == UpdateType.BUGFIX ? "bugfixmsg" : "updatemsg";
        String successTemplate = type == UpdateType.BUGFIX ? "bugfixsuccess" : "updatesuccess";
        String updateColor = type == UpdateType.BUGFIX ? "<yellow>" : "<green>";

        UpdateText updateText = new UpdateText(type, update);
        this.main.updateList.addFirst(updateText);

        MiniMessage mm = MiniMessage.miniMessage();
        String msg = "<hover:show_text:'" + updateColor + updateText.getText() + "'>" + Locale.instance.getNoPrefix(messageTemplate).replace("%name%", sender.getName()) + "</hover>";
        Component parsed = mm.deserialize(msg);
        sender.sendMessage(Utils.chat(Locale.instance.getNoPrefix(successTemplate).replace("%update%", update)));

        if (silent) {
            this.main.playUpdateSoundToAll();
            Bukkit.broadcast(parsed);
        }
        this.main.incrementMissedUpdatesToAll();

        return true;
    }
}