package com.gbep.botapi.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ChooseGameService {
    public String play(Long chatID){
        String text = "Введите номер игры, в которую хотите сыграть:\n" +
                "1. Определения\n" +
                "2. Викторина\n" +
                "3. Пропущенные слова\n";
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatID.toString());
        message.setText(text);
        return "Game win!";


    }
}
