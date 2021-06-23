/**
 * Dies ist ein Plugin von FrameDev
 * Bitte nichts §ndern, @Copyright by FrameDev
 */
package de.framedev.timer.main;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener {

    private static Main plugin;
    private boolean down = true;
    private Economy economy;
    private Timer timer;

    @Override
    public void onEnable() {
        plugin = this;
        registerListeners();
        Config.loadConfig();
        Config.updateConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        this.down = getConfig().getBoolean("Down");
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!setupEconomy()) {
                    System.out.println("§cVault not Enabled!");
                }
            }
        }.runTaskLater(this, 160);
        this.timer = new Timer();
    }

    /**
     * @return the down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @return the plugin
     */
    public static Main getInstance() {
        return plugin;
    }

    private boolean running;

    public void registerListeners() {
    }

    public void getTimer(Player p) {
        if (down) {
            if (timer.date.getMinutes() >= 1) {
                if (timer.date.getSeconds() == 00) {
                    if (timer.date.getMinutes() == 1) {
                        p.sendMessage("§aEs sind noch §b" + timer.date.getMinutes() + " §aMinute");
                    } else {
                        p.sendMessage("§aEs sind noch §b" + timer.date.getMinutes() + " §aMinute(n)");
                    }
                }
            }
        }
    }

    /**
     * @return the economy
     */
    public Economy getEconomy() {
        return economy;
    }

    /**
     * @return the hours
     */
    public int getHours() {
        return timer.hours = getConfig().getInt("Hours");
    }

    /**
     * @return the seconds
     */
    public int getSeconds() {
        return timer.seconds = getConfig().getInt("Seconds");
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return timer.minutes = getConfig().getInt("Minutes");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("starttimer")) {
            timer.startTimer(sender, args);
        }
        if (command.getName().equalsIgnoreCase("stoptimer")) {
            timer.stopTimer(sender);
        }
        if (command.getName().equalsIgnoreCase("pausetimer")) {
            timer.pauseTimer(sender);
        }
        if (command.getName().equalsIgnoreCase("resettimer")) {
            timer.resetTimer(sender);
        }
        if (command.getName().equalsIgnoreCase("resumetimer")) {
            timer.resumeTimer(sender);

        }
        if (command.getName().equalsIgnoreCase("timer")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    reloadConfig();
                    sender.sendMessage("§8[§dTimer§8]§6 | §aConfig Reloaded!");
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("isdown")) {
                    down = Boolean.parseBoolean(args[1]);
                    sender.sendMessage("§8[§dTimer§8]§6 | §aDown wurde gesetzt auf §6" + down);
                } else if (args[0].equalsIgnoreCase("sethours")) {
                    int hours = Integer.parseInt(args[1]);
                    timer.hours = hours;
                    timer.date.setHours(hours);
                    timer.date2.setHours(hours);
                    getConfig().set("Hours", hours);
                    sender.sendMessage("§8[§dTimer§8]§6 | §aStunden wurden auf §b" + hours
                            + " §agesetzt!");
                } else if (args[0].equalsIgnoreCase("setminutes")) {
                    int minutes = Integer.parseInt(args[1]);
                    timer.minutes = minutes;
                    timer.date2.setMinutes(minutes);
                    timer.date.setMinutes(minutes);
                    getConfig().set("Minutes", minutes);
                    sender.sendMessage("§8[§dTimer§8]§6 | §aMinuten wurden auf §b" + minutes
                            + " §agesetzt!");
                } else if (args[0].equalsIgnoreCase("setdays")) {
                    int minutes = Integer.parseInt(args[1]);
                    timer.days = minutes;
                    timer.date2.setDate(minutes + 1);
                    timer.date.setDate(minutes + 1);
                    sender.sendMessage("§8[§dTimer§8]§6 | §aTage wurden auf §b" + minutes
                            + " §agesetzt!");
                }
            } else {
                sender.sendMessage("§cBitte benutze §6/Timer <reload>§4§l!");
            }
        }
        return super.onCommand(sender, command, label, args);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
}
