package com.walaryne.flymod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.Collection;

import static net.minecraft.command.argument.EntityArgumentType.getPlayers;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public class FlyCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(literal("fly")
                .requires(source -> source.hasPermissionLevel(2))
                .then(argument("targets", EntityArgumentType.players())
                        .executes(ctx -> fly(ctx.getSource(), getPlayers(ctx, "targets"))))
                .executes(ctx -> fly(ctx.getSource(), null)));
    }

    private static int fly(ServerCommandSource source, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {

        if(targets == null) {
            handlePlayers(source.getPlayer(), source);
        } else {
            targets.forEach(target -> handlePlayers(target, source));
        }

        return 1;
    }

    private static void handlePlayers(ServerPlayerEntity player, ServerCommandSource source) {
        final Text name = player.getName();

        if (!player.getAbilities().allowFlying) {
            player.getAbilities().allowFlying = true;
            source.sendFeedback(new TranslatableText("commands.fly.successfulon", name), false);
        } else {
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            source.sendFeedback(new TranslatableText("commands.fly.successfuloff", name), false);
        }

        player.sendAbilitiesUpdate();
    }
}
