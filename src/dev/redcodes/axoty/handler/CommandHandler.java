package dev.redcodes.axoty.handler;

import dev.redcodes.axoty.general.AxolotlCommand;
import dev.redcodes.axoty.general.UserCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import dev.redcodes.axoty.general.fact.FactCommand;
import dev.redcodes.axoty.general.image.ImageCommand;
import dev.redcodes.axoty.general.meme.MemeCommand;
import dev.redcodes.axoty.suggestion.commands.SuggestCommand;
import dev.redcodes.axoty.util.InfoCommand;

public class CommandHandler extends ListenerAdapter {

	
	public void onSlashCommand(SlashCommandEvent e) {
		
		String[] args = e.getCommandPath().split("/");
		
		switch(args[0].toLowerCase()) {
		
		case "axolotl":
			new Thread(() -> {
				AxolotlCommand.onCommand(e);
			}).start();
			break;
			
		case "fact":
			new Thread(() -> {
				FactCommand.onCommand(e);
			}).start();
			break;
			
		case "image":
			new Thread(() -> {
				ImageCommand.onCommand(e);
			}).start();
			break;
			
		case "user":
			new Thread(() -> {
				UserCommand.onCommand(e);
			}).start();
			break;
			
		case "info":
			new Thread(() -> {
				InfoCommand.onCommand(e);
			}).start();
			break;
			
		case "suggest":
			new Thread(() -> {
				SuggestCommand.onCommand(e);
			}).start();
			break;
			
		case "meme":
			new Thread(() -> {
				MemeCommand.onCommand(e);
			}).start();
			break;
			
		case "video":
			new Thread(() -> {
				
			}).start();
			break;
		
		
		default:
			
			break;
		
		}
		
	}
	
}
