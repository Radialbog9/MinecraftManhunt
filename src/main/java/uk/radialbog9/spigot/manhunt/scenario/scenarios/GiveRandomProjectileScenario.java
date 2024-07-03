/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 *  You are allowed to use this code under the GPL3 license, which allows
 *  commercial use, distribution, modification, and licensed works,
 *  providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario("RANDOM_PROJECTILES")
@ScenarioRunnable
@SuppressWarnings("unused")
public class GiveRandomProjectileScenario extends BukkitRunnable {
    public void randomProjectileItem(Location loc) {
        int random = Utils.getRandomInt(0, 152);
        int quant = Utils.getRandomInt(1, 3);
        ItemStack is = null;
        if(random >= 0 && random < 29) { // Egg (29)
            // Reroll quantity for item egg
            quant = Utils.getRandomInt(2, 16);
            // Set item to egg
            is = new ItemStack(Material.EGG, quant);
        } else if (random >= 29 && random < 58) { // Snowball (29)
            // Reroll quantity for item snowball
            quant = Utils.getRandomInt(2, 16);
            // Set item to snowball
            is = new ItemStack(Material.SNOWBALL, quant);
        } else if (random >= 58 && random < 87) { // Arrow (29)
            // Reroll quantity for item arrow
            quant = Utils.getRandomInt(2, 16);
            // Set item to arrow
            is = new ItemStack(Material.ARROW, quant);
        } else if (random >= 87 && random < 89) { // Ender pearl (2)
            // Set item to Ender Pearl
            is = new ItemStack(Material.ENDER_PEARL, quant);
        } else if (random >= 89 && random < 93) { // Night vision tipped arrow (4)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.NIGHT_VISION));
            is.setItemMeta(pm);
        } else if (random >= 93 && random < 99) { // Invisibility tipped arrow (7)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.INVISIBILITY, true, false));
            is.setItemMeta(pm);
        } else if (random >= 99 && random < 106) { // Leaping tipped arrow (7)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.JUMP, true, false));
            is.setItemMeta(pm);
        } else if (random >= 106 && random < 114) { // Fire resistance tipped arrow (8)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE, true, false));
            is.setItemMeta(pm);
        } else if (random >= 114 && random < 116) { // Swiftness tipped arrow (2)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.SPEED, false, true));
            is.setItemMeta(pm);
        } else if (random >= 116 && random < 118) { // Slowness tipped arrow (2)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.SLOWNESS, false, true));
            is.setItemMeta(pm);
        } else if (random >= 118 && random < 122) { // Turtle Master tipped arrow (4)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.TURTLE_MASTER));
            is.setItemMeta(pm);
        } else if (random >= 122 && random < 126) { // Water Breathing tipped arrow (4)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.WATER_BREATHING, true, false));
            is.setItemMeta(pm);
        } else if (random >= 126 && random < 130) { // Healing tipped arrow (4)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
            is.setItemMeta(pm);
        } else if (random == 130) { // Harming tipped arrow (1)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE, false, true));
            is.setItemMeta(pm);
        } else if (random == 131) { // Poison tipped arrow (1)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.POISON, true, false));
            is.setItemMeta(pm);
        } else if (random >= 132 && random < 134) { // Regeneration tipped arrow (2)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.REGEN, false, true));
            is.setItemMeta(pm);
        } else if (random >= 134 && random < 136) { // Strength tipped arrow (2)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
            is.setItemMeta(pm);
        } else if (random == 136) { // Weakness tipped arrow (1)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.WEAKNESS, true, false));
            is.setItemMeta(pm);
        } else if (random >= 137 && random < 147) { // Luck tipped arrow (10)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.LUCK));
            is.setItemMeta(pm);
        } else if (random >= 147 && random < 150) { // Slow fall tipped arrow (3)
            is = new ItemStack(Material.TIPPED_ARROW, quant);
            PotionMeta pm = (PotionMeta) is.getItemMeta();
            pm.setBasePotionData(new PotionData(PotionType.SLOW_FALLING, true, false));
            is.setItemMeta(pm);
        } else if (random >= 150 && random < 153) { // Spectral arrow (3)
            is = new ItemStack(Material.SPECTRAL_ARROW, quant);
        }
        loc.getWorld().dropItem(loc, is);
    }
    @Override
    public void run() {
        if (GameManager.getGame().isGameStarted() && GameManager.getGame().getActiveScenarios().contains("RANDOM_PROJECTILES")) {
            for (Player p : GameManager.getGame().getPlayers()) {
                randomProjectileItem(p.getLocation());
            }
        }
    }
}
