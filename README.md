# MINECRAFT MANHUNT


[![Donate](https://img.shields.io/badge/donate-Buy%20Me%20A%20Coffee-orange?style=flat-square&logo=buymeacoffee)](https://buymeacoff.ee/Radialbog9)
[![Subscribe](https://img.shields.io/badge/subscribe-YouTube-orange?style=flat-square&logo=youtube)](https://rb9.xyz/sub)
[![Discord](https://img.shields.io/discord/450232632798740480?style=flat-square&color=orange&logo=discord)](https://rb9.xyz/discord)

[![Crowdin](https://badges.crowdin.net/minecraft-manhunt/localized.svg)](https://crowdin.com/project/minecraft-manhunt)

![bStats Players](https://img.shields.io/bstats/players/9573?style=for-the-badge&color=yellow)
![bStats Servers](https://img.shields.io/bstats/servers/9573?style=for-the-badge&color=yellow)
![SpigotMC tested server versions](https://img.shields.io/spiget/tested-versions/97765?color=yellow&style=for-the-badge)

[![GitHub license](https://img.shields.io/github/license/Radialbog9/MinecraftManhunt?color=blue&logo=github&style=for-the-badge)](https://github.com/Radialbog9/MinecraftManhunt/blob/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)](https://github.com/Radialbog9/MinecraftManhunt/issues)
[![GitHub forks](https://img.shields.io/github/forks/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)](https://github.com/Radialbog9/MinecraftManhunt/network)
![GitHub stars](https://img.shields.io/github/stars/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)
![GitHub all releases](https://img.shields.io/github/downloads/Radialbog9/MinecraftManhunt/total?style=for-the-badge&color=blue&logo=github)
![GitHub last commit](https://img.shields.io/github/last-commit/Radialbog9/MinecraftManhunt?color=blue&style=for-the-badge&logo=github)
![GitHub repo size](https://img.shields.io/github/repo-size/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)
![GitHub top language](https://img.shields.io/github/languages/top/Radialbog9/MinecraftManhunt?color=blue&logo=github&style=for-the-badge)

[![wakatime](https://wakatime.com/badge/github/Radialbog9/MinecraftManhunt.svg?style=for-the-badge)](https://wakatime.com/badge/github/Radialbog9/MinecraftManhunt)

---

A unique take on Dream's manhunt game with many features, built from scratch.

All credit for the idea goes to Dream. 
His Manhunt videos are awesome, and you should [check him out](https://www.youtube.com/Dream). 

---

[![bStats](https://bstats.org/signatures/bukkit/MinecraftManhunt.svg)](https://bstats.org/plugin/bukkit/MinecraftManhunt/9573)

---

## Notice
___This plugin is out of beta, but there's probably still a lot of bugs!___ 
___You can help out by testing the latest build on our [GitHub Actions](https://github.com/Radialbog9/MinecraftManhunt/actions/workflows/maven.yml) or the [latest stable release](https://github.com/Radialbog9/MinecraftManhunt/releases).___

---

## Basic setup
* Change settings and add hunters/runners with `/manhunt settings`.
* If you want to stop the game manually run `/manhunt stop`.

## Info
When the game is started, all other players will be put into spectator, and the hunters will be given a compass that they can use to track the nearest runner. 
When all runners are dead the hunters win but if one of the runners kills the Ender Dragon the runners win. 
The runners cannot respawn, but the hunters can.
For the rules, check the [rulebook](https://radialbog9.gitbook.io/minecraft-manhunt/usage/suggested-game-rules) (obviously you can make up your own rules if these don't fit your taste).

## Features
* Multiple hunters/runners support
* SuperVanish/PremiumVanish support
* Scenarios - Add twists to your game
* Spectator support
* Automatic removal of player from game if they disconnect
* Settings menu

## Dependencies
This plugin does not have any required dependencies, but it works with the following plugins:
- `SuperVanish` or `PremiumVanish` - Hides players in vanish from the player list
- `LibsDisguises` - Add more disguise scenarios

If you want to ccreate an addon for this plugin, check out the [API documentation](https://radialbog9.gitbook.io/minecraft-manhunt/developers/api) 
and the [Javadocs](https://radialbog9.github.io/MinecraftManhunt/javadoc/).

## Contributing
Please report any bugs you find in a GitHub Issue or improve our code and make a pull request! 
We're always open to suggestions!

## API
This plugin has a (somewhat simple and somewhat terrible) API for developers to use. 
You can start and stop the game and access the list of runners and hunters.
See [this page](https://radialbog9.gitbook.io/minecraft-manhunt/developers/api) for more info.

## bStats
We use bStats to track how people use our plugin.
> If you don't want bStats to collect data from your server, you can disable it in the bStats config file. This file can be found in the `/plugins/bStats/` folder.

---
## Config
The config file is now automatically generated. Check the [documentation](https://radialbog9.gitbook.io/minecraft-manhunt/usage/config.yml) for more info.

## Language
This plugin uses a configurable language system.
To use the prebundled languages, change the `language` key in the config to one of the following (in order of completeness):
* `en_GB` (English UK - default)
* `en_US` (English US)
* `pl_PL` (Polish) \[Needs Proof-reading\]
* `it_IT` (Italian) \[Incomplete\]
* `nl_NL` (Dutch) \[Incomplete\]
* `de_DE` (German) \[Incomplete\]
* `zh_CN` (Chinese Simplified) \[Incomplete\]
* `ko_KR` (Korean) \[Incomplete\]
* `fr_FR` (French) \[Incomplete\]
* `uk_UA` (Ukranian) \[Incomplete\]
* `ru_RU` (Russian) \[Incomplete\]
* `ja_JP` (Japanese) \[Incomplete\]
* `es_ES` (Spanish) \[Incomplete\]
* `sv_SE` (Swedish) \[Incomplete\]

This list may be incomplete, so check the Crowdin page for more up-to-date info.

If you natively speak or are fluent in any of these languages then you can help us translate! 
Just go to Crowdin and start translating: https://crowdin.com/project/minecraft-manhunt

If you want to be a proofreader, join the [Discord server](https://rb9.xyz/discord) then create a General Help ticket.
