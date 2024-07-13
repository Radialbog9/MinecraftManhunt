/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.game.GameEndCause;

/**
 * This event is for when the game ends
 */
@SuppressWarnings("unused")
public class ManhuntGameEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Getter
    @Setter
    private GameEndCause endCause;

    /**
     * Initiates the event.
     * When GameEndCause e is not specified it defaults to ENDED_PREMATURELY.
     */
    public ManhuntGameEndEvent() { endCause = GameEndCause.ENDED_PREMATURELY; }

    /**
     * Initiates the event.
     * @param c game end cause
     */
    public ManhuntGameEndEvent(GameEndCause c) { endCause = c; }


    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
