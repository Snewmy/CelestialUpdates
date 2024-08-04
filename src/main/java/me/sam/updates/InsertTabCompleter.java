package me.sam.updates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class InsertTabCompleter implements TabCompleter {
    Updates main;

    public InsertTabCompleter(Updates main) {
        this.main = main;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            commands.add("last");
            for (int i = 0; i < this.main.updateList.size(); i++)
                commands.add(i + "");
            StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if (args.length == 2) {
            commands.add("bugfix");
            commands.add("update");
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}

