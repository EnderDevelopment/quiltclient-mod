package com.penoxibharadwaj.quiltclientmod.music;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvent;
import java.util.ArrayList;
import java.util.List;

public
class MusicPlayer {
    private static final List<SoundEvent> playlist = new ArrayList<>();
    private static int currentIndex = 0;
    private static boolean isPlaying = false;

    public static void addToPlaylist(SoundEvent soundEvent) {
        playlist.add(soundEvent);
    }

    public static void play() {
        if (!playlist.isEmpty()) {
            MinecraftClient.getInstance().getSoundManager().play(playlist.get(currentIndex));
            isPlaying = true;
        }
    }

    public static void pause() {
        MinecraftClient.getInstance().getSoundManager().stopAll();
        isPlaying = false;
    }

    public static void next() {
        if (!playlist.isEmpty()) {
            currentIndex = (currentIndex + 1) % playlist.size();
            play();
        }
    }

    public static void previous() {
        if (!playlist.isEmpty()) {
            currentIndex = (currentIndex - 1 + playlist.size()) % playlist.size();
            play();
        }
    }

    public static boolean isPlaying() {
        return isPlaying;
    }
}
