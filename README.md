# MINECRAFT MANHUNT


[![Jenkins build status](https://ci.radialbog9.uk/job/MinecraftManhunt/badge/icon?style=flat-square)](https://ci.radialbog9.uk/job/MinecraftManhunt/)
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
___You can help out by testing the latest build on our [Jenkins](https://ci.radialbog9.uk/job/MinecraftManhunt/) or the [latest stable release](https://github.com/Radialbog9/MinecraftManhunt/releases).___

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
The config file is well documented with comments. Just look at the `config.yml` file in the plugin's folder on your server.

Below are some parts of the default config. More documentation about the config can be found [here](https://radialbog9.gitbook.io/minecraft-manhunt/usage/config.yml).
On large updates, you might have to delete the config file and let it regenerate.
```yaml
# Gives runner(s) a head start by giving hunters blindness, slowness, and weakness
# Set to false for a more authentic manhunt.
head-start:
  # Head start enabled?
  enabled: true
  # Length of the head start (in seconds)
  length: 30

# ...

### SCENARIOS ###
scenarios:
  RUNNER_CREATIVE:
    # Runners are set to creative every `time` seconds.
    time: 300
    # Runners can keep creative for `duration` seconds.
    duration: 5
    # ALlow flying while runners in creative?
    allow-fly: false

# ...

### Join Message ###
join-message:
  enabled: true
  noperm: '&aWelcome to this Manhunt server! The game will start shortly.'

# ...

### Language preferences ###
# Set this to one of the supported languages.
# Set this to 'custom' to generate a custom language file.
language: 'en_gb'

# ...
```

## Language
This plugin uses a configurable language system.
To use the prebundled languages, change the `language` key in the config to one of the following (in order of completeness):
* `en_GB` (English UK - default)
* `en_US` (English US)
* `pl_PL` (Polish) \[Incomplete\]
* `de_DE` (German) \[Incomplete\]
* `ko_KR` (Korean) \[Needs Proof-reading\]
* `sv_SE` (Swedish) \[Incomplete\]
* `fr_FR` (French) \[Incomplete\]
* `es_ES` (Spanish) \[Incomplete\]
* `it_IT` (Italian) \[Incomplete\]
* `ja_JP` (Japanese) \[Incomplete\]
* `nl_NL` (Dutch) \[Incomplete\]
* `ru_RU` (Russian) \[Incomplete\]
* `zh_CN` (Chinese Simplified) \[Incomplete\]

This list may be incomplete, so check the Crowdin page for more up-to-date info.

If you natively speak or are fluent in any of these languages then you can help us translate! Just go to this website and start translating: https://crowdin.com/project/minecraft-manhunt

If you want to be a proofreader, join the [Discord server](https://rb9.xyz/discord) then DM `radialbog9` on Discord!
