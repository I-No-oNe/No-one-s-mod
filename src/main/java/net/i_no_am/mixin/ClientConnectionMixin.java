package net.i_no_am.mixin;

import net.i_no_am.modules.ToggledHack;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.i_no_am.client.ClientEntrypoint.packetQueue;
import static net.i_no_am.client.ClientEntrypoint.toggledHacks;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    // Log outgoing packets (for debugging)
    @SuppressWarnings({"EmptyMethod", "CommentedOutCode"})
    @Inject(method = "sendImmediately", at = @At("HEAD"))
    void onSendImmediately(Packet<?> packet, PacketCallbacks callbacks, boolean flush, CallbackInfo ci) {
//        LOGGER.info(String.format("---> [%04d] %s", ZonedDateTime.now().toInstant().toEpochMilli() % 1000, packet.getClass().getSimpleName()));

//        if (packet instanceof PlayerMoveC2SPacket _packet) {
//            LOGGER.info(String.format("  PlayerMoveC2SPacket(%.2f, %.2f, %.2f)", _packet.getX(0), _packet.getY(0), _packet.getZ(0)));
//        }
    }

    // Log incoming packets (for debugging)
    @Inject(method = "handlePacket", at = @At("HEAD"))
    private static <T extends PacketListener> void onHandlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
//        LOGGER.info(String.format("<--- [%04d] %s", ZonedDateTime.now().toInstant().toEpochMilli() % 1000, packet.getClass().getSimpleName()));

        // Save statistics response

//        if (packet instanceof PlayerPositionLookS2CPacket _packet) {
//            LOGGER.info(String.format("  PlayerPositionLookS2CPacket(%.2f, %.2f, %.2f)", _packet.getX(), _packet.getY(), _packet.getZ()));
//        }
    }

    // Clear packet queue
    @Inject(method = "disconnect", at = @At("HEAD"))
    void onDisconnect(Text reason, CallbackInfo ci) {
        packetQueue.clear();
        for (ToggledHack hack : toggledHacks) {
            if (hack.enabled != hack.defaultEnabled) {
                hack.toggle();
            }
        }
    }
}
