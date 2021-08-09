package dev.redcodes.axoty.util;

import java.util.ArrayList;
import java.util.List;

import dev.redcodes.axoty.Axoty;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public class ApiCommand {

	public static void onCommand(SlashCommandEvent e) {
		
		e.deferReply().queue();
		
		EmbedBuilder msg = new EmbedBuilder();
		msg.setDescription("We provide a powerful API that's publicaly available for free. You can get random Axolotl Images, Memes and more by just one request to our API.");
		msg.setTitle("Axoty API");
		msg.setColor(0x33cc33);
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		
		List<Button> buttons = new ArrayList<Button>();
		buttons.add(Button.link("https://api.axoty.xyz/", Emoji.fromUnicode("🌐")).withLabel("API"));
		buttons.add(Button.link("https://github.com/AxotyBot/axoty_api/blob/master/readme.md#get-random", Emoji.fromUnicode("📜")).withLabel("Documentation"));
		
		e.getHook().editOriginalEmbeds(msg.build()).setActionRow(buttons).queue();
		
		
	}
	
}
