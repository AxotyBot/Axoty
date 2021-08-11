package dev.redcodes.axoty.general.video;

import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class VideoCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxotyUser user = new AxotyUser(e.getUser());

		e.getHook().editOriginal(VideoMessage.getEmbed().build()).queue();
		
		user.addVideosRequested();

	}

}