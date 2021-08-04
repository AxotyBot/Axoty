package dev.redcodes.axoty.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class ApiCommand {

	public static void onCommand(SlashCommandEvent e) {
		
		e.deferReply().queue();
		
		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("The API is currently");
		
	}
	
}
