package uk.radialbog9.spigot.manhunt.kits;

import lombok.Getter;

import java.util.HashMap;

public class KitProvider {
    @Getter
    private HashMap<String, Kit> hunterKits;

    @Getter
    private HashMap<String, Kit> runnerKits;

    public KitProvider() {

    }
}
