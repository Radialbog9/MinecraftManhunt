# Minecraft Manhunt
A classic take on Dream's manhunt game.
No original source code has been used.

## Notice
___This plugin is still in development. The manhunt is currently not playable___

Progress so far:
* Added main game variables
* Added runners death event

To do:
* Add commands
* Add join event for when game is ongoing
* Add compass tracking for the hunters
* Add hunter death event

## Basic setup
* Add the runners by typing command: `/manhunt runner <player>`
* Add the hunters by typing command: `/manhunt hunter <player>`
* Start the game by typing command: `/manhunt start`

## Info
When the game is started, all other players will be put into spectator and the hunters will be given a compass that they can use to track the nearest runner. 
When all runners are dead the hunters win but if one of the runners kills the ender dragon the runners win. 
The runners cannot respawn but the hunters can.

## All commands

| Command                    | Description                                                    |
|----------------------------|----------------------------------------------------------------|
| `/manhunt start`           | Starts the manhunt.                                            |
| `/manhunt stop`            | Ends the manhunt prematurely.                                  |
| `/manhunt list`            | Lists the players who are runners and hunters.                 |
| `/manhunt reset`           | Removes all players from hunter and runner.                    |
| `/manhunt hunter <player>` | Sets a player as hunter and remove them from runner.           |
| `/manhunt runner <player>` | Sets a player as runner and remove them from hunter.           |
| `/manhunt remove <player>` | Removes a specific player from the manhunt.                    |
| `/spectate <player>`       | Teleports to a runner/hunter (only works if you're spectating) |