package dev.redcodes.axoty.general.fact;

import java.util.ArrayList;
import java.util.List;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.api.AxolotlFact;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

public class FactMessage {

	public static MessageBuilder getEmbed() {
		AxolotlFact fact = new AxolotlFact();

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Axolotl!");
		msg.setDescription(fact.getFact());
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		msg.setColor(0x33cc33);

		List<ActionRow> rows = new ArrayList<ActionRow>();
		List<Button> buttons = new ArrayList<Button>();

		buttons.add(Button.success("fact", "Get another fact").withEmoji(Emoji.fromUnicode("📰")));
		buttons.add(Button.success("img", "Get an Image").withEmoji(Emoji.fromUnicode("🖼")));
		buttons.add(Button.success("meme", "Get a Meme").withEmoji(Emoji.fromUnicode("🎤")));
		buttons.add(Button.success("video", "Get a video [SOON!]").withEmoji(Emoji.fromUnicode("🎥")).asDisabled());

		rows.add(ActionRow.of(buttons));
		
		MessageBuilder builder = new MessageBuilder();
		builder.setEmbeds(msg.build());
		builder.setActionRows(rows);
		
		return builder;
		
	}
	
}
