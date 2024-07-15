/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.utils;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;

public class LibsDisguisesUtils {
    public static void disguisePlayerRandomMob(Player p) {
        boolean isMobYet = false;
        DisguiseType disguisetype = null;
        while (!isMobYet) {
            int dis = Utils.getRandomInt(0, DisguiseType.values().length - 1);
            disguisetype = DisguiseType.values()[dis];
            isMobYet = disguisetype.isMob();
        }
        Disguise disguise = new MobDisguise(disguisetype);
        DisguiseAPI.disguiseEntity(p, disguise);
        p.sendMessage(LanguageTranslator.translate(
                "disguise.disguised-mob",
                disguisetype.toReadable()
        ));
    }
}
