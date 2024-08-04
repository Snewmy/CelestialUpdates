package me.sam.updates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class RemoveTabCompleter implements TabCompleter {
    Updates main;

    public RemoveTabCompleter(Updates main) {
        this.main = main;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            for (int i = 0; i < this.main.updateList.size(); i++)
                commands.add(i + "");
            StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if (args.length == 2 &&
                Utils.parseInt(args[0]).isPresent()) {
            int index = Integer.parseInt(args[0]);
            if (index < this.main.updateList.size()) {
                UpdateText updateText = this.main.updateList.get(index);
                commands.add(updateText.getText());
                StringUtil.copyPartialMatches(args[1], commands, completions);
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
