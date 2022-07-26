/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.playerdata;

import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.radialbog9.spigot.manhunt.Manhunt;

import java.io.File;
import java.io.IOException;

@SuppressWarnings({"ResultOfMethodCallIgnored", "unused"})
public class PlayerData {
    @Getter
    private final OfflinePlayer player;
    private FileConfiguration config;
    private final File configFile;
    @Getter
    private int hunterWins = 0;
    @Getter
    private int hunterLosses = 0;
    @Getter
    private int runnerWins = 0;
    @Getter
    private int runnerDeaths = 0;

    public PlayerData(OfflinePlayer p) {
        //set the player
        player = p;

        //create the data file on the system
        File playerDataFolder = new File(Manhunt.getInstance().getDataFolder(), "playerdata");
        if(!playerDataFolder.exists()) playerDataFolder.mkdirs();
        configFile = new File(playerDataFolder, p.getUniqueId() + ".yml");
        boolean configExists = configFile.exists();
        try {
            if(!configExists) configFile.createNewFile();
            config = new YamlConfiguration();
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        //save defaults to config or load from config
        if(!configExists) save();
        else load();
    }

    public void addHunterWin() { hunterWins ++; }

    public void addHunterLoss() { hunterLosses ++; }

    public void addRunnerWin() { runnerWins ++; }

    public void addRunnerDeath() { runnerDeaths++; }

    public void save() {
        config.set("hunter-wins", hunterWins);
        config.set("hunter-losses", hunterLosses);
        config.set("runner-wins", runnerWins);
        config.set("runner-deaths", runnerDeaths);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        hunterWins = config.getInt("hunter-wins", 0);
        hunterLosses = config.getInt("hunter-losses", 0);
        runnerWins = config.getInt("runner-wins", 0);
        runnerDeaths = config.getInt("runner-deaths", 0);
    }
}
