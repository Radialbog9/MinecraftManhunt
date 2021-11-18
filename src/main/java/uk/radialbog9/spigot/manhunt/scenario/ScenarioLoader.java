package uk.radialbog9.spigot.manhunt.scenario;

import lombok.Getter;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

@SuppressWarnings({"unused"})
public class ScenarioLoader {
    @Getter
    private final HashMap<ScenarioType, Class<?>> availableScenarios;

    public ScenarioLoader() throws Exception {
        availableScenarios = new HashMap<>();
        Set<Class<?>> classSet = Utils.getClasses(
                new File(Manhunt.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()),
                "uk.radialbog9.spigot.manhunt.scenario.scenarios"
        );
        for(Class<?> c : classSet) {
            //Get if the class is a scenario
            Scenario annotation = c.getAnnotation(Scenario.class);
            if(annotation == null) continue;
            //If check is fine then add to the list of available scenarios
            availableScenarios.put(annotation.value(), c);
        }

        //load scenarios that require LibsDisguises if enabled
        if(ManhuntVars.isLibsDisguisesEnabled()) {
            Set<Class<?>> classSet2 = Utils.getClasses(
                    new File(Manhunt.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()),
                    "uk.radialbog9.spigot.manhunt.scenario.ldisscenarios"
            );
            for(Class<?> c : classSet2) {
                //Get if the class is a scenario
                Scenario annotation = c.getAnnotation(Scenario.class);
                if(annotation == null) continue;
                //If check is fine then add to the list of available scenarios
                availableScenarios.put(annotation.value(), c);
            }
        }
    }
}
