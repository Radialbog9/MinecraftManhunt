/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioType;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario(ScenarioType.RUNNER_RANDOM_HUNTER_DISGUISE)
public class RandRunnerHunterDisgScenario extends BukkitRunnable {
    @Override
    public void run() {
        if(ManhuntVars.isLibsDisguisesEnabled() && ManhuntVars.isGameStarted()) {
            for(Player p : ManhuntVars.getRunners()) {
                Player randplayer = (Player) ManhuntVars.getHunters().toArray()[Utils.getRandomInt(0, ManhuntVars.getHunters().size() - 1)];
                Disguise disguise = new PlayerDisguise(randplayer);
                DisguiseAPI.disguiseEntity(p, disguise);
                p.sendMessage("You are now disguised as " + randplayer.getDisplayName());
            }
        } else {
            this.cancel();
        }
    }
}
