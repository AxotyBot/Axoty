package dev.redcodes.axoty.general.gif;

import java.util.ArrayList;
import java.util.List;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.api.AxolotlGif;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

public class GifMessage {

	public static MessageBuilder getEmbed() {
		AxolotlGif gif = new AxolotlGif();

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Axolotl!");
		msg.setImage(gif.getUrl().toString());
		msg.setFooter("Suggested by: " + gif.getSuggester() + "\n\n© Axoty " + Axoty.year, Axoty.icon);
		msg.setColor(0x33cc33);

		List<ActionRow> rows = new ArrayList<ActionRow>();
		List<Button> buttons = new ArrayList<Button>();

		buttons.add(Button.success("gif", "Get another Gif").withEmoji(Emoji.fromUnicode("♾")));
		buttons.add(Button.success("img", "Get an Image").withEmoji(Emoji.fromUnicode("🖼")));
		buttons.add(Button.success("meme", "Get a Meme").withEmoji(Emoji.fromUnicode("🎤")));
		buttons.add(Button.success("fact", "Get a fact").withEmoji(Emoji.fromUnicode("📰")));
		buttons.add(Button.success("video", "Get a video [SOON!]").withEmoji(Emoji.fromUnicode("🎥")).asDisabled());

		rows.add(ActionRow.of(buttons));
		rows.add(ActionRow.of(Button.link(gif.getSourceUrl().toString(), "Source").withEmoji(Emoji.fromUnicode("🌐"))));
		
		MessageBuilder builder = new MessageBuilder();
		builder.setEmbeds(msg.build());
		builder.setActionRows(rows);
		
		return builder;
	}
	
}
