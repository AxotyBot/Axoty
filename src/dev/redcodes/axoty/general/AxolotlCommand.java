package dev.redcodes.axoty.general;

import dev.redcodes.axoty.data.users.AxotyUser;
import dev.redcodes.axoty.general.fact.FactMessage;
import dev.redcodes.axoty.general.image.ImageMessage;
import dev.redcodes.axoty.general.meme.MemeMessage;
import dev.redcodes.axoty.general.video.VideoMessage;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class AxolotlCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxotyUser user = new AxotyUser(e.getUser());

		if (e.getOption("type").getAsString().equalsIgnoreCase("img")) {

			e.getHook().editOriginal(ImageMessage.getEmbed().build()).queue();

			user.addImagesRequested();

		} else if (e.getOption("type").getAsString().equalsIgnoreCase("fact")) {

			e.getHook().editOriginal(FactMessage.getEmbed().build()).queue();

			user.addFactsRequested();

		} else if (e.getOption("type").getAsString().equalsIgnoreCase("meme")) {

			e.getHook().editOriginal(MemeMessage.getEmbed().build()).queue();

			user.addMemesRequested();

		} else if (e.getOption("type").getAsString().equalsIgnoreCase("vid")) {

			e.getHook().editOriginal(VideoMessage.getEmbed().build()).queue();

			user.addVideosRequested();
		}

	}

}
