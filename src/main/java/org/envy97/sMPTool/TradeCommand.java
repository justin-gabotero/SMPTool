package org.envy97.sMPTool;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static org.envy97.sMPTool.SMPTool.getTradeHandler;

public class TradeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String @NotNull [] args) {
        TradeHandler tradeHandler = getTradeHandler();

        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use this command!");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("accept")) {
            handleAccept();
            return true;
        }


        if (args.length != 2) {
            sender.sendMessage("Usage: /trade <player> <count>");
            return true;
        }

        if (args[0].equalsIgnoreCase("get")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("Player not found!");
                return true;
            }
            TradeObject trade = tradeHandler.getRequest(target);
            if (trade == null) {
                sender.sendMessage("Player has no trades active!");
            } else {
                sender.sendMessage(trade.getTradeItem().displayName());
            }
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

        if (tradeHandler.getRequest(target) != null) {
            // TODO: expiring trades after n-amount of seconds.
            TextComponent tradeMessage = Component.text()
                    .append(target.displayName())
                    .append(Component.text(" already has a trade request active!"))
                    .color(NamedTextColor.RED)
                    .build();
            sender.sendMessage(tradeMessage);
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

        TradeObject trade = new TradeObject((Player) sender, target, tradeItem);
        tradeHandler.tradeRequest((Player) sender, target, trade);

        TextComponent tradeMessage = Component.text()
                .append(((Player) sender).displayName().color(NamedTextColor.YELLOW))
                .append(Component.text(" wants to send you a trade!",
                        NamedTextColor.YELLOW))
                .build();
        target.sendMessage(tradeMessage);

        TextComponent confirmMessage = Component.text()
                .content("Sent a trade to ")
                .append(target.displayName())
                .color(NamedTextColor.YELLOW)
                .build();
        sender.sendMessage(confirmMessage);

        return true;
    }

    private void handleAccept() {

    }
}
