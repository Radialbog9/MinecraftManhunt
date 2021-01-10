# Config
__Note:__ When updating the plugin to a newer version, you may need to regenerate the config by deleting your current config file.

Here is the default configuration file:
```yaml
######################################################################################################
#   ▄▄       ▄▄   ▄▄▄▄▄▄▄▄▄▄▄   ▄▄        ▄   ▄         ▄   ▄         ▄   ▄▄        ▄   ▄▄▄▄▄▄▄▄▄▄▄  #
#  ▐░░▌     ▐░░▌ ▐░░░░░░░░░░░▌ ▐░░▌      ▐░▌ ▐░▌       ▐░▌ ▐░▌       ▐░▌ ▐░░▌      ▐░▌ ▐░░░░░░░░░░░▌ #
#  ▐░▌░▌   ▐░▐░▌ ▐░█▀▀▀▀▀▀▀█░▌ ▐░▌░▌     ▐░▌ ▐░▌       ▐░▌ ▐░▌       ▐░▌ ▐░▌░▌     ▐░▌  ▀▀▀▀█░█▀▀▀▀  #
#  ▐░▌▐░▌ ▐░▌▐░▌ ▐░▌       ▐░▌ ▐░▌▐░▌    ▐░▌ ▐░▌       ▐░▌ ▐░▌       ▐░▌ ▐░▌▐░▌    ▐░▌      ▐░▌      #
#  ▐░▌ ▐░▐░▌ ▐░▌ ▐░█▄▄▄▄▄▄▄█░▌ ▐░▌ ▐░▌   ▐░▌ ▐░█▄▄▄▄▄▄▄█░▌ ▐░▌       ▐░▌ ▐░▌ ▐░▌   ▐░▌      ▐░▌      #
#  ▐░▌  ▐░▌  ▐░▌ ▐░░░░░░░░░░░▌ ▐░▌  ▐░▌  ▐░▌ ▐░░░░░░░░░░░▌ ▐░▌       ▐░▌ ▐░▌  ▐░▌  ▐░▌      ▐░▌      #
#  ▐░▌   ▀   ▐░▌ ▐░█▀▀▀▀▀▀▀█░▌ ▐░▌   ▐░▌ ▐░▌ ▐░█▀▀▀▀▀▀▀█░▌ ▐░▌       ▐░▌ ▐░▌   ▐░▌ ▐░▌      ▐░▌      #
#  ▐░▌       ▐░▌ ▐░▌       ▐░▌ ▐░▌    ▐░▌▐░▌ ▐░▌       ▐░▌ ▐░▌       ▐░▌ ▐░▌    ▐░▌▐░▌      ▐░▌      #
#  ▐░▌       ▐░▌ ▐░▌       ▐░▌ ▐░▌     ▐░▐░▌ ▐░▌       ▐░▌ ▐░█▄▄▄▄▄▄▄█░▌ ▐░▌     ▐░▐░▌      ▐░▌      #
#  ▐░▌       ▐░▌ ▐░▌       ▐░▌ ▐░▌      ▐░░▌ ▐░▌       ▐░▌ ▐░░░░░░░░░░░▌ ▐░▌      ▐░░▌      ▐░▌      #
#   ▀         ▀   ▀         ▀   ▀        ▀▀   ▀         ▀   ▀▀▀▀▀▀▀▀▀▀▀   ▀        ▀▀        ▀       #
######################################################################################################
#                               Minecraft Manhunt by Radialbog9                                      #
######################################################################################################

# Gives runner(s) a head start by giving hunters blindness, slowness, and weakness
# Set to false for a more authentic manhunt.
head-start:
  # Head start enabled?
  enabled: true
  # Length of the head start (in seconds)
  length: 30

# Language
# Some entries have required arguments (such as player names) which are indicated by '%s'.
# The arguments have to be in the order they were set.
language:
  no-command-specified: '&6[Manhunt]&r&a No command specified. Type /manhunt help for command help.'
  no-permission: '&6[Manhunt]&r&a You do not have permission to do this!'
  player-not-online: '&6[Manhunt]&r&a The player&r&c %s &r&ais not online!'
  unknown-subcommand: '&6[Manhunt]&r&a Unknown subcommand!'
  console-cannot-spectate: '&6[Manhunt]&r&a Console cannot spectate players!'
  not-spectator: '&6[Manhunt]&r&a You are not a spectator!'
  no-subcommand-perm: '&a- You do not have permission for any sub-commands.'
  usage: '&6[Manhunt]&r&a Usage:&c %s'
  not-enough-args: '&6[Manhunt]&r&a Not enough arguments!'
  too-many-args: '&6[Manhunt]&r&a Too many arguments!'
  no-change-hunter-in-game: '&6[Manhunt]&r&a You can not change hunters while the game is ongoing.'
  no-change-runner-in-game: '&6[Manhunt]&r&a You can not change runners while the game is ongoing.'
  now-hunter: '&6[Manhunt]&r&a You are now a hunter!'
  now-runner: '&6[Manhunt]&r&a You are now a runner!'
  now-spectator: '&6[Manhunt]&r&a You are now a spectator!'
  no-more-runners-left: '&6[Manhunt]&r&a There are no more runners on the server! Hunters win!'
  all-runners-dead: '&6[Manhunt]&r&a There are no more runners left alive! Hunters win!'
  no-more-hunters-left: '&6[Manhunt]&r&a There are no more hunters on the server! Runners win!'
  runners-kill-ender-dragon: '&6[Manhunt]&r&a The runners have killed the Ender Dragon! Runners Win!'
  time-up: '&6[Manhunt]&r&a The runner has not been killed in time! Hunters win!'
  game-ended-prematurely: '&6[Manhunt]&r&a The game has been stopped!'
  game-started: '&6[Manhunt]&r&a The game has started!'
  game-ended: '&6[Manhunt]&r&a The game has ended!'
  no-players-to-track: '&6[Manhunt]&r&a &cNo players found to track.'
  no-game-in-progress: '&6[Manhunt]&r&a There is no game in progress.'
  game-already-in-progress: '&6[Manhunt]&r&a A game is already in progress!'
  now-spectating-player: '&6[Manhunt]&r&a You are now spectating&c %s&r&a.'
  too-few-players: '&6[Manhunt]&r&a The game must have at least 1 hunter and 1 runner!'
```
