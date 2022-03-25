/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.kits;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public class Kit {
    @Getter
    private String name;
    @Getter
    @Setter
    private ArrayList<ItemStack> items;

    public Kit(String name, ArrayList<ItemStack> items) {
        this.name = name;
        this.items = items;
    }
}
