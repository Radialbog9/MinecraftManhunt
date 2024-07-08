/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.utils.CompassTrackable;

import java.util.ArrayList;
import java.util.List;

@Scenario("RUNNER_GHOSTS")
@ScenarioRunnable
@SuppressWarnings("unused")
public class RunnerGhostsScenario extends BukkitRunnable implements Listener {
    private final List<Player> invisibleRunners = new ArrayList<>();

    private class RevealTask extends BukkitRunnable {
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
            invisibleRunners.remove(p);
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

                // Add the runner to the list of invisible runners
                invisibleRunners.add(p);

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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            Player p = e.getPlayer();
            if(invisibleRunners.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockBreakEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            Player p = e.getPlayer();
            if(invisibleRunners.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            if(e.getDamager() instanceof Player p) {
                if(invisibleRunners.contains(p)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            if(e.getWhoClicked() instanceof Player p) {
                if(invisibleRunners.contains(p)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInteract(EntityInteractEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            if(e.getEntity() instanceof Player p) {
                if(invisibleRunners.contains(p)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
