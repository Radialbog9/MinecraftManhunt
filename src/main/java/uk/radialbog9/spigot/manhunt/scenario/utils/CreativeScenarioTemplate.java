package uk.radialbog9.spigot.manhunt.scenario.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;

import java.util.List;

public class CreativeScenarioTemplate extends BukkitRunnable {
    public List<Player> getPlayerSet() {
        return GameManager.getGame().getPlayers();
    }

    @Override
    public void run() {
        String scenarioName = this.getClass().getAnnotation(Scenario.class).value();
        if(ScenarioUtils.isScenarioEnabled(this)) {
            for(Player p : getPlayerSet()) {
                p.setGameMode(GameMode.CREATIVE);
                p.setAllowFlight(Manhunt.getInstance().getConfig().getBoolean("scenarios." + scenarioName + ".allow-fly"));
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    for(Player p : GameManager.getGame().getHunters()) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.setAllowFlight(false);
                        p.setFlying(false);
                    }
                }
            }.runTaskLater(
                    Manhunt.getInstance(),
                    Manhunt.getInstance().getConfig().getInt("scenarios." + scenarioName + ".duration") * 20L
            );
        }
    }
}
