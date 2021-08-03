package dev.redcodes.axoty.general;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.data.users.AxotyUser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class UserCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxotyUser user;

		if (e.getOptions().size() == 1) {
			user = new AxotyUser(e.getOption("user").getAsUser());
		} else {
			user = new AxotyUser(e.getUser());
		}

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle(user.getUser().getAsTag());
		msg.setDescription(user.getUser().getAsMention());
		msg.addField("Images Requested", String.valueOf(user.getImagesRequested()), true);
		msg.addField("Facts Requested", String.valueOf(user.getFactsRequested()), true);
		msg.addField("Memes Requested", String.valueOf(user.getMemesRequested()), true);
		msg.addField("Videos Requested [SOON!]", "*This feature will be added soon!*", true);
		msg.setThumbnail(user.getUser().getAvatarUrl());
		msg.setColor(0x33cc33);
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		
		e.getHook().editOriginalEmbeds(msg.build()).queue();

	}

}
