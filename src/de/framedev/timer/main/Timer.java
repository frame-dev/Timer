/**
 * Dies ist ein Plugin von FrameDev
 * Bitte nichts ändern, @Copyright by FrameDev 
 */
package de.framedev.timer.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author darrylhuber
 *
 */
public class Timer {

	/**
	 * 
	 */
	public Timer() {
	}

	public ArrayList<Integer> second = new ArrayList<Integer>();
	public ArrayList<Integer> minute = new ArrayList<Integer>();
	public ArrayList<Integer> hour = new ArrayList<Integer>();
	public static HashMap<CommandSender, String> texts = new HashMap<>();
	public int minutes = 0;
	public int seconds = 0;
	public int hours = 0;
	public Sound sound = Sound.valueOf(Main.getInstance().getConfig().getString("Sound"));
	public int days = 0;
	public Date date = new Date(0);
	public int x = 0;

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours = Main.getInstance().getConfig().getInt("Hours");
	}

	/**
	 * @return the seconds
	 */
	public int getSeconds() {
		return seconds = Main.getInstance().getConfig().getInt("Seconds");
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes = Main.getInstance().getConfig().getInt("Minutes");
	}

	Date date2;

	@SuppressWarnings("deprecation")
	public void startTimer(CommandSender sender, String[] args) {
		if (!Main.getInstance().isRunning()) {
			StringBuilder s = new StringBuilder();
			for(int i = 0; i < args.length; i++) {
				s.append(args[i]);
				if(i < args.length - 1)  {
				 s.append(" ");
				}
			}
			String text = s.toString();
			if (text.contains("&"))
				text = text.replace('&', '§');
			texts.put(sender, text);
			Main.getInstance().setRunning(false);
			date.setDate(1);
			date.setSeconds(0);
			date.setMinutes(0);
			date.setHours(0);
			Bukkit.getScheduler().cancelTasks(Main.getInstance());
			if (Main.getInstance().isDown()) {
				getHours();
				getMinutes();
				getSeconds();
				date.setSeconds(seconds);
				date.setHours(hours);
				date.setMinutes(minutes);
			}
			new BukkitRunnable() {

				@Override
				public void run() {
					if (Main.getInstance().isDown()) {
						date.setMinutes(date.getMinutes());
						date.setHours(date.getHours());
						date.setSeconds(date.getSeconds() - 1);
					} else {
						date.setMinutes(date.getMinutes());
						date.setHours(date.getHours());
						date.setSeconds(date.getSeconds() + 1);
					}
				}
			}.runTaskTimer(Main.getInstance(), 0, 20);
			new BukkitRunnable() {

				@Override
				public void run() {
					Bukkit.getOnlinePlayers().forEach(current -> {
						if (Main.getInstance().isDown()) {
							if (date.getMinutes() <= 0) {
								if (date.getSeconds() <= 0) {
									Bukkit.getScheduler()
											.cancelTasks(Main.getInstance());
									current.sendMessage(
											"§8[§dTimer§8]§6 | §cDer Timer ist abgelaufen!");
									current.playSound(current.getLocation(), sound,
											1, 1);
									Main.getInstance().setRunning(false);
									return;
								}
							}
						}
					});
				}
			}.runTaskTimer(Main.getInstance(), 0, 20);
			date2 = new Date(0);
			date2.setHours(0);
			date2.setHours(date.getHours());
			date2.setMinutes(0);
			date2.setMinutes(0);
			Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					Bukkit.getOnlinePlayers().forEach(current -> {
						date2.setHours(date.getHours());
						date2.setMinutes(date.getMinutes());
						date2.setSeconds(date.getSeconds());
						if (Main.getInstance().isDown()) {
							Main.getInstance().getTimer(current);
						}
						if (date.getDate() >= 2) {
							int day = date.getDate() - 1;
							String time = new SimpleDateFormat("HH:mm:ss").format(date2);
							current.spigot().sendMessage(ChatMessageType.ACTION_BAR,
									new TextComponent("§a" + texts.get(sender)
											+ " §b Tag(e) " + day + " | "
											+ time));
							days = day;
						} else {
							String formattedDate = new SimpleDateFormat("HH:mm:ss")
									.format(date2);
							current.spigot().sendMessage(ChatMessageType.ACTION_BAR,
									new TextComponent("§a" + texts.get(sender) + " §b" + formattedDate));
						}

					});
				}
			}, 0, 20);
			Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage("§8[§dTimer§8]§6 | §aTimer wurde gestartet!"));
			new ComponentAPI().sendTitle((Player) sender, "§aTimer wurde gestartet!", "§8[§dTimer§8]§6", 2,
					60, 2);
			Main.getInstance().setRunning(true);
		}
	}

	@SuppressWarnings("deprecation")
	public void stopTimer(CommandSender sender) {
		if (Main.getInstance().isRunning()) {
			Bukkit.getScheduler().cancelTasks(Main.getInstance());
			date.setSeconds(0);
			date.setHours(0);
			date.setMinutes(0);
			Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage("§8[§dTimer§8]§6 | §aTimer wurde gestoppt!"));
			Main.getInstance().setRunning(false);
		}
	}

	@SuppressWarnings("deprecation")
	public void pauseTimer(CommandSender sender) {
		if (Main.getInstance().isRunning()) {
			days = date.getDate();
			minute.add(date.getMinutes());
			second.add(date.getSeconds());
			hour.add(date.getHours());
			Bukkit.getScheduler().cancelTasks(Main.getInstance());
			date.setSeconds(0);
			date.setHours(0);
			date.setMinutes(0);
			Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage("§8[§dTimer§8]§6 | §aTimer wurde pausiert!"));
			Main.getInstance().setRunning(false);
		}
	}

	public void resetTimer(CommandSender sender) {
		if (Main.getInstance().isRunning()) {
			getHours();
			getMinutes();
			getSeconds();
			hour.clear();
			minute.clear();
			second.clear();
			Bukkit.getScheduler().cancelTasks(Main.getInstance());
			Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage("§8[§dTimer§8]§6 | §aTimer wurde Resettet!") );
			Main.getInstance().setRunning(false);
		}
	}

	@SuppressWarnings("deprecation")
	public void resumeTimer(CommandSender sender) {
		if (!Main.getInstance().isRunning()) {
			if (minute != null && second != null && hour != null) {
				date2 = new Date(0);
				date2.setHours(0);
				date.setDate(1);
				date2.setHours(hour.get(0));
				date2.setMinutes(0);
				date2.setMinutes(minute.get(0));
				date2.setSeconds(0);
				date2.setSeconds(second.get(0));
				date2.setDate(days);
				date.setDate(days);
				Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
					@Override
					public void run() {
						Bukkit.getOnlinePlayers().forEach(current -> {
							date2.setHours(date.getHours());
							date2.setMinutes(date.getMinutes());
							date2.setSeconds(date.getSeconds());
							if (Main.getInstance().isDown()) {
								Main.getInstance().getTimer(current);
							}
							if (date.getDate() >= 2) {
								int day = date.getDate() - 1;
								String time = new SimpleDateFormat("HH:mm:ss")
										.format(date2);
								current.spigot().sendMessage(ChatMessageType.ACTION_BAR,
										new TextComponent("§a"
												+ texts.get(sender)
												+ " §b Tag(e) " + day
												+ " | " + time));
							} else {
								String formattedDate = new SimpleDateFormat("HH:mm:ss")
										.format(date2);
								current.spigot().sendMessage(ChatMessageType.ACTION_BAR,
										new TextComponent("§a"
												+ texts.get(sender)
												+ " §b"
												+ formattedDate));
							}
						});
					}
				}, 0, 20);
				date.setSeconds(second.get(0));
				date.setHours(hour.get(0));
				date.setMinutes(minute.get(0));
				new BukkitRunnable() {

					@Override
					public void run() {
						if (Main.getInstance().isDown()) {
							date.setMinutes(date.getMinutes());
							date.setHours(date.getHours());
							date.setSeconds(date.getSeconds() - 1);
						} else {
							date.setMinutes(date.getMinutes());
							date.setHours(date.getHours());
							date.setSeconds(date.getSeconds() + 1);
						}
					}
				}.runTaskTimer(Main.getInstance(), 0, 20);
			}
			minute.clear();
			second.clear();
			hour.clear();
			new BukkitRunnable() {

				@Override
				public void run() {
					Bukkit.getOnlinePlayers().forEach(current -> {
						if (Main.getInstance().isDown()) {
							if (date.getMinutes() <= 0) {
								if (date.getSeconds() <= 0) {
									Bukkit.getScheduler()
											.cancelTasks(Main.getInstance());
									current.sendMessage(
											"§8[§dTimer§8]§6 | §cDer Timer ist abgelaufen!");
									current.playSound(current.getLocation(), sound,
											1, 1);
									Main.getInstance().setRunning(false);
									return;
								}
							}
						}
					});
				}
			}.runTaskTimer(Main.getInstance(), 0, 20);
			Main.getInstance().setRunning(true);
			Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage("§8[§dTimer§8]§6 | §aDer Timer läuft weiter!"));
		}
	}

}
