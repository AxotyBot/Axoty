package dev.redcodes.axoty.general.video;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public class VideoButton {

	public static void onButtonClick(ButtonClickEvent e) {
		
		e.deferReply().queue();
		
		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(VideoMessage.getEmbed().build()).queue();
		
		user.addVideosRequested();
		
	}
	
}
