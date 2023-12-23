package jp.jyn.byebyewither.listeners;

import org.bukkit.World;
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
    private final Set<String> worlds;

    public Wither(Set<String> worlds) {
        this.worlds = worlds;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getEntityType() != EntityType.WITHER) {
            return;
        }
        Optional<String> worldName = Optional.ofNullable(e.getLocation().getWorld()).map(WorldInfo::getName);
        if (worldName.isEmpty() || !worlds.contains(worldName.get())) {
            return;
        }

        e.setCancelled(true);
    }
}
