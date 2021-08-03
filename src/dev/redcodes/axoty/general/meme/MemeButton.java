package dev.redcodes.axoty.general.meme;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public class MemeButton {

	public static void onButtonClick(ButtonClickEvent e) {
		
		e.deferReply().queue();
		
		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(MemeMessage.getEmbed().build()).queue();
		
		user.addMemesRequested();
		
	}
	
}
