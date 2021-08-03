package dev.redcodes.axoty.general.image;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public class ImageButton {

	public static void onButtonClick(ButtonClickEvent e) {
		
		e.deferReply().queue();
		
		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(ImageMessage.getEmbed().build()).queue();
		
		user.addImagesRequested();
		
	}
	
}
