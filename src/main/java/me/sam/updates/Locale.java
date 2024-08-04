package me.sam.updates;

public class Locale {

    public static Locale instance;

    public Locale() {
        instance = this;
    }

    public String get(String configKey) {
        return Updates.instance.messages.getString("prefix") + Updates.instance.messages.getString(configKey);
    }

    public String getNoPrefix(String configKey) {
        return Updates.instance.messages.getString(configKey);
    }

    public String getPrefix() {
        return Updates.instance.messages.getString("prefix");
    }
}
