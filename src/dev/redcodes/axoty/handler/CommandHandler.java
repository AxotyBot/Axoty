package dev.redcodes.axoty.handler;

import dev.redcodes.axoty.general.AxolotlCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import dev.redcodes.axoty.general.fact.FactCommand;
import dev.redcodes.axoty.general.image.ImageCommand;

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
		
		
		default:
			
			break;
		
		}
		
	}
	
}
