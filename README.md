# MINECRAFT MANHUNT


[![Jenkins build status](https://ci.radialbog9.uk/job/MinecraftManhunt/badge/icon?style=flat-square)](https://ci.radialbog9.uk/job/MinecraftManhunt/)
[![Donate](https://img.shields.io/badge/donate-Buy%20Me%20A%20Coffee-orange?style=flat-square&logo=buymeacoffee)](https://buymeacoff.ee/Radialbog9)
[![Subscribe](https://img.shields.io/badge/subscribe-YouTube-orange?style=flat-square&logo=youtube)](https://rb9.xyz/sub)
[![Discord](https://img.shields.io/discord/450232632798740480?style=flat-square&color=orange&logo=discord)](https://rb9.xyz/discord)

![bStats Players](https://img.shields.io/bstats/players/9573?style=for-the-badge&color=yellow)
![bStats Servers](https://img.shields.io/bstats/servers/9573?style=for-the-badge&color=yellow)
![SpigotMC tested server versions](https://img.shields.io/spiget/tested-versions/97765?color=yellow&style=for-the-badge)

![LGTM Grade](https://img.shields.io/lgtm/grade/java/github/Radialbog9/MinecraftManhunt?logo=lgtm&style=for-the-badge)
![LGTM Alerts](https://img.shields.io/lgtm/alerts/github/Radialbog9/MinecraftManhunt?logo=lgtm&style=for-the-badge)

[![GitHub license](https://img.shields.io/github/license/Radialbog9/MinecraftManhunt?color=blue&logo=github&style=for-the-badge)](https://github.com/Radialbog9/MinecraftManhunt/blob/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)](https://github.com/Radialbog9/MinecraftManhunt/issues)
[![GitHub forks](https://img.shields.io/github/forks/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)](https://github.com/Radialbog9/MinecraftManhunt/network)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)
![GitHub all releases](https://img.shields.io/github/downloads/Radialbog9/MinecraftManhunt/total?style=for-the-badge&color=blue&logo=github)
![GitHub last commit](https://img.shields.io/github/last-commit/Radialbog9/MinecraftManhunt?color=blue&style=for-the-badge&logo=github)
![GitHub repo size](https://img.shields.io/github/repo-size/Radialbog9/MinecraftManhunt?style=for-the-badge&color=blue&logo=github)
![GitHub top language](https://img.shields.io/github/languages/top/Radialbog9/MinecraftManhunt?color=blue&logo=github&style=for-the-badge)
![Lines of code](https://img.shields.io/tokei/lines/github/Radialbog9/MinecraftManhunt?color=blue&logo=github&style=for-the-badge)

---

A unique take on Dream's manhunt game with many features, built from scratch.

All credit for the idea goes to Dream. 
His Manhunt videos are awesome, and you should [check him out](https://www.youtube.com/Dream). 

---

![bStats](https://bstats.org/signatures/bukkit/MinecraftManhunt.svg)

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
For the rules, check the [rulebook](https://radialbog9.github.io/MinecraftManhunt/rulebook) (obviously you can make up your own rules if these don't fit your taste).

## Features
* Multiple hunters/runners support
* SuperVanish/PremiumVanish support
* "Dream mode" - increases drop rates on blaze/endermen and ender pearl barter rates
* Spectator support
* Automatic removal of player from game if they disconnect
* Settings menu

## Dependencies
This plugin does not have any dependencies, but it works with SuperVanish or PremiumVanish to hide any spectators who are in vanish from the Manhunt player list.

## Contributing
Please report any bugs you find in a GitHub Issue or improve our code and make a pull request! 
We're always open to suggestions!

## API
This plugin has a (somewhat simple and somewhat terrible) API for developers to use. 
You can start and stop the game and access the list of runners and hunters.
See [this page](https://radialbog9.github.io/api.md) for more info.

## bStats
We use bStats to track how people use our plugin.
> If you don't want bStats to collect data from your server, you can disable it in the bStats config file. This file can be found in the `/plugins/bStats/` folder.

---
## Config
The config file is well documented with comments. Just look at the `config.yml` file in the plugin's folder on your server.

Below is parts of the default config. More documentation about the config can be found [here](https://radialbog9.github.io/MinecraftManhunt/config).
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
  HUNTER_CREATIVE:
    # Hunters are set to creative every `time` seconds.
    time: 300
    # Hunters can keep creative for `duration` seconds.
    duration: 5
    # ALlow flying while hunters in creative?
    allow-fly: false

# ...
```

## Language
This plugin uses a slightly crappy language system. 
You can change the language, using `{0}`, `{1}`, etc to signify placeholders. 

Here's an example. Be aware you may have to delete the language file to let it regenerate after large updates if you experience errors. 

```yaml
# General language
no-permission: '&6[Manhunt]&r&a You do not have permission to do this!'
player-not-online: '&6[Manhunt]&r&a The player&r&c {0} &r&ais not online!'
unknown-subcommand: '&6[Manhunt]&r&a Unknown subcommand!'
not-enough-args: '&6[Manhunt]&r&a Not enough arguments!'

# ...

help:
  help: 'Shows help'
  hunter: 'Sets a player to hunter'
  runner: 'Sets a player to runner'

# ...

# Settings menu
settingsmenu:
  title: '&7---- &6Manhunt Settings Menu &7----'
  enabled: '&a✓'
  disabled: '&c✕'
  click-to-toggle: '&6Click to toggle!'
  click-to-change: '&6Click to change!'
  click-to-add: '&6Click to add!'
  click-to-start: '&6Click to start!'
  options:
    head-start: '&7[ &r{0} &r&7] &eHead start'

# ...

# Scenarios
scenariomenu:
  title: '&7---- &6Manhunt Scenario Menu &7----'
  enabled: '&a✓'
  disabled: '&c✕'
# ...
  display-format: '&7[&r{0}&r&7] &a{1}'
  not-available:
    generic-load-error: '&4Not available: &cLoad error'
scenario:
  RUNNER_NO_FALL: 'Runners take no fall damage'
  HUNTER_NO_FALL: 'Hunters take no fall damage'
# ...
```

The full language file can be found here.