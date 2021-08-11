package dev.redcodes.axoty.suggestion.commands;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.api.ContentType;
import dev.redcodes.axoty.data.connection.MongoDBHandler;
import dev.redcodes.axoty.suggestion.AxolotlSuggestion;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class SuggestCommand {

	
	public static void onCommand(SlashCommandEvent e) {
		
		e.deferReply(true).queue();
		
		URL url = null;
		URL source = null;
		
		try {
			url = new URL(e.getOption("url").getAsString());
			source = new URL(e.getOption("source-url").getAsString());
		} catch(MalformedURLException ex) {
			EmbedBuilder error = new EmbedBuilder();
			error.setTitle("Invalid URL(s)");
			error.setColor(Color.RED);
			error.setFooter("© Axoty " + Axoty.year, Axoty.icon);
			e.getHook().editOriginalEmbeds(error.build()).queue();
		}
		
		
		MongoCollection<Document> imgs = MongoDBHandler.getDatabase().getCollection("images");
		Document imgsDoc = imgs.find(Filters.eq("url", url.toString())).first();
		MongoCollection<Document> memes = MongoDBHandler.getDatabase().getCollection("memes");
		Document memesDoc = memes.find(Filters.eq("url", url.toString())).first();
		MongoCollection<Document> gifs = MongoDBHandler.getDatabase().getCollection("gifs");
		Document gifsDoc = gifs.find(Filters.eq("url", url.toString())).first();
		
		
		if(imgsDoc != null || memesDoc != null || gifsDoc != null) {
			EmbedBuilder error = new EmbedBuilder();
			error.setTitle("This content is already in our Dataset.");
			error.setColor(Color.RED);
			error.setFooter("© Axoty " + Axoty.year, Axoty.icon);
			e.getHook().editOriginalEmbeds(error.build()).queue();
			return;
		}
		
		
		
		ContentType type = ContentType.valueOf(e.getOption("type").getAsString().toUpperCase());
		
		new AxolotlSuggestion(type, url, source, e.getUser());
		
		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Suggestion sent!");
		msg.setDescription("You will be notified about the status of your suggestion in a Direct Message from the Bot.");
		msg.setColor(0x33cc33);
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		
		e.getHook().editOriginalEmbeds(msg.build()).queue();
		
		
	}
	
}
