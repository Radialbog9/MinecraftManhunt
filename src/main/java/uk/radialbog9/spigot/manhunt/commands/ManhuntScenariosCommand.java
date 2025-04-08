/*
 * Copyright (c) 2020-2025 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.Flag;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioMenu;

@SuppressWarnings("unused")
public class ManhuntScenariosCommand {
    @CommandMethod("manhunt scenarios")
    @CommandPermission("manhunt.scenarios")
    public void mScenariosMenu(@NotNull CommandSender sender, @Flag("page") Integer page) {
        if(page == null || page < 1)
            page = 1;

        if (!(sender instanceof Player)){
            sender.sendMessage(LanguageTranslator.translate("no-run-console"));
            return;
        }
        ScenarioMenu.displayMenu((Player) sender, page);
    }

    @CommandMethod("manhunt scenarios list enabled")
    @CommandPermission("manhunt.scenarios")
    public void mScenarioListEnabled(@NotNull CommandSender sender) {
        sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-list-enabled"));
        for (String scenario : GameManager.getGame().getActiveScenarios()) {
            sender.sendMessage(LanguageTranslator.translate(
                    "scenariomenu.scenario-list-format",
                    LanguageTranslator.translate("scenario." + scenario),
                    scenario)
            );
        }
    }

    @CommandMethod("manhunt scenarios list")
    @CommandPermission("manhunt.scenarios")
    public void mScenarioListDefault(@NotNull CommandSender sender) {
        mScenarioListEnabled(sender);
    }

    @CommandMethod("manhunt scenarios list available")
    @CommandPermission("manhunt.scenarios")
    public void mScenarioListAvailable(@NotNull CommandSender sender) {
        sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-list-available"));
        for (String scenario : Manhunt.getScenarioLoader().getAvailableScenarios().keySet()) {
            sender.sendMessage(LanguageTranslator.translate(
                    "scenariomenu.scenario-list-format",
                    LanguageTranslator.translate("scenario." + scenario),
                    scenario)
            );
        }
    }

    @CommandMethod("manhunt scenarios toggle <scenario>")
    @CommandPermission("manhunt.scenarios")
    public void mScenarioToggle(
            @NotNull CommandSender sender,
            @Argument(value = "scenario", suggestions = "scenariotype") String scenario,
            @Flag("page") Integer page
    ) {
        if(page == null || page < 1)
            page = 1;

        // Check if game is started
        if (GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("scenariomenu.no-change-ingame"));
            return;
        }

        // Check if scenario is available
        if(!Manhunt.getScenarioLoader().getAvailableScenarios().containsKey(scenario)) {
            sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-unavailable", scenario));
            return;
        }

        // Scenario is available
        if (GameManager.getGame().getActiveScenarios().contains(scenario)) {
            //Scenario is enabled, disable it!
            GameManager.getGame().getActiveScenarios().remove(scenario);
            sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-disabled", scenario));
        } else {
            //Scenario is disabled, enable it!
            GameManager.getGame().getActiveScenarios().add(scenario);
            sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-enabled", scenario));
        }

        if (sender instanceof Player) {
            // Display the scenario menu if the sender is a player
            ScenarioMenu.displayMenu((Player) sender, page);
        }
    }
}
