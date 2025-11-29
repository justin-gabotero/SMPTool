package org.envy97.sMPTool;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class TradeHandler {
    private final HashMap<UUID, TradeObject> tradeOffers = new HashMap<>();

    public void tradeRequest(Player sender, Player receiver, TradeObject trade) {
        tradeOffers.put(receiver.getUniqueId(), trade);
    }

    public TradeObject getRequest(Player receiver) {
        return tradeOffers.get(receiver.getUniqueId());
    }


}
