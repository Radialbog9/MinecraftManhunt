/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.playerdata;

import lombok.Getter;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

/**
 * Utility class for managing data files
 */
public class DataUtils {
    @Getter
    private static final HashMap<OfflinePlayer, PlayerData> playerConfigCache = new HashMap<>();

    /**
     * Gets a player's data object
     * @param p Player
     * @return The player's config
     */
    public static PlayerData getPlayerData(OfflinePlayer p) {
        PlayerData pd = playerConfigCache.get(p);
        if(pd == null) {
            pd = new PlayerData(p);
            playerConfigCache.put(p, pd);
        }
        return pd;
    }

    /**
     * Removes data of players who aren't online from the cache
     */
    public static void purgePlayerCache() {
        for(OfflinePlayer p : playerConfigCache.keySet()) {
            if(!p.isOnline()) {
                getPlayerData(p).save();
                playerConfigCache.remove(p);
            }
        }
    }
}
