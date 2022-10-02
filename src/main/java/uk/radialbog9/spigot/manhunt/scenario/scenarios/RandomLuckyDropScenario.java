/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 *  You are allowed to use this code under the GPL3 license, which allows
 *  commercial use, distribution, modification, and licensed works,
 *  providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioType;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

@Scenario(ScenarioType.RANDOM_LUCKY_DROPS)
@ScenarioRunnable
public class RandomLuckyDropScenario extends BukkitRunnable {
    /**
     * The main method that gets called per player
     * @param loc The location to drop the "surprise"
     */
    public void dropRandomLuckyDrop(Location loc) {
        int random = Utils.getRandomInt(0, 100);
        //0<=x<1 (1) - Insta TNT
        if(random == 0)
            spawnEntity(loc, EntityType.PRIMED_TNT);
        //1<=x<11 (10) - Creeper
        else if(random >= 1 && random < 11)
            spawnEntity(loc, EntityType.CREEPER);
        //11<=x<21 (10) - Skeleton
        else if(random >= 11 && random < 21)
            spawnEntity(loc, EntityType.SKELETON);
        //21<=x<31 (10) - Zombie
        else if(random >= 21 && random < 31)
            spawnEntity(loc, EntityType.ZOMBIE);
        //31<=x<41 (10) - Spider
        else if(random >= 31 && random < 41)
            spawnEntity(loc, EntityType.SPIDER);
        //41<=x<46 (5) - Ghast
        else if(random >= 41 && random < 46)
            spawnEntity(loc, EntityType.GHAST);
        //46<=x<47 (1) - Wither
        else if(random == 46)
            spawnEntity(loc, EntityType.WITHER);
        //47<=x<51 (4) - Bee bomb
        else if(random >= 47 && random < 51) for(int i = 0; i < 25; i++)
            spawnEntity(loc, EntityType.BEE);
        //51<=x<61 (10) - random amount of diamond
        else if(random >= 51 && random < 61)
            loc.getWorld().dropItemNaturally(loc, new ItemStack(org.bukkit.Material.DIAMOND, Utils.getRandomInt(1, 32)));
        //61<=x<71 (10) - End loot table
        else if(random >= 61 && random < 71) {
            dropLootTable(loc, LootTables.END_CITY_TREASURE.getLootTable());
        }
    }

    /**
     * Drops a random set of items from the specified loot table
     * @param loc The location to drop the items
     * @param table The loot table to get the items from
     */
    public void dropLootTable(Location loc, LootTable table) {
        //Build loot table
        LootContext.Builder builder = new LootContext.Builder(loc);
        LootContext context = builder.build();
        //Generate items
        ArrayList<ItemStack> items = new ArrayList<>(table.populateLoot(new Random(), context));
        for (ItemStack drop : items) {
            dropItem(loc, drop);
        }
    }

    /**
     * Drops an itemstack at a location
     * @param loc The location to drop the item
     * @param item The itemstack to drop
     */
    public void dropItem(Location loc, ItemStack item) {
        loc.getWorld().dropItemNaturally(loc, item);
    }

    /**
     * Spawns an entity of a specified type
     * @param loc The location to spawn the entity
     * @param type The type of entity to spawn
     */
    public void spawnEntity(Location loc, EntityType type) {
        loc.getWorld().spawnEntity(loc, type);
    }

    /**
     * The main method of the runnable
     */
    @Override
    public void run() {
        if(!GameManager.getGame().isGameStarted() && GameManager.getGame().getActiveScenarios().contains(ScenarioType.RANDOM_LUCKY_DROPS))
            // drop a random item from the list of lucky items
            for(Player p : GameManager.getGame().getPlayers()) dropRandomLuckyDrop(p.getLocation());
    }
}
