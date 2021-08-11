package dev.redcodes.axoty.handler;

import dev.redcodes.axoty.general.image.ImageButton;
import dev.redcodes.axoty.general.meme.MemeButton;
import dev.redcodes.axoty.general.video.VideoButton;
import dev.redcodes.axoty.general.fact.FactButton;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ButtonHandler extends ListenerAdapter {

	public void onButtonClick(ButtonClickEvent e) {
		
		switch (e.getButton().getId()) {

		case "img":
			new Thread(() -> {
				ImageButton.onButtonClick(e);
			}).start();
			break;

		case "fact":
			new Thread(() -> {
				FactButton.onButtonClick(e);
			}).start();
			break;
			
		case "meme":
			new Thread(() -> {
				MemeButton.onButtonClick(e);
			}).start();
			break;

		case "video":
			new Thread(() -> {
				VideoButton.onButtonClick(e);
			}).start();
			break;
			
		default:

			break;

		}

	}

}
