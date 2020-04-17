package de.blockstube.userplugin.countdown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;

/**
 * @author schlingeldrops
 * This is a countdown class, it manage a countdown.
 */
public class Countdown {

    private final JavaPlugin javaPlugin;
    private final int countLength;
    private final Runnable finishRunnable;
    private final int[] alerts;
    private final String messagePattern;

    private BukkitTask bukkitTask;

    private int count = 0;

    public Countdown(JavaPlugin javaPlugin, int countLength, Runnable finishRunnable, int[] alerts, String messagePattern) {
        this.javaPlugin = javaPlugin;
        this.countLength = countLength;
        this.finishRunnable = finishRunnable;
        this.alerts = alerts;
        this.messagePattern = messagePattern;
    }

    public Countdown(JavaPlugin javaPlugin, int countLength, Runnable finishRunnable, int... alerts) {
        this(javaPlugin, countLength, finishRunnable, alerts, "§7Der Countdown ended in §c§l%TIME% §7Sekunden");
    }

    public Countdown(JavaPlugin javaPlugin, int countLength, Runnable finishRunnable) {
        this(javaPlugin, countLength, finishRunnable, new int[0]);
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    /**
     * @return The length of the countdown.
     */
    public int getCountLength() {
        return countLength;
    }

    /**
     * @return The Runnable that are run at the end.
     */
    public Runnable getFinishRunnable() {
        return finishRunnable;
    }

    /**
     * @return The alert times then will throw a message.
     */
    public int[] getAlerts() {
        return alerts.clone();
    }

    /**
     * @return The message pattern.
     */
    public String getMessagePattern() {
        return messagePattern;
    }

    /**
     * @return The tick at the moment.
     */
    public int getCount() {
        return count;
    }

    private void tick() {
        if (Arrays.stream(alerts).anyMatch(value -> value == count))
            Bukkit.broadcastMessage(messagePattern.replace("%TIME%", String.valueOf(count)));

        if (count == 0) {
            finishRunnable.run();
            stop();
            return;
        }

        count--;
    }

    /**
     * With this method you can start the countdown.
     */
    public void start() {
        count = countLength;
        bukkitTask = Bukkit.getScheduler().runTaskTimer(javaPlugin, this::tick, 0L, 20L);
    }

    /**
     * With this method you can stop the countdown.
     */
    public void stop() {
        Bukkit.getScheduler().cancelTask(bukkitTask.getTaskId());
    }
}
