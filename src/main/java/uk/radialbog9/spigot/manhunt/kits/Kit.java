/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.kits;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public class Kit {
    private String name;
    private ArrayList<ItemStack> items;
    private KitType kitType;

    public Kit(String name, KitType kitType, ArrayList<ItemStack> items) {
        this.name = name;
        this.kitType = kitType;
        this.items = items;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ItemStack> getItems() {
        return this.items;
    }
    public void setItems(ArrayList<ItemStack> items) {
        this.items = items;
    }

    public KitType getKitType() {
        return this.kitType;
    }
    public void setKitType(KitType kitType) {
        this.kitType = kitType;
    }


}
