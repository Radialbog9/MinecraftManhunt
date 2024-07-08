/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.template;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.types.ScenarioTypeRunnable;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.List;
import java.util.Map;

public abstract class PotionScenarioTemplate extends ScenarioTypeRunnable {
    public abstract List<Player> getPlayerSet();
    public abstract List<PotionEffectType> getPotionEffectTypes();

    @Override
    public Map<String, Object> getDefaultConfig() {
        return Map.of(
                "time", 300,
                "duration", 5,
                "amplifier", 1
        );
    }

    @Override
    public void run() {
        String scenarioName = this.getClass().getAnnotation(Scenario.class).value();
        if (ScenarioUtils.isScenarioEnabled(this)) {
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
                int durationSeconds = (int) ScenarioUtils.getConfigValue(this, "duration");
                int amplifier = (int) ScenarioUtils.getConfigValue(this, "amplifier");

                PotionEffect pe = new PotionEffect(choice, (durationSeconds * 20), amplifier);

                // Apply the potion effect to the player
                p.addPotionEffect(pe);
            }
        }
    }
}
