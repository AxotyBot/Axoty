package dev.redcodes.axoty.general;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.api.AxolotlImage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public class AxolotlCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxolotlImage img = new AxolotlImage();

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Axolotl!");
		msg.setImage(img.getUrl().toString());
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		msg.setColor(0x33cc33);

		e.getHook().editOriginalEmbeds(msg.build())
				.setActionRow(Button.link(img.getUrl().toString(), "Source").withEmoji(Emoji.fromUnicode("🌐")))
				.queue();

	}

}
