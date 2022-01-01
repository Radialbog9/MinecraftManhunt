package uk.radialbog9.spigot.manhunt.scenario;

import lombok.Getter;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

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
        for(Class<?> cla : classSet) {
            //Get if the class is a scenario
            Scenario annotation = cla.getAnnotation(Scenario.class);
            if(annotation == null) continue;
            //If check is fine then add to the list of available scenarios
            availableScenarios.put(annotation.value(), cla);
        }

        //load scenarios that require LibsDisguises if enabled
        if(ManhuntVars.isLibsDisguisesEnabled()) {
            Set<Class<?>> classSet2 = Utils.getClasses(
                    new File(Manhunt.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()),
                    "uk.radialbog9.spigot.manhunt.scenario.ldisscenarios"
            );
            for(Class<?> cla : classSet2) {
                //Get if the class is a scenario
                Scenario annotation = cla.getAnnotation(Scenario.class);
                if(annotation == null) continue;
                //If check is fine then add to the list of available scenarios
                availableScenarios.put(annotation.value(), cla);
            }
        }
    }
}
