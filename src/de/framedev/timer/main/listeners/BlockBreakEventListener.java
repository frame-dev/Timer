/**
 * Dies ist ein Plugin von FrameDev
 * Bitte nichts ändern, @Copyright by FrameDev 
 */
package de.framedev.timer.main.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.framedev.timer.main.Main;

/**
 * @author Darryl
 *
 */
public class BlockBreakEventListener implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;

	/**
	 * 
	 */
	public BlockBreakEventListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(e.getBlock().getType() == Material.DIAMOND_ORE) {
			Block block = e.getBlock();
			e.setDropItems(false);
			int rnd = new Random().nextInt(4);
			switch (rnd) {
			case 1:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, 1));
				break;
			case 2:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, 2));
				break;
			case 3:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, 3));
				break;
			case 4:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, 4));
				break;
			default:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, 1));
				break;
			}
			e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, 4));
		} else if(e.getBlock().getType() == Material.IRON_ORE) {
			Block block = e.getBlock();
			e.setDropItems(false);
			int rnd = new Random().nextInt(4);
			switch (rnd) {
			case 1:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_ORE, 1));
				break;
			case 2:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_ORE, 2));
				break;
			case 3:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_ORE, 3));
				break;
			case 4:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_ORE, 4));
				break;
			default:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_ORE, 4));
				break;
			}
		} else if(e.getBlock().getType() == Material.GOLD_ORE) {
			Block block = e.getBlock();
			e.setDropItems(false);
			int rnd = new Random().nextInt(4);
			switch (rnd) {
			case 1:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_ORE, 1));
				break;
			case 2:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_ORE, 2));
				break;
			case 3:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_ORE, 3));
				break;
			case 4:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_ORE, 4));
				break;
			default:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_ORE, 1));
				break;
			}
		} else if(e.getBlock().getType() == Material.COAL_ORE) {
			Block block = e.getBlock();
			e.setDropItems(false);
			int rnd = new Random().nextInt(4);
			switch (rnd) {
			case 1:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, 1));
				break;
			case 2:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, 2));
				break;
			case 3:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, 3));
				break;
			case 4:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, 4));
				break;
			default:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, 1));
				break;
			}
		} else if(e.getBlock().getType() == Material.EMERALD_ORE) {
			Block block = e.getBlock();
			e.setDropItems(false);
			int rnd = new Random().nextInt(4);
			switch (rnd) {
			case 1:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.EMERALD, 1));
				break;
			case 2:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.EMERALD, 2));
				break;
			case 3:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.EMERALD, 2));
				break;
			case 4:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.EMERALD, 1));
				break;
			default:
				e.getPlayer().getWorld().dropItem(block.getLocation(), new ItemStack(Material.EMERALD, 1));
				break;
			}
		}
	}

}
