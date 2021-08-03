package dev.redcodes.axoty.util;

import java.time.Instant;

import dev.redcodes.axoty.Axoty;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class InfoCommand {

	public static void onCommand(SlashCommandEvent e) {
		
		e.deferReply().queue();
		
		long duration = Instant.now().getEpochSecond() - Axoty.online.getEpochSecond();
		long days = duration / 86400;
		long hours = duration / 3600 % 24;
		long minutes = duration / 60 % 60;
		long seconds = duration / 1 % 60;
		
		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Bot Information");
		msg.setDescription("*A Bot by <@277048745458401282>*");
		msg.addField("Version", Axoty.Version, true);
		msg.addField("JDA Version", "4.3.0_301", true);
		msg.addField("Java Version", System.getProperty("java.version"), true);
		msg.addField("Online for", days + " Days, " + hours + " Hours, " + minutes + " Minutes, " + seconds + " Seconds", true);
		msg.setThumbnail(e.getJDA().getSelfUser().getAvatarUrl());
		msg.setColor(0x33cc33);
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		
		e.getHook().editOriginalEmbeds(msg.build()).queue();
		
	}
	
}
