__Note:__ When updating the plugin to a newer version, you may need to regenerate the config by deleting your current language file, as some language may not be displayed properly.

Here is the default language file (`plugins/Manhunt/lang.yml`):
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

### Language ###
# Some entries have required arguments (such as player names) which are indicated by {0}, {1}, etc.

# General language
no-permission: '&6[Manhunt]&r&a You do not have permission to do this!'
player-not-online: '&6[Manhunt]&r&a The player&r&c {0} &r&ais not online!'
unknown-subcommand: '&6[Manhunt]&r&a Unknown subcommand!'
not-enough-args: '&6[Manhunt]&r&a Not enough arguments!'
too-many-args: '&6[Manhunt]&r&a Too many arguments!'
invalid-integer: '&6[Manhunt]&r&a You have typed an invalid integer!'
no-game-in-progress: '&6[Manhunt]&r&a There is no game in progress.'
join-in-progress: '&6[Manhunt]&r&a A game is in progress so you have been put into spectator!'
# Main command
no-command-specified: '&6[Manhunt]&r&a No command specified. Type /manhunt help for command help.'
# Help and usage
no-subcommand-perm: '&a- You do not have permission for any sub-commands.'
usage: '&6[Manhunt]&r&a Usage: &c{0}'
command-help: '&6[Manhunt]&r&a Command Help:'
player-placeholder: '<player>'
help-format: '&a- &c{0} &r&a- &r&e{1}' # &a- &c/manhunt help &r&a- &r&eShows help
help:
  help: 'Shows help'
  hunter: 'Sets a player to hunter'
  runner: 'Sets a player to runner'
  remove: 'Removes a player from hunter and runner'
  start: 'Starts the manhunt'
  stop: 'Stops the game'
  list: 'List all runners and hunters'
  revive: 'Revives a specified runner'
  spectate: 'Spectates players'
# Hunters
p-now-hunter: '&6[Manhunt]&r&c {0} &r&ais now a hunter!'
p-already-hunter: '&6[Manhunt]&r&c {0} &r&ais already a hunter!'
now-hunter: '&6[Manhunt]&r&a You are now a hunter!'
no-change-hunter-in-game: '&6[Manhunt]&r&a You can not change hunters while the game is ongoing.'
hunter-disconnected: '&6[Manhunt]&r&c %s &r&ahas disconnected. They are no longer a hunter.'
# Hunter compass
tracking-player: '&6[Manhunt]&r&a Tracking player &r&c{0}&r&a (&r&c{1} &r&ablocks away).'
no-players-to-track: '&6[Manhunt]&r&a &cNo players found to track.'
# Runners
p-now-runner: '&6[Manhunt]&r&c {0} &r&ais now a runner!'
p-already-runner: '&6[Manhunt]&r&c {0} &r&ais already a runner!'
now-runner: '&6[Manhunt]&r&a You are now a runner!'
no-change-runner-in-game: '&6[Manhunt]&r&a You can not change runners while the game is ongoing.'
runner-died: '&6[Manhunt]&r&c %s &r&adied. There are now&r&c {0} &r&arunners remaining.'
runner-disconnected: '&6[Manhunt]&r&c %s &r&ahas disconnected. They are no longer a runner.'
# Remove players/add spectator
p-now-spectator: '&6[Manhunt]&r&c {0} &r&ais now a spectator!'
p-already-spectator: '&6[Manhunt]&r&c {0} &r&ais already a spectator!'
no-remove-in-game: '&6[Manhunt]&r&a You can not remove players while the game is ongoing.'
now-spectator: '&6[Manhunt]&r&a You are now a spectator!'
# Start and stop game
game-started: '&6[Manhunt]&r&a The game has started!'
too-few-players: '&6[Manhunt]&r&a The game must have at least 1 hunter and 1 runner!'
game-already-in-progress: '&6[Manhunt]&r&a A game is already in progress!'
game-ended: '&6[Manhunt]&r&a The game has ended!'
game-ended-prematurely: '&6[Manhunt]&r&a The game has been stopped!'
# Game end causes
no-more-runners-left: '&6[Manhunt]&r&a There are no more runners on the server! Hunters win!'
all-runners-dead: '&6[Manhunt]&r&a There are no more runners left alive! Hunters win!'
no-more-hunters-left: '&6[Manhunt]&r&a There are no more hunters on the server! Runners win!'
runners-kill-ender-dragon: '&6[Manhunt]&r&a The runners have killed the Ender Dragon! Runners Win!'
time-up: '&6[Manhunt]&r&a The runner has not been killed in time! Hunters win!'
# Manhunt player list
game-is-started: '&6[Manhunt]&r&a The game is &r&cstarted&r&a.'
game-is-stopped: '&6[Manhunt]&r&a The game is &r&cstopped&r&a.'
list-count: '&6[Manhunt]&r&a There are&r&c {0} &r&ahunters,&r&c {1} &r&arunners, and&r&c {2} &r&aspectators.'
hunter-list: '&6[Manhunt]&r&a Hunters: {0}'
runner-list: '&6[Manhunt]&r&a Runners: {1}'
spectator-list: '&6[Manhunt]&r&a Spectators: {2}'
# Spectate command
console-cannot-spectate: '&6[Manhunt]&r&a Console cannot spectate players!'
not-spectator: '&6[Manhunt]&r&a You are not a spectator!'
now-spectating-player: '&6[Manhunt]&r&a You are now spectating&c {0}&r&a.'
# Settings
head-start-enabled: '&6[Manhunt]&r&a Head Start is now enabled!'
head-start-disabled: '&6[Manhunt]&r&a Head Start is now disabled!'
head-start-timer-set: '&6[Manhunt]&r&a Head Start Timer has been set to &c{0} &r&aseconds.'
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
    head-start-timer: '&7[ &r&a{0} seconds &r&7] &eHead start timer'
    add-runner: '&eAdd Runner'
    add-hunter: '&eAdd Hunter'
    scenarios: '&eEdit scenarios (beta)'
    start-game: '&e&lStart game'
# Scenarios
scenariomenu:
  title: '&7---- &6Manhunt Scenario Menu &7----'
  enabled: '&a✓'
  disabled: '&c✕'
  unavailable: '&8✕'
  click-to-enable: '&6Click to &aenable&6!'
  click-to-disable: '&6Click to &cdisable&6!'
  display-format: '&7[&r{0}&r&7] &a{1}' # example with default value: [✓] Runners take no fall damage
  not-available:
    generic-load-error: '&4Not available: &cLoad error'
scenario:
  RUNNER_NO_FALL: 'Runners take no fall damage'
  HUNTER_NO_FALL: 'Hunters take no fall damage'
  RUNNER_CREATIVE: 'Runners get creative mode for 5s every 200s'
  HUNTER_CREATIVE: 'Hunters get creative mode for 5s every 200s'
  RUNNER_RANDOM_MOB_DISGUISE: 'Runners get disguised as a random mob'
  HUNTER_RANDOM_MOB_DISGUISE: 'Hunters get disguised as a random mob'
  RUNNER_RANDOM_HUNTER_DISGUISE: 'Runners get disguised as a random hunter'
```