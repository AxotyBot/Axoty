package dev.redcodes.axoty.general.meme;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class MemeCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(MemeMessage.getEmbed().build()).queue();
		
		user.addMemesRequested();

	}

}