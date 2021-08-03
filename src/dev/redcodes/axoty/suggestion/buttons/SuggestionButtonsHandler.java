package dev.redcodes.axoty.suggestion.buttons;

import java.awt.Color;

import org.bson.types.ObjectId;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.suggestion.AxolotlSuggestion;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

public class SuggestionButtonsHandler extends ListenerAdapter {

	public void onButtonClick(ButtonClickEvent e) {
		
		new Thread(() -> {

			String[] args = e.getButton().getId().split("-");

			try {
				AxolotlSuggestion suggestion = new AxolotlSuggestion(new ObjectId(args[0]));

				if (suggestion.exists()) {
					switch (args[1]) {

					case "accept":
						suggestion.accept();

						EmbedBuilder msg = new EmbedBuilder();
						msg.setTitle("New Suggestion");
						msg.setAuthor(suggestion.getUser().getAsTag(), "https://axoty.xyz",
								suggestion.getUser().getAvatarUrl());
						msg.addField("Type", suggestion.getType().toString(), true);
						msg.addField("URL", suggestion.getUrl().toString(), true);
						msg.addField("Source", suggestion.getSourceUrl().toString(), true);
						msg.setImage(suggestion.getUrl().toString());
						msg.setFooter("© Axoty " + Axoty.year, Axoty.icon);
						msg.setColor(0x33cc33);

						e.getMessage().editMessageEmbeds(msg.build())
								.setActionRows(ActionRow.of(Button
										.success("never gonna give you up", "Approved by " + e.getUser().getAsTag())
										.asDisabled().withEmoji(Emoji.fromUnicode("✅"))))
								.queue();
						break;

					case "deny1":
						suggestion.deny("One or both of the URL's where invalid.");
						
						EmbedBuilder msg1 = new EmbedBuilder();
						msg1.setTitle("New Suggestion");
						msg1.setAuthor(suggestion.getUser().getAsTag(), "https://axoty.xyz",
								suggestion.getUser().getAvatarUrl());
						msg1.addField("Type", suggestion.getType().toString(), true);
						msg1.addField("URL", suggestion.getUrl().toString(), true);
						msg1.addField("Source", suggestion.getSourceUrl().toString(), true);
						msg1.setImage(suggestion.getUrl().toString());
						msg1.setFooter("© Axoty " + Axoty.year, Axoty.icon);
						msg1.setColor(Color.RED);

						e.getMessage().editMessageEmbeds(msg1.build())
								.setActionRows(ActionRow.of(Button
										.danger("never gonna give you up", "Denied by " + e.getUser().getAsTag())
										.asDisabled().withEmoji(Emoji.fromUnicode("❌"))))
								.queue();
						break;

					case "deny2":
						suggestion.deny("The content didn't contained an Axolotl.");
						EmbedBuilder msg11 = new EmbedBuilder();
						msg11.setTitle("New Suggestion");
						msg11.setAuthor(suggestion.getUser().getAsTag(), "https://axoty.xyz",
								suggestion.getUser().getAvatarUrl());
						msg11.addField("Type", suggestion.getType().toString(), true);
						msg11.addField("URL", suggestion.getUrl().toString(), true);
						msg11.addField("Source", suggestion.getSourceUrl().toString(), true);
						msg11.setImage(suggestion.getUrl().toString());
						msg11.setFooter("© Axoty " + Axoty.year, Axoty.icon);
						msg11.setColor(Color.RED);

						e.getMessage().editMessageEmbeds(msg11.build())
								.setActionRows(ActionRow.of(Button
										.danger("never gonna give you up", "Denied by " + e.getUser().getAsTag())
										.asDisabled().withEmoji(Emoji.fromUnicode("❌"))))
								.queue();
						break;

					case "deny3":
						suggestion.deny(
								"The suggested content was a duplicate of an already existing content in the Dataset.");
						EmbedBuilder msg111 = new EmbedBuilder();
						msg111.setTitle("New Suggestion");
						msg111.setAuthor(suggestion.getUser().getAsTag(), "https://axoty.xyz",
								suggestion.getUser().getAvatarUrl());
						msg111.addField("Type", suggestion.getType().toString(), true);
						msg111.addField("URL", suggestion.getUrl().toString(), true);
						msg111.addField("Source", suggestion.getSourceUrl().toString(), true);
						msg111.setImage(suggestion.getUrl().toString());
						msg111.setFooter("© Axoty " + Axoty.year, Axoty.icon);
						msg111.setColor(Color.RED);

						e.getMessage().editMessageEmbeds(msg111.build())
								.setActionRows(ActionRow.of(Button
										.danger("never gonna give you up", "Denied by " + e.getUser().getAsTag())
										.asDisabled().withEmoji(Emoji.fromUnicode("❌"))))
								.queue();
						break;

					case "deny4":
						suggestion.deny(
								"Unspecified Reason. Please contact a Teammember if you want more information about this decission.");
						EmbedBuilder msg1111 = new EmbedBuilder();
						msg1111.setTitle("New Suggestion");
						msg1111.setAuthor(suggestion.getUser().getAsTag(), "https://axoty.xyz",
								suggestion.getUser().getAvatarUrl());
						msg1111.addField("Type", suggestion.getType().toString(), true);
						msg1111.addField("URL", suggestion.getUrl().toString(), true);
						msg1111.addField("Source", suggestion.getSourceUrl().toString(), true);
						msg1111.setImage(suggestion.getUrl().toString());
						msg1111.setFooter("© Axoty " + Axoty.year, Axoty.icon);
						msg1111.setColor(Color.RED);

						e.getMessage().editMessageEmbeds(msg1111.build())
								.setActionRows(ActionRow.of(Button
										.danger("never gonna give you up", "Denied by " + e.getUser().getAsTag())
										.asDisabled().withEmoji(Emoji.fromUnicode("❌"))));
						break;

					default:

						break;

					}
				}

				return;

			} catch (IllegalArgumentException ex) {
				return;
			}

		}).start();
	}

}
