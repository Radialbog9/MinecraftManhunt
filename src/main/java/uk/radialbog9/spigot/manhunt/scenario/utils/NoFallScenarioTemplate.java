package uk.radialbog9.spigot.manhunt.scenario.utils;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import uk.radialbog9.spigot.manhunt.game.GameManager;

import java.util.List;

public class NoFallScenarioTemplate implements Listener {
    public List<Player> getPlayerSet() {
        return GameManager.getGame().getPlayers();
    }

    @EventHandler
    public void noFallScenario(EntityDamageEvent e) {
        if(
                ScenarioUtils.isScenarioEnabled(this) &&
                e.getEntityType() == EntityType.PLAYER
        ) {
            Player p = (Player) e.getEntity();
            if(getPlayerSet().contains(p) && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }
}
