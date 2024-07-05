package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;

import java.util.ArrayList;
import java.util.List;

@Scenario("SWAP_ROLES")
@ScenarioRunnable
public class SwapRolesScenario extends BukkitRunnable {
    @Override
    public void run() {
        // Create a copy of the hunters and runners list
        List<Player> hunters = new ArrayList<>(GameManager.getGame().getHunters());
        List<Player> runners = new ArrayList<>(GameManager.getGame().getRunners());

        hunters.forEach(player -> {
            GameManager.getGame().getHunters().remove(player);
            GameManager.getGame().getRunners().add(player);
        });

        runners.forEach(player -> {
            GameManager.getGame().getRunners().remove(player);
            GameManager.getGame().getHunters().add(player);
        });
    }
}
