package mrunknown404.unknownlibs;

import mrunknown404.unknownlibs.commands.CommandQuickGamemode;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MOD_ID, useMetadata = true, acceptableRemoteVersions = "*")
public class Main {
	public static final String MOD_ID = "unknownlibs";
	
	@Instance
	public static Main main;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println("Loaded UnknownLibs Version: " + e.getModMetadata().version);
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent e) {
		e.registerServerCommand(new CommandQuickGamemode(GameType.SURVIVAL));
		e.registerServerCommand(new CommandQuickGamemode(GameType.CREATIVE));
		e.registerServerCommand(new CommandQuickGamemode(GameType.ADVENTURE));
		e.registerServerCommand(new CommandQuickGamemode(GameType.SPECTATOR));
	}
}
