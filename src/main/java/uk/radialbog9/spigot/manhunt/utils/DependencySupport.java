/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * Class which stores states of plugin hooks
 */
public class DependencySupport {
    @Getter
    @Setter
    private static boolean vanishEnabled;

    @Getter
    @Setter
    private static boolean libsDisguisesEnabled;
}
