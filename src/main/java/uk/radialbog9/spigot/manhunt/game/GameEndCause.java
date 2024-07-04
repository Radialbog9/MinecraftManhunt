/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.game;

/**
 * Game Win Causes
 */
public enum GameEndCause {
    /**
     * When the runners are all dead
     */
    RUNNERS_ALL_DIE,
    /**
     * When the runners kill the Ender Dragon
     */
    RUNNERS_KILL_DRAGON,
    /**
     * When all hunters have left the server
     */
    ALL_HUNTERS_LEAVE,
    /**
     * When all runners have left the server
     */
    ALL_RUNNERS_LEAVE,
    /**
     * When the game timer expires (certain gamemodes)
     */
    TIME_UP,
    /**
     * When the game is stopped with a command
     */
    ENDED_PREMATURELY
}
