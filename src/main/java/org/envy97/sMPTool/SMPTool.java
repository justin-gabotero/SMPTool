package org.envy97.sMPTool;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SMPTool extends JavaPlugin {
    public static TradeHandler tradeHandler;

    @Override
    public void onEnable() {
        tradeHandler = new TradeHandler();
        Objects.requireNonNull(getCommand("trade")).setExecutor(new TradeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TradeHandler getTradeHandler() {
        return tradeHandler;
    }
}
