# Config
__Note:__ When updating the plugin to a newer version, you may need to regenerate the config by deleting your current config file, as some things may not work.

Here is the default configuration file (`plugins/Manhunt/config.yml`):
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

### Settings ###
# These are the default settings, most of which will be changable in the upcoming settings menu

# Gives runner(s) a head start by giving hunter(s) blindness, slowness, and weakness
# Set to false in here or in the settings menu for a more authentic manhunt.
head-start:
  # Head start enabled?
  enabled: true
  # Length of the head start (in seconds)
  length: 30

# Kits (incomplete feature; doesn't work yet)
kits:
  defaultkits:
    runner: default
    hunter: default
  runners:
    default:
      items:
        '1':
          material: "POISONOUS_POTATO"
          quantity: 1
  hunters:
    default:
      items:
        '1':
          material: "COMPASS"
          quantity: 1

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
  HUNTER_RANDOM_MOB_DISGUISE:
    # Hunters are disguised as mobs every `time` seconds
    time: 300
  RUNNER_RANDOM_MOB_DISGUISE:
    # Runners are disguised as mobs every `time` seconds
    time: 300
  RUNNER_RANDOM_HUNTER_DISGUISE:
    # Runners are disguised as hunters every `time` seconds
    time: 300


### Join Message ###
join-message:
  enabled: true
  noperm: '&aWelcome to this Manhunt server! The game will start shortly.'
  perm: '&aWelcome! Use /manhunt runner and /manhunt hunter to add players and /manhunt settings (beta) to change settings and start the game!'

### Language ###  THIS HAS BEEN MOVED TO lang.yml!
# Some entries have required arguments (such as player names) which are indicated by '%s'.
# The arguments have to be in the order they were set in the default language.
language:
  # General language
  no-permission: '&6[Manhunt]&r&a You do not have permission to do this!'
  player-not-online: '&6[Manhunt]&r&a The player&r&c %s &r&ais not online!'
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
  usage: '&6[Manhunt]&r&a Usage:&c %s'
  # Hunters
  p-now-hunter: '&6[Manhunt]&r&c %s &r&ais now a hunter!'
  p-already-hunter: '&6[Manhunt]&r&c %s &r&ais already a hunter!'
  now-hunter: '&6[Manhunt]&r&a You are now a hunter!'
  no-change-hunter-in-game: '&6[Manhunt]&r&a You can not change hunters while the game is ongoing.'
  hunter-disconnected: '&6[Manhunt]&r&c %s &r&ahas disconnected. They are no longer a hunter.'
  # Hunter compass
  tracking-player: '&6[Manhunt]&r&a Tracking player&r&c %s&r&a.'
  no-players-to-track: '&6[Manhunt]&r&a &cNo players found to track.'
  # Runners
  p-now-runner: '&6[Manhunt]&r&c %s &r&ais now a runner!'
  p-already-runner: '&6[Manhunt]&r&c %s &r&ais already a runner!'
  now-runner: '&6[Manhunt]&r&a You are now a runner!'
  no-change-runner-in-game: '&6[Manhunt]&r&a You can not change runners while the game is ongoing.'
  runner-died: '&6[Manhunt]&r&c %s &r&adied. There are now&r&c %s &r&arunners remaining.'
  runner-disconnected: '&6[Manhunt]&r&c %s &r&ahas disconnected. They are no longer a runner.'
  # Remove players/add spectator
  p-now-spectator: '&6[Manhunt]&r&c %s &r&ais now a spectator!'
  p-already-spectator: '&6[Manhunt]&r&c %s &r&ais already a spectator!'
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
  list-count: '&6[Manhunt]&r&a There are&r&c %s &r&ahunters,&r&c %s &r&arunners, and&r&c %s &r&aspectators.'
  hunter-list: '&6[Manhunt]&r&a Hunters: %s'
  runner-list: '&6[Manhunt]&r&a Runners: %s'
  spectator-list: '&6[Manhunt]&r&a Spectators: %s'
  # - The two variables below at default values would give something like "&r&cNotch&r&a, &r&cjeb_&r&a" in the manhunt player list
  # - If you change display names with Essentials or another plugin they will show up here instead of the player names
  list-name-prefix: '&r&c'
  list-name-suffix: '&r&a'
  # Spectate command
  console-cannot-spectate: '&6[Manhunt]&r&a Console cannot spectate players!'
  not-spectator: '&6[Manhunt]&r&a You are not a spectator!'
  now-spectating-player: '&6[Manhunt]&r&a You are now spectating&c %s&r&a.'
  # Settings
  head-start-enabled: '&6[Manhunt]&r&a Head Start is now enabled!'
  head-start-disabled: '&6[Manhunt]&r&a Head Start is now disabled!'
  head-start-timer-set: '&6[Manhunt]&r&a Head Start Timer has been set to&c %s &r&aseconds.'
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
      head-start: '&7[ &r%s &r&7] &eHead start'
      head-start-timer: '&7[ &r&a%s seconds &r&7] &eHead start timer'
      add-runner: '&eAdd Runner'
      add-hunter: '&eAdd Hunter'
      scenarios: '&eEdit scenarios (beta)'
      start-game: '&e&lStart game'
```
