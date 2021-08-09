package dev.redcodes.axoty.suggestion;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.api.ContentType;
import dev.redcodes.axoty.data.connection.MongoDBHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

public class AxolotlSuggestion {

	ContentType type;
	URL url;
	URL sourceUrl;
	User user;
	ObjectId id;
	boolean exists;

	public AxolotlSuggestion(ContentType type, URL url, URL sourceUrl, User user) {
		this.type = type;
		this.url = url;
		this.sourceUrl = sourceUrl;
		this.user = user;

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("suggestions");
		Document doc = new Document("type", type.toString()).append("url", this.url.toString())
				.append("sourceUrl", sourceUrl.toString()).append("userId", this.user.getIdLong());
		collection.insertOne(doc);

		this.exists = true;

		this.id = collection.find(doc).first().getObjectId("_id");

		EmbedBuilder suggestion = new EmbedBuilder();
		suggestion.setTitle("New Suggestion");
		suggestion.setAuthor(this.user.getAsTag(), "https://axoty.xyz", this.user.getAvatarUrl());
		suggestion.addField("Type", this.type.toString(), true);
		suggestion.addField("URL", this.url.toString(), true);
		suggestion.addField("Source", this.sourceUrl.toString(), true);
		suggestion.setImage(this.url.toString());
		suggestion.setFooter("© Axoty " + Axoty.year, Axoty.icon);

		List<ActionRow> rows = new ArrayList<ActionRow>();
		List<Button> buttons1 = new ArrayList<Button>();
		List<Button> buttons2 = new ArrayList<Button>();

		buttons1.add(Button.success(this.id.toString() + "-accept", "Accept").withEmoji(Emoji.fromUnicode("✅")));

		buttons2.add(
				Button.danger(this.id.toString() + "-deny1", "Deny - Invalid URL").withEmoji(Emoji.fromUnicode("❌")));
		buttons2.add(Button.danger(this.id.toString() + "-deny2", "Deny - Not an Axolotl")
				.withEmoji(Emoji.fromUnicode("❌")));
		buttons2.add(Button.danger(this.id.toString() + "-deny3", "Deny - Duplicate Suggestion")
				.withEmoji(Emoji.fromUnicode("❌")));
		buttons2.add(Button.danger(this.id.toString() + "-deny4", "Deny - Invalid Source").withEmoji(Emoji.fromUnicode("❌")));
		buttons2.add(Button.danger(this.id.toString() + "-deny5", "Deny - Other").withEmoji(Emoji.fromUnicode("❌")));

		rows.add(ActionRow.of(buttons1));
		rows.add(ActionRow.of(buttons2));

		this.user.getJDA().getGuildById(846088733131931688L).getTextChannelById(872419797793640458L)
				.sendMessageEmbeds(suggestion.build()).setActionRows(rows).queue();
	}

	public AxolotlSuggestion(ObjectId id) {
		this.id = id;

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("suggestions");
		Document doc = collection.find(Filters.eq("_id", this.id)).first();

		if (doc != null) {

			exists = true;

			try {
				this.url = new URL(doc.getString("url"));
				this.sourceUrl = new URL(doc.getString("sourceUrl"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			this.type = ContentType.valueOf(doc.getString("type"));
			this.user = Axoty.jda.retrieveUserById(doc.getLong("userId")).complete();

		} else {
			exists = false;
		}
	}

	public void accept() {

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection(this.type.toString().toLowerCase() + "s");
		Document doc = new Document("url", this.url.toString()).append("sourceUrl", this.sourceUrl.toString())
				.append("suggester", this.user.getAsTag());
		collection.insertOne(doc);

		MongoCollection<Document> suggestions = MongoDBHandler.getDatabase().getCollection("suggestions");
		suggestions.deleteOne(Filters.eq("_id", this.id));

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Your Suggestion was approved!");
		msg.setDescription("The content is now in our Dataset and can be viewed by all users and the API.");
		msg.setImage(this.url.toString());
		msg.setColor(0x33cc33);
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		this.user.openPrivateChannel().queue(pc -> {
			pc.sendMessageEmbeds(msg.build()).queue();
		});

	}
	
	public void deny(String reason) {

		MongoCollection<Document> suggestions = MongoDBHandler.getDatabase().getCollection("suggestions");
		suggestions.deleteOne(Filters.eq("_id", this.id));

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Your Suggestion was denied!");
		msg.setDescription("The content was denied by one of our Moderation Team Members.\n\nReason: " + reason);
		msg.setImage(this.url.toString());
		msg.setColor(Color.RED);
		msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
		this.user.openPrivateChannel().queue(pc -> {
			pc.sendMessageEmbeds(msg.build()).queue();
		});
	}

	public boolean exists() {
		return exists;
	}

	public ContentType getType() {
		return this.type;
	}

	public URL getUrl() {
		return this.url;
	}

	public URL getSourceUrl() {
		return this.sourceUrl;
	}

	public User getUser() {
		return this.user;
	}

}
