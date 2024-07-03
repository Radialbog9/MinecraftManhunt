package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.utils.template.PotionScenarioTemplate;

import java.util.List;

@Scenario("RUNNER_INVISIBILITY")
@ScenarioRunnable
@SuppressWarnings("unused")
public class RunnerInvisibilityScenario extends PotionScenarioTemplate {

    @Override
    public List<Player> getPlayerSet() {
        return GameManager.getGame().getRunners();
    }

    @Override
    public List<PotionEffectType> getPotionEffectTypes() {
        return List.of(PotionEffectType.INVISIBILITY);
    }
}
