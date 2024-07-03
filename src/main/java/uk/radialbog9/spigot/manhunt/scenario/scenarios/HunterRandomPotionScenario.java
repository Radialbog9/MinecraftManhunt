package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.utils.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario("HUNTER_RANDOM_POTION_EFFECT")
@ScenarioRunnable
@SuppressWarnings("unused")
public class HunterRandomPotionScenario extends BukkitRunnable {
    @Override
    public void run() {
        if (ScenarioUtils.isScenarioEnabled(this)) {
            for (Player p : GameManager.getGame().getHunters()) {
                // Pick a random potion effect
                PotionEffectType[] vals = PotionEffectType.values();
                PotionEffectType choice = vals[Utils.getRandomInt(0, vals.length - 1)];
                PotionEffect pe = new PotionEffect(choice, 10, 1);
                p.addPotionEffect(pe);
            }
        }
    }
}
