package com.gbep.botapi.bot.handlers;

import com.gbep.botapi.bot.State;
import com.gbep.botapi.bot.service.MainMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@AllArgsConstructor
public class MainMenuHandler implements MainHandler{

    private MainMenuService mainMenuService;
    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId());
    }

    @Override
    public State getHandlerName() {
        return State.SHOW_MAIN_MENU;
    }
}
