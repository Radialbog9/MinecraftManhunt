/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;
import uk.radialbog9.spigot.manhunt.scenario.template.SwapScenarioTemplate;

import java.util.List;

import static uk.radialbog9.spigot.manhunt.utils.Utils.swapLocation;

@Scenario("LOCATION_SWAP_ALL")
@ScenarioRunnable
public class LocSwapAllScenario extends SwapScenarioTemplate implements ScenarioConfigurable {
    private static class Config extends ScenarioConfiguration implements RunnableRequiredConfig {
        @Getter
        private int time = 300;
    }

    @Getter
    private Config config = new Config();

    @Override
    public void setConfig(ScenarioConfiguration config) {
        this.config = (Config) config;
    }

    @Override
    public void swap(Player player1, Player player2) {
        swapLocation(player1, player2);
    }

    @Override
    public List<Player> getPlayerSet() {
        return GameManager.getGame().getPlayers();
    }
}
