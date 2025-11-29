package org.envy97.sMPTool;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TradeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String @NotNull [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use this command!");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("accept")) {

            return true;
        }

        if (args.length != 2) {
            sender.sendMessage("Usage: /trade <player> <count>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("Player not found!");
            return true;
        }

        if (target.equals(sender)) {
            sender.sendMessage("You cannot trade with yourself.");
            return true;
        }

        int itemCount;
        try {
            itemCount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid item count!");
            return true;
        }

        ItemStack hand = ((Player) sender).getInventory().getItemInMainHand();
        if (hand.isEmpty()) {
            sender.sendMessage("Please hold an item to trade!");
        }

        if (hand.getAmount() < itemCount) {
            sender.sendMessage("You do not have enough items!");
            return true;
        }

        ItemStack tradeItem = hand.clone();
        tradeItem.setAmount(itemCount);

        // handle trade accept

        return false;
    }
}
