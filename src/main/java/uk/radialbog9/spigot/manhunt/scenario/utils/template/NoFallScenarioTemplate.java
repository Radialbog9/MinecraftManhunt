package uk.radialbog9.spigot.manhunt.scenario.utils.template;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import uk.radialbog9.spigot.manhunt.scenario.utils.ScenarioUtils;

import java.util.List;

public abstract class NoFallScenarioTemplate implements Listener {
    public abstract List<Player> getPlayerSet();

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
