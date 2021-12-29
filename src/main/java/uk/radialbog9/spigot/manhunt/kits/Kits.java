/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.kits;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class Kits {
    /**
     * Imports the kits from the specified file
     * @param kitSection the configuration section containing the kits
     * @param type the type of the kits (hunter/runner)
     */
    public static HashMap<String, Kit> importKits(ConfigurationSection kitSection, KitType type) {
        HashMap<String, Kit> kits = new HashMap<>();
        for (String key : kitSection.getKeys(false)) {
            ConfigurationSection kitsec = kitSection.getConfigurationSection(key);
            ArrayList<ItemStack> itemStacks = new ArrayList<>();
            for (String stack : kitsec.getConfigurationSection("items").getKeys(false)) {
                int amount = Math.min(kitsec.getConfigurationSection(stack).getInt("quantity"), 64);
                ItemStack itemStack = new ItemStack(Material.getMaterial(kitsec.getConfigurationSection(stack).getString("item")), amount);
                itemStacks.add(itemStack);
            }
            Kit thisKit = new Kit(key, type, itemStacks);
            kits.put(key, thisKit);
        }
        return kits;
    }
}
