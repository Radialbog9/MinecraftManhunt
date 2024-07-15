/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioListener;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;
import lombok.Getter;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario("SNEAK_DISABLES")
@ScenarioRunnable
@ScenarioListener
public class SneakDisablesScenario extends BukkitRunnable implements Listener, ScenarioConfigurable {
    private boolean disableSneak = false;

    class SneakReEnable extends BukkitRunnable {
        @Override
        public void run() {
            disableSneak = false;
            Utils.broadcastServerMessage(LanguageTranslator.translate("scenario.SNEAK_DISABLES.disabled"));
        }
    }

    @Override
    public void run() {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            // Disable sneak
            disableSneak = true;

            // Re-enable sneak after duration
            new SneakReEnable()
                    .runTaskLater(
                            Manhunt.getInstance(),
                            getConfig().getDuration() * 20L // 20 tps
                    );

            Utils.broadcastServerMessage(LanguageTranslator.translate("scenario.SNEAK_DISABLES.enabled"));
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this) &&
                GameManager.getGame().getPlayers().contains(e.getPlayer()) &&
                e.isSneaking() &&
                disableSneak
        ) {
            // Player is trying to sneak and shouldn't be able to, cancel it
            e.setCancelled(true);
        }
    }

    private static class Config extends ScenarioConfiguration implements RunnableRequiredConfig {
        @Getter
        private int time = 150;

        @Getter
        private int duration = 5;
    }

    @Getter
    private Config config = new Config();


    @Override
    public void setConfig(ScenarioConfiguration config) {
        this.config = (Config) config;
    }
}
