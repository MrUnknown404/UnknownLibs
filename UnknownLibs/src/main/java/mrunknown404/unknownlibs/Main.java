package mrunknown404.unknownlibs;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MOD_ID)
public class Main {
	public static final String MOD_ID = "unknownlibs";
	
	public Main() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
	}
	
	private void commonSetup(@SuppressWarnings("unused") FMLCommonSetupEvent e) {
		System.out.println("Loaded UnknownLibs Version: 1.0.0");
	}
}
