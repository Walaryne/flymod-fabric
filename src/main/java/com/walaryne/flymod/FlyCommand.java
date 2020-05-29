package com.walaryne.flymod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;


public class FlyCommand {
    public static LiteralCommandNode<ServerCommandSource> register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        return dispatcher.register(literal("fly")
                .executes(FlyCommand::fly));
    }

    private static int fly(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        final ServerCommandSource source = ctx.getSource();

        final PlayerEntity player = source.getPlayer();

        if (!player.abilities.allowFlying) {
            player.abilities.allowFlying = true;
            //toggleString = "commands.fly.successfulon";
        } else {
            player.abilities.allowFlying = false;
            player.abilities.flying = false;
            //toggleString = "commands.fly.successfuloff";
        }

        player.sendAbilitiesUpdate();

        return 1;
    }
}
