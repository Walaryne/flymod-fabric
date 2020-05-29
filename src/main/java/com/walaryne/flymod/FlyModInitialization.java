package com.walaryne.flymod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;


public class FlyModInitialization implements ModInitializer {

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register(FlyCommand::register);
	}
}
