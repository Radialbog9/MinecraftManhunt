package uk.radialbog9.spigot.manhunt.utils;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CompassTrackable {
    @Getter
    private static final List<Player> hiddenPlayers = new ArrayList<>();

    /**
     * Returns a list of players that are trackable
     * @param players List of players to filter
     * @return List of players that are trackable
     */
    public static List<Player> getTrackable(List<Player> players) {
        return players.stream().filter(
                player -> !hiddenPlayers.contains(player)
        ).toList();
    }

}
