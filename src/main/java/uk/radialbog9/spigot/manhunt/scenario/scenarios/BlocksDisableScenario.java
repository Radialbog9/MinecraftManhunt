package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioListener;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.utils.ScenarioUtils;

@Scenario("BLOCKS_DISABLES")
@ScenarioRunnable
@ScenarioListener
public class BlocksDisableScenario extends BukkitRunnable implements Listener {
    private boolean disableBlocks = false;

    class BlocksReEnable extends BukkitRunnable {
        @Override
        public void run() {
            disableBlocks = false;
        }
    }

    @Override
    public void run() {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            // Disable sneak
            disableBlocks = true;

            // Re-enable sneak after duration
            new BlocksReEnable()
                    .runTaskLater(
                            Manhunt.getInstance(),
                            Manhunt.getInstance().getConfig().getInt(
                                    "scenarios.BLOCKS_DISABLES.duration"
                            ) * 20L // 20 tps
                    );
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this) &&
                GameManager.getGame().getPlayers().contains(e.getPlayer()) &&
                disableBlocks
        ) {
            // Player is trying to break a block and shouldn't be able to, cancel it
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this) &&
                GameManager.getGame().getPlayers().contains(e.getPlayer()) &&
                disableBlocks
        ) {
            // Player is trying to break a block and shouldn't be able to, cancel it
            e.setCancelled(true);
        }
    }

}
