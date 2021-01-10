package uk.radialbog9.spigot.manhunt.utils;

/**
 * Game Win Causes
 *
 * All causes:
 * RUNNERS_ALL_DIE: When the runners are all dead <br>
 * RUNNERS_KILL_DRAGON: When the runners kill the Ender Dragon <br>
 * ALL_HUNTERS_LEAVE: When all hunters have left the server <br>
 * ALL_RUNNERS_LEAVE: When all runners have left the server <br>
 * TIME_UP: To be used in a gamemode where runners have a certain time to survive <br>
 * ENDED_PREMATURELY: When a game is stopped via a command. <br>
 */
public enum GameEndCause {
    RUNNERS_ALL_DIE,
    RUNNERS_KILL_DRAGON,
    ALL_HUNTERS_LEAVE,
    ALL_RUNNERS_LEAVE,
    TIME_UP,
    ENDED_PREMATURELY
}
