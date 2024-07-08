/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.ldisscenarios;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.*;
import uk.radialbog9.spigot.manhunt.scenario.utils.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.utils.DependencySupport;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario("RUNNER_RANDOM_HUNTER_DISGUISE")
@ScenarioRunnable
@SuppressWarnings({"unused"})
public class RandRunnerHunterDisgScenario extends BukkitRunnable {
    @Override
    public void run() {
        if(DependencySupport.isLibsDisguisesEnabled() &&
                ScenarioUtils.isScenarioEnabled(this)
        ) {
            for(Player p : GameManager.getGame().getRunners()) {
                Player randplayer = (Player) GameManager.getGame().getHunters().toArray()[Utils.getRandomInt(0, GameManager.getGame().getHunters().size() - 1)];
                Disguise disguise = new PlayerDisguise(randplayer);
                DisguiseAPI.disguiseEntity(p, disguise);
                p.sendMessage("You are now disguised as " + randplayer.getDisplayName());
            }
        } else {
            this.cancel();
        }
    }
}
