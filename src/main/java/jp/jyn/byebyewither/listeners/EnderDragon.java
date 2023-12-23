package jp.jyn.byebyewither.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
public class EnderDragon implements Listener {

    // allowed worlds
    private final Set<String> ignoredWorlds;

    public EnderDragon(Set<String> ignoredWorlds) {
        this.ignoredWorlds = ignoredWorlds;
    }

    @EventHandler
    public void BlockPlace(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (e.getMaterial() != Material.END_CRYSTAL) {
            return;
        }
        Optional<Material> clickedBlockMaterial = Optional.ofNullable(e.getClickedBlock()).map(Block::getType);
        if (clickedBlockMaterial.filter(material -> material == Material.BEDROCK).isEmpty()) {
            return;
        }

        Player player = e.getPlayer();
        World world = player.getWorld();
        if (world.getEnvironment() != Environment.THE_END) {
            return;
        }
        if (ignoredWorlds.contains(world.getName())) {
            return;
        }
        if (player.hasPermission("byebyewither.allowdragon")) {
            return;
        }

        e.setCancelled(true);
    }
}
