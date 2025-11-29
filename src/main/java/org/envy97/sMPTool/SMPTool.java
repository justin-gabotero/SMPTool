package org.envy97.sMPTool;

import org.bukkit.plugin.java.JavaPlugin;

public final class SMPTool extends JavaPlugin {

    @Override
    public void onEnable() {
       getCommand("trade").setExecutor(new TradeCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
