package dev.redcodes.axoty.general.gif;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public class GifButton {

	public static void onButtonClick(ButtonClickEvent e) {
		
		e.deferReply().queue();
		
		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(GifMessage.getEmbed().build()).queue();
		
		user.addGifsRequested();
		
	}
	
}
