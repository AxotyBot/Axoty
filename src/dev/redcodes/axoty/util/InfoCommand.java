package dev.redcodes.axoty.util;

import java.time.Instant;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.data.connection.MongoDBHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class InfoCommand {

	public static void onCommand(SlashCommandEvent e) {

		e.deferReply().queue();

		long duration = Instant.now().getEpochSecond() - Axoty.online.getEpochSecond();
		long days = duration / 86400;
		long hours = duration / 3600 % 24;
		long minutes = duration / 60 % 60;
		long seconds = duration / 1 % 60;

		int users = 0;

		for (Guild guild : e.getJDA().getGuilds()) {
			users = users + guild.getMemberCount();
		}

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Bot Information");
		msg.setDescription("*A Bot by <@277048745458401282>*");
		msg.addField("Guilds", String.valueOf(e.getJDA().getGuilds().size()), true);
		msg.addField("Users", String.valueOf(users), true);
		msg.addField("Content in queue",
				String.valueOf(MongoDBHandler.getDatabase().getCollection("suggestions").countDocuments()), true);
		msg.addField("Version", Axoty.Version, true);
		msg.addField("JDA Version", "4.3.0_304", true);
		msg.addField("Java Version", System.getProperty("java.version"), true);
		msg.addField("Online for",
				days + " Days, " + hours + " Hours, " + minutes + " Minutes, " + seconds + " Seconds", false);
		msg.addField("Inspired by", "[Xondalf Discord Bot](https://top.gg/bot/833016200740601909)", false);
		msg.addField("", "> [Website](https://axoty.xyz/) • [API](https://api.axoty.xyz/) • [Support](https://discord.gg/FxXcRbgyVq)", false);
		msg.setThumbnail(e.getJDA().getSelfUser().getAvatarUrl());
		msg.setColor(0x33cc33);
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);

		e.getHook().editOriginalEmbeds(msg.build()).queue();

	}

}
