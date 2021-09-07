package com.gbep.botapi.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainMenuService {
    public SendMessage getMainMenuMessage(final long chatId) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getMainMenuKeyboard();
        final SendMessage mainMenuMessage = new SendMessage();
        mainMenuMessage.enableMarkdown(true);
        mainMenuMessage.setChatId(String.valueOf(chatId));

        if (replyKeyboardMarkup != null) {
            mainMenuMessage.setReplyMarkup(replyKeyboardMarkup);
        }

        return mainMenuMessage;
    }

    private ReplyKeyboardMarkup getMainMenuKeyboard() {

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        row1.add(new KeyboardButton("Выбрать игру"));
        row1.add(new KeyboardButton("Загрузить игру"));
        row2.add(new KeyboardButton("Посмотреть статистику"));
        row2.add(new KeyboardButton("Помощь"));
        keyboard.add(row1);
        keyboard.add(row2);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

}
