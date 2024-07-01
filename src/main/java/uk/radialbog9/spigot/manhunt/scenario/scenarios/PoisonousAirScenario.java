/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 *  You are allowed to use this code under the GPL3 license, which allows
 *  commercial use, distribution, modification, and licensed works,
 *  providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;

@Scenario("POISONOUS_AIR")
@ScenarioRunnable
public class PoisonousAirScenario extends BukkitRunnable {

    @Override
    public void run() {
        if(GameManager.getGame().isGameStarted() && GameManager.getGame().getActiveScenarios().contains("POISONOUS_AIR")) {
            for(Player player : GameManager.getGame().getPlayers()) {
                if(!player.getLocation().getBlock().getType().isSolid()) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 25, 1));
                }
            }
        }
    }
}
