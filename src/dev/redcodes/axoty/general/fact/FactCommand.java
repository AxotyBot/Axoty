package dev.redcodes.axoty.general.fact;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class FactCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxotyUser user = new AxotyUser(e.getUser());
		
		e.getHook().editOriginal(FactMessage.getEmbed().build()).queue();
		
		user.addFactsRequested();

	}

}
