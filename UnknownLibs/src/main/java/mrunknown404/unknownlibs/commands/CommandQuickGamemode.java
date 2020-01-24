package mrunknown404.unknownlibs.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.GameType;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public class CommandQuickGamemode extends CommandBase {
	private final GameType gameType;
	
	public CommandQuickGamemode(GameType gameType) {
		this.gameType = gameType;
	}
	
	@Override
	public String getName() {
		return gameType == GameType.SURVIVAL ? "gms" :
				gameType == GameType.CREATIVE ? "gmc" : gameType == GameType.ADVENTURE ? "gma" : gameType == GameType.SPECTATOR ? "gmsp" : "null";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/" + getName() + " [name (optional)]";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer entityplayer;
		if (args.length == 1) {
			entityplayer = getPlayer(server, sender, args[0]);
		} else if (args.length == 0) {
			entityplayer = getCommandSenderAsPlayer(sender);
		} else {
			throw new WrongUsageException("commands.gamemode.usage", new Object[0]);
		}
		
		entityplayer.setGameType(gameType);
		ITextComponent itextcomponent = new TextComponentTranslation("gameMode." + gameType.getName());
		
		if (sender.getEntityWorld().getGameRules().getBoolean("sendCommandFeedback")) {
			entityplayer.sendMessage(new TextComponentTranslation("gameMode.changed", new Object[] { itextcomponent }));
		}
		
		if (entityplayer == sender) {
			notifyCommandListener(sender, this, 1, "commands.gamemode.success.self", new Object[] { itextcomponent });
		} else {
			notifyCommandListener(sender, this, 1, "commands.gamemode.success.other", new Object[] { entityplayer.getName(), itextcomponent });
		}
	}
}
