package com.stelch.games2.Formatting;

import java.util.Iterator;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Text {
  public static enum MessageType {
    TEXT_CHAT,
    TITLE,
    DRAGON_BAR,
    ACTION_BAR;

    private MessageType() {}
  }

  public static enum NotifyType {
    JOIN,
    LEAVE,
    KICK,
    GAME,
    ANNOUNCE,
    PERMISSION,
    ADMIN;

    private NotifyType() {}
  }

  public static boolean sendAll(String msg, MessageType type) {
    Player p;
    for (Iterator localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext(); sendMessage(p, msg, type)) {
      p = (Player) localIterator.next();
    }
    return true;
  }

  public static boolean sendMessage(Player p, String msg, NotifyType type) {
    return sendMessage(p, "&9" + type + "> &7" + msg, MessageType.TEXT_CHAT);
  }

  public static boolean sendMessage(Player p, String msg, MessageType type) {
    msg = ChatColor.translateAlternateColorCodes('&', msg);
    switch (type) {
      case TEXT_CHAT:
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg + "&r"));
        return true;
      case TITLE:
        return true;
      case DRAGON_BAR:
        return true;
      case ACTION_BAR:
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msg + "\"}"), (byte) 2));
        return true;
    }
    return false;
  }

  public static String format(String str) {
    return ChatColor.translateAlternateColorCodes('&', "&r" + str);
  }
}


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Formatting\Text.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */