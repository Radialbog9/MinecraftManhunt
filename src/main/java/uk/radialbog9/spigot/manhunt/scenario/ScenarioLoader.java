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
    private final HashMap<ScenarioType, ScenarioBase> availableScenarios;

    public ScenarioLoader() throws Exception {
        availableScenarios = new HashMap<>();
        Set<Class<?>> classSet = Utils.getClasses(
                new File(Manhunt.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()),
                "uk.radialbog9.spigot.manhunt.scenario.scenarios"
        );
        for(Class<?> cla : classSet) {
            Object cl = cla;
            ScenarioBase c = null;
            if(Arrays.asList(cl.getClass().getInterfaces()).contains(ScenarioBase.class)) {
                c = (ScenarioBase) cl;
            }

            if(c == null) {
                Manhunt.getInstance().getLogger().log(Level.WARNING, "Scenario " + cl.getClass().getSimpleName() + " wasn't a base!");
                continue;
            }

            //Get if the class is a scenario
            Scenario annotation = c.getClass().getAnnotation(Scenario.class);
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
            for(Object cla : classSet2) {
                Object cl = cla;
                ScenarioBase c = null;
                if(Arrays.asList(cl.getClass().getInterfaces()).contains(ScenarioBase.class)) {
                    c = (ScenarioBase) cl.getClass().cast(ScenarioBase.class);
                }

                if(c == null) {
                    Manhunt.getInstance().getLogger().log(Level.WARNING, "Scenario " + cl.getClass().getSimpleName() + " wasn't a base!");
                    continue;
                }

                //Get if the class is a scenario
                Scenario annotation = c.getClass().getAnnotation(Scenario.class);
                if(annotation == null) continue;
                //If check is fine then add to the list of available scenarios
                availableScenarios.put(annotation.value(), c);
            }
        }
    }
}
