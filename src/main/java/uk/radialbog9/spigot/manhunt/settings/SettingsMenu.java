package uk.radialbog9.spigot.manhunt.settings;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.Utils;

public class SettingsMenu {
    public void displayMenu(Player p) {
        p.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.title")));
        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.head-start")),
                "/manhunt settings headstart",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-toggle"))
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.head-start-timer")),
                "/manhunt settings headstarttimer ",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-change"))
        ));

        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.start-game")),
                "/manhunt settings headstarttimer ",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-start"))
        ));
    }
}
