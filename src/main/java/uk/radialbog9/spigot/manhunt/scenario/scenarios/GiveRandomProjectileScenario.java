/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@Scenario("RANDOM_PROJECTILES")
@ScenarioRunnable
@SuppressWarnings("unused")
public class GiveRandomProjectileScenario extends BukkitRunnable implements ScenarioConfigurable {
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
            is = generateTippedArrow(quant, PotionType.LONG_NIGHT_VISION);
        } else if (random >= 93 && random < 99) { // Invisibility tipped arrow (7)
            is = generateTippedArrow(quant, PotionType.LONG_INVISIBILITY);
        } else if (random >= 99 && random < 106) { // Leaping tipped arrow (7)
            is = generateTippedArrow(quant, PotionType.LONG_LEAPING);
        } else if (random >= 106 && random < 114) { // Fire resistance tipped arrow (8)
            is = generateTippedArrow(quant, PotionType.LONG_FIRE_RESISTANCE);
        } else if (random >= 114 && random < 116) { // Swiftness tipped arrow (2)
            is = generateTippedArrow(quant, PotionType.STRONG_SWIFTNESS);
        } else if (random >= 116 && random < 118) { // Slowness tipped arrow (2)
            is = generateTippedArrow(quant, PotionType.LONG_SLOWNESS);
        } else if (random >= 118 && random < 122) { // Turtle Master tipped arrow (4)
            is = generateTippedArrow(quant, PotionType.TURTLE_MASTER);
        } else if (random >= 122 && random < 126) { // Water Breathing tipped arrow (4)
            is = generateTippedArrow(quant, PotionType.LONG_WATER_BREATHING);
        } else if (random >= 126 && random < 130) { // Healing tipped arrow (4)
            is = generateTippedArrow(quant, PotionType.HEALING);
        } else if (random == 130) { // Harming tipped arrow (1)
            is = generateTippedArrow(quant, PotionType.STRONG_HARMING);
        } else if (random == 131) { // Poison tipped arrow (1)
            is = generateTippedArrow(quant, PotionType.LONG_POISON);
        } else if (random >= 132 && random < 134) { // Regeneration tipped arrow (2)
            is = generateTippedArrow(quant, PotionType.STRONG_REGENERATION);
        } else if (random >= 134 && random < 136) { // Strength tipped arrow (2)
            is = generateTippedArrow(quant, PotionType.STRONG_STRENGTH);
        } else if (random == 136) { // Weakness tipped arrow (1)
            is = generateTippedArrow(quant, PotionType.LONG_WEAKNESS);
        } else if (random >= 137 && random < 147) { // Luck tipped arrow (10)
            is = generateTippedArrow(quant, PotionType.LUCK);
        } else if (random >= 147 && random < 150) { // Slow fall tipped arrow (3)
            is = generateTippedArrow(quant, PotionType.LONG_SLOW_FALLING);
        } else if (random >= 150 && random < 153) { // Spectral arrow (3)
            is = new ItemStack(Material.SPECTRAL_ARROW, quant);
        }
        loc.getWorld().dropItem(loc, is);
    }

    private ItemStack generateTippedArrow(int quantity, PotionType type) {
        ItemStack is = new ItemStack(Material.TIPPED_ARROW, quantity);
        PotionMeta pm = (PotionMeta) is.getItemMeta();
        if(pm == null) {
            Manhunt.getInstance().getLogger().warning("Failed to generate tipped arrow item meta.");
            return is;
        }
        pm.setBasePotionType(type);
        is.setItemMeta(pm);
        return is;
    }

    @Override
    public void run() {
        if (ScenarioUtils.isScenarioEnabled(this)) {
            for (Player p : GameManager.getGame().getPlayers()) {
                randomProjectileItem(p.getLocation());
            }
        }
    }

    private static class Config extends ScenarioConfiguration implements RunnableRequiredConfig {
        @Getter
        private int time = 250;
    }

    @Getter
    private Config config = new Config();


    @Override
    public void setConfig(ScenarioConfiguration config) {
        this.config = (Config) config;
    }
}
