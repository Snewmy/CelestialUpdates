package me.sam.updates;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinListener implements Listener {
    Updates main;

    public JoinListener(Updates main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (!Updates.updatesTrackers.containsKey(p.getUniqueId())) {
            UpdatesTracker updatesTracker1 = new UpdatesTracker();
            Updates.updatesTrackers.put(p.getUniqueId(), updatesTracker1);
        }
        final UpdatesTracker updatesTracker = Updates.updatesTrackers.get(p.getUniqueId());
        this.main.getServer().getScheduler().runTaskLater((Plugin)this.main, new Runnable() {
            public void run() {
                JoinListener.this.main.sendPlayerUpdatesJoinMessage(p, updatesTracker);
            }
        }, 60L);
    }
}
