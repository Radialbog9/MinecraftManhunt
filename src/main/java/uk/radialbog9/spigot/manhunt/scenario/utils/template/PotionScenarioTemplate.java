package uk.radialbog9.spigot.manhunt.scenario.utils.template;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.utils.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.List;

public abstract class PotionScenarioTemplate extends BukkitRunnable {
    public abstract List<Player> getPlayerSet();
    public abstract List<PotionEffectType> getPotionEffectTypes();

    @Override
    public void run() {
        String scenarioName = this.getClass().getAnnotation(Scenario.class).value();
        if (GameManager.getGame().isGameStarted() &&
                ScenarioUtils.isScenarioEnabled(this)) {
            for (Player p : getPlayerSet()) {
                // Pick a random potion effect
                PotionEffectType choice;
                if(getPotionEffectTypes().size() > 1) {
                    int rand = Utils.getRandomInt(0, getPotionEffectTypes().size() - 1);
                    choice = getPotionEffectTypes().get(rand);
                } else {
                    choice = getPotionEffectTypes().get(0);
                }

                // Create that potion effect
                int durationSeconds = Manhunt.getInstance().getConfig().getInt("scenarios." + scenarioName + ".duration");
                int amplifier = Manhunt.getInstance().getConfig().getInt("scenarios." + scenarioName + ".amplifier");

                PotionEffect pe = new PotionEffect(choice, (durationSeconds * 20), amplifier);

                // Apply the potion effect to the player
                p.addPotionEffect(pe);
            }
        }
    }
}
