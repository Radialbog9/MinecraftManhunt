# Commands
All commands are listed below.

## All commands

| Command                                    | Description                                                     |
|--------------------------------------------|-----------------------------------------------------------------|
| `/manhunt help`                            | Shows other manhunt commands.                                   |
| `/manhunt hunter <player>`                 | Sets a player as hunter and remove them from runner.            |
| `/manhunt runner <player>`                 | Sets a player as runner and remove them from hunter.            |
| `/manhunt remove <player>`                 | Removes a specific player from the manhunt.                     |
| `/manhunt revive <player>`                 | Revives a runner if they're out.                                |
| `/manhunt start`                           | Starts the manhunt.                                             |
| `/manhunt stop`                            | Ends the manhunt prematurely.                                   |
| `/manhunt list`                            | Lists the players who are runners and hunters.                  |
| `/manhunt settings`                        | Fancy settings menu                                             |
| `/manhunt scenarios`                       | Fancy scenarios menu                                            |
| `/spectate <player>`                       | Teleports to a runner/hunter (only works if you're a spectator) |

## Permissions
| Permission       | Description                                        | Default     |
|------------------|----------------------------------------------------|-------------|
| &lt;none&gt;     | Allows player to run `/manhunt help`               | All Players |
| manhunt.add      | Allows player to add hunters/runners               | OP Players  |
| manhunt.remove   | Allows player to remove a player                   | OP Players  |
| manhunt.start    | Allows player to start the game                    | OP Players  |
| manhunt.stop     | Allows player to end the game forcibly             | OP Players  |
| manhunt.list     | Allows player to list hunters/runners              | All Players |
| manhunt.reset    | Allows player to reset the game                    | OP Players  |
| manhunt.spectate | Allows players to use `/spectate <player>`         | All Players |
| manhunt.admin    | Lets players recieve admin join message if enabled | OP Players  |
| manhunt.settings | Lets players change settings and scenarios         | OP Players  |
| manhunt.revive   | Allows player to revive dead hunters               | OP Players  |