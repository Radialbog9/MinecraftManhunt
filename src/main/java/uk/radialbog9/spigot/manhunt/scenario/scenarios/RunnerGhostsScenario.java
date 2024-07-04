package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.utils.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.utils.CompassTrackable;

import java.util.List;

@Scenario("RUNNER_GHOSTS")
@ScenarioRunnable
@SuppressWarnings("unused")
public class RunnerGhostsScenario extends BukkitRunnable {

    private static class RevealTask extends BukkitRunnable {
        private final Player p;
        private final List<Player> hunters;
        RevealTask(Player p, List<Player> hunters) {
            this.p = p;
            this.hunters = hunters;
        }
        @Override
        public void run() {
            // Reveal the runner's location to the hunters
            CompassTrackable.getHiddenPlayers().remove(p);
            hunters.forEach(hunter ->
                    hunter.showPlayer(Manhunt.getInstance(), p)
            );
        }
    }

    @Override
    public void run() {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            List<Player> hunters = GameManager.getGame().getHunters();

            int durationTicks = 20 * Manhunt.getInstance().getConfig().getInt("scenarios.RUNNER_GHOSTS.duration");

            for (Player p : GameManager.getGame().getRunners()) {
                // Make the runner invisible
                PotionEffect invis = new PotionEffect(
                        PotionEffectType.INVISIBILITY,
                        durationTicks,
                        1,
                        false,
                        false
                );
                p.addPotionEffect(invis);

                // Hide the runner from the hunters
                hunters.forEach(hunter -> hunter.hidePlayer(Manhunt.getInstance(), p));

                // Hide the runner from the hunters' compass
                CompassTrackable.getHiddenPlayers().add(p);

                // Schedule the runner to be revealed after the duration
                new RevealTask(p, hunters)
                        .runTaskLater(Manhunt.getInstance(), durationTicks);
            }
        }
    }
}
