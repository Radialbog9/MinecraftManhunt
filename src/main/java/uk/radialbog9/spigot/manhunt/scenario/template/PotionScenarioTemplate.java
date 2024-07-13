/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.template;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.List;

public abstract class PotionScenarioTemplate extends BukkitRunnable implements ScenarioConfigurable {
    public abstract List<Player> getPlayerSet();
    public abstract List<PotionEffectType> getPotionEffectTypes();

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
                int durationSeconds = getConfig().getDuration();
                int amplifier = getConfig().getAmplifier();

                PotionEffect pe = new PotionEffect(choice, (durationSeconds * 20), amplifier);

                // Apply the potion effect to the player
                p.addPotionEffect(pe);
            }
        }
    }

    private static class Config extends ScenarioConfiguration implements RunnableRequiredConfig {
        @Getter
        private int time = 260;

        @Getter
        private int duration = 5;

        @Getter
        private int amplifier = 1;
    }

    @Getter
    private Config config = new Config();


    @Override
    public void setConfig(ScenarioConfiguration config) {
        this.config = (Config) config;
    }
}
