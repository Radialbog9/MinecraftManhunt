# Minecraft Manhunt

[![Jenkins build status](https://ci.radialbog9.uk/job/Minecraft%20Manhunt/badge/icon?style=flat-square)](https://ci.radialbog9.uk/job/Minecraft%20Manhunt/)
[![Donate](https://img.shields.io/badge/donate-PayPal-orange?style=flat-square&logo=paypal)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=5DFKLGMU7QAMU&source=url)
[![Subscribe](https://img.shields.io/badge/subscribe-YouTube-orange?style=flat-square&logo=youtube)](https://bit.ly/Sub2Radialbog9)

![bStats Players](https://img.shields.io/bstats/players/9573?style=for-the-badge&color=yellow)
![bStats Servers](https://img.shields.io/bstats/servers/9573?style=for-the-badge&color=yellow)

[![GitHub issues](https://img.shields.io/github/issues/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)](https://github.com/Radialbog9/MinecraftManhunt/issues)
[![GitHub forks](https://img.shields.io/github/forks/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)](https://github.com/Radialbog9/MinecraftManhunt/network)
![GitHub all releases](https://img.shields.io/github/downloads/Radialbog9/MinecraftManhunt/total?style=for-the-badge&color=blue&logo=github)
![GitHub last commit](https://img.shields.io/github/last-commit/Radialbog9/MinecraftManhunt?color=blue&style=for-the-badge&logo=github)

A classic take on Dream's manhunt game. 
None of his original source code has been used.

All credit for the idea goes to Dream. 
His videos are awesome, and you should [check him out](https://www.youtube.com/Dream). 

![bStats](https://bstats.org/signatures/bukkit/MinecraftManhunt.svg)

## Notice
___This plugin is out of beta, but there's still a lot of bugs! You can help out by testing the latest build on our [Jenkins](https://ci.radialbog9.uk/job/Minecraft%20Manhunt/).___

Progress so far:
* Added main game variables
* Added runners death event
* Added compass tracking for hunters
* Add join event for when game is ongoing
* Add hunter death event
* Add commands
* Add hunter win event

To do:
* Possibly add SuperVanish/PremiumVanish integration for player list

## Basic setup
* Add the runners by typing command: `/manhunt runner <player>`
* Add the hunters by typing command: `/manhunt hunter <player>`
* Start the game by typing command: `/manhunt start`

## Info
When the game is started, all other players will be put into spectator and the hunters will be given a compass that they can use to track the nearest runner. 
When all runners are dead the hunters win but if one of the runners kills the ender dragon the runners win. 
The runners cannot respawn but the hunters can.

## Contributing
Please report any bugs you find or improve our code and make a pull request! 
We're always open for suggestions!

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

## bStats
We use bStats to track how people use our plugin.
> If you don't want bStats to collect data from your server, you can disable it in the bStats config file. This file can be found in the `/plugins/bStats/` folder.
