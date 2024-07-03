package uk.radialbog9.spigot.manhunt.scenario.utils.template;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.utils.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.List;

public abstract class PotionScenarioTemplate extends BukkitRunnable {
    public abstract List<Player> getPlayerSet();
    public abstract List<PotionEffectType> getPotionEffectTypes();

    @Override
    public void run() {
        if (ScenarioUtils.isScenarioEnabled(this)) {
            for (Player p : getPlayerSet()) {
                // Pick a random potion effect
                int rand = Utils.getRandomInt(0, getPotionEffectTypes().size() - 1);
                PotionEffectType choice = getPotionEffectTypes().get(rand);

                // Create that potion effect
                PotionEffect pe = new PotionEffect(choice, 10, 1);

                // Apply the potion effect to the player
                p.addPotionEffect(pe);
            }
        }
    }
}
