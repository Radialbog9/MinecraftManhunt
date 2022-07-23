/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.ldisscenarios;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.scenario.*;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario(ScenarioType.RUNNER_RANDOM_MOB_DISGUISE)
@ScenarioRunnable
@SuppressWarnings({"unused"})
public class RandRunnerMobDisgScenario extends BukkitRunnable {
    @Override
    public void run() {
        if(
                ManhuntVars.isLibsDisguisesEnabled() &&
                ManhuntVars.isGameStarted() &&
                ManhuntVars.getScenarioList().contains(ScenarioType.RUNNER_RANDOM_MOB_DISGUISE)
        ) {
            for(Player p : ManhuntVars.getRunners()) {
                boolean isMobYet = false;
                DisguiseType disguisetype = null;
                while (!isMobYet) {
                    int dis = Utils.getRandomInt(0, DisguiseType.values().length - 1);
                    disguisetype = DisguiseType.values()[dis];
                    isMobYet = disguisetype.isMob();
                }
                Disguise disguise = new MobDisguise(disguisetype);
                DisguiseAPI.disguiseEntity(p, disguise);
                p.sendMessage("You are now a " + disguisetype.toReadable());
            }
        } else {
            this.cancel();
        }
    }
}
