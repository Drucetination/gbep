package com.gbep.botapi.bot.handlers;

import com.gbep.botapi.bot.State;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MainHandler {
    SendMessage handle(Message message);
    State getHandlerName();
}
