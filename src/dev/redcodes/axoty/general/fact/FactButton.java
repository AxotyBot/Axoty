package dev.redcodes.axoty.general.fact;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public class FactButton {

	public static void onButtonClick(ButtonClickEvent e) {

		e.deferReply().queue();
		
		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(FactMessage.getEmbed().build()).queue();
		
		user.addFactsRequested();

	}
	
}
