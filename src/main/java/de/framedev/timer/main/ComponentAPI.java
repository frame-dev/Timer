/**
 * Dies ist ein Plugin von FrameDev
 * Bitte nichts ändern, @Copyright by FrameDev 
 */
package de.framedev.timer.main;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class ComponentAPI {

	public ComponentAPI() {
	}

	public void sendActionbar(Player player, String message) {
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(message).create());
	}

	@Deprecated
	public void sendTitle(Player player, String title, String subTitle) {
		player.sendTitle(title, subTitle);
	}

	public void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
		player.sendTitle(title, subTitle, fadeIn, stay, fadeOut);
	}

}
