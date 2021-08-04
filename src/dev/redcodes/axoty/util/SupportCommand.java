package dev.redcodes.axoty.util;

import dev.redcodes.axoty.Axoty;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public class SupportCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Axoty Support");
		msg.setDescription(
				"If you need help with any of the features that axoty provides, feel free to contact us on the [Redcodes Development Discord Server.](https://discord.gg/FxXcRbgyVq)");
		msg.setColor(0x33cc33);
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);

		e.getHook().editOriginalEmbeds(msg.build()).setActionRow(
				Button.link("https://discord.gg/FxXcRbgyVq", Emoji.fromUnicode("🗣")).withLabel("Join Support Server"))
				.queue();
	}

}
