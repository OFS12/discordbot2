package de.OFS12.Bot;

import java.io.BufferedReader;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import de.OFS12.listener.CommandListener;
import java.io.InputStreamReader;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class DiscordBot {
	
	public ShardManager shardMan;

	public static void main(String[] args) {
		try {
			new DiscordBot();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		
	}
	
	public DiscordBot() throws LoginException, IllegalArgumentException {
		
		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
		builder.setToken("NzAzMTcwMjcxOTYzNzc1MDg2.XqLIWA.xRTtdMCscXpVijhN3AGY9fBky34");
		
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setActivity(Activity.watching("nach dem Serverstatus."));
		
		builder.addEventListeners(new CommandListener());
		
		shardMan = builder.build();
		System.out.println("Bot online.");
		
		shutdown();
	}
	
	

	public void shutdown() {
		
		new Thread(() -> {
			
			String line = "";
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				while((line = reader.readLine()) !=null) {
					
					if(line.equalsIgnoreCase("exit")) {
						if(shardMan != null) {
							shardMan.setStatus(OnlineStatus.OFFLINE);
							shardMan.shutdown();
							System.out.println("Bot offline.");
							
							reader.close();
						}
						else System.out.println("Nutze 'exit'");
					}
					
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start();
			
	}
	
}
