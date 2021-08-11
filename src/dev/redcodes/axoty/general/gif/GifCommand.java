package dev.redcodes.axoty.general.gif;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class GifCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(GifMessage.getEmbed().build()).queue();
		
		user.addGifsRequested();

	}

}
