/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import lombok.Getter;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.DependencySupport;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class ScenarioLoader {
    @Getter
    private final HashMap<String, Class<?>> availableScenarios;

    public void loadScenariosFromPackage(File codeSourcePath, String pkg) {
        Set<Class<?>> classSet = Utils.getClasses(codeSourcePath, pkg);
        for(Class<?> cla : classSet) {
            //Get if the class is a scenario
            Scenario annotation = cla.getAnnotation(Scenario.class);
            if(annotation == null) continue;
            //If check is fine then add to the list of available scenarios
            availableScenarios.put(annotation.value(), cla);
        }
    }

    public ScenarioLoader() throws Exception {
        availableScenarios = new HashMap<>();

        //load scenarios that don't require LibsDisguises
        loadScenariosFromPackage(
                new File(Manhunt.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()),
                "uk.radialbog9.spigot.manhunt.scenario.scenarios");

        //load scenarios that require LibsDisguises if enabled
        if(DependencySupport.isLibsDisguisesEnabled()) {
            loadScenariosFromPackage(
                    new File(Manhunt.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()),
                    "uk.radialbog9.spigot.manhunt.scenario.ldisscenarios");
        }
    }
}
