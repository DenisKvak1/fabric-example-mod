package net.fabricmc.rainbowMessage;

import me.lortseam.completeconfig.gui.ConfigScreenBuilder;
import me.lortseam.completeconfig.gui.cloth.ClothConfigScreenBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.fabricmc.rainbowMessage.config.RainBowConfig;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class RainbowMessage implements ModInitializer {
    private static RainbowMessage instance;
    public MinecraftClient minecraftClient;
    private RainBowConfig configurate;
    private final String modId = "rainbowmessage";


    public RainbowMessage() {
    }


    @Override
    public void onInitialize() {
        minecraftClient = MinecraftClient.getInstance();
        ConfigScreenBuilder.setMain(this.modId, new ClothConfigScreenBuilder());
        configurate = new RainBowConfig(this.modId);
        configurate.load();

        this.initChatHandler();
    }


    public void initChatHandler(){
        ClientSendMessageEvents.MODIFY_CHAT.register(this::onMessage);
    }

    public String onMessage(String message) {
        if(!(configurate.isActive) && Objects.equals(configurate.rainbowPattern, "")) return  message;

        StringBuilder builder = new StringBuilder(message);
        String prefix = configurate.rainbowPattern;

        if (message.startsWith("!")) {
            builder.insert(1, prefix);
        } else {
            builder.insert(0, prefix);
        }
        return builder.toString();
    }

    public String getModID() {
        return this.modId;
    }

    public static RainbowMessage getInstance() {
        if (instance == null) {
            instance = new RainbowMessage();
        }
        return instance;
    }
}
