package net.i_no_am.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Notifier {

    private boolean hasSentMessage = false;

    public Notifier() {
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            if (!hasSentMessage && client.getNetworkHandler() != null && client.getNetworkHandler().getConnection() != null) {
                sendMessageOnPlayerJoin(client);
                hasSentMessage = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        sendCommandSection(client);
                        timer.cancel();
                    }
                }, 40000);
            }
        });
    }

    private void sendMessageOnPlayerJoin(MinecraftClient client) {
        assert client.player != null;
        client.player.sendMessage(Text.of("                               §6Modules:§r                                    "));
        client.player.sendMessage(Text.of("§aInventory Tweaks:§r Press Mouse and shift to move items, Press Shift+q+ctrl to throw everything, Press number 0 to throw everything."));
        client.player.sendMessage(Text.of("§aElytra Switch:§r Automatically switch to elytra when jumping and switch to chestplate when on ground."));
        client.player.sendMessage(Text.of("§aAuto Attack:§r Automatically attack entities and surround the target with green circle."));
        client.player.sendMessage(Text.of("§aElytra Fly:§r Elytra fly, won't work if No fall And Fly are enabled."));
        client.player.sendMessage(Text.of("§aFreeCamera:§r Make your camera move out of your body."));
        client.player.sendMessage(Text.of("§aBoat Fly:§r Make boats fly when you ride them."));
        client.player.sendMessage(Text.of("§aRender Tweaks:§r make the game look better."));
        client.player.sendMessage(Text.of("§aNo Armor Render:§r disable armor rendering."));
        client.player.sendMessage(Text.of("§aScaffold:§r Place blocks under you."));
        client.player.sendMessage(Text.of("§aNoFall:§r Disable fall damage."));
        client.player.sendMessage(Text.of("§aFly Hack:§r Make you fly."));
        client.player.sendMessage(Text.of("§aFastMine:§r Instant mine."));
        client.player.sendMessage(Text.of(" "));
    }

    private void sendCommandSection(MinecraftClient client) {
        assert client.player != null;
        client.player.sendMessage(Text.of(" "));
        client.player.sendMessage(Text.of("                                 §6Commands:§r                                    "));
        client.player.sendMessage(Text.of("§b/vanillaclient §r-> make the client back to fabric,help hiding from servers that you are using fabric."));
        client.player.sendMessage(Text.of("§b/gui disable §r-> turn off the gui | §b/gui enable §r-> turn on the gui."));
        client.player.sendMessage(Text.of("§b/vclip §8{number} §r-> teleport the player §8{numbers}§r blocks."));
        client.player.sendMessage(Text.of("§b/dclip §8{number} §r-> teleport the player §8{numbers}§r blocks."));
        client.player.sendMessage(Text.of("§b/hclip §8{number} §r-> teleport the player §8{numbers}§r blocks."));
        client.player.sendMessage(Text.of("§b/camera_overlay §r-> Turn on a green overlay when FreeCamera is on."));
        client.player.sendMessage(Text.of("§b/chestplate disable§r -> turn off the chestplate rendering."));
        client.player.sendMessage(Text.of("§b/head disable§r -> turn off the helmet rendering."));
        client.player.sendMessage(Text.of("§b/legs disable§r -> turn off the legs rendering."));
        client.player.sendMessage(Text.of("§b/feet disable§r -> turn off the boots rendering."));
        client.player.sendMessage(Text.of("§b/elytra-speed§r -> set the elytra speed fly hack."));
        client.player.sendMessage(Text.of("§b/fly-speed§r -> set the fly hack speed."));
        client.player.sendMessage(Text.of(" "));
    }
}
