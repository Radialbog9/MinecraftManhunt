/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;

@Scenario("POISONOUS_AIR")
@ScenarioRunnable
@SuppressWarnings("unused")
public class PoisonousAirScenario extends BukkitRunnable implements ScenarioConfigurable {

    @Override
    public void run() {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            for(Player player : GameManager.getGame().getPlayers()) {
                // Check if the player is swimming or gliding
                // If they are, we need to check the block they are in
                // Otherwise, we need to check the block above them (their head)
                Location checkLocation = player.getLocation().clone().add(0, 1, 0);
                if(player.isSwimming() || player.isGliding())
                    checkLocation = player.getLocation();

                // Check if the block is solid, water, or lava
                Material blockType = checkLocation.getBlock().getType();

                if(!blockType.isSolid() &&
                        blockType != Material.WATER &&
                        blockType != Material.LAVA &&
                        blockType != Material.POWDER_SNOW
                ) {
                    // Apply poison effect if the player is not in a solid block, water, or lava
                    // i.e. they are in the air/non-solid block that would be affected by the poisonous air
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 25, 1));
                }
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
