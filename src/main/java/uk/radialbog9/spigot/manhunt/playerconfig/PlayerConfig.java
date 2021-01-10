package uk.radialbog9.spigot.manhunt.playerconfig;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class PlayerConfig {
    private Player player;
    private FileConfiguration config;
    private File configFile;
    private int hunterWins = 0;
    private int hunterLosses = 0;
    private int runnerWins = 0;
    private int runnerLosses = 0;

    public PlayerConfig(Player p) {
        //set the player
        player = p;

        //create the data file on the system
        File playerDataFolder = new File(Manhunt.getInstance().getDataFolder(), "playerdata");
        if(!playerDataFolder.exists()) playerDataFolder.mkdirs();
        configFile = new File(playerDataFolder, p.getUniqueId().toString() + ".yml");
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

    public Player getPlayer() { return player; }

    public int getHunterWins() { return hunterWins; }

    public void addHunterWin() { hunterWins ++; }

    public int getHunterLosses() { return hunterLosses; }

    public void addHunterLoss() { hunterLosses ++; }

    public int getRunnerWins() { return runnerWins; }

    public void addRunnerWin() { runnerWins ++; }

    public int getRunnerDeaths() { return runnerLosses; }

    public void addRunnerDeath() { runnerLosses ++; }

    public void save() {
        config.set("hunter-wins", hunterWins);
        config.set("hunter-losses", hunterLosses);
        config.set("runner-wins", runnerWins);
        config.set("runner-deaths", runnerLosses);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        hunterWins = config.getInt("hunter-wins");
        hunterLosses = config.getInt("hunter-losses");
        runnerWins = config.getInt("runner-wins");
        runnerLosses = config.getInt("runner-deaths");
    }
}
