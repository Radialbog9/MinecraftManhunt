# API

The plugin has a few classes which could be classed as an API. These are all over the place at the moment, but I might move them into their own API package soon.

Here are the methods/classes to use at the moment (subject to change):

```
// Methods
uk.radialbog9.spigot.manhunt.game.GameManager.startGame(); //Starts Game
uk.radialbog9.spigot.manhunt.game.GameManager.endGame(gameEndCause); //Stops game with specific end cause

// Classes
uk.radialbog9.spigot.manhunt.utils.ManhuntVars //Provides all per-game variables (i.e hunters, runners)
uk.radialbog9.spigot.manhunt.settings.ManhuntSettings //Provides most settings editable in the config file

// Events
uk.radialbog9.spigot.manhunt.events.ManhuntGameStartEvent
uk.radialbog9.spigot.manhunt.events.ManhuntGameEndEvent
```
