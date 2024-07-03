package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.utils.template.PotionScenarioTemplate;

import java.util.Arrays;
import java.util.List;

@Scenario("RUNNER_RANDOM_POTION_EFFECT")
@ScenarioRunnable
@SuppressWarnings("unused")
public class RunnerRandomPotionScenario extends PotionScenarioTemplate {
    @Override
    public List<Player> getPlayerSet() {
        return GameManager.getGame().getRunners();
    }

    @Override
    public List<PotionEffectType> getPotionEffectTypes() {
        return Arrays.stream(PotionEffectType.values()).toList();
    }
}