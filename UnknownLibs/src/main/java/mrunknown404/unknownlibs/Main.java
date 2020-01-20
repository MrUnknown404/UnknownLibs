package mrunknown404.unknownlibs;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MOD_ID, useMetadata = true, acceptableRemoteVersions = "*")
public class Main {
	public static final String MOD_ID = "unknownlibs";
	
	@Instance
	public static Main main;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println("Loaded UnknownLibs Version: " + e.getModMetadata().version);
	}
}
