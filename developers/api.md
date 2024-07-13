# API

Javadocs for all classes are [here](https://ci.radialbog9.uk/job/MinecraftManhunt/javadoc).

## Game Management

Game management classes are included in the&#x20;

To start the game, call the `GameManager.startGame()` method.

To end the game, call the `GameManager.endGame(gameEndCause)` method with an end cause from the `GameEndCause` class.

## Access Settings and Configurable Game Vars

Through the following classes:

* `uk.radialbog9.spigot.manhunt.utils.ManhuntVars` - per-game variables
* `uk.radialbog9.spigot.manhunt.settings.ManhuntSettings` - configurable settings

## Events

In the `uk.radialbog9.spigot.manhunt.events`.

* `ManhuntGameStartEvent` called when the game starts.
* `ManhuntGameEndEvent` called when the game ends.

## Scenarios

Adding custom scenarios is quite easy. Create a package for your scenarios, then just create a class for your scenario. Annotate it with the `@Scenario` annotation. like in the examples below:

```java
@Scenario("MYPLUGIN_SOME_SCENARIO")
@ScenarioRunnable
public class SomeScenario extends BukkitRunnable implements ScenarioConfigurable {
    private static class Config extends ScenarioConfiguration implements RunnableRequiredConfig {
        private int time = 300;
        
        public int getTime() {
            // Or use Lombok's @Getter
            return time;
        }
    }
    
    private Config config = new Config();
    
    @Override
    public ScenarioConfiguration getConfig() {
        return this.config;
    }

    @Override
    public void setConfig(ScenarioConfiguration config) {
        this.config = (Config) config;
    }
    
    @Override
    public void run() {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            for (Player p : GameManager.getGame().getHunters()) {
                p.sendMessage("Scenario!");
            }
        }
    }
}
```

```java
@Scenario("MYPLUGIN_OTHER_SCENARIO")
@ScenarioListener
public class OtherScenario implements Listener {
    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        if(ScenarioUtils.isScenarioEnabled(this)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("No block placing for you!");
        }
    }
}
```

In the classes above, you'll also notice two other things.

* Each class is also annotated with either `@ScenarioRunnable` or `@ScenarioListener`. This tells Manhunt whether to register this as a runnable or as an event listener. Both can be used at once.
* Each run or event handler method also has an if statement with `ScenarioUtils.isScenarioEnabled(this)` in. This is a helper method to check if the game is started and if your scenario is enabled. While this isn't strictly required for runnables since they are cancelled when the game ends, it's still good practice to check and is required for listeners since they can't be unregistered.
* Config is registered as a subclass of your scenario class. If you have a configuration for your scenario (and you must have for runnable scenario), your scenario must also implement `ScenarioConfigurable`. It should extend `ScenarioConfiguration` and, if it is a runnable, implement `RunnableRequiredConfig`.

The scenario name in the annotation does not need to be the same as the class name. It is recommended to begin with your plugin's name as to not conflict with any other scenario. This is the scenario name which will be used in the config and for the language entry.

To register your scenarios, run the following method in your Plugin main class:

```java
Manhunt.getScenarioLoader().loadScenariosFromPackage(
                new File(YourPluginMain.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()),
                "me.yourname.yourplugin.scenarios");
```

To register language, create either a Map\<String, String> of your language keys and values, or load into a Properties class, then call the `Manhunt.getLanguage().loadLanguage()` method as below:

<pre class="language-java"><code class="lang-java"><strong>// From a properties file
</strong><strong>File customFile = new File(getDataFolder(), "language.properties");
</strong>if(!customFile.exists()) saveResource("language.properties", false);
<strong>Reader langReader = new FileReader(customFile);
</strong><strong>
</strong><strong>Properties customLanguage = new Properties();
</strong><strong>customLanguage.load(langReader);
</strong><strong>
</strong><strong>Manhunt.getLanguage().loadLanguage(customLanguage);
</strong><strong>
</strong><strong>// From a Map
</strong><strong>Map&#x3C;String, String> languageMap = new HashMap&#x3C;>();
</strong><strong>// TODO load language keys here
</strong>Manhunt.getLanguage().loadLanguage(languageMap);
</code></pre>
