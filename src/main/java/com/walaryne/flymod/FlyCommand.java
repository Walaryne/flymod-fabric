package com.walaryne.flymod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.CommandSource;

import static com.mojang.brigadier.arguments.StringArgumentType.getString; // getString(ctx, "string")
import static com.mojang.brigadier.arguments.StringArgumentType.word; // word(), string(), greedyString()
import static net.minecraft.server.command.CommandManager.argument; // argument("bar", word())
import static net.minecraft.server.command.CommandManager.*; // Import everything

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public class FlyCommand {
    public static LiteralCommandNode register(CommandDispatcher<CommandSource> command) {
        return dispatcher.register(literal("fly"));
    }
}
