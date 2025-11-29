package org.envy97.sMPTool;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TradeObject {
    private final Player sender;
    private final Player receiver;
    private final ItemStack tradeItem;

    public TradeObject(Player sender, Player receiver, ItemStack tradeItem) {
        this.sender = sender;
        this.receiver = receiver;
        this.tradeItem = tradeItem;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public ItemStack getTradeItem() {
        return tradeItem.clone();
    }
}
