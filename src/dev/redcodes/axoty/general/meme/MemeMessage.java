package dev.redcodes.axoty.general.meme;

import java.util.ArrayList;
import java.util.List;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.api.AxolotlMeme;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

public class MemeMessage {

	public static MessageBuilder getEmbed() {
		AxolotlMeme img = new AxolotlMeme();

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Axolotl!");
		msg.setImage(img.getUrl().toString());
		msg.setFooter("Suggested by: " + img.getSuggester() + "\n\n© Axoty " + Axoty.year, Axoty.icon);
		msg.setColor(0x33cc33);

		List<ActionRow> rows = new ArrayList<ActionRow>();
		List<Button> buttons = new ArrayList<Button>();

		buttons.add(Button.success("meme", "Get another Meme").withEmoji(Emoji.fromUnicode("🎤")));
		buttons.add(Button.success("img", "Get an Image").withEmoji(Emoji.fromUnicode("🖼")));
		buttons.add(Button.success("fact", "Get a fact").withEmoji(Emoji.fromUnicode("📰")));
		buttons.add(Button.success("gif", "Get a Gif").withEmoji(Emoji.fromUnicode("♾")));
		buttons.add(Button.success("video", "Get a video [SOON!]").withEmoji(Emoji.fromUnicode("🎥")).asDisabled());

		rows.add(ActionRow.of(buttons));
		rows.add(ActionRow.of(Button.link(img.getSourceUrl().toString(), "Source").withEmoji(Emoji.fromUnicode("🌐"))));
		
		MessageBuilder builder = new MessageBuilder();
		builder.setEmbeds(msg.build());
		builder.setActionRows(rows);
		
		return builder;
	}
	
}
