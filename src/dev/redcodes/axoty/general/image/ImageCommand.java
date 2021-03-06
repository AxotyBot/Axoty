package dev.redcodes.axoty.general.image;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class ImageCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(ImageMessage.getEmbed().build()).queue();
		
		user.addImagesRequested();

	}

}
