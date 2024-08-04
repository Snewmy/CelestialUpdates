package me.sam.updates;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatColor;

public class Utils {
    public static final Pattern patternBrackets = Pattern.compile("\\{#[0-9a-fA-F]{6}\\}");

    public static final Pattern pattern = Pattern.compile("#[0-9a-fA-F]{6}");

    public static String chat(String s) {
        s = removeBrackets(s);
        Matcher match = pattern.matcher(s);
        while (match.find()) {
            String color = s.substring(match.start(), match.end());
            s = s.replace(color, ChatColor.of(color) + "");
            match = pattern.matcher(s);
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private static String removeBrackets(String text) {
        Matcher m = patternBrackets.matcher(text);
        String replaced = text;
        while (m.find()) {
            String hexcode = m.group();
            String fixed = hexcode.substring(2, 8);
            replaced = replaced.replace(hexcode, "#" + fixed);
        }
        return replaced;
    }

    public static Optional<Integer> parseInt(String s) {
        try {
            return Optional.of(Integer.valueOf(Integer.parseInt(s)));
        } catch (NumberFormatException e1) {
            return Optional.empty();
        }
    }
}
