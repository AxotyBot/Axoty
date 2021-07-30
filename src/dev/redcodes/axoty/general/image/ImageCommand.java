package dev.redcodes.axoty.general.image;

import java.util.ArrayList;
import java.util.List;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.api.AxolotlImage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

public class ImageCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		AxolotlImage img = new AxolotlImage();

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Axolotl!");
		msg.setImage(img.getUrl().toString());
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		msg.setColor(0x33cc33);

		List<ActionRow> rows = new ArrayList<ActionRow>();
		List<Button> buttons = new ArrayList<Button>();

		buttons.add(Button.success("img", "Get another Image").withEmoji(Emoji.fromUnicode("🖼")));
		buttons.add(Button.success("fact", "Get a fact").withEmoji(Emoji.fromUnicode("📰")));

		rows.add(ActionRow.of(buttons));
		rows.add(ActionRow.of(Button.link(img.getUrl().toString(), "Source").withEmoji(Emoji.fromUnicode("🌐"))));

		e.getHook().editOriginalEmbeds(msg.build()).setActionRows(rows).queue();

	}

}
