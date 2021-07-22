# MINECRAFT MANHUNT


[![Jenkins build status](https://ci.radialbog9.uk/job/Minecraft%20Manhunt/badge/icon?style=flat-square)](https://ci.radialbog9.uk/job/Minecraft%20Manhunt/)
[![Donate](https://img.shields.io/badge/donate-PayPal-orange?style=flat-square&logo=paypal)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=5DFKLGMU7QAMU&source=url)
[![Subscribe](https://img.shields.io/badge/subscribe-YouTube-orange?style=flat-square&logo=youtube)](https://bit.ly/Sub2Radialbog9)
[![Discord](https://img.shields.io/discord/450232632798740480?style=flat-square&color=orange&logo=discord)](https://discord.gg/drtz8wm)

![bStats Players](https://img.shields.io/bstats/players/9573?style=for-the-badge&color=yellow)
![bStats Servers](https://img.shields.io/bstats/servers/9573?style=for-the-badge&color=yellow)

[![GitHub issues](https://img.shields.io/github/issues/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)](https://github.com/Radialbog9/MinecraftManhunt/issues)
[![GitHub forks](https://img.shields.io/github/forks/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)](https://github.com/Radialbog9/MinecraftManhunt/network)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)
![GitHub all releases](https://img.shields.io/github/downloads/Radialbog9/MinecraftManhunt/total?style=for-the-badge&color=blue&logo=github)
![GitHub last commit](https://img.shields.io/github/last-commit/Radialbog9/MinecraftManhunt?color=blue&style=for-the-badge&logo=github)

---

A unique take on Dream's manhunt game with many features. 
None of his original source code has been used.

All credit for the idea goes to Dream. 
His videos are awesome, and you should [check him out](https://www.youtube.com/Dream). 

---

![bStats](https://bstats.org/signatures/bukkit/MinecraftManhunt.svg)

---

## Notice
___This plugin is out of beta, but there's still a lot of bugs! You can help out by testing the latest build on our [Jenkins](https://ci.radialbog9.uk/job/Minecraft%20Manhunt/) or the [latest stable release](https://github.com/Radialbog9/MinecraftManhunt/releases).___

---
Progress so far:
* Added main game variables
* Added runner death event
* Added compass tracking for hunters
* Add join event for when game is ongoing
* Add hunter death event
* Add commands
* Add hunter win event
* Finish player data local storage

To do:
* Finish moving language to config file

---

## Basic setup
* Add the runners by typing command: `/manhunt runner <player>`.
* Add the hunters by typing command: `/manhunt hunter <player>`.
* Start the game by typing command: `/manhunt start`.
* If you want to stop the game manually run `/manhunt stop`.

## Info
When the game is started, all other players will be put into spectator, and the hunters will be given a compass that they can use to track the nearest runner. 
When all runners are dead the hunters win but if one of the runners kills the Ender Dragon the runners win. 
The runners cannot respawn, but the hunters can.
For the rules, check the [rulebook](https://radialbog9.github.io/MinecraftManhunt/rulebook) (obviously you can make up your own rules if these don't fit your taste).

##Dependencies
This plugin does not have any dependencies, but it works with SuperVanish or PremiumVanish to hide any spectators who are in vanish from the Manhunt player list.

## Contributing
Please report any bugs you find in a GitHub Issue or improve our code and make a pull request! 
We're always open for suggestions!

## API
This plugin has a (somewhat simple) API for developers to use. 
You can start and stop the game and access the list of runners and hunters.
See [this page](https://radialbog9.github.io/api.md) for more info.

## bStats
We use bStats to track how people use our plugin.
> If you don't want bStats to collect data from your server, you can disable it in the bStats config file. This file can be found in the `/plugins/bStats/` folder.

---
## Config
The config file is well documented with comments. Just look at the `config.yml` file in the plugin's folder on your server.

Below is parts of the default config. More documentation about the config can be found [here](https://radialbog9.github.io/MinecraftManhunt/config).
```yaml
# Gives runner(s) a head start by giving hunters blindness, slowness, and weakness
# Set to false for a more authentic manhunt.
head-start:
  # Head start enabled?
  enabled: true
  # Length of the head start (in seconds)
  length: 30

# Language
# Some entries have required arguments (such as player names) which are indicated by '%s'
language:
  no-command-specified: '&6[Manhunt]&r&a No command specified. Type /manhunt help for command help.'
  # ...
  player-not-online: '&6[Manhunt]&r&a The player&r&c %s &r&ais not online!' # '%s' is replaced with the player name
  # ...
```
