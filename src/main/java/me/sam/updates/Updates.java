package me.sam.updates;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


public class Updates extends JavaPlugin {
    LinkedList<UpdateText> updateList = new LinkedList<>();

    public File dataFile = new File(getDataFolder(), "data.yml");

    public File playerdataFile = new File(getDataFolder(), "playerdata.yml");

    public FileConfiguration data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dataFile);

    public FileConfiguration playerdata = (FileConfiguration)YamlConfiguration.loadConfiguration(this.playerdataFile);

    public static HashMap<UUID, UpdatesTracker> updatesTrackers = new HashMap<>();
    public static Updates instance;
    File messagesFile;
    FileConfiguration messages;

    public void onEnable() {
        instance = this;
        saveResource("messages.yml", false);
        messagesFile = new File(getDataFolder(), "messages.yml");
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        getCommand("addupdate").setExecutor(new AddCommand(this, true));
        getCommand("addupdate").setTabCompleter(new UpdatesTabCompleter(this));
        getCommand("updates").setExecutor(new UpdatesCommand(this));
        getCommand("addupdatesilent").setExecutor(new AddCommand(this, false));
        getCommand("addupdatesilent").setTabCompleter(new UpdatesTabCompleter(this));
        getCommand("removeupdate").setExecutor(new RemoveCommand(this));
        getCommand("removeupdate").setTabCompleter(new RemoveTabCompleter(this));
        getCommand("insertupdate").setExecutor(new InsertCommand(this));
        getCommand("insertupdate").setTabCompleter(new InsertTabCompleter(this));
        getServer().getPluginManager().registerEvents(new JoinListener(this), (Plugin)this);
        loadData();
        autoSaveTask();
        new Locale();
    }

    public void onDisable() {
        super.onDisable();
        saveData();
    }

    public void addToList(UpdateText updateText) {
        if (this.updateList.size() != 0)
            this.updateList.remove(0);
        this.updateList.add(updateText);
    }

    public String getPage1() {
        StringBuilder sb = new StringBuilder();
        sb.append(Locale.instance.getNoPrefix("colorkey") + "<newline>");
        for (int i = 0; i <= 6; i++) {
            if (i < this.updateList.size()) {
                UpdateText updateText = this.updateList.get(i);
                if (updateText.getUpdateType() == UpdateType.UPDATE) {
                    sb.append(Locale.instance.getNoPrefix("updatetext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                } else if (updateText.getUpdateType() == UpdateType.BUGFIX) {
                    sb.append(Locale.instance.getNoPrefix("bugfixtext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                }
            } else {
                sb.append("<newline>");
            }
        }
        return sb.toString().trim();
    }

    public String getPage2() {
        StringBuilder sb = new StringBuilder();
        sb.append(Locale.instance.getNoPrefix("colorkey") + "<newline>");
        for (int i = 7; i <= 13; i++) {
            if (i < this.updateList.size()) {
                UpdateText updateText = this.updateList.get(i);
                if (updateText.getUpdateType() == UpdateType.UPDATE) {
                    sb.append(Locale.instance.getNoPrefix("updatetext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                } else if (updateText.getUpdateType() == UpdateType.BUGFIX) {
                    sb.append(Locale.instance.getNoPrefix("bugfixtext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                }
            } else {
                sb.append("<newline>");
            }
        }
        return sb.toString().trim();
    }

    public String getPage3() {
        StringBuilder sb = new StringBuilder();
        sb.append(Locale.instance.getNoPrefix("colorkey") + "<newline>");
        for (int i = 14; i <= 20; i++) {
            if (i < this.updateList.size()) {
                UpdateText updateText = this.updateList.get(i);
                if (updateText.getUpdateType() == UpdateType.UPDATE) {
                    sb.append(Locale.instance.getNoPrefix("updatetext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                } else if (updateText.getUpdateType() == UpdateType.BUGFIX) {
                    sb.append(Locale.instance.getNoPrefix("bugfixtext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                }
            } else {
                sb.append("<newline>");
            }
        }
        return sb.toString().trim();
    }

    public String getPage4() {
        StringBuilder sb = new StringBuilder();
        sb.append(Locale.instance.getNoPrefix("colorkey") + "<newline>");
        for (int i = 21; i <= 27; i++) {
            if (i < this.updateList.size()) {
                UpdateText updateText = this.updateList.get(i);
                if (updateText.getUpdateType() == UpdateType.UPDATE) {
                    sb.append(Locale.instance.getNoPrefix("updatetext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                } else if (updateText.getUpdateType() == UpdateType.BUGFIX) {
                    sb.append(Locale.instance.getNoPrefix("bugfixtext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                }
            } else {
                sb.append("<newline>");
            }
        }
        return sb.toString().trim();
    }

    public String getPage5() {
        StringBuilder sb = new StringBuilder();
        sb.append(Locale.instance.getNoPrefix("colorkey") + "<newline>");
        for (int i = 28; i <= 34; i++) {
            if (i < this.updateList.size()) {
                UpdateText updateText = this.updateList.get(i);
                if (updateText.getUpdateType() == UpdateType.UPDATE) {
                    sb.append(Locale.instance.getNoPrefix("updatetext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                } else if (updateText.getUpdateType() == UpdateType.BUGFIX) {
                    sb.append(Locale.instance.getNoPrefix("bugfixtext").replace("%time%", updateText.getDatetime()).replace("%update%", updateText.getText())).append("<newline>");
                }
            } else {
                sb.append("<newline>");
            }
        }
        return sb.toString().trim();
    }

    public void sendPlayerUpdatesMessage(final Player p, final UpdatesTracker updatesTracker) {
        MiniMessage mm = MiniMessage.miniMessage();
        String msg = Locale.instance.getNoPrefix("updatesprefix")
                + "<hover:show_text:'" + getPage1() + "'>" + Locale.instance.getNoPrefix("page1") + "</hover>  "
                + "<hover:show_text:'" + getPage2() + "'>" + Locale.instance.getNoPrefix("page2") + "</hover>  "
                + "<hover:show_text:'" + getPage3() + "'>" + Locale.instance.getNoPrefix("page3") + "</hover>  "
                + "<hover:show_text:'" + getPage4() + "'>" + Locale.instance.getNoPrefix("page4") + "</hover>  "
                + "<hover:show_text:'" + getPage5() + "'>" + Locale.instance.getNoPrefix("page5") + "</hover>  ";
        Component parsed = mm.deserialize(msg);
        p.sendMessage(parsed);
        p.sendMessage(Utils.chat(Locale.instance.get("updateshovermsg")));
        if (updatesTracker.getMissingUpdates() > 0)
            getServer().getScheduler().runTaskLater((Plugin)this, new Runnable() {
                public void run() {
                    p.sendMessage(Utils.chat(Locale.instance.get("updatesmissed").replace("%amount%", updatesTracker.getMissingUpdates() + "")));
                    playUpdateSound(p);
                    updatesTracker.setMissingUpdates(0);
                }
            }, 20L);
    }

    public void sendPlayerUpdatesJoinMessage(final Player p, final UpdatesTracker updatesTracker) {
        if (updatesTracker.getMissingUpdates() > 0) {
            MiniMessage mm = MiniMessage.miniMessage();
            String msg = Locale.instance.getNoPrefix("join-message")
                    + "<hover:show_text:'" + getPage1() + "'>" + Locale.instance.getNoPrefix("join-message-hover") + "</hover>  ";
            Component parsed = mm.deserialize(msg);
            p.sendMessage(parsed);
            playUpdateSound(p);
            updatesTracker.setMissingUpdates(0);
        }
    }

    public void playUpdateSound(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 3.0F);
    }
    public void playUpdateSoundToAll() {
        for (Player p : Bukkit.getOnlinePlayers())
            playUpdateSound(p);
    }

    public void incrementMissedUpdatesToAll() {
        for (Map.Entry<UUID, UpdatesTracker> entry : updatesTrackers.entrySet())
            ((UpdatesTracker)entry.getValue()).setMissingUpdates(((UpdatesTracker)entry.getValue()).getMissingUpdates() + 1);
    }

    public void playZeldaGetItemSound(final Player p) {
        BukkitScheduler scheduler = getServer().getScheduler();
        p.playNote(p.getLocation(), Instrument.BIT, Note.sharp(0, Note.Tone.F));
        p.playNote(p.getLocation(), Instrument.BIT, Note.sharp(1, Note.Tone.C));
        p.playNote(p.getLocation(), Instrument.BIT, Note.sharp(1, Note.Tone.F));
        scheduler.runTaskLater((Plugin)this, new Runnable() {
            public void run() {
                p.playNote(p.getLocation(), Instrument.BIT, Note.natural(0, Note.Tone.G));
                p.playNote(p.getLocation(), Instrument.BIT, Note.natural(1, Note.Tone.D));
                p.playNote(p.getLocation(), Instrument.BIT, Note.natural(1, Note.Tone.B));
            }
        }, 3L);
        scheduler.runTaskLater((Plugin)this, new Runnable() {
            public void run() {
                p.playNote(p.getLocation(), Instrument.BIT, Note.sharp(0, Note.Tone.G));
                p.playNote(p.getLocation(), Instrument.BIT, Note.sharp(1, Note.Tone.D));
                p.playNote(p.getLocation(), Instrument.BIT, Note.natural(1, Note.Tone.C));
            }
        }, 6L);
        scheduler.runTaskLater((Plugin)this, new Runnable() {
            public void run() {
                p.playNote(p.getLocation(), Instrument.BIT, Note.natural(0, Note.Tone.A));
                p.playNote(p.getLocation(), Instrument.BIT, Note.natural(1, Note.Tone.E));
                p.playNote(p.getLocation(), Instrument.BIT, Note.sharp(1, Note.Tone.C));
            }
        }, 9L);
    }

    public void playZeldaGetItemSoundToAll() {
        for (Player p : Bukkit.getOnlinePlayers())
            playZeldaGetItemSound(p);
    }

    public void autoSaveTask() {
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskTimer((Plugin)this, new Runnable() {
            public void run() {
                Updates.this.saveData();
            }
        },  24000L, 24000L);
    }

    public void loadData() {
        if (!this.data.isConfigurationSection("updates")) {
            this.data.createSection("updates");
            try {
                this.data.save(this.dataFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (!this.playerdata.isConfigurationSection("players")) {
            this.playerdata.createSection("players");
            try {
                this.playerdata.save(this.playerdataFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        ConfigurationSection updates = this.data.getConfigurationSection("updates");
        for (String text : updates.getKeys(false)) {
            try {
                String date = updates.getString(text + ".date");
                UpdateType updateType = UpdateType.valueOf(updates.getString(text + ".type"));
                String originalText = text.replace('_', '.').replace("\\\"", "\"");
                UpdateText updateText = new UpdateText(updateType, originalText);
                updateText.setDatetime(date);
                this.updateList.add(updateText);
            } catch (NullPointerException e1) {
                String date = updates.getString(text + ".date");
                Bukkit.getLogger().severe("Issue loading data;");
                Bukkit.getLogger().severe("Text = " + text);
                Bukkit.getLogger().severe("Date = " + date);
                Bukkit.getLogger().severe("Type = " + updates.getString(text + ".type"));
            }
        }
        ConfigurationSection playerData = this.playerdata.getConfigurationSection("players");
        for (String uuidString : playerData.getKeys(false)) {
            UUID uuid = UUID.fromString(uuidString);
            UpdatesTracker updatesTracker = new UpdatesTracker();
            int missedUpdates = playerData.getInt(uuidString + ".missedUpdates");
            updatesTracker.setMissingUpdates(missedUpdates);
            updatesTrackers.put(uuid, updatesTracker);
        }
    }

    public void saveData() {
        this.data.set("updates", null);
        this.data.createSection("updates");
        ConfigurationSection updates = this.data.getConfigurationSection("updates");

        for (UpdateText updateText : this.updateList) {
            try {
                String text = updateText.getText()
                        .replace('.', '_')
                        .replace('\n', ' ')
                        .replace('\r', ' ')
                        .replace("\"", "\\\"");
                String date = updateText.getDatetime();
                String updateType = updateText.getUpdateType().toString();
                updates.set(text + ".date", date);
                updates.set(text + ".type", updateType);
            } catch (NullPointerException e1) {
                Bukkit.getLogger().severe("Issue Saving data;");
                Bukkit.getLogger().severe("Text = " + updateText.getText());
                Bukkit.getLogger().severe("Date = " + updateText.getDatetime());
                Bukkit.getLogger().severe("Type = " + updateText.getUpdateType().toString());
            }
        }

        try {
            this.data.save(this.dataFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ConfigurationSection playerData = this.playerdata.getConfigurationSection("players");
        for (Map.Entry<UUID, UpdatesTracker> entry : updatesTrackers.entrySet()) {
            UUID uuid = entry.getKey();
            UpdatesTracker updatesTracker = entry.getValue();
            playerData.set(uuid.toString() + ".missedUpdates", updatesTracker.getMissingUpdates());
        }

        try {
            this.playerdata.save(this.playerdataFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}


