package dev.redcodes.axoty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.security.auth.login.LoginException;

import dev.redcodes.axoty.handler.CommandHandler;
import dev.redcodes.axoty.token.DONOTOPEN;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Axoty {

	private Thread loop;

	public static JDA jda;

	public static String Version = "Release 1.0";

	public static boolean Dev = false;

	public static String year = "2021";

	public static String icon = "https://i.imgur.com/O74dUFG.jpg";

	public static Instant online = Instant.now();

	public static void main(String[] args) {
		try {
			new Axoty();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public Axoty() throws LoginException, IllegalArgumentException {

		String token = null;
		if (Dev) {
			token = DONOTOPEN.getDevToken();
		} else {
			token = DONOTOPEN.getToken();
		}

		JDABuilder builder = JDABuilder.createDefault(token);

		builder.setActivity(Activity.watching("Bot starting..."));
		builder.setStatus(OnlineStatus.IDLE);

		builder.addEventListeners(new CommandHandler());

		List<GatewayIntent> intents = new ArrayList<GatewayIntent>();
		intents.addAll(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
		intents.remove(GatewayIntent.GUILD_PRESENCES);

		builder.setEnabledIntents(intents);
		builder.setMemberCachePolicy(MemberCachePolicy.ALL);

		jda = builder.build();
		System.out.println("The Bot is now Online!");

		year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

		shutdown();
		runLoop();

	}

	public void shutdown() {

		new Thread(() -> {

			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				while ((line = reader.readLine()) != null) {
					if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("stop")) {
						shutdown = true;
						if (jda != null) {
							jda.getPresence().setStatus(OnlineStatus.OFFLINE);
							jda.shutdown();
							System.out.println("The Bot is now Offline!");
						}
						if (loop != null) {
							loop.interrupt();
						}
						reader.close();
						System.exit(0);
						break;

					} else {
						System.out.println("Use 'exit' or 'stop' to shutdown");
					}
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}).start();
	}

	public boolean shutdown = false;

	public void runLoop() {
		this.loop = new Thread(() -> {
			long time = System.currentTimeMillis();

			while (!shutdown) {
				if (System.currentTimeMillis() >= time + 1000) {
					time = System.currentTimeMillis();
					onSecond();

				}
			}

		});
		this.loop.setName("Loop");
		this.loop.start();
	}

	int next = 7;
	boolean commandCheck = true;
	String[] status = new String[] { "axolotl groans", "%members% User", "%version%"

	};

	public void onSecond() {
		if (next <= 0) {

			if (commandCheck) {
				commandCheck = false;

				List<CommandData> cmds = new ArrayList<CommandData>();
				cmds.add(new CommandData("axolotl", "Gives you a random Axolotl Picture."));

				jda.getGuildById(580732235313971211l).updateCommands().addCommands(cmds).queue();
				System.out.println("Commands published!");
			}

			Random rand = new Random();
			int i = rand.nextInt(status.length);

			int users = 0;

			for (Guild guild : jda.getGuilds()) {
				users = users + guild.getMemberCount();
			}

			String text = status[i].replace("%members%", String.valueOf(users)).replace("%version%", Version)
					.replace("%guilds%", String.valueOf(jda.getGuilds().size()));

			if (!jda.getPresence().getActivity().getName().equals(text)) {

				if (!Dev) {
					jda.getPresence().setStatus(OnlineStatus.ONLINE);
					if (text.contains("axolotl")) {
						jda.getPresence().setActivity(Activity.listening(text));
					} else if (text.contains("User") || text.contains("Guilds") || text.contains("axolotl")) {
						jda.getPresence().setActivity(Activity.watching(text));
					} else {
						jda.getPresence().setActivity(Activity.playing(text));
					}
				} else {
					jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
					jda.getPresence().setActivity(Activity.watching("Development"));
				}
			} else {
				onSecond();
			}

			next = 7;
		} else {
			next--;
		}

	}

}
