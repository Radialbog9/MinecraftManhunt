# Minecraft Manhunt

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=5DFKLGMU7QAMU&source=url)
[![Jenkins build status](http://ci.radialbog9.uk/job/Minecraft%20Manhunt/badge/icon)](http://ci.radialbog9.uk/job/Minecraft%20Manhunt/)

A classic take on Dream's manhunt game. 
None of his original source code has been used. 

## Notice
___This plugin is still in development. The manhunt is currently not playable___

Progress so far:
* Added main game variables
* Added runners death event
* Added compass tracking for hunters
* Add join event for when game is ongoing
* Add hunter death event

To do:
* Add commands
* Add win event

## Basic setup
* Add the runners by typing command: `/manhunt runner <player>`
* Add the hunters by typing command: `/manhunt hunter <player>`
* Start the game by typing command: `/manhunt start`

## Info
When the game is started, all other players will be put into spectator and the hunters will be given a compass that they can use to track the nearest runner. 
When all runners are dead the hunters win but if one of the runners kills the ender dragon the runners win. 
The runners cannot respawn but the hunters can.

## All commands

| Command                                    | Description                                                     |
|--------------------------------------------|-----------------------------------------------------------------|
| `/manhunt help`                            | Shows other manhunt commands.                                   |
| `/manhunt hunter <player>`                 | Sets a player as hunter and remove them from runner.            |
| `/manhunt runner <player>`                 | Sets a player as runner and remove them from hunter.            |
| `/manhunt remove <player>`                 | Removes a specific player from the manhunt.                     |
| `/manhunt start`                           | Starts the manhunt.                                             |
| `/manhunt stop`                            | Ends the manhunt prematurely.                                   |
| `/manhunt list`                            | Lists the players who are runners and hunters.                  |
| `/manhunt reset`                           | Removes all players from hunter and runner.                     |
| `/spectate <player>`                       | Teleports to a runner/hunter (only works if you're a spectator) |

## Permissions
| Permission       | Description                                |
|------------------|--------------------------------------------|
|                  | Allows player to run `/manhunt help`       |
| manhunt.add      | Allows player to add hunters/runners       |
| manhunt.remove   | Allows player to remove a player           |
| manhunt.start    | Allows player to start the game            |
| manhunt.stop     | Allows player to end the game forcibly     |
| manhunt.list     | Allows player to list hunters/runners      |
| manhunt.reset    | Allows player to reset the game            |
| manhunt.spectate | Allows players to use `/spectate <player>` |
