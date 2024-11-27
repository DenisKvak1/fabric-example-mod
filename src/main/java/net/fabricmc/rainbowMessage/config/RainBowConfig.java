package net.fabricmc.rainbowMessage.config;

import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.data.Config;
import me.lortseam.completeconfig.data.ConfigOptions;

public class RainBowConfig extends Config {
    public RainBowConfig(String modId) {
        super(ConfigOptions.mod(modId));
    }

    @ConfigEntry
    public String rainbowPattern = "";
    @ConfigEntry
    public boolean isActive = true;
}
