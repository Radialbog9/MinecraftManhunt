package uk.radialbog9.spigot.manhunt.scenario;

import lombok.Getter;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Set;

public class ScenarioLoader {
    @Getter
    private final HashMap<ScenarioType, Class<?>> availableScenarios;

    public ScenarioLoader() throws URISyntaxException {
        availableScenarios = new HashMap<>();
        Set<Class<?>> classSet = Utils.getClasses(
                new File(Manhunt.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()),
                "uk.radialbog9.spigot.manhunt.scenario.scenarios"
        );
        classSet.forEach(c -> {
            Scenario annotation = c.getAnnotation(Scenario.class);
            if(annotation != null) {
                availableScenarios.put(annotation.value(), c);
            }
        });
    }
}
