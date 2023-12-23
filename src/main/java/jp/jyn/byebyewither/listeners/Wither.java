package jp.jyn.byebyewither.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.generator.WorldInfo;

import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
public class Wither implements Listener {

    // allowed worlds
    private final Set<String> ignoredWorlds;

    public Wither(Set<String> ignoredWorlds) {
        this.ignoredWorlds = ignoredWorlds;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getEntityType() != EntityType.WITHER) {
            return;
        }
        Optional<String> worldName = Optional.ofNullable(e.getLocation().getWorld()).map(WorldInfo::getName);
        if (worldName.isEmpty() || !ignoredWorlds.contains(worldName.get())) {
            return;
        }

        e.setCancelled(true);
    }
}
