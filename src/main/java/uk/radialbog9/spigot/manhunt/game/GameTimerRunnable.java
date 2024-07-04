package uk.radialbog9.spigot.manhunt.game;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

public class GameTimerRunnable extends BukkitRunnable {
    @Override
    public void run() {
        if(GameManager.getGame().isGameStarted()) {
            if (secondsToEnd() <= 0) {
                // End game
                GameManager.endGame(GameEndCause.TIME_UP);
                return;
            }
            displayTimer();
        }
    }

    private void displayTimer() {
        // Display timer on all player's screens
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(
                            LanguageTranslator.translate(
                                    "time-remaining",
                                    formatTimeSeconds(secondsToEnd())
                            )
                    )
            );
        }
    }

    private long secondsToEnd() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = GameManager.getGame().getGameEndTime();

        return SECONDS.between(now, end);
    }

    private String formatTimeSeconds(long timeSeconds) {
        Duration dur = Duration.ofSeconds(timeSeconds);
        long HH = dur.toHours();
        long MM = dur.toMinutesPart();
        long SS = dur.toSecondsPart();
        return String.format("%02d:%02d:%02d", HH, MM, SS);
    }
}
