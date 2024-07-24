/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import uk.radialbog9.spigot.manhunt.Manhunt;

import java.io.File;
import java.util.*;

public class Leaderboard {
    private enum LeaderboardTypes {
        WINS_TOTAL, WINS_RUNNER, WINS_HUNTER, DEATHS_RUNNER, LOSSES
    }
    private HashMap<OfflinePlayer, Integer> reverseSortByValue(HashMap<OfflinePlayer, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<OfflinePlayer, Integer> > list =
                new ArrayList<>(hm.entrySet());

        // Sort the list
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // put data from sorted list to hashmap
        HashMap<OfflinePlayer, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<OfflinePlayer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public HashMap<OfflinePlayer, Integer> getTotalWinsLeaderboard() {
        HashMap<OfflinePlayer, Integer> lb = new HashMap<>();
        File playerDataFolder = new File(Manhunt.getInstance().getDataFolder(), "playerdata");
        if(!playerDataFolder.exists()) playerDataFolder.mkdirs();
        for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
            if(new File(playerDataFolder, p.getUniqueId() + ".yml").exists()) {
                PlayerData pData = DataUtils.getPlayerData(p);
                lb.put(p, pData.getHunterWins() + pData.getRunnerWins());
            }
        }
        return reverseSortByValue(lb);
    }
}
