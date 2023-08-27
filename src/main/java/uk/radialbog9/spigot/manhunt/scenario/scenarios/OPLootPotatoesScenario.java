/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioListener;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioType;

import java.util.ArrayList;
import java.util.Random;

@Scenario(ScenarioType.OP_LOOT_POTATOES)
@ScenarioListener
public class OPLootPotatoesScenario implements Listener {
    @EventHandler
    public void potatoBreakEvent(BlockBreakEvent e) {
        if(
                !GameManager.getGame().isGameStarted()
                && GameManager.getGame().getActiveScenarios().contains(ScenarioType.OP_LOOT_POTATOES)
                && e.getBlock().getType() == Material.POTATOES
                && ((Ageable) e.getBlock().getBlockData()).getAge() == 7
        ) {
            //Stop potatoes from dropping
            e.setDropItems(false);
            //Get loot table to drop
            LootTable endCityLoot = LootTables.END_CITY_TREASURE.getLootTable();
            //Build loot table
            LootContext.Builder builder = new LootContext.Builder(e.getBlock().getLocation());
            LootContext context = builder.build();
            //Generate items
            ArrayList<ItemStack> items = new ArrayList<>(endCityLoot.populateLoot(new Random(), context));
            for (ItemStack drop : items) {
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
            }
        }
    }
}
