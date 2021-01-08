package uk.radialbog9.spigot.manhunt.playerconfig;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {
    private Player player;
    private FileConfiguration config;
    private File configFile;
    private int hunterWins = 0;
    private int hunterLosses = 0;
    private int runnerWins = 0;
    private int runnerLosses = 0;

    public PlayerConfig(Player p) {
        player = p;
        File playerDataFolder = new File(Manhunt.getInstance().getDataFolder(), "playerdata");
        if(!playerDataFolder.exists()) playerDataFolder.mkdirs();
        configFile = new File(playerDataFolder, p.getUniqueId().toString() + ".yml");

    }

    public Player getPlayer() { return player; }

    public int getHunterWins() { return hunterWins; }

    public void addHunterWin() { hunterWins ++; }

    public int getHunterLosses() { return hunterLosses; }

    public void addHunterLoss() { hunterLosses ++; }

    public int getRunnerWins() { return runnerWins; }

    public int getRunnerLosses() { return runnerLosses; }

    public FileConfiguration getConfig() { return config; }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
